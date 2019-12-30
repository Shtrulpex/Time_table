package com.example.hard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hard.R;

public class settings_activity extends AppCompatActivity {

    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void onStart() {
        super.onStart();
        login = getIntent().getStringExtra("login");
    }

    public void chanl(View v){
        Intent i = new Intent(settings_activity.this, chanl_activity.class);
        i.putExtra("login", getIntent().getStringExtra("login"));
        startActivity(i);
    }

    public void chanp(View v){
        //Intent i = new Intent(settings_activity.this, chanp_activity.class);
        //startActivity(i);
    }

    public void delu(View v){
        //Intent i = new Intent(settings_activity.this, delu_activity.class);
        //startActivity(i);
    }

}
