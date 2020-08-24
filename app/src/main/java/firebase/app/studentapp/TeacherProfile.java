package firebase.app.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TeacherProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
    }
    public void assignment(View view)
    {
        Intent I=new Intent(this,TeacherAssignment.class);
        startActivity(I);
    }
    public void showAssignment(View view)
    {
        Intent I=new Intent(this,ShowAssignment.class);
        startActivity(I);
    }
}
