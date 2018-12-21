package com.example.khaled.dawarly.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaled.dawarly.Activity_Items_of_User;
import com.example.khaled.dawarly.Activity_Report_User;
import com.example.khaled.dawarly.Controller.FireBaseClass;
import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;
import com.example.khaled.dawarly.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Admin_Reports_Adapter extends BaseAdapter {


    ArrayList<Report>reports;
    LayoutInflater l;
    Activity activity;
    FireBaseClass fireBaseClass;
    public Admin_Reports_Adapter(Activity x, ArrayList<Report>r)
    {
        this.activity = x;
        reports = r;
        l = (LayoutInflater)x.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        fireBaseClass = new FireBaseClass(x);
    }
    @Override
    public int getCount() {
        return reports.size();
    }

    @Override
    public Object getItem(int position) {
        return reports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Picasso.get().load(items.get(i).img).into(image); to download image using url
        View v = l.inflate(R.layout.reportcard,null);

        TextView rmail = v.findViewById(R.id.rmail);
        TextView rdesc = v.findViewById(R.id.rdesc);
        Button rdeleteuser = v.findViewById(R.id.rduser);
        Button rdeletereport = v.findViewById(R.id.rdreport);

        rmail.setText(reports.get(position).getEmail());
        rdesc.setText(reports.get(position).getDescription());
        rmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fireBaseClass.CheckInternetConnection())
                    return;
                fireBaseClass.LoadUser(reports.get(position).getEmail(), new FireBaseClass.FirebaseCallback() {
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
                        User.current_user = user;
                        activity.startActivity(new Intent(activity,Activity_Items_of_User.class));
                    }
                });
            }
        });
        rdeleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Are you sure to delete")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                fireBaseClass.LoadUser(reports.get(position).getEmail(), new FireBaseClass.FirebaseCallback() {
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
                                        user.setBan_Status(true);
                                        fireBaseClass.UpdateUser(user, new FireBaseClass.FirebaseCallback() {
                                            @Override
                                            public void upload_done(boolean bool) {
                                                if(bool){
                                                    Toast.makeText(activity,"Done deleting...",Toast.LENGTH_SHORT).show();
                                                    activity.recreate();
                                                }
                                                else
                                                    Toast.makeText(activity,"Something went wrong",Toast.LENGTH_SHORT).show();
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

                                            }
                                        });
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                builder.create();
            }
        });
        rdeletereport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Are you sure to delete")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                fireBaseClass.DeleteReport(reports.get(position).getID(), new FireBaseClass.FirebaseCallback() {
                                    @Override
                                    public void upload_done(boolean bool) {
                                        if(bool){
                                            Toast.makeText(activity,"Done deleting...",Toast.LENGTH_SHORT).show();
                                            activity.recreate();
                                        }
                                        else
                                            Toast.makeText(activity,"Something went wrong",Toast.LENGTH_SHORT).show();
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

                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                builder.create();
            }
        });

        return  v;
    }
}
