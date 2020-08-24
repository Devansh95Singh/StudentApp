package firebase.app.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TeacherLogin extends AppCompatActivity {
    EditText mail,pass;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        mail=(EditText)findViewById(R.id.mail);
        pass=(EditText)findViewById(R.id.pass);
        databaseReference=FirebaseDatabase.getInstance().getReference();

    }

    public void TeacherLoginMeth(View view) {
        String emailValue=mail.getText().toString().trim();
        final String password=pass.getText().toString().trim();
        Query query=databaseReference.child(new GetDateTime().getYear())
                .child("teacher").orderByChild("email").equalTo(emailValue);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.getChildren().iterator().hasNext()) {
                        for (DataSnapshot myValue : dataSnapshot.getChildren()) {
                            String dbPassword = myValue.child("password").getValue(String.class);
                            String dbKey = myValue.child("userkey").getValue(String.class);
                            if (password.equals(dbPassword)) {
                                startActivity(new Intent(TeacherLogin.this, TeacherProfile.class).putExtra("userkey", dbKey));
                            } else {
                                Toast.makeText(TeacherLogin.this, "Password not match", Toast.LENGTH_SHORT).show();
                            }

                        }
                    } else {
                        Toast.makeText(TeacherLogin.this, "Email not found", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(TeacherLogin.this,""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
