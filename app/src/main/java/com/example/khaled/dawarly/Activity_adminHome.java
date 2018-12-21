package com.example.khaled.dawarly;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.khaled.dawarly.Adapters.Admin_Reports_Adapter;
import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;

import java.util.ArrayList;

public class Activity_adminHome extends AppCompatActivity {

    ListView listView;
    FireBaseClass fireBaseClass;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        listView = findViewById(R.id.adminlist);
        fireBaseClass = new FireBaseClass(this);
        activity = this;
        fireBaseClass.LoadReports(new FireBaseClass.FirebaseCallback() {
            @Override
            public void upload_done(boolean bool) {

            }

            @Override
            public void geturi(String uri) {

            }

            @Override
            public void getitems(ArrayList<Item> items) {

            }

            @Override
            public void getreports(ArrayList<Report> reports) {
                listView.setAdapter(new Admin_Reports_Adapter(activity,reports));
            }

            @Override
            public void getuser(User user) {

            }
        });

    }
}
