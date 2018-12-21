package com.example.khaled.dawarly;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.User;

public class Activity_Post_Item extends AppCompatActivity {

    Spinner catSpinner;
    EditText ename,edesc;
    ImageView selected_image;
    Uri item_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__post__item);

        item_image = null;
        catSpinner = (Spinner)findViewById(R.id.item_Category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapter);
        ename = (EditText)findViewById(R.id.item_name);
        edesc = (EditText)findViewById(R.id.item_desc);
        selected_image = (ImageView)findViewById(R.id.selectediv);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            item_image = data.getData();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            selected_image.setImageBitmap(imageBitmap);
        }
    }

    public void advance_toquiz(View view) {
        if(ename.getText().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please fill the name of item!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(edesc.getText().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please fill the description of item!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(selected_image == null)
        {
            Toast.makeText(getApplicationContext(),"Please take a picture of item!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(catSpinner.getSelectedItemId() == -1)
        {
            Toast.makeText(getApplicationContext(),"Please select the category of item!",Toast.LENGTH_SHORT).show();
            return;
        }
        Item item = new Item();
        item.setCategory(catSpinner.getSelectedItem().toString());
        item.setName(ename.getText().toString());
        item.setDescription(edesc.getText().toString());
        item.setPicture(item_image);
        item.setEmail(User.current_user.getEmail());
        Item.posting_item = item;
        startActivity(new Intent(getApplicationContext(),Activity_Post_Quiz.class));
    }

    public void capture_image(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }
}
