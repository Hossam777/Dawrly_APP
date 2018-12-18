package com.example.khaled.dawarly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity_Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
    public void login(View view) {
        Intent calc = new Intent(getApplicationContext(), Activity_Login.class);
        startActivity(calc);
    }
}
