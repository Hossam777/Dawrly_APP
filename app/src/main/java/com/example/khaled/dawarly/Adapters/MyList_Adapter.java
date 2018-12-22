package com.example.khaled.dawarly.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
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
import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;
import com.example.khaled.dawarly.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyList_Adapter extends BaseAdapter {


    ArrayList<Item>items;
    ArrayList<Boolean>states;
    LayoutInflater l;
    Activity activity;
    FireBaseClass fireBaseClass;
    public MyList_Adapter(Activity x, ArrayList<Item>i,ArrayList<Boolean>s)
    {
        this.activity = x;
        items = i;
        l = (LayoutInflater)x.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        fireBaseClass = new FireBaseClass(x);
        states = s;
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
        View v = l.inflate(R.layout.mylistcard,null);

        ImageView piimage = v.findViewById(R.id.mlimage);
        TextView piname = v.findViewById(R.id.mlname);
        TextView pidesc = v.findViewById(R.id.mldesc);
        Button pireport = v.findViewById(R.id.mlreport);
        Button mlshowcontacts = v.findViewById(R.id.mlshowcontacts);

        Picasso.get().load(items.get(position).getPicture()).into(piimage);
        piname.setText(items.get(position).getName());
        pidesc.setText(items.get(position).getDescription());
        pireport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Report report = new Report();
                report.setPostID(items.get(position).getItemid());
                report.setEmail(items.get(position).getEmail());
                Report.current_report = report;
                activity.startActivity(new Intent(activity,Activity_Report_User.class));
            }
        });
        mlshowcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (states.get(position)){
                    if(!fireBaseClass.CheckInternetConnection())
                    {
                        Toast.makeText(activity,"No Internet Connection!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    fireBaseClass.LoadUser(items.get(position).getEmail(), new FireBaseClass.FirebaseCallback() {
                        @Override
                        public void upload_done(boolean bool) {

                        }

                        @Override
                        public void geturi(String uri) {

                        }

                        @Override
                        public void getitems(ArrayList<Item> items) {

                        }

                        @Override
                        public void getreports(ArrayList<Report> reports) {

                        }

                        @Override
                        public void getuser(User user) {
                            if (user != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                builder.setMessage("Name : " + user.getName() + "\n" + "Email : " + user.getEmail() + "\n" + "Mobile : " + user.getMobile() + "\n")
                                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        });
                                builder.create();
                            }
                        }
                    });
            }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("This item has been blocked for you because you have submitted less than 80% in the quiz")
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    builder.create();
                }
            }
        });

        return  v;
    }
}
