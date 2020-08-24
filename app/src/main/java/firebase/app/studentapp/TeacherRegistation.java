package firebase.app.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherRegistation extends AppCompatActivity {
    EditText[]ets=new EditText[3];
    int [] its={R.id.et_username,R.id.et_email,R.id.et_password};
    String []value=new String[3];
    int i;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registation);
        for(i=0;i<ets.length;i++)
        {
            ets[i]=findViewById(its[i]);
        }
        databaseReference= FirebaseDatabase.getInstance().getReference();

    }

    public void signUp(View view)
    {
        for(i=0;i<ets.length;i++)
        {
            if(ets[i].getText().toString().isEmpty())
            {
                ets[i].setError("Empty");
                ets[i].requestFocus();
            }
        }
        if(i==ets.length)
        {
            for(i=0;i<ets.length;i++)
            {
                value[i]=ets[i].getText().toString().trim();
            }
            String key=databaseReference.child(new GetDateTime().getYear()).child("teacher").push().getKey();
            databaseReference.child(new GetDateTime().getYear()).child("teacher").child(key).child("username").setValue(value[0]);
            databaseReference.child(new GetDateTime().getYear()).child("teacher").child(key).child("email").setValue(value[1]);
            databaseReference.child(new GetDateTime().getYear()).child("teacher").child(key).child("password").setValue(value[2]);
            databaseReference.child(new GetDateTime().getYear()).child("teacher").child(key).child("userkey").setValue(key);
            Toast.makeText(this, "Reagistration Done", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this, "Registration Not Done", Toast.LENGTH_SHORT).show();
        }
    }
}
