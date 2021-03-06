package com.example.hard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Time_table_activity extends AppCompatActivity {

    DBHelper_days dbHelper_days;
    int weekd = 1;
    String login, event;
    LinearLayout eventSheet, eventSh;
    int startH, finishH, startM, finishM;
    Button mn, tu, we, th, fr, st, sn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table_activity);
    }

    View.OnClickListener change = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(Time_table_activity.this, change_activity.class);
            i.putExtra("user", v.getId());
            startActivity(i);

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Log_d", "hi");
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Понедельник");
        weekd = 1;
        mn = (Button)findViewById(R.id.mn);
        tu = (Button)findViewById(R.id.tu);
        we = (Button)findViewById(R.id.we);
        th = (Button)findViewById(R.id.th);
        fr = (Button)findViewById(R.id.fr);
        st = (Button)findViewById(R.id.st);
        sn = (Button)findViewById(R.id.sn);
        mn.setTextColor(Color.GREEN);
        create();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        login = getIntent().getStringExtra("login");
        if(login.equals("Shtrulpex")){
            getMenuInflater().inflate(R.menu.menu_2, menu);
        }else getMenuInflater().inflate(R.menu.menu_1, menu);
        return true;
    }

    public void onSettingsClick(MenuItem item){
        login = getIntent().getStringExtra("login");
        Intent i = new Intent(Time_table_activity.this, settings_activity.class);
        i.putExtra("login", login);
        startActivity(i);
    }

    void init(){
        mn = (Button)findViewById(R.id.mn);
        tu = (Button)findViewById(R.id.tu);
        we = (Button)findViewById(R.id.we);
        th = (Button)findViewById(R.id.th);
        fr = (Button)findViewById(R.id.fr);
        st = (Button)findViewById(R.id.st);
        sn = (Button)findViewById(R.id.sn);
    }

    public void mn(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Понедельник");
        init();
        mn.setTextColor(Color.GREEN);
        tu.setTextColor(Color.BLACK);
        we.setTextColor(Color.BLACK);
        th.setTextColor(Color.BLACK);
        fr.setTextColor(Color.BLACK);
        st.setTextColor(Color.BLACK);
        sn.setTextColor(Color.BLACK);
        weekd = 1;
        create();
    }

    public void tu(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Вторник");
        weekd = 2;
        init();
        tu.setTextColor(Color.GREEN);
        mn.setTextColor(Color.BLACK);
        we.setTextColor(Color.BLACK);
        th.setTextColor(Color.BLACK);
        fr.setTextColor(Color.BLACK);
        st.setTextColor(Color.BLACK);
        sn.setTextColor(Color.BLACK);
        create();
    }

    public void we(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Среда");
        weekd = 3;
        init();
        we.setTextColor(Color.GREEN);
        tu.setTextColor(Color.BLACK);
        mn.setTextColor(Color.BLACK);
        th.setTextColor(Color.BLACK);
        fr.setTextColor(Color.BLACK);
        st.setTextColor(Color.BLACK);
        sn.setTextColor(Color.BLACK);
        create();
    }

    public void th(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Четверг");
        init();
        th.setTextColor(Color.GREEN);
        tu.setTextColor(Color.BLACK);
        we.setTextColor(Color.BLACK);
        mn.setTextColor(Color.BLACK);
        fr.setTextColor(Color.BLACK);
        st.setTextColor(Color.BLACK);
        sn.setTextColor(Color.BLACK);
        weekd = 4;
        create();
    }

    public void fr(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Пятница");
        init();
        fr.setTextColor(Color.GREEN);
        tu.setTextColor(Color.BLACK);
        we.setTextColor(Color.BLACK);
        mn.setTextColor(Color.BLACK);
        th.setTextColor(Color.BLACK);
        st.setTextColor(Color.BLACK);
        sn.setTextColor(Color.BLACK);
        weekd = 5;
        create();
    }

    public void st(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Суббота");
        init();
        st.setTextColor(Color.GREEN);
        tu.setTextColor(Color.BLACK);
        we.setTextColor(Color.BLACK);
        th.setTextColor(Color.BLACK);
        mn.setTextColor(Color.BLACK);
        fr.setTextColor(Color.BLACK);
        sn.setTextColor(Color.BLACK);
        weekd = 6;
        create();
    }

    public void sn(View v){
        setContentView(R.layout.time_table_activity);
        TextView day = (TextView)findViewById(R.id.weekd);
        day.setText("Воскресенье");
        init();
        sn.setTextColor(Color.GREEN);
        tu.setTextColor(Color.BLACK);
        we.setTextColor(Color.BLACK);
        th.setTextColor(Color.BLACK);
        fr.setTextColor(Color.BLACK);
        st.setTextColor(Color.BLACK);
        mn.setTextColor(Color.BLACK);
        weekd = 7;
        create();
    }

    public String time(){
        if(startM/10==0){
            if(finishM/10==0){
                return startH+":0"+startM+"-"+finishH+":0"+finishM;
            }else{
                return startH+":0"+startM+"-"+finishH+":"+finishM;
            }
        }else{
            if (finishM/10==0){
                return startH+":"+startM+"-"+finishH+":0"+finishM;
            }else{
                return startH+":"+startM+"-"+finishH+":"+finishM;
            }
        }
    }

    void create(){
        try {
            eventSheet = (LinearLayout) findViewById(R.id.eventSheet);
            dbHelper_days = new DBHelper_days(this);
            SQLiteDatabase db = dbHelper_days.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Cursor cursor = db.query(DBHelper_days.TABLE_DAYS2, null, null, null, null, null, null);

            login = getIntent().getStringExtra("login");

            if (cursor.moveToFirst()) {
                Log.d("Log_d",login);
                int logIndex = cursor.getColumnIndex(DBHelper_days.KEY_LOGIN);
                int dayIndex = cursor.getColumnIndex(DBHelper_days.KEY_WEEKDAY);
                int startHIndex = cursor.getColumnIndex(DBHelper_days.KEY_HOURSTART);
                int startMIndex = cursor.getColumnIndex(DBHelper_days.KEY_MINSTART);
                int finishHIndex = cursor.getColumnIndex(DBHelper_days.KEY_HOURSTOP);
                int finishMIndex = cursor.getColumnIndex(DBHelper_days.KEY_MINSTOP);
                int eventIndex = cursor.getColumnIndex(DBHelper_days.KEY_EVENT);
                int idIndex = cursor.getColumnIndex(DBHelper_days.KEY_ID);
                int noteIndex = cursor.getColumnIndex(DBHelper_days.KEY_NOTE);

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

                        tw.setText(time());

                        tw.setTextSize(24);
                        eventSh = new LinearLayout(this);
                        eventSh.setOrientation(LinearLayout.VERTICAL);

                        eventSheet.addView(eventSh, llParams);
                        eventSh.setOnClickListener(change);
                        eventSh.addView(tw, lParams);
                        eventSh.setId(id);
                        tw = new TextView(this);
                        tw.setText(event);
                        tw.setTextSize(20);
                        eventSh.addView(tw, lParams);

                        try{
                            String s = cursor.getString(noteIndex);
                            if(s!=null) {
                                tw = new TextView(this);
                                tw.setText("Заметки: \n" + s);
                                tw.setTextSize(16);
                                eventSh.addView(tw, lParams);
                            }
                        }catch(Exception e){

                        }

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

    public void onAdminClick(MenuItem item){
        Intent i = new Intent(Time_table_activity.this, admin_activity.class);
        startActivity(i);
    }

}