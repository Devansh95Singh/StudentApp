package firebase.app.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ShowAssignment extends AppCompatActivity {
    DatabaseReference databaseReference;
    StorageReference storageReference;
    ArrayList<String> mSubject=new ArrayList<>();
    ArrayList<String> mtext=new ArrayList<>();
    ArrayList<String> mfile=new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_assignment);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference();
        recyclerView=(RecyclerView)findViewById(R.id.teachShowAssignment);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        databaseReference.child(new GetDateTime().getYear()).child("assignment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot myValue: dataSnapshot.getChildren()){
                    mSubject.add(myValue.child("subject").getValue(String.class));
                    mtext.add(myValue.child("text").getValue(String.class));
                    mfile.add(myValue.child("file").getValue(String.class));
                    AssignmentAdapter adapter=new AssignmentAdapter(ShowAssignment.this,mSubject,mtext,mfile);
                    recyclerView.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
