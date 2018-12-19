package com.example.khaled.dawarly.Entities;

import java.util.Map;

public class User {
    private String Name;
    private String Password;
    private String Email;
    private String Mobile;
    private String Gender;
    private int Age;
    private float Rate;
    private boolean Ban_Status;

    public User(){

    }
    void setUser(String name,String password,String email,String mobile,String gender,int age,float rate,boolean ban_Status){
        Name = name;
        Password = password;
        Email = email;
        Mobile = mobile;
        Gender = gender;
        Age = age;
        Rate = rate;
        Ban_Status = ban_Status;
    }
    public String getName(){return Name;}
    public String getPassword(){return Password;}
    public String getEmail(){return Email;}
    public String getMobile(){return Mobile;}
    public String getGender(){return Gender;}
    public int getAge(){return Age;}
    public float getRate(){return Rate;}
    public Boolean getBan_Status(){return Ban_Status;}

    public void setName(String name){Name = name;}
    public void setPassword(String password){Password = password;}
    public void setEmail(String email){Email = email;}
    public void setMobile(String mobile){Mobile = mobile;}
    public void setGender(String gender){Gender = gender;}
    public void setAge(int age){Age = age;}
    public void setRate(float rate){Rate = rate;}
    public void setBan_Status(boolean ban_Status){Ban_Status = ban_Status;}
}
