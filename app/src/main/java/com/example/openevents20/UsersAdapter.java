package com.example.openevents20;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder>{

    String names[],images[],emails[],last_names[];
    Context context;


    public UsersAdapter(Context ct, String losnames[],String losimages[],String losemails[], String loslast_names[]){
    context = ct;
    names = losnames;
    images = losimages;
    emails = losemails;
    last_names = loslast_names;

    };

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_user_row,parent,false);


        return new UsersAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            holder.mytextView.setText(names[position]);
            if (images[position].trim().length() != 0){
                Picasso.get().load(images[position]).into(holder.myImage);
            }
            int i = position;
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("name",names[i]);
                intent.putExtra("last_name",last_names[i]);
                intent.putExtra("image",images[i]);
                intent.putExtra("email",emails[i]);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mytextView;
        ImageView myImage;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mytextView = itemView.findViewById(R.id.Name);
            myImage = itemView.findViewById(R.id.Image);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
