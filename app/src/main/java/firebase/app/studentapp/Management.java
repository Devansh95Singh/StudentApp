package firebase.app.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Management extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
    }
    public void studentDetail(View view)
    {
        Intent I=new Intent(this,ManagementStudent.class);
        startActivity(I);
    }
}
