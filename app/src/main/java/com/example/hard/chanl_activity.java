package com.example.hard;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class chanl_activity extends AppCompatActivity {

    String login, log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chanl);
    }

    public void onretclick(View v){
        finish();
    }

    public void save(View v){
        login = getIntent().getStringExtra("login");

        EditText et = (EditText)findViewById(R.id.editText);

        point:try{
            log = et.getText().toString();
            if(log==null||log==""||log.length()<1){
                Toast.makeText(this, "Поле не заполнено", Toast.LENGTH_LONG).show();
                break point;
            }

            DBHelper_auth dbHelper_a = new DBHelper_auth(this);
            SQLiteDatabase db = dbHelper_a.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Cursor cursor = db.query(DBHelper_auth.TABLE_CONTACTS, null, null, null, null, null, null);

            int logIndex = cursor.getColumnIndex(DBHelper_auth.KEY_LOGIN);

            if(cursor.moveToFirst()){
                do{
                    if(log.equals(cursor.getString(logIndex))){
                        TextView tw = (TextView)findViewById(R.id.login_equ);
                        tw.setTextColor(Color.RED);
                        tw.setText("Такой логинуже существует");
                        break point;
                    }
                }while(cursor.moveToNext());
                if(cursor.moveToFirst()){
                    do{
                        if(login.equals(cursor.getString(logIndex))){
                            contentValues.put(DBHelper_auth.KEY_LOGIN, log);
                            int a = db.update(DBHelper_auth.TABLE_CONTACTS, contentValues, DBHelper_auth.KEY_LOGIN+"= ?", new String[] {login});
                            Log.d("Log_d", cursor.getString(logIndex));
                        }
                    }while(cursor.moveToNext());
                }
            }

            contentValues.clear();
            cursor.close();

            DBHelper_days dbHelper = new DBHelper_days(this);
            db = dbHelper.getWritableDatabase();
            contentValues = new ContentValues();
            cursor = db.query(DBHelper_days.TABLE_DAYS2, null, null, null, null, null, null);

            logIndex = cursor.getColumnIndex(DBHelper_days.KEY_LOGIN);

            contentValues.put(DBHelper_days.KEY_LOGIN, log);

            if(cursor.moveToFirst()){
                do{
                    if(login.equals(cursor.getString(logIndex))){
                        int a = db.update(DBHelper_days.TABLE_DAYS2, contentValues, DBHelper_days.KEY_LOGIN+"= ?", new String[] {login});
                        Log.d("Log_d", cursor.getString(logIndex));
                    }
                }while(cursor.moveToNext());
            }
            finish();
        }catch(Exception e){
            Toast.makeText(this, "Поле не заполнено", Toast.LENGTH_LONG).show();
        }
    }
}