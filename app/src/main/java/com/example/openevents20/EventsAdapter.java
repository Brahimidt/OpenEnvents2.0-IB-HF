package com.example.openevents20;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    Context context;
    Integer[] id;
    String names[], imageUrl[], location[],description[];
    Date dates[],startDate[],endDate[];

    public EventsAdapter(Context ct, Integer losid[], String losnames[], String losLocations[], Date losdates[],String losimagesUrl[],Date losendDate[],Date losstartDate[], String losDescription[]){
        context = ct;
        id = losid;
        names = losnames;
        imageUrl = losimagesUrl;
        location= losLocations;
        dates = losdates;
        startDate = losstartDate;
        endDate = losendDate;
        description = losDescription;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_event_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {

        holder.thaName.setText(names[position]);
        holder.thaDate.setText(dates[position].toString());
        holder.thaLocation.setText(location[position]);
        Picasso.get().load(imageUrl[position]).into(holder.thaImage);
        int i = position;
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailEvent.class);
                intent.putExtra("name", names[i]);
                intent.putExtra("location", location[i]);
                intent.putExtra("imageUrl", imageUrl[i]);
                intent.putExtra("startDate", startDate[i].toString());
                intent.putExtra("endDate", endDate[i].toString());
                intent.putExtra("description", description[i]);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView thaName,thaDate,thaLocation;
        ImageView thaImage;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            thaName = itemView.findViewById(R.id.thaName);
            thaDate = itemView.findViewById(R.id.thaDate);
            thaLocation = itemView.findViewById(R.id.thaLocation);
            thaImage = itemView.findViewById(R.id.thaImage);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
