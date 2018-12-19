package com.example.khaled.dawarly.Entities;

public class Quiz {
    String []Qestions;
    void setQestions(String []qestions){Qestions=qestions;}

    public Quiz(String []qestions){
        Qestions=qestions;
    }
    public String[] getQestions() {
        return Qestions;
    }
    public String getQuestions_toString(){
        String return_String = "";
        for(int i=0;i<Qestions.length;i++)
            return_String += Qestions[i]+";";
        return  return_String;
    }
}
