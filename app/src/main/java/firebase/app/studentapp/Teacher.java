package firebase.app.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Teacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
    }
    public void register(View view)
    {
        Intent I=new Intent(this,TeacherRegistation.class);
        startActivity(I);
        finish();
    }
    public void login(View view)
    {
        Intent I=new Intent(this,TeacherLogin.class);
        startActivity(I);
    }
}
