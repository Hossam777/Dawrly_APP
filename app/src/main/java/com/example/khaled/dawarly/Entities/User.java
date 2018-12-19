package com.example.khaled.dawarly.Entities;

public class User {
    private String Name;
    private String Password;
    private String Email;
    private String Mobile;
    private String Gender;
    private int Age;
    private float Rate;
    private boolean Ban_Status;

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
    String getName(){return Name;}
    String getPassword(){return Password;}
    String getEmail(){return Email;}
    String getMobile(){return Mobile;}
    String getGender(){return Gender;}
    int getAge(){return Age;}
    float getRate(){return Rate;}
    Boolean getBan_Status(){return Ban_Status;}

}
