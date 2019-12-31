package com.example.hard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class delu_activity extends AppCompatActivity {

    String login;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delu);
    }

    public void onDelClick(View v){
        login = getIntent().getStringExtra("login");

        DBHelper_auth dbHelper_a = new DBHelper_auth(this);
        SQLiteDatabase db = dbHelper_a.getWritableDatabase();
        Cursor cursor = db.query(DBHelper_auth.TABLE_CONTACTS, null, null, null, null, null, null);

        int logIndex = cursor.getColumnIndex(DBHelper_auth.KEY_LOGIN);
        int passIndex = cursor.getColumnIndex(DBHelper_auth.KEY_PASSWORD);
        et = et = (EditText)findViewById(R.id.passworddel);

        point: try{
            if(cursor.moveToFirst()){
                do{
                   if(login.equals(cursor.getString(logIndex))){
                       if(et.getText().toString().equals(cursor.getString(passIndex))){
                           int a = db.delete(DBHelper_auth.TABLE_CONTACTS, DBHelper_days.KEY_LOGIN + "= ?", new String[]{login});
                       }else{
                           Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show();
                           break point;
                       }
                   }
                }while(cursor.moveToNext());

                cursor.close();

                DBHelper_days dbHelper = new DBHelper_days(this);
                db = dbHelper.getWritableDatabase();
                cursor = db.query(DBHelper_days.TABLE_DAYS2, null, null, null, null, null, null);

                logIndex = cursor.getColumnIndex(DBHelper_days.KEY_LOGIN);

                if(cursor.moveToFirst()){
                    do{
                        if(cursor.getString(logIndex).equals(login)){
                            int a = db.delete(DBHelper_days.TABLE_DAYS2, DBHelper_days.KEY_LOGIN + "= ?", new String[]{login});
                        }
                    }while(cursor.moveToNext());
                }

                Intent i = new Intent(delu_activity.this, Sign_in_activity.class);
                startActivity(i);

            }
        }catch(Exception e){

        }
    }

    public void retclick(View v){
        finish();
    }

}
