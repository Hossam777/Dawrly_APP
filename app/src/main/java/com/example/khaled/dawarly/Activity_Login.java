package com.example.khaled.dawarly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
