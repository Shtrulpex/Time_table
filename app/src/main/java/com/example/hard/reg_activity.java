package com.example.hard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class reg_activity extends AppCompatActivity{

    DBHelper_auth dbHelper;

    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
    }

    public void onRegButtonClick(View v) {


        TextView pass_uncor = (TextView) findViewById(R.id.pass_uncorr);
        TextView log_uncor = (TextView) findViewById(R.id.log_uncor);
        String log = ((EditText) findViewById(R.id.reg_log)).getText().toString();
        String password1 = ((EditText) findViewById(R.id.reg_pass1)).getText().toString();
        String password2 = ((EditText) findViewById(R.id.reg_pass2)).getText().toString();


        dbHelper = new DBHelper_auth(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.query(DBHelper_auth.TABLE_CONTACTS, null, null, null, null, null, null);

        point:
        if (!password1.equals(password2) || password1.length() < 4) {
            pass_uncor.setText("Пароль не совпадает или слишком короткий");
        } else {
            if (cursor.moveToFirst()) {
                int loginIndex = cursor.getColumnIndex(DBHelper_auth.KEY_LOGIN);
                do {
                    if (cursor.getString(loginIndex).equals(log)) {
                        log_uncor.setText("Такой логин уже существует");
                        break point;
                    }
                } while (cursor.moveToNext());
            }
            contentValues.put(DBHelper_auth.KEY_LOGIN, log);
            contentValues.put(DBHelper_auth.KEY_PASSWORD, password1);
            db.insert(DBHelper_auth.TABLE_CONTACTS, null, contentValues);
            cursor.close();
            finish();
        }
    }

    public void onRetButtonClick(View v){
        finish();
    }
}
