package com.example.khaled.dawarly;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaled.dawarly.Adapters.User_Item_List;
import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;

import java.util.ArrayList;

public class Activity_Items_of_User extends AppCompatActivity {

    TextView mail;
    ListView listView;
    User user;
    FireBaseClass fireBaseClass;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_of__user);

        user = User.current_user;
        mail = findViewById(R.id.user_mail);
        listView = findViewById(R.id.user_item_list);
        mail.setText(user.getEmail());
        fireBaseClass = new FireBaseClass(this);
        activity = this;

        if(!fireBaseClass.CheckInternetConnection())
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
            return;
        }

        fireBaseClass.LoadItemsofUser(user.getEmail(), new FireBaseClass.FirebaseCallback() {
            @Override
            public void upload_done(boolean bool) {

            }

            @Override
            public void geturi(String uri) {

            }

            @Override
            public void getitems(ArrayList<Item> items) {
                listView.setAdapter(new User_Item_List(activity,items));
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
