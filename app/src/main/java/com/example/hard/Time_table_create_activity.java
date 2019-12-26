package com.example.hard;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Time_table_create_activity extends AppCompatActivity {

    DBHelper_days1 dbHelper;
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

        login = getIntent().getStringExtra("login");
        try {


            int startH = Integer.parseInt(((EditText) findViewById(R.id.hour_start)).getText().toString());
            int startM = Integer.parseInt(((EditText) findViewById(R.id.min_start)).getText().toString());
            int endH = Integer.parseInt(((EditText) findViewById(R.id.hour_end)).getText().toString());
            int endM = Integer.parseInt(((EditText) findViewById(R.id.min_end)).getText().toString());
            String event = ((EditText) findViewById(R.id.event)).getText().toString();


            dbHelper = new DBHelper_days1(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Cursor cursor = db.query(DBHelper_days1.TABLE_DAYS, null, null, null, null, null, null);

            contentValues.put(DBHelper_days1.KEY_LOGIN, login);
            contentValues.put(DBHelper_days1.KEY_WEEKDAY, weekday);
            contentValues.put(DBHelper_days1.KEY_HOURSTART, startH);
            contentValues.put(DBHelper_days1.KEY_MINSTART, startM);
            contentValues.put(DBHelper_days1.KEY_HOURSTOP, endH);
            contentValues.put(DBHelper_days1.KEY_MINSTOP, endM);
            contentValues.put(DBHelper_days1.KEY_EVENT, event);
            contentValues.put(DBHelper_days1.KEY_REP, repeat);

            db.insert(DBHelper_days1.TABLE_DAYS, null, contentValues);

            cursor.close();
            finish();
        }catch (Exception e){
            Toast.makeText(this, "Вы заполнили не все поля", Toast.LENGTH_LONG).show();
        }

    }

}
