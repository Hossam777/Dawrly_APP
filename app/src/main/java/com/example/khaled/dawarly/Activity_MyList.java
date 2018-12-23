package com.example.khaled.dawarly;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khaled.dawarly.Adapters.MyList_Adapter;
import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;

import java.util.ArrayList;

public class Activity_MyList extends AppCompatActivity {

    ListView mylistview;
    User user;
    FireBaseClass fireBaseClass;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__my_list);

        activity = this;
        mylistview = findViewById(R.id.mylistview);
        user = User.current_user;
        fireBaseClass = new FireBaseClass(this);
        if(fireBaseClass.CheckInternetConnection())
        {
            if(user.getList().length() > 0){
            String arrlist[] = user.getList().split(";");
                ArrayList<String> arrayList_IDs = new ArrayList<>();
                final ArrayList<Boolean> arrayList_status = new ArrayList<>();
                for (int i = 0; i < arrlist.length; i++) {
                    arrayList_IDs.add(arrlist[i].substring(0, arrlist[i].length() - 1));
                    if (arrlist[i].charAt(arrlist[i].length() - 1) == '1')
                        arrayList_status.add(true);
                    else
                        arrayList_status.add(false);
                }
                fireBaseClass.LoadItemswithIDS(arrayList_IDs, new FireBaseClass.FirebaseCallback() {
                    @Override
                    public void upload_done(boolean bool) {

                    }

                    @Override
                    public void geturi(String uri) {

                    }

                    @Override
                    public void getitems(ArrayList<Item> items) {
                        mylistview.setAdapter(new MyList_Adapter(activity, items, arrayList_status));
                    }

                    @Override
                    public void getreports(ArrayList<Report> reports) {

                    }

                    @Override
                    public void getuser(User user) {

                    }
                });
            }else{
                Toast.makeText(getApplicationContext(),"You don't have any items in your list yet!",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
        }
    }
}
