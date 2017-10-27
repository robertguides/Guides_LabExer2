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
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button SPreferences,IStorage,Next;
    FileOutputStream fos;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        SPreferences = (Button) findViewById(R.id.BTN1);
        IStorage = (Button) findViewById(R.id.BTN2);
        Next = (Button) findViewById(R.id.BTN3);
        preferences = getSharedPreferences("pref", Context.MODE_WORLD_READABLE);
    }
    public void Next(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);


    }


    public void savePreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", username.getText().toString());
        editor.putString("pass", password.getText().toString());
        editor.commit();

        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_LONG).show();
    }
    public void saveStorage (View view) {
        String message = "username is " + username.getText().toString() + " and password is " + password.getText().toString()+ " in Internal Storage";

        try{
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_LONG).show();

    }


}
