package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onRegisterButtonClick(View v){
        Intent i = new Intent(MainActivity.this, Register_activity.class);
        startActivity(i);
    }
    public void OnEnterButtonClick(View v){
        String login = ((EditText) findViewById(R.id.log)).getText().toString();
        String password = ((EditText)findViewById(R.id.pass)).getText().toString();
    }
}
