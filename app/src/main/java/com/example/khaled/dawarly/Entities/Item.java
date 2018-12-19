package com.example.khaled.dawarly.Entities;

import android.graphics.Bitmap;

public class Item {
    private String Category;
    private Bitmap Picture;
    private String Name;
    private String Description;
    void setItem(String name,String category,String description,Bitmap picture){
        Name = name;
        Category = category;
        Description = description;
        Picture = picture;
    }
    String getName(){return Name;}
    String getCategory(){return Category;}
    String getDescription(){return Description;}
    Bitmap getPicture(){return Picture;}
}
