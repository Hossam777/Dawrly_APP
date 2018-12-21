package com.example.khaled.dawarly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Activity_Login extends AppCompatActivity {

    private FireBaseClass fireBaseClass;
    private EditText mail,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fireBaseClass = new FireBaseClass(this);
        mAuth = FirebaseAuth.getInstance();
        mail = (EditText)findViewById(R.id.emailogin);
        password = (EditText)findViewById(R.id.passwordlogin);

        //Picasso.get().load(items.get(i).img).into(image); to download image using url

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!fireBaseClass.CheckInternetConnection())
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            fireBaseClass.LoadUser(currentUser.getEmail(), new FireBaseClass.FirebaseCallback() {
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

                }

                @Override
                public void getuser(User user) {
                    if(user != null)
                    {
                        user.setCurrent_user(user);
                        startActivity(new Intent(getApplicationContext(),Activity_HomePage.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Email is wrong",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void signup(View view) {
        Intent calc = new Intent(getApplicationContext(),Activity_Signup.class);
        startActivity(calc);
    }
    public void login(View view) {
        if(!fireBaseClass.CheckInternetConnection())
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
            return;
        }
        if(mail.getText().equals("") || password.getText().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please write mail and password",Toast.LENGTH_SHORT).show();
            return;
        }
        fireBaseClass.LoadUser(mail.getText().toString(), new FireBaseClass.FirebaseCallback() {
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

            }

            @Override
            public void getuser(User user) {
                if(user != null)
                {
                    if(user.getPassword().equals(password.getText().toString())) {
                        if(user.getBan_Status())
                        {
                            Toast.makeText(getApplicationContext(),"You have been banned for your bad actions",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mAuth.signInWithEmailAndPassword(user.getEmail(),user.getPassword());
                        user.setCurrent_user(user);
                        startActivity(new Intent(getApplicationContext(), Activity_HomePage.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Password is wrong",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Email is wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void show_items(View view) {
        Intent calc = new Intent(getApplicationContext(),Activity_Show_Items.class);
        startActivity(calc);
    }
}
