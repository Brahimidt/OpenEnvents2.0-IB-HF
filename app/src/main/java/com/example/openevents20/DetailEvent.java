package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class DetailEvent extends AppCompatActivity {


    ImageView image;
    TextView name,location,description,startDate,endDate;

    String myImage, myName, myLocation, myDescription, myEndDate, myStartDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        image = findViewById(R.id.planPicture);
        name = findViewById(R.id.title);
        location = findViewById(R.id.Location);
        description = findViewById(R.id.Description);
        endDate = findViewById(R.id.EndDate);
        startDate = findViewById(R.id.StartDate);


        myImage = getIntent().getStringExtra("imageUrl");
        myName = getIntent().getStringExtra("name");
        myLocation = getIntent().getStringExtra("location");
        myDescription = getIntent().getStringExtra("description");
        myEndDate = getIntent().getStringExtra("endDate");
        myStartDate = getIntent().getStringExtra("startDate");

        name.setText(myName);
        location.setText(myLocation);
        Picasso.get().load(myImage).into(image);
        startDate.setText(myStartDate);
        description.setText(myDescription);
        endDate.setText(myEndDate);

    }


}