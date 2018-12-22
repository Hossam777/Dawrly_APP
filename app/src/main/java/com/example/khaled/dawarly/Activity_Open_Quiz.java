package com.example.khaled.dawarly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;

import java.util.ArrayList;
import java.util.Random;

public class Activity_Open_Quiz extends AppCompatActivity {

    FireBaseClass fireBaseClass;
    Item currItem;

    TextView q1,q2,q3,q4,q5;
    RadioButton RBarray[][];
    String Questions[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__open__quiz);

        fireBaseClass = new FireBaseClass(this);
        currItem = Item.showing_item;
        Questions = currItem.getQuiz().getQestions();
        q1 = findViewById(R.id.rq1);
        q2 = findViewById(R.id.rq2);
        q3 = findViewById(R.id.rq3);
        RBarray = new RadioButton[][]{
               new RadioButton[]{findViewById(R.id.rq1a1),findViewById(R.id.rq1a2),findViewById(R.id.rq1a3)},
               new RadioButton[]{findViewById(R.id.rq2a1),findViewById(R.id.rq2a2),findViewById(R.id.rq2a3)},
               new RadioButton[]{findViewById(R.id.rq3a1),findViewById(R.id.rq3a2),findViewById(R.id.rq3a3)},
               new RadioButton[]{findViewById(R.id.rq4a1),findViewById(R.id.rq4a2),findViewById(R.id.rq4a3)},
               new RadioButton[]{findViewById(R.id.rq5a1),findViewById(R.id.rq5a2),findViewById(R.id.rq5a3)}
        };
        Random r = new Random();
        for(int i=0;i<5;i++)
        {
            q1.setText(Questions[i][0]);
            int start = r.nextInt()%3;
            RBarray[i][start].setText(Questions[i][1]);
            RBarray[i][(start+1)%3].setText(Questions[i][2]);
            RBarray[i][(start+2)%3].setText(Questions[i][3]);

        }

    }

    public void Post_quiz_answer(View view) {
        if( !RBarray[0][0].isChecked() || !RBarray[0][1].isChecked() || !RBarray[0][2].isChecked())
        {
            Toast.makeText(getApplicationContext(),"Please choose answer for question 1",Toast.LENGTH_SHORT).show();
            return;
        }
        if( !RBarray[1][0].isChecked() || !RBarray[1][1].isChecked() || !RBarray[1][2].isChecked())
        {
            Toast.makeText(getApplicationContext(),"Please choose answer for question 2",Toast.LENGTH_SHORT).show();
            return;
        }
        if( !RBarray[2][0].isChecked() || !RBarray[2][1].isChecked() || !RBarray[2][2].isChecked())
        {
            Toast.makeText(getApplicationContext(),"Please choose answer for question 3",Toast.LENGTH_SHORT).show();
            return;
        }
        if( !RBarray[3][0].isChecked() || !RBarray[3][1].isChecked() || !RBarray[3][2].isChecked())
        {
            Toast.makeText(getApplicationContext(),"Please choose answer for question 4",Toast.LENGTH_SHORT).show();
            return;
        }
        if( !RBarray[4][0].isChecked() || !RBarray[4][1].isChecked() || !RBarray[4][2].isChecked())
        {
            Toast.makeText(getApplicationContext(),"Please choose answer for question 5",Toast.LENGTH_SHORT).show();
            return;
        }
        String FinalAnswers[] = new String[5];
        for(int i = 0;i<3;i++)
        {
            if(RBarray[i][0].isChecked())
                FinalAnswers[i] = RBarray[i][0].getText().toString();
            if(RBarray[i][1].isChecked())
                FinalAnswers[i] = RBarray[i][1].getText().toString();
            if(RBarray[i][1].isChecked())
                FinalAnswers[i] = RBarray[i][2].getText().toString();
        }
        int degree = 0;
        for (int i=0;i<5;i++)
        {
            if(FinalAnswers[i].equals(Questions[i][1]))
                degree += 20;
        }
        if(!fireBaseClass.CheckInternetConnection())
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(degree < 80)
        {
            User.current_user.addtoList(currItem.getItemid() + "0;");
            fireBaseClass.UpdateUser(User.current_user, new FireBaseClass.FirebaseCallback() {
                @Override
                public void upload_done(boolean bool) {
                    if ((bool)){
                        Toast.makeText(getApplicationContext(), "Item saved to your list", Toast.LENGTH_SHORT).show();
                        finish();
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
        }else{
            User.current_user.addtoList(currItem.getItemid() + "1;");
            fireBaseClass.UpdateUser(User.current_user, new FireBaseClass.FirebaseCallback() {
                @Override
                public void upload_done(boolean bool) {
                    if ((bool)){
                        Toast.makeText(getApplicationContext(),"Item saved to your list",Toast.LENGTH_SHORT).show();
                        finish();
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
}
