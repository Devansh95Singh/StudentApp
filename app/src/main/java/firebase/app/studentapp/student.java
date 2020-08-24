package firebase.app.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
    }
    public void registration(View view)
    {
        Intent I=new Intent(this,studentregistration.class);
        startActivity(I);
        finish();

    }
    public void login(View view)
    {
        Intent I=new Intent(this,StudentLogin.class);
        startActivity(I);

    }
}
