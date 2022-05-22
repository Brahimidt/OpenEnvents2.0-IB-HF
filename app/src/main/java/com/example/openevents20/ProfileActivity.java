package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://puigmal.salle.url.edu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    retrofitInterface service = retrofit.create(retrofitInterface.class);
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView email = findViewById(R.id.thaEmail);
        TextView firstName = findViewById(R.id.thaFirstName);
        TextView lastName = findViewById(R.id.thaLastName);
        ImageView image = findViewById(R.id.thaImage);


        sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        String StoredValue = sharedPreferences.getString("token", "");
        String StoredValue2 = sharedPreferences.getString("email", "");

        service.Profile(("Bearer "+StoredValue), ("search?s=")+StoredValue2).enqueue(new Callback<List<ProfileRequest>>() {
            @Override
            public void onResponse(Call<List<ProfileRequest>> call, Response<List<ProfileRequest>> response) {

                if (response.isSuccessful()){
                    List<ProfileRequest> myList = response.body();
                    email.setText(myList.get(0).email);
                    firstName.setText(myList.get(0).name);
                    lastName.setText(myList.get(0).last_name);
                    Picasso.get().load(myList.get(0).image).into(image);
                }
                else{
                    Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<ProfileRequest>> call, Throwable t) {
            }
        });
    }
}