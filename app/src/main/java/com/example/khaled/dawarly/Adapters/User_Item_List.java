package com.example.khaled.dawarly.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaled.dawarly.Activity_Report_User;
import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;
import com.example.khaled.dawarly.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class User_Item_List extends BaseAdapter {


    ArrayList<Item>items;
    LayoutInflater l;
    Activity activity;
    public User_Item_List(Activity x, ArrayList<Item>i)
    {
        this.activity = x;
        items = i;
        l = (LayoutInflater)x.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Picasso.get().load(items.get(i).img).into(image); to download image using url
        View v = l.inflate(R.layout.custompostcard,null);

        ImageView cpiimage = v.findViewById(R.id.cpiimage);
        TextView cpiname = v.findViewById(R.id.cpiname);
        TextView cpidesc = v.findViewById(R.id.cpidesc);
        TextView quiz_sheet = v.findViewById(R.id.quiz_sheet);

        Picasso.get().load(items.get(position).getPictureurl()).into(cpiimage);
        cpiname.setText(items.get(position).getName());
        cpidesc.setText(items.get(position).getDescription());
        String tmp = "Quiz : <br>";
        String tmp2[][] = items.get(position).getQuiz().getQestions();
        for(int i=0;i<5;i++)
        {
            tmp += tmp2[i][0] + " : ";
            tmp += tmp2[i][1] + "  , ";
            tmp += tmp2[i][2] + "  , ";
            tmp += tmp2[i][3] + "<br>" ;
        }
        quiz_sheet.setText(Html.fromHtml(tmp));

        return  v;
    }
}
