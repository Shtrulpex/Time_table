package com.example.hard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Time_table_activity extends AppCompatActivity {

    DBHelper_days1 dbHelper_days1;
    int weekd = 1;
    String login, event;
    LinearLayout eventSheet;
    int startH, finishH, startM, finishM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table_activity);



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Log_d", "hi");
        setContentView(R.layout.time_table_activity);

        try {
            eventSheet = (LinearLayout) findViewById(R.id.eventSheet);
            dbHelper_days1 = new DBHelper_days1(this);
            SQLiteDatabase db = dbHelper_days1.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Cursor cursor = db.query(DBHelper_days1.TABLE_DAYS, null, null, null, null, null, null);


            login = getIntent().getStringExtra("login");


            if (cursor.moveToFirst()) {
            Log.d("Log_d",login);
            int logIndex = cursor.getColumnIndex(DBHelper_days1.KEY_LOGIN);
            int dayIndex = cursor.getColumnIndex(DBHelper_days1.KEY_WEEKDAY);
            int startHIndex = cursor.getColumnIndex(DBHelper_days1.KEY_HOURSTART);
            int startMIndex = cursor.getColumnIndex(DBHelper_days1.KEY_MINSTART);
            int finishHIndex = cursor.getColumnIndex(DBHelper_days1.KEY_HOURSTOP);
            int finishMIndex = cursor.getColumnIndex(DBHelper_days1.KEY_MINSTOP);
            int eventIndex = cursor.getColumnIndex(DBHelper_days1.KEY_EVENT);

            Log.d("Log_d", "working");
            do {
                Log.d("Log_d", "here");
                if (login.equals(cursor.getString(logIndex)) && weekd == cursor.getInt(dayIndex)) {


                    startH = cursor.getInt(startHIndex);
                    finishH = cursor.getInt(finishHIndex);
                    startM = cursor.getInt(startMIndex);
                    finishM = cursor.getInt(finishMIndex);
                    event = cursor.getString(eventIndex);

                    LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    TextView tw = new TextView(this);
                    tw.setText(time()+"\n"+event);
                    eventSheet.addView(tw, lParams);

                }
            } while (cursor.moveToNext());
            }
        }catch(Exception e){

        }
    }

    public void mn(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Понедельник");
        weekd = 1;
        create();
    }

    public void tu(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Вторник");
        weekd = 2;
        create();
    }

    public void we(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Среда");
        weekd = 3;
        create();
    }

    public void th(View v){
        setContentView(R.layout.time_table_activity);
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Четверг");
        weekd = 4;
        create();
    }

    public void fr(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Пятница");
        weekd = 5;
        create();
    }

    public void st(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Суббота");
        weekd = 6;
        create();
    }

    public void sn(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Воскресенье");
        weekd = 7;
        create();
    }

    public String time(){
        return startH + ":" + startM + "-" + finishH + ":" + finishM;
    }

    void create(){
        try {
            eventSheet = (LinearLayout) findViewById(R.id.eventSheet);
            dbHelper_days1 = new DBHelper_days1(this);
            SQLiteDatabase db = dbHelper_days1.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Cursor cursor = db.query(DBHelper_days1.TABLE_DAYS, null, null, null, null, null, null);


            login = getIntent().getStringExtra("login");


            if (cursor.moveToFirst()) {
                Log.d("Log_d",login);
                int logIndex = cursor.getColumnIndex(DBHelper_days1.KEY_LOGIN);
                int dayIndex = cursor.getColumnIndex(DBHelper_days1.KEY_WEEKDAY);
                int startHIndex = cursor.getColumnIndex(DBHelper_days1.KEY_HOURSTART);
                int startMIndex = cursor.getColumnIndex(DBHelper_days1.KEY_MINSTART);
                int finishHIndex = cursor.getColumnIndex(DBHelper_days1.KEY_HOURSTOP);
                int finishMIndex = cursor.getColumnIndex(DBHelper_days1.KEY_MINSTOP);
                int eventIndex = cursor.getColumnIndex(DBHelper_days1.KEY_EVENT);

                Log.d("Log_d", "working");
                do {
                    Log.d("Log_d", "here");
                    if (login.equals(cursor.getString(logIndex)) && weekd == cursor.getInt(dayIndex)) {


                        startH = cursor.getInt(startHIndex);
                        finishH = cursor.getInt(finishHIndex);
                        startM = cursor.getInt(startMIndex);
                        finishM = cursor.getInt(finishMIndex);
                        event = cursor.getString(eventIndex);

                        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        TextView tw = new TextView(this);
                        tw.setText(time()+"\n"+event);
                        eventSheet.addView(tw, lParams);

                    }
                } while (cursor.moveToNext());
            }
        }catch(Exception e){

        }
    }

    public void add_event(View v){
        login = getIntent().getStringExtra("login");
        Intent i = new Intent(Time_table_activity.this, Time_table_create_activity.class);
        i.putExtra("login", login);
        startActivity(i);
    }

}