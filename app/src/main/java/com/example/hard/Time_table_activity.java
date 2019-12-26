package com.example.hard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Time_table_activity extends AppCompatActivity {

    DBHelper_days1 dbHelper_days1;
    int weekd = 1;
    String login, event;
    LinearLayout eventSheet, eventSh;
    int startH, finishH, startM, finishM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table_activity);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {Log.d("Log_d", "+"+v.getId());
                switch(v.getId()){
                    case R.id.mn:
                        break;
                    case R.id.tu:
                        break;
                    case R.id.we:
                        break;
                    case R.id.th:
                        break;
                    case R.id.fr:
                        break;
                    case R.id.st:
                        break;
                    case R.id.sn:
                        break;
                    default:
                        login = getIntent().getStringExtra("login");

                        try{
                            String et = ((EditText)findViewById((v.getId())*100000)).getText().toString();

                            dbHelper_days1 = new DBHelper_days1(Time_table_activity.this);
                            SQLiteDatabase db = dbHelper_days1.getWritableDatabase();
                            ContentValues contentValues = new ContentValues();
                            Cursor cursor = db.query(DBHelper_days1.TABLE_DAYS, null, null, null, null, null, null);

                            int logIndex = cursor.getColumnIndex(DBHelper_days1.KEY_LOGIN);
                            int dayIndex = cursor.getColumnIndex(DBHelper_days1.KEY_WEEKDAY);
                            int idIndex = cursor.getColumnIndex(DBHelper_days1.KEY_ID);

                            if(cursor.moveToFirst()) {
                                do {
                                    if (login.equals(cursor.getString(logIndex)) && weekd == cursor.getInt(dayIndex) && cursor.getInt(idIndex)==v.getId()) {
                                        contentValues.put(DBHelper_days1.KEY_NOTE, et);
                                        int a = db.update(DBHelper_days1.TABLE_DAYS, contentValues, DBHelper_days1.KEY_ID+"= ?", new String[] {"12"});
                                    }
                                } while (cursor.moveToNext());
                            }
                        }catch (Exception e){
                            Toast.makeText(Time_table_activity.this, "Поле пустое", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Log_d", "hi");
        setContentView(R.layout.time_table_activity);

        create();
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
                int idIndex = cursor.getColumnIndex(DBHelper_days1.KEY_ID);
                int noteIndex = cursor.getColumnIndex(DBHelper_days1.KEY_NOTE);

                Log.d("Log_d", "working");
                do {
                    if (login.equals(cursor.getString(logIndex)) && weekd == cursor.getInt(dayIndex)) {

                        int id = cursor.getInt(idIndex);
                        Log.d("Log_d", ""+id);
                        startH = cursor.getInt(startHIndex);
                        finishH = cursor.getInt(finishHIndex);
                        startM = cursor.getInt(startMIndex);
                        finishM = cursor.getInt(finishMIndex);
                        event = cursor.getString(eventIndex);

                        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        TextView tw = new TextView(this);
                        EditText et = new EditText(this);
                        Button bt = new Button(this);

                        tw.setText(time()+"\n"+event);
                        try{
                            tw.setText(time()+"\n"+event+"\n\n"+"Заметка:"+"\n"+cursor.getString(noteIndex));
                        }catch (Exception e){

                        }

                        et.setId(id);
                        lParams.weight = 1;

                        bt.setText("Save");
                        bt.setId(100000*id);

                        eventSh = new LinearLayout(this);
                        eventSh.setOrientation(LinearLayout.HORIZONTAL);
                        eventSheet.addView(eventSh, llParams);
                        eventSh.addView(tw, lParams);
                        eventSh.addView(et, lParams);
                        eventSh.addView(bt, lParams);

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