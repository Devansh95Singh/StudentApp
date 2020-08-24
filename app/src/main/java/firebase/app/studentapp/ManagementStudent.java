package firebase.app.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManagementStudent extends AppCompatActivity {
    RecyclerView mngStudentRecycleView;
    DatabaseReference databaseReference;
    ArrayList<String> mName=new ArrayList<String>();
    ArrayList<String> mEmail=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_student);
        mngStudentRecycleView=(RecyclerView)findViewById(R.id.mngStudentRecycleView);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        mngStudentRecycleView.setLayoutManager(layoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        Query query=databaseReference.child(new GetDateTime().getYear()).child("student").orderByChild("usertype").equalTo("studentPending");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot myValue : dataSnapshot.getChildren()) {

                        mName.add(myValue.child("name").getValue(String.class));
                        mEmail.add(myValue.child("email").getValue(String.class));
                        StudentAdapter adapter = new StudentAdapter(ManagementStudent.this, mName, mEmail);
                        mngStudentRecycleView.setAdapter(adapter);


                    }
                }catch(Exception e)
                {
                    Toast.makeText(ManagementStudent.this, "details not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
