package com.example.khaled.dawarly.Entities;


public class Report {
    private String Email;
    private String Description;
    private String ID;
    public Report(){

    }
    public void setDescription(String description) { Description = description; }
    public void setEmail(String email) {Email = email; }
    public void setID(String id) {ID = id; }
    public String getEmail() {return Email; }
    public String getDescription() {return Description; }
    public String getID() {return ID; }
}
