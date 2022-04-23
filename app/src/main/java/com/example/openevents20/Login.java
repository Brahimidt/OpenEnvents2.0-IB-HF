package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Login extends AppCompatActivity {


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
                    try {
                        //https://www.baeldung.com/httpurlconnection-post
                        URL url = new URL("http://puigmal.salle.url.edu/api/v2/users/login");
                        HttpURLConnection con = (HttpURLConnection)url.openConnection();
                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json; utf-8");
                        con.setRequestProperty("Accept", "application/json");
                        con.setDoOutput(true);
                        String jsonInputString = "{ 'name': '"+email.getText().toString()+"','password': '"+password.getText().toString()+"'}";
                        try(OutputStream os = con.getOutputStream()) {
                            byte[] input = jsonInputString.getBytes("utf-8");
                            os.write(input, 0, input.length);
                        }
                        } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

    }
}