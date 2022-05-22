package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersListActivity extends AppCompatActivity {

    String name[], image[],email[], last_name[];
    List<String> names= new ArrayList<String>();
    List<String> last_names= new ArrayList<String>();
    List<String> emails = new ArrayList<String>();
    List<String> imagesUrl= new ArrayList<String>();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://puigmal.salle.url.edu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RecyclerView recyclerView;
    retrofitInterface service = retrofit.create(retrofitInterface.class);

    SharedPreferences sharedPreferences;
    Context ThisContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        String StoredValue = sharedPreferences.getString("token", "");
        recyclerView = findViewById(R.id.recyclerView);

        service.Users(("Bearer "+StoredValue)).enqueue(new Callback<List<UserRequest>>() {
            @Override
            public void onResponse(Call<List<UserRequest>> call, Response<List<UserRequest>> response) {

                if (response.isSuccessful()){

                    List<UserRequest> myList = response.body();

                    for (int i = 0; i < myList.size(); i++) {
                        UserRequest obj = myList.get(i);
                        names.add(obj.name);
                        imagesUrl.add(obj.image);
                        emails.add(obj.email);
                        last_names.add(obj.last_name);
                    }

                    String[] losnames = new String[names.size()];
                    String[] losimagesUrl = new String[names.size()];
                    String[] loslast_names = new String[names.size()];
                    String[] losemails = new String[names.size()];


                    losnames = names.toArray(losnames);
                    losimagesUrl = imagesUrl.toArray(losimagesUrl);
                    loslast_names = last_names.toArray(loslast_names);
                    losemails = emails.toArray(losemails);


                    UsersAdapter myAdapter = new UsersAdapter(ThisContext,losnames,losimagesUrl,losemails,loslast_names);
                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ThisContext));
                }
                else{
                    Toast.makeText(UsersListActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<UserRequest>> call, Throwable t) {
            }
        });

    }
}