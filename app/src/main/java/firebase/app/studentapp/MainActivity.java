package firebase.app.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void student(View view)
    {
        Intent I=new Intent(this,student.class);
        startActivity(I);

    }
    public void teacher(View view)
    {
        Intent I=new Intent(this,Teacher.class);
        startActivity(I);

    }
    public void management(View view)
    {
        Intent  I =new Intent(this,ManagementLogin.class);
        startActivity(I);
    }

}
