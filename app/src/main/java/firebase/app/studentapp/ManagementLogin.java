package firebase.app.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ManagementLogin extends AppCompatActivity {
    EditText mail,pass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_login);
        auth=FirebaseAuth.getInstance();
        mail=(EditText)findViewById(R.id.mail);
        pass=(EditText)findViewById(R.id.pass);
    }
    public void get(View view)
    {
        if(mail.getText().toString().isEmpty())
        {
            mail.setError("Empty");
            mail.requestFocus();
        }
        else if(pass.getText().toString().isEmpty())

        {
            pass.setError("Empty");
            pass.requestFocus();
        }
        else
        {
            String email,password;
            email=mail.getText().toString().trim();
            password=pass.getText().toString().trim();
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Intent I=new Intent(ManagementLogin.this,Management.class);
                        startActivity(I);

                    }

                }
            });
        }
    }
}
