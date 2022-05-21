package com.example.openevents20;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    Context context;
    int id[];
    String names[], imageUrl[], location[];
    Date dates[];

    public EventsAdapter(Context ct, int losid[], String losnames[], String losLocations[], Date losdates[],String losimagesUrl[]){

        id = losid;
        names = losnames;
        imageUrl = losimagesUrl;
        location= losLocations;
        dates = losdates;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
