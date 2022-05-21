package com.example.openevents20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView recyclerView;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://puigmal.salle.url.edu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    retrofitInterface service = retrofit.create(retrofitInterface.class);

    SharedPreferences sharedPreferences;

    List<Integer> ids= new ArrayList<Integer>();;
    List<String> names= new ArrayList<String>();;
    List<String> locations= new ArrayList<String>();;
    List<Date> dates= new ArrayList<Date>();;
    List<String> imagesUrl= new ArrayList<String>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        String StoredValue = sharedPreferences.getString("token", "");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        recyclerView = findViewById(R.id.myRecyclerView);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        Context ThisContext = this;

        service.Events(("Bearer "+StoredValue)).enqueue(new Callback<List<EventsRequest>>() {
            @Override
            public void onResponse(Call<List<EventsRequest>> call, Response<List<EventsRequest>> response) {

                if (response.isSuccessful()){

                   List<EventsRequest> myList = response.body();

                    for (int i = 0; i < myList.size(); i++) {
                        EventsRequest obj = myList.get(i);
                        ids.add(obj.id);
                        names.add(obj.name);
                        locations.add(obj.location);
                        dates.add(obj.date);
                        imagesUrl.add(obj.image);
                    }

                    Integer[] losid = new Integer[ids.size()];
                    String[] losnames = new String[ids.size()];
                    String[] losLocations = new String[ids.size()];
                    Date[] losdates = new Date[ids.size()];
                    String[] losimagesUrl = new String[ids.size()];

                    losid = ids.toArray(losid);
                    losnames = names.toArray(losnames);
                    losLocations = locations.toArray(losLocations);
                    losdates = dates.toArray(losdates);
                    losimagesUrl = imagesUrl.toArray(losimagesUrl);


                    EventsAdapter myAdapter = new EventsAdapter(ThisContext,losid,losnames,losLocations,losdates,losimagesUrl);
                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ThisContext));
                }
                else{
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<EventsRequest>> call, Throwable t) {
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.events:
                break;
            case R.id.logout:
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.clear();
                myEdit.commit();
                Intent intent = new Intent(MainActivity.this, Login.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.users:
                Intent intent2 = new Intent(MainActivity.this, UsersListActivity.class);// New activity
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;
            case R.id.profile:
                Intent intent3 = new Intent(MainActivity.this, ProfileActivity.class);// New activity
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}