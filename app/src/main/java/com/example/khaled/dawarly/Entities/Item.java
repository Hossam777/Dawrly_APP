package com.example.khaled.dawarly.Entities;

import android.net.Uri;

public class Item {
    private String Itemid;
    private String Category;
    private Uri Picture;
    private String  Pictureurl;
    private String Name;
    private String Description;
    private String Email;
    private Quiz quiz;
    public static Item posting_item;
    public static Item showing_item;
    public Item(){
        posting_item = null;
        showing_item = null;
    }
    public void setItem(String name, String category, String description, Uri picture){
        Name = name;
        Category = category;
        Description = description;
        Picture = picture;
    }
    public void setCategory(String category){Category = category;}
    public void setItemid(String itemid){Itemid = itemid;}
    public void setName(String name){Name = name;}
    public void setEmail(String e){Email = e;}
    public void setQuiz(String squiz){quiz = new Quiz(squiz.split(";")); }
    public void setPicture(Uri picture){Picture = picture;}
    public void setPictureurl(String picture){Pictureurl = picture;}
    public void setDescription(String description){Description = description;}
    public String getName(){return Name;}
    public String getEmail(){return Email;}
    public String getCategory(){return Category;}
    public String getDescription(){return Description;}
    public String getItemid(){return Itemid;}
    public Uri getPicture(){return Picture;}
    public String  getPictureurl(){return Pictureurl;}
    public Quiz getQuiz(){return quiz;}

}
