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
    DBHelper_logDb dbHelper_logDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table_activity);



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Log_d", "hi");

        try {
            eventSheet = (LinearLayout) findViewById(R.id.eventSheet);
            dbHelper_days1 = new DBHelper_days1(this);
            SQLiteDatabase db = dbHelper_days1.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Cursor cursor = db.query(DBHelper_days1.TABLE_DAYS, null, null, null, null, null, null);

            dbHelper_logDb = new DBHelper_logDb(this);
            SQLiteDatabase db1 = dbHelper_logDb.getWritableDatabase();
            ContentValues contentValues1 = new ContentValues();
            Cursor cursor1 = db1.query(DBHelper_logDb.TABLE_CONTACTS, null, null, null, null, null, null);

            if (cursor1.moveToLast()) {
                int logIndex = cursor1.getColumnIndex(DBHelper_logDb.KEY_LOGIN);
                login = cursor1.getString(logIndex);
                Log.d("Log_d", "I'me");
            }

            if (cursor.moveToFirst()) {
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
                        tw.setText(startH);
                        eventSheet.addView(tw, lParams);

                    }
                } while (cursor.moveToNext());
            }
        }catch(Exception e){

        }
    }

    public void mn(View v){
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Понедельник");
        weekd = 1;
    }

    public void tu(View v){
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Вторник");
        weekd = 2;
    }

    public void we(View v){
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Среда");
        weekd = 3;
    }

    public void th(View v){
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Четверг");
        weekd = 4;
    }

    public void fr(View v){
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Пятница");
        weekd = 5;
    }

    public void st(View v){
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Суббота");
        weekd = 6;
    }

    public void sn(View v){
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Воскресенье");
        weekd = 7;
    }

    public String time(){
        return startH + ":" + startM + "-" + finishH + ":" + finishM;
    }

    public void add_event(View v){
        Intent i = new Intent(Time_table_activity.this, Time_table_create_activity.class);
        startActivity(i);
    }

}