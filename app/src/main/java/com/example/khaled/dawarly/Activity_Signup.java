package com.example.khaled.dawarly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;
import com.google.android.gms.common.util.NumberUtils;
import com.google.android.gms.flags.impl.DataUtils;

import java.util.ArrayList;
import java.util.Calendar;

import io.opencensus.internal.StringUtil;

public class Activity_Signup extends AppCompatActivity {

    RadioButton female,male;
    EditText fname,sname,mail,password,repassword,emobile,day,month,year;
    FireBaseClass fireBaseClass ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fireBaseClass = new FireBaseClass(this);

        fname = (EditText)findViewById(R.id.firstname);
        sname = (EditText)findViewById(R.id.secondname);
        mail = (EditText)findViewById(R.id.emailsignu);
        password = (EditText)findViewById(R.id.passwordsignu);
        repassword = (EditText)findViewById(R.id.repasswordsignu);
        emobile = (EditText)findViewById(R.id.phone_number);
        day = (EditText)findViewById(R.id.day);
        month = (EditText)findViewById(R.id.month);
        year = (EditText)findViewById(R.id.year);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);

    }
    public void login(View view) {
        if(!fireBaseClass.CheckInternetConnection())
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
            return;
        }
    String firstname = fname.getText().toString();
    String secondname = sname.getText().toString();
    String email = mail.getText().toString();
    String pass = password.getText().toString();
    String repass = repassword.getText().toString();
    String mobile = emobile.getText().toString();
    String bDay = day.getText().toString();
    String bMonth = month.getText().toString();
    String bYear = year.getText().toString();
    String gender ="";
        if(male.isChecked())
        {
            gender="Male";
        }
        else if (female.isChecked()) {
            gender = "Female";
        }
        if (firstname.equals(" ")
                || firstname.length()<3)
        {
            Toast.makeText(getApplicationContext(),"Invalid first name",Toast.LENGTH_SHORT).show();
            return;
        }
        if (secondname.equals(" ")
                || secondname.length()<3)
        {
            Toast.makeText(getApplicationContext(),"Invalid second name",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!email.contains("@")
                || !email.contains(".")
                || (email.substring(email.length()-5)).indexOf("@")<(email.substring(email.length()-5)).indexOf("."))
        {
            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass != repass || pass.length() < 9)
        {
            Toast.makeText(getApplicationContext(),"Wrong password, must be at least 8 chars",Toast.LENGTH_SHORT).show();
            return;
        }
        if(mobile.length() != 11 || !NumberUtils.isNumeric(mobile))
        {
            Toast.makeText(getApplicationContext(),"wrong mobile number",Toast.LENGTH_SHORT).show();
            return;
        }
        if(bDay.equals(""))
        {
            Toast.makeText(getApplicationContext(),"cannot defined birthday",Toast.LENGTH_SHORT).show();
            return;
        }
        if(bMonth.equals(""))
        {
            Toast.makeText(getApplicationContext(),"cannot defined birthday",Toast.LENGTH_SHORT).show();
            return;
        }
        if(bYear.equals(""))
        {
            Toast.makeText(getApplicationContext(),"cannot defined birthday",Toast.LENGTH_SHORT).show();
            return;
        }
        if (gender.equals(""))
        {
            Toast.makeText(getApplicationContext(),"whats your gender",Toast.LENGTH_SHORT).show();
            return;
        }
        final User user = new User();
        user.setName(firstname + " " + secondname);
        user.setEmail(email);
        user.setPassword(pass);
        user.setList("");
        int age =  Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(bYear);
        user.setAge(age);
        user.setRate((float) 2.5);
        user.setBan_Status(false);
        user.setMobile(mobile);
        fireBaseClass.UploadUser(user, new FireBaseClass.FirebaseCallback() {
            @Override
            public void upload_done(boolean bool) {
                if(bool){
                    Toast.makeText(getApplicationContext(),"Signed up done",Toast.LENGTH_SHORT).show();
                    user.setCurrent_user(user);
                    startActivity(new Intent(getApplicationContext(),Activity_HomePage.class));
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
