package com.example.khaled.dawarly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Activity_Post_Quiz extends AppCompatActivity {

    EditText q1,q2,q3,q4,q5,a1q1,a2q1,a3q1,a1q2,a2q2,a3q2,a1q3,a2q3,a3q3,a1q4,a2q4,a3q4,a1q5,a2q5,a3q5;
    FireBaseClass fireBaseClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__post__quiz);

        fireBaseClass = new FireBaseClass(this);

        TextView mytextview = (TextView)findViewById(R.id.textview1);
        String sourceString = mytextview.getText().toString();
        mytextview.setText(Html.fromHtml(sourceString));

        q1 = (EditText)findViewById(R.id.q1);
        q2 = (EditText)findViewById(R.id.q2);
        q3 = (EditText)findViewById(R.id.q3);
        a1q1 = (EditText)findViewById(R.id.q1a1);
        a2q1 = (EditText)findViewById(R.id.q1a2);
        a3q1 = (EditText)findViewById(R.id.q1a3);
        a1q2 = (EditText)findViewById(R.id.q2a1);
        a2q2 = (EditText)findViewById(R.id.q2a2);
        a3q2 = (EditText)findViewById(R.id.q2a3);
        a1q3 = (EditText)findViewById(R.id.q3a1);
        a2q3 = (EditText)findViewById(R.id.q3a2);
        a3q3 = (EditText)findViewById(R.id.q3a3);
        a1q4 = (EditText)findViewById(R.id.q4a1);
        a2q4 = (EditText)findViewById(R.id.q4a2);
        a3q4 = (EditText)findViewById(R.id.q4a3);
        a1q5 = (EditText)findViewById(R.id.q5a1);
        a2q5 = (EditText)findViewById(R.id.q5a2);
        a3q5 = (EditText)findViewById(R.id.q5a3);


    }

    public void upload_item(View view) {
        String quiz = "";
        if(q1.getText().equals("") || a1q1.getText().equals("") || a2q1.getText().equals("") || a3q1.getText().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please fill question1 and its answers!",Toast.LENGTH_SHORT).show();
            return;
        }
        quiz += q1.getText().toString() + ":" + a1q1.getText().toString() + "," + a2q1.getText().toString() + "," + a3q1.getText().toString() + ";" ;
        if(q2.getText().equals("") || a1q2.getText().equals("") || a2q2.getText().equals("") || a3q2.getText().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please fill question2 and its answers!",Toast.LENGTH_SHORT).show();
            return;
        }
        quiz += q2.getText().toString() + ":" + a1q2.getText().toString() + "," + a2q2.getText().toString() + "," + a3q2.getText().toString() + ";" ;
        if(q3.getText().equals("") || a1q3.getText().equals("") || a2q3.getText().equals("") || a3q3.getText().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please fill question3 and its answers!",Toast.LENGTH_SHORT).show();
            return;
        }
        quiz += q3.getText().toString() + ":" + a1q3.getText().toString() + "," + a2q3.getText().toString() + "," + a3q3.getText().toString() + ";" ;
        if(q4.getText().equals("") || a1q4.getText().equals("") || a2q4.getText().equals("") || a3q4.getText().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please fill question4 and its answers!",Toast.LENGTH_SHORT).show();
            return;
        }
        quiz += q4.getText().toString() + ":" + a1q4.getText().toString() + "," + a2q4.getText().toString() + "," + a3q4.getText().toString() + ";" ;
        if(q5.getText().equals("") || a1q5.getText().equals("") || a2q5.getText().equals("") || a3q5.getText().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please fill question5 and its answers!",Toast.LENGTH_SHORT).show();
            return;
        }
        quiz += q5.getText().toString() + ":" + a1q5.getText().toString() + "," + a2q5.getText().toString() + "," + a3q5.getText().toString() + ";" ;
        Item.posting_item.setQuiz(quiz);
        if(!fireBaseClass.CheckInternetConnection())
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
        fireBaseClass.UploadItem(Item.posting_item, new FireBaseClass.FirebaseCallback() {
            @Override
            public void upload_done(boolean bool) {
                if(bool) {
                    Toast.makeText(getApplicationContext(), "Upload Done", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Activity_HomePage.class));
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
