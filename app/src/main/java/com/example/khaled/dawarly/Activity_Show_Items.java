package com.example.khaled.dawarly;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.khaled.dawarly.Adapters.Show_Items_Adapter;
import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;

import java.util.ArrayList;

public class Activity_Show_Items extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ListView listView;
    FireBaseClass fireBaseClass;
    Activity ThisActivity;
    Spinner showitem_Category;
    ArrayList<Item> Titems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__show__items);

        Titems = null;
        showitem_Category = findViewById(R.id.showitem_Category);
        showitem_Category.setOnItemSelectedListener(this);
        fireBaseClass = new FireBaseClass(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_list2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        showitem_Category.setAdapter(adapter);
        if(!fireBaseClass.CheckInternetConnection())
        {
            Toast.makeText(getApplicationContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
            return;
        }
        fireBaseClass = new FireBaseClass(this);
        ThisActivity = this;
        listView = (ListView)findViewById(R.id.items_listview);
        if(!fireBaseClass.CheckInternetConnection())
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
            return;
        }
        fireBaseClass.LoadItems(new FireBaseClass.FirebaseCallback() {
            @Override
            public void upload_done(boolean bool) {

            }

            @Override
            public void geturi(String uri) {

            }

            @Override
            public void getitems(ArrayList<Item> items) {
                Titems = items;
                listView.setAdapter(new Show_Items_Adapter(ThisActivity,items,null));
            }

            @Override
            public void getreports(ArrayList<Report> reports) {

            }

            @Override
            public void getuser(User user) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (Titems != null) {
            listView.setAdapter(new Show_Items_Adapter(ThisActivity, Titems, showitem_Category.getSelectedItem().toString()));
        } else{
            Toast.makeText(getApplicationContext(), "Please wait untill load finish", Toast.LENGTH_SHORT).show();
            showitem_Category.setSelection(0);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
