package com.example.khaled.dawarly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity_HomePage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void show_items(View view) {
        startActivity(new Intent(getApplicationContext(),Activity_Show_Items.class));
    }

    public void post_item(View view) {
        startActivity(new Intent(getApplicationContext(),Activity_Post_Item.class));
    }

    public void my_list(View view) {
        startActivity(new Intent(getApplicationContext(),Activity_MyList.class));
    }
}
