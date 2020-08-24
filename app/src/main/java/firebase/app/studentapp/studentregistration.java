package firebase.app.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class studentregistration extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText []ets=new EditText[5];
    EditText et_email;
    int[] ids={R.id.name,R.id.fathername,R.id.mobnumber,R.id.address,R.id.password};
    ArrayAdapter AD;
    Spinner spin_class;
    String [] classes={"I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII"};
    RadioButton radio_male,radio_female;
    RadioGroup radio_group;
    String gender="male";
    String [] value=new String[5];
    String email;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentregistration);
        et_email=(EditText)findViewById(R.id.et_email);
        for(i=0;i<5;i++)
        {
            ets[i]=(EditText)findViewById(ids[i]);
        }
        spin_class=(Spinner)findViewById(R.id.spin_class);
        AD=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,classes);
        spin_class.setAdapter(AD);
        radio_male = (RadioButton) findViewById(R.id.radio_male);
        radio_female = (RadioButton) findViewById(R.id.radio_female);
        radio_group=(RadioGroup)findViewById(R.id.radio_group);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {

                        case R.id.radio_male:
                            gender ="male";
                            break;
                        case R.id.radio_female:
                            gender="female";
                            break;

                }
            }
        });
        databaseReference= FirebaseDatabase.getInstance().getReference();

    }
    public void signUp(View view)
    {
        try {
            for (i = 0; i < ets.length; i++) {
                if (ets[i].getText().toString().isEmpty()) {
                    ets[i].setError("Empty");
                    ets[i].requestFocus();
                }
            }
            if (i == ets.length) {
                email = et_email.getText().toString().trim();
                for (i = 0; i < 5; i++) {
                    value[i] = ets[i].getText().toString().trim();
                }
                String key = databaseReference.child(new GetDateTime().getYear()).child("student").push().getKey();
                databaseReference.child(new GetDateTime().getYear()).child("student").child(key).child("name").setValue(value[0]);
                databaseReference.child(new GetDateTime().getYear()).child("student").child(key).child("fathername").setValue(value[1]);
                databaseReference.child(new GetDateTime().getYear()).child("student").child(key).child("address").setValue(value[2]);
                databaseReference.child(new GetDateTime().getYear()).child("student").child(key).child("mobnumber").setValue(value[3]);
                databaseReference.child(new GetDateTime().getYear()).child("student").child(key).child("password").setValue(value[4]);
                databaseReference.child(new GetDateTime().getYear()).child("student").child(key).child("email").setValue(email);
                databaseReference.child(new GetDateTime().getYear()).child("student").child(key).child("class").setValue(spin_class.getSelectedItem().toString());
                databaseReference.child(new GetDateTime().getYear()).child("student").child(key).child("gender").setValue(gender);
                databaseReference.child(new GetDateTime().getYear()).child("student").child(key).child("userkey").setValue(key);
                databaseReference.child(new GetDateTime().getYear()).child("student").child(key).child("usertype").setValue("studentPending");

                Toast.makeText(this, "Registration Done", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(this, "Registration not done", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
