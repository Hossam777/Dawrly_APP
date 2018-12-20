package com.example.khaled.dawarly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;

import java.util.ArrayList;

public class Activity_Login extends AppCompatActivity {

    FireBaseClass fireBaseClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fireBaseClass = new FireBaseClass(this);

        //Picasso.get().load(items.get(i).img).into(image); to download image using url
        
    }
    public void signup(View view) {
        Intent calc = new Intent(getApplicationContext(),Activity_Signup.class);
        startActivity(calc);
    }
    public void home(View view) {
        Intent calc = new Intent(getApplicationContext(),Activity_HomePage.class);
        startActivity(calc);
    }
}
