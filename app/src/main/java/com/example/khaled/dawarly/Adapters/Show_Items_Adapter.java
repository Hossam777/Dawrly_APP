package com.example.khaled.dawarly.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaled.dawarly.Activity_Open_Quiz;
import com.example.khaled.dawarly.Activity_Report_User;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;
import com.example.khaled.dawarly.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Show_Items_Adapter extends BaseAdapter {


    ArrayList<Item>items;
    LayoutInflater l;
    Activity activity;
    public Show_Items_Adapter(Activity x,ArrayList<Item>i,String Category)
    {
        this.activity = x;
        items = i;
        l = (LayoutInflater)x.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(Category != null && !Category.equals("ALL"))
        {
            ArrayList<Item>tmpitems = new ArrayList<>();
            for(int j=0;j<items.size();j++){
                if(items.get(j).getCategory().equals(Category))
                    tmpitems.add(items.get(j));
            }
            items = tmpitems;
        }
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
        View v = l.inflate(R.layout.postcard,null);

        ImageView piimage = v.findViewById(R.id.piimage);
        TextView piname = v.findViewById(R.id.piname);
        TextView pidesc = v.findViewById(R.id.pidesc);
        Button pireport = v.findViewById(R.id.pireport);
        Button piquiz = v.findViewById(R.id.piquiz);

        Picasso.get().load(items.get(position).getPictureurl()).into(piimage);
        piname.setText(items.get(position).getName());
        pidesc.setText(items.get(position).getDescription());
        pireport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User.current_user == null){
                    Toast.makeText(activity,"You must login first",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(User.current_user.getList().length()>1) {
                String arr[] = User.current_user.getList().split(";");
                    for(int i=0;i<arr.length;i++)
                    {
                        if(arr[i].substring(0,arr[i].length()-1).equals(items.get(position).getItemid()))
                        {
                            Toast.makeText(activity,"This item have been saved to your list",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
                if(User.current_user.getEmail().equals(items.get(position).getEmail()))
                {
                    Toast.makeText(activity,"This is your post enta nset wla eh",Toast.LENGTH_SHORT).show();
                    return;
                }
                Report report = new Report();
                report.setPostID(items.get(position).getItemid());
                report.setEmail(items.get(position).getEmail());
                Report.current_report = report;
                activity.startActivity(new Intent(activity,Activity_Report_User.class));
            }
        });
        piquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User.current_user == null){
                    Toast.makeText(activity,"You must login first",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(User.current_user.getList().length()>1) {
                    String arr[] = User.current_user.getList().split(";");
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].substring(0, arr[i].length() - 1).equals(items.get(position).getItemid())) {
                            Toast.makeText(activity, "This item have been saved to your list", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
                if(User.current_user.getEmail().equals(items.get(position).getEmail()))
                {
                    Toast.makeText(activity,"This is your post enta nset wla eh",Toast.LENGTH_SHORT).show();
                    return;
                }
                Item.showing_item = items.get(position);
                activity.startActivity(new Intent(activity, Activity_Open_Quiz.class));
            }
        });

        return  v;
    }
}
