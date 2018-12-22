package com.example.khaled.dawarly.Entities;


public class Report {
    private String Email;
    private String Description;
    private String PostID;
    private String ID;
    public static Report current_report;
    public Report(){
        current_report = null;
    }
    public void setDescription(String description) { Description = description; }
    public void setEmail(String email) {Email = email; }
    public void setID(String id) {ID = id; }
    public void setPostID(String pid) {PostID = pid; }
    public String getEmail() {return Email; }
    public String getDescription() {return Description; }
    public String getID() {return ID; }
    public String getPostID() {return PostID; }
}
