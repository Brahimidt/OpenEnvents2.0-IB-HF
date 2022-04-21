package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.SignIn);
        EditText email   = (EditText)findViewById(R.id.email);
        EditText password   = (EditText)findViewById(R.id.password);
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

                }
            }
        });

    }
}