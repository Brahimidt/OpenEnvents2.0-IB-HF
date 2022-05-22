package com.example.openevents20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder>{

    String names[],images[];
    Context context;


    public UsersAdapter(Context ct, String losnames[],String losimages[]){
    context = ct;
    names = losnames;
    images = losimages;

    };

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_user_row,parent,false);


        return new UsersAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.mytextView.setText(names[position]);
            if (images[position].trim().length() != 0){
                Picasso.get().load(images[position]).into(holder.myImage);
            }


    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mytextView;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mytextView = itemView.findViewById(R.id.Name);
            myImage = itemView.findViewById(R.id.Image);
        }
    }
}
