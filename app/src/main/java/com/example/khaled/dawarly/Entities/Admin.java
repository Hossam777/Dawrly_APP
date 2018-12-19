package com.example.khaled.dawarly.Entities;

import java.util.ArrayList;

public class Admin {
    ArrayList<String> Emails;
    ArrayList<String> Passwords;
    Admin(){
        Emails = new ArrayList<>();
        Passwords = new ArrayList<>();

        Emails.add("hossam@gmail.com");
        Passwords.add("hossam");
        Emails.add("hassan@gmail.com");
        Passwords.add("hassan");
        Emails.add("doaa@gmail.com");
        Passwords.add("doaa");
        Emails.add("khaled@gmail.com");
        Passwords.add("khaled");
    }
    Boolean check_if_Admin(String mail,String password){
        if(Emails.contains(mail) && Passwords.get(Emails.indexOf(mail)).equals(password))
            return true;
        return false;
    }
}
