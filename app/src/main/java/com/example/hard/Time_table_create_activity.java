package com.example.hard;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Time_table_create_activity extends AppCompatActivity {

    DBHelper_days dbHelper;
    int weekday= 1;
    int repeat = 0;
    int k=0;
    String login;
    Button b1, b2 ,b3, b4, b5, b6, b7;
    int []week;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table_create_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        week = new int[]{0, 0, 0, 0, 0, 0, 0};
    }

    public void w1(View v){
        b1 = (Button)findViewById(R.id.w1);
        if(week[0]==0){
            b1.setTextColor(Color.GREEN);
            week[0]=1;
        }else{
            b1.setTextColor(Color.BLACK);
            week[0]=0;
        }
    }

    public void w2(View v){
        b2 = (Button)findViewById(R.id.w2);
        if(week[1]==0){
            b2.setTextColor(Color.GREEN);
            week[1]=1;
        }else{
            b2.setTextColor(Color.BLACK);
            week[1]=0;
        }
    }

    public void w3(View v){
        b3 = (Button)findViewById(R.id.w3);
        if(week[2]==0){
            b3.setTextColor(Color.GREEN);
            week[2]=1;
        }else{
            b3.setTextColor(Color.BLACK);
            week[2]=0;
        }
    }

    public void w4(View v){
        b4 = (Button)findViewById(R.id.w4);
        if(week[3]==0){
            b4.setTextColor(Color.GREEN);
            week[3]=1;
        }else{
            b4.setTextColor(Color.BLACK);
            week[3]=0;
        }
    }

    public void w5(View v){
        b5 = (Button)findViewById(R.id.w5);
        if(week[4]==0){
            b5.setTextColor(Color.GREEN);
            week[4]=1;
        }else{
            b5.setTextColor(Color.BLACK);
            week[4]=0;
        }
    }

    public void w6(View v){
        b6 = (Button)findViewById(R.id.w6);
        if(week[5]==0){
            b6.setTextColor(Color.GREEN);
            week[5]=1;
        }else{
            b6.setTextColor(Color.BLACK);
            week[5]=0;
        }
    }

    public void w7(View v){
        b7 = (Button)findViewById(R.id.w7);
        if(week[6]==0){
            b7.setTextColor(Color.GREEN);
            week[6]=1;
        }else{
            b7.setTextColor(Color.BLACK);
            week[6]=0;
        }
    }

    public void b1(View v){
        finish();
    }

    public void onSubButtonClick(View v){

        login = getIntent().getStringExtra("login");
        point:
        try {


            int startH = Integer.parseInt(((EditText) findViewById(R.id.hour_start)).getText().toString());
            int startM = Integer.parseInt(((EditText) findViewById(R.id.min_start)).getText().toString());
            int endH = Integer.parseInt(((EditText) findViewById(R.id.hour_end)).getText().toString());
            int endM = Integer.parseInt(((EditText) findViewById(R.id.min_end)).getText().toString());
            String event = ((EditText) findViewById(R.id.event)).getText().toString();

            if(startH>23||endH>23||startM>59||endM>59){
                Toast.makeText(this, "Такого времени не существует", Toast.LENGTH_LONG).show();
                break point;
            }

            if(startH>endH){
                Toast.makeText(this, "У нас время идётв другую сторону", Toast.LENGTH_LONG).show();
                break point;
            }

            if(startH==endH && startM>endM){
                Toast.makeText(this, "У нас время идётв другую сторону", Toast.LENGTH_LONG).show();
                break point;
            }

            for(int i=0; i<7; i++){
                if(week[i]==0){
                    k++;
                }else break;
            }

            if(k==7){
                k=0;
                Toast.makeText(this, "Вы не выбрали день", Toast.LENGTH_LONG).show();
                break point;
            }

            dbHelper = new DBHelper_days(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Cursor cursor = db.query(DBHelper_days.TABLE_DAYS2, null, null, null, null, null, null);

            for(int i = 0; i<7; i++){
                if(week[i]==1){
                    contentValues.put(DBHelper_days.KEY_LOGIN, login);
                    contentValues.put(DBHelper_days.KEY_HOURSTART, startH);
                    contentValues.put(DBHelper_days.KEY_MINSTART, startM);
                    contentValues.put(DBHelper_days.KEY_HOURSTOP, endH);
                    contentValues.put(DBHelper_days.KEY_MINSTOP, endM);
                    contentValues.put(DBHelper_days.KEY_EVENT, event);
                    contentValues.put(DBHelper_days.KEY_REP, repeat);
                    contentValues.put(DBHelper_days.KEY_WEEKDAY, i+1);
                    db.insert(DBHelper_days.TABLE_DAYS2, null, contentValues);
                }
            }

            cursor.close();
            finish();
        }catch (Exception e){
            Toast.makeText(this, "Вы заполнили не все поля", Toast.LENGTH_LONG).show();
        }

    }

}
