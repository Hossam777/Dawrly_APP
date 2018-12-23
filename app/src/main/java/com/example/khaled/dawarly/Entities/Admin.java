package com.example.khaled.dawarly.Entities;

import java.util.ArrayList;

public class Admin {
    ArrayList<String> Emails;
    ArrayList<String> Passwords;
    public Admin(){
        Emails = new ArrayList<>();
        Passwords = new ArrayList<>();

        Emails.add("Hossam");
        Passwords.add("hossam");
        Emails.add("Hassan");
        Passwords.add("hassan");
        Emails.add("Doaa");
        Passwords.add("doaa");
        Emails.add("Khaled");
        Passwords.add("khaled");
    }
    public Boolean check_if_Admin(String mail,String password){
        if(Emails.contains(mail) && Passwords.get(Emails.indexOf(mail)).equals(password))
            return true;
        return false;
    }
}
