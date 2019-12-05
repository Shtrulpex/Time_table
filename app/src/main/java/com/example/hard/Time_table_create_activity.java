package com.example.hard;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Time_table_create_activity extends AppCompatActivity {

    DBHelper_days dbHelper;
    int weekday= 1;
    int repeat = 0;
    String login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table_create_activity);
        TextView weekd = (TextView)findViewById(R.id.textView6);
        switch(weekday){
            case 1:
                weekd.setText("Понедельник");
                break;
            case 2:
                weekd.setText("Вторник");
                break;
            case 3:
                weekd.setText("Среда");
                break;
            case 4:
                weekd.setText("Четверг");
                break;
            case 5:
                weekd.setText("Пятница");
                break;
            case 6:
                weekd.setText("Суббота");
                break;
            case 7:
                weekd.setText("Воскресенье");
                break;
        }
    }

    public void w1(View v){
        TextView weekd = (TextView)findViewById(R.id.textView6);
        weekday = 1;
        weekd.setText("Понедельник");
    }

    public void w2(View v){
        TextView weekd = (TextView)findViewById(R.id.textView6);
        weekday = 2;
        weekd.setText("Вторник");
    }

    public void w3(View v){
        TextView weekd = (TextView)findViewById(R.id.textView6);
        weekday = 3;
        weekd.setText("Среда");
    }

    public void w4(View v){
        TextView weekd = (TextView)findViewById(R.id.textView6);
        weekday = 4;
        weekd.setText("Четверг");
    }

    public void w5(View v){
        TextView weekd = (TextView)findViewById(R.id.textView6);
        weekday = 5;
        weekd.setText("Пятница");
    }

    public void w6(View v){
        TextView weekd = (TextView)findViewById(R.id.textView6);
        weekday = 6;
        weekd.setText("Суббота");
    }

    public void w7(View v){
        TextView weekd = (TextView)findViewById(R.id.textView6);
        weekday = 7;
        weekd.setText("Воскресенье");
    }

    public void b1(View v){
        finish();
    }

    public void onSubButtonClick(View v){

        CheckBox check = (CheckBox)findViewById(R.id.switch2);


        int startH = Integer.parseInt(((EditText)findViewById(R.id.hour_start)).getText().toString());
        int startM = Integer.parseInt(((EditText)findViewById(R.id.min_start)).getText().toString());
        int endH = Integer.parseInt(((EditText)findViewById(R.id.hour_end)).getText().toString());
        int endM = Integer.parseInt(((EditText)findViewById(R.id.min_end)).getText().toString());
        String event = ((EditText)findViewById(R.id.event)).getText().toString();

        if(check.isChecked()){
            repeat = 1;
        }else repeat = 0;

        dbHelper = new DBHelper_days(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.query(DBHelper_days.TABLE_CONTACTS, null, null, null, null, null, null);

        DBHelper_logDb dbHelper_logDb = new DBHelper_logDb(this);
        SQLiteDatabase db1 = dbHelper_logDb.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        Cursor cursor1 = db.query(DBHelper_days.TABLE_CONTACTS, null, null, null, null, null, null);

        if(cursor1.moveToLast()){
           int logIndex = cursor1.getColumnIndex(DBHelper_logDb.KEY_LOGIN);
           login = cursor1.getString(logIndex);
        }

        contentValues.put(DBHelper_days.KEY_LOGIN, login);
        contentValues.put(DBHelper_days.KEY_WEEKDAY, weekday);
        contentValues.put(DBHelper_days.KEY_HOURSTART, startH);
        contentValues.put(DBHelper_days.KEY_MINSTART, startM);
        contentValues.put(DBHelper_days.KEY_HOURSTOP, endH);
        contentValues.put(DBHelper_days.KEY_MINSTOP, endM);
        contentValues.put(DBHelper_days.KEY_EVENT, event);
        contentValues.put(DBHelper_days.KEY_REPEAT, repeat);

        db.insert(DBHelper_days.TABLE_CONTACTS, null, contentValues);

        cursor.close();
        finish();
    }

}
