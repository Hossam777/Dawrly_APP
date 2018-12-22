package com.example.khaled.dawarly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;

import java.util.ArrayList;

public class Activity_Report_User extends AppCompatActivity {

    EditText report_desc;
    FireBaseClass fireBaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__report__user);
        report_desc = findViewById(R.id.report_desc);
        fireBaseClass = new FireBaseClass(this);
    }

    public void submit(View view) {
        if(report_desc.getText().length() < 25)
        {
            Toast.makeText(getApplicationContext(),"Please write at least 25 char in description",Toast.LENGTH_SHORT).show();
            return;
        }
        Report.current_report.setDescription(report_desc.getText().toString());
        if(!fireBaseClass.CheckInternetConnection())
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
            return;
        }
        fireBaseClass.UploadReport(Report.current_report, new FireBaseClass.FirebaseCallback() {
            @Override
            public void upload_done(boolean bool) {
                if(bool){
                    Toast.makeText(getApplicationContext(),"report uploaded",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void geturi(String uri) {

            }

            @Override
            public void getitems(ArrayList<Item> items) {

            }

            @Override
            public void getreports(ArrayList<Report> reports) {

            }

            @Override
            public void getuser(User user) {

            }
        });
    }
}
