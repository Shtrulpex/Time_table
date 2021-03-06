package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Sign_in_activity extends AppCompatActivity {

    DBHelper_auth dbHelper;
    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);
        EditText log = (EditText) findViewById(R.id.log);
        EditText pass = (EditText) findViewById(R.id.pass);
        TextView uncorrect = (TextView) findViewById(R.id.uncorrect);
        log.setText("");
        pass.setText("");
        uncorrect.setText("");
    }


    public void onRegisterButtonClick(View v) {
        Intent i = new Intent(Sign_in_activity.this, reg_activity.class);
        startActivity(i);
    }

    public void onEnterButtonClick(View v) {


        TextView uncorrect = (TextView) findViewById(R.id.uncorrect);
        EditText log = (EditText) findViewById(R.id.log);
        EditText pass = (EditText) findViewById(R.id.pass);
        login = log.getText().toString();
        String password = pass.getText().toString();

        dbHelper = new DBHelper_auth(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper_auth.TABLE_CONTACTS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {

            int loginIndex = cursor.getColumnIndex(DBHelper_auth.KEY_LOGIN);
            int passswordIndex = cursor.getColumnIndex(DBHelper_auth.KEY_PASSWORD);
            do {

                if (cursor.getString(loginIndex).equals(login) && cursor.getString(passswordIndex).equals(password)) {
                        setContentView(R.layout.time_table_activity);
                        Intent i = new Intent(Sign_in_activity.this, Time_table_activity.class);
                        Log.d("Log_d", "Enter");
                        i.putExtra("login", login);
                        startActivity(i);
                }
            } while (cursor.moveToNext()) ;
            log.setText("");
            pass.setText("");
            uncorrect.setText("Неверный логин или пароль");
            cursor.close();

        }
    }

}

