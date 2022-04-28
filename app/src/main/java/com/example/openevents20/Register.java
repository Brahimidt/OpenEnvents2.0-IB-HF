package com.example.openevents20;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Register extends AppCompatActivity {

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
                if (email.getText().toString().equals("") && pswd.getText().toString().equals("") && repswd.getText().toString().equals("") && firstname.getText().toString().equals("") && lastname.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "Fill all the information", Toast.LENGTH_LONG).show();
                } else if (pswd.getText().toString().equals(repswd.getText().toString())) {
                    try {
                        URL url = new URL("http://puigmal.salle.url.edu/api/v2/users/register");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json; uft-8");
                        con.setRequestProperty("Accept", "application/json");
                        con.setDoOutput(true);
                        String jsonInputString = "{ 'name': '" + email.getText().toString() +
                                "','last_name': '" + lastname.getText().toString() +
                                "','email': '" + email.getText().toString() +
                                "','password': '" + pswd.getText().toString() +
                                "','image': '" + img.getText().toString() + "'}";
                        try (OutputStream os = con.getOutputStream()) {
                            byte[] input = jsonInputString.getBytes("utf-8");
                            os.write(input, 0, input.length);
                        }
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(Register.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}