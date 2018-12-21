package com.example.khaled.dawarly.Entities;

public class Quiz {
    String []Qestions;
    void setQestions(String []qestions){Qestions=qestions;}

    public Quiz(String []qestions){
        Qestions=qestions;
    }
    public String[][] getQestions() {
        String [][]Qestions2d = new String[5][4];
        for(int i=0;i<Qestions.length;i++)
        {
            String []tmp1 = Qestions[i].split(":");
            Qestions2d[i][0] = tmp1[0];
            String []tmp2 = tmp1[1].split(",");
            Qestions2d[i][1] = tmp2[0];
            Qestions2d[i][2] = tmp2[1];
            Qestions2d[i][3] = tmp2[2];
        }
        return Qestions2d;
    }
    public String getQuestions_toString(){
        String return_String = "";
        for(int i=0;i<Qestions.length;i++)
            return_String += Qestions[i]+";";
        return  return_String;
    }
}
