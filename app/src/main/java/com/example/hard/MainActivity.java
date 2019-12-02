package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity  extends AppCompatActivity {
    int d = 0;
    boolean n;
    DBHelper_auth dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onRegisterButtonClick(View v) {
        Intent i = new Intent(MainActivity.this, reg_activity.class);
        startActivity(i);
    }

    public void onEnterButtonClick(View v) {


        TextView uncorrect = (TextView) findViewById(R.id.uncorrect);
        EditText log = (EditText) findViewById(R.id.log);
        EditText pass = (EditText) findViewById(R.id.pass);
        String login = ((EditText) findViewById(R.id.log)).getText().toString();
        String password = ((EditText) findViewById(R.id.pass)).getText().toString();

        dbHelper = new DBHelper_auth(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper_auth.TABLE_CONTACTS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int loginIndex = cursor.getColumnIndex(DBHelper_auth.KEY_LOGIN);
            int passswordIndex = cursor.getColumnIndex(DBHelper_auth.KEY_PASSWORD);
            do {
                if (cursor.getString(loginIndex).equals(login) && cursor.getString(passswordIndex).equals(password)) {
                    cursor.close();

                    SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    Cursor cursor1 = db.query(DBHelper_days.TABLE_CONTACTS, null, null, null, null, null, null);

                    if (cursor1.moveToLast()) {
                        int logIndex = cursor1.getColumnIndex(DBHelper_days.KEY_LOGIN);
                        int evIndex = cursor1.getColumnIndex(DBHelper_days.KEY_EVENT);
                        String smt = cursor1.getString(logIndex);
                        contentValues.put(DBHelper_days.KEY_LOGIN, login);

                        if (smt.equals(login)) {
                            n=true;
                            Log.d("m_Log", "n = false");
                        } else {
                            db.insert(DBHelper_days.TABLE_CONTACTS, null, contentValues);
                            n = false;
                            Log.d("m_Log", "n = true");
                        }
                        Intent i = new Intent(MainActivity.this, Time_table_create_activity.class);
                        startActivity(i);
                    } else d++;
                }
            }
                while (cursor.moveToNext()) ;

        }
            if (d == cursor.getCount()) {
                log.setText("");
                pass.setText("");
                uncorrect.setText("Неверный логин или пароль");
                d = 0;
            }
        }
    }

