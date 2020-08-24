package firebase.app.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;

public class TeacherAssignment extends AppCompatActivity {
    EditText et_assignmentText, et_assignmentSubject;
    DatabaseReference databaseReference;
    String downloadUrl;
    StorageReference storageReference;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_assignment);
        et_assignmentText = (EditText) findViewById(R.id.et_assignmentText);
        et_assignmentSubject = (EditText) findViewById(R.id.et_assignmentSubject);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void submit(View view) {
        if (et_assignmentText.getText().toString().isEmpty()) {
            et_assignmentText.setError("Empty");
            et_assignmentText.requestFocus();
        } else if (et_assignmentSubject.getText().toString().isEmpty()) {
            et_assignmentSubject.setError("Empty");
            et_assignmentSubject.requestFocus();

        } else {
            String text = et_assignmentText.getText().toString().trim();
            String subject = et_assignmentSubject.getText().toString().trim();
            String key = databaseReference.child(new GetDateTime().getYear()).child("assignment").push().getKey();
            databaseReference.child(new GetDateTime().getYear()).child("assignment").child(key).child("subject").setValue(subject);
            databaseReference.child(new GetDateTime().getYear()).child("assignment").child(key).child("text").setValue(text);
            databaseReference.child(new GetDateTime().getYear()).child("assignment").child(key).child("file").setValue(downloadUrl);
            Toast.makeText(this, "Assignment Uploaded", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    public void uploadAssignmentFile(View view) {
        Intent I = new Intent();
        I.setType("application/pdf");
        I.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(I, "Select PDF file"), 11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 11 && resultCode == RESULT_OK && data != null) {
            final Uri uri = data.getData();
            progressDialog=new ProgressDialog(TeacherAssignment.this);
            progressDialog.setMessage("Uploading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            final StorageReference filePath = storageReference.child("assignment").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {

                        filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        progressDialog.dismiss();
                                        downloadUrl = String.valueOf(uri);
                                        Toast.makeText(TeacherAssignment.this, "" + downloadUrl, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                }
            });

        }
    }
}