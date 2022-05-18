package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://puigmal.salle.url.edu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    retrofitInterface service = retrofit.create(retrofitInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.SignIn);
        EditText email   = (EditText)findViewById(R.id.email);
        TextView signup = (TextView)findViewById(R.id.SignUp);
        EditText password   = (EditText)findViewById(R.id.password);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Login.this, Register.class);
                Login.this.startActivity(myIntent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(email.getText().toString().equals("Your email") && password.getText().toString().equals("Your password")){
                    Toast.makeText(Login.this , "Please put an email and a password!",
                            Toast.LENGTH_SHORT).show();
                }else if(email.getText().toString().equals("Your email")){
                    Toast.makeText(Login.this , "Please put an email!",
                            Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().equals("Your password")) {
                    Toast.makeText(Login.this , "Please put a password!",
                            Toast.LENGTH_SHORT).show();
                } else {

                    LoginRequest obj = new LoginRequest(email.getText().toString(),password.getText().toString());

                    service.Login(obj).enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            if (response.isSuccessful()){
                                //Toast.makeText(Login.this, response.body().accessToken, Toast.LENGTH_SHORT).show();
                                Toast.makeText(Login.this, "Connection Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);// New activity
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {
                        }
                    });

                }
            }
        });

    }
}