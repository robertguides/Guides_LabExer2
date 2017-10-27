package com.example.guides.labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    Button LPreferences,LIStorage,Clear,Back;
    TextView tvDisplay;
    FileInputStream fis;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LPreferences = (Button) findViewById(R.id.BTN4);
        LIStorage = (Button) findViewById(R.id.BTN5);
        Clear = (Button) findViewById(R.id.BTN6);
        Back = (Button) findViewById(R.id.BTN7);
        tvDisplay = (TextView) findViewById(R.id.DISPLAY);
        preferences = getSharedPreferences("pref", Context.MODE_WORLD_READABLE);

    }
    public void displayPreferences(View view){
        String username = preferences.getString("user", "");
        String password = preferences.getString("pass", "");

        tvDisplay.setText("User is " + username + " and password is " + password + " in Shared Preferences");
    }
    public void displayStorage (View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = openFileInput("output.txt");
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }
    public void Clear(View view){
        tvDisplay.setText("");
    }
    public void Back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
