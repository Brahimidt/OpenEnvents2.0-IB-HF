package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.goebl.david.Webb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://puigmal.salle.url.edu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    retrofitInterface service = retrofit.create(retrofitInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btnCreate = (Button) findViewById(R.id.btnCreate);
        EditText email = (EditText) findViewById(R.id.editEmail);
        EditText firstname = (EditText) findViewById(R.id.editFirstName);
        EditText lastname = (EditText) findViewById(R.id.editLastName);
        EditText pswd = (EditText) findViewById(R.id.editPassword);
        EditText repswd = (EditText) findViewById(R.id.editConfirmPassword);
        EditText img = (EditText) findViewById(R.id.editImg);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstnamestring = firstname.getText().toString();
                String lastnamestring = lastname.getText().toString();
                String emailstring = email.getText().toString();
                String passwordstring = pswd.getText().toString();
                String imagestring = img.getText().toString();
                RegisterRequest obj = new RegisterRequest(firstnamestring,lastnamestring,emailstring,passwordstring,imagestring);

                if (email.getText().toString().equals("") && pswd.getText().toString().equals("") && repswd.getText().toString().equals("") && firstname.getText().toString().equals("") && lastname.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "Fill all the information", Toast.LENGTH_LONG).show();
                } else if (pswd.getText().toString().equals(repswd.getText().toString())) {

                    service.Register(obj).enqueue(new Callback<RegisterRequest>() {

                        @Override
                        public void onResponse(Call<RegisterRequest> call, Response<RegisterRequest> response) {
                            Toast.makeText(Register.this, "User created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, Login.class);// New activity
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                        @Override
                        public void onFailure(Call<RegisterRequest> call, Throwable t) {
                        }
                    });
                }else{
                    Toast.makeText(Register.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}