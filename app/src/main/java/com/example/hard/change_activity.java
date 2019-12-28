package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class change_activity extends AppCompatActivity {

    int id;
    EditText note, dz;
    TextView event, time;
    int startH, startM, finishM, finishH;
    DBHelper_days dbHelper_days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_activity);

        event = (TextView)findViewById(R.id.event);
        time = (TextView)findViewById(R.id.time);
        note = (EditText)findViewById(R.id.note);
        dz = (EditText)findViewById(R.id.DZ);

        dbHelper_days = new DBHelper_days(this);
        SQLiteDatabase db = dbHelper_days.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.query(DBHelper_days.TABLE_DAYS2, null, null, null, null, null, null);

        if(cursor.moveToFirst()){

            int startHIndex = cursor.getColumnIndex(DBHelper_days.KEY_HOURSTART);
            int startMIndex = cursor.getColumnIndex(DBHelper_days.KEY_MINSTART);
            int finishHIndex = cursor.getColumnIndex(DBHelper_days.KEY_HOURSTOP);
            int finishMIndex = cursor.getColumnIndex(DBHelper_days.KEY_MINSTOP);
            int eventIndex = cursor.getColumnIndex(DBHelper_days.KEY_EVENT);
            int idIndex = cursor.getColumnIndex(DBHelper_days.KEY_ID);
            int noteIndex = cursor.getColumnIndex(DBHelper_days.KEY_NOTE);
            int dzIndex = cursor.getColumnIndex(DBHelper_days.KEY_DZ);
            id = getIntent().getIntExtra("user", 1);

            do{
                if(id==cursor.getInt(idIndex)){

                    startH = cursor.getInt(startHIndex);
                    finishH = cursor.getInt(finishHIndex);
                    startM = cursor.getInt(startMIndex);
                    finishM = cursor.getInt(finishMIndex);

                    event.setText(cursor.getString(eventIndex));
                    time.setText(time());

                    try{
                        note.setText(cursor.getString(noteIndex));
                    }catch(Exception e){}
                    try{
                        dz.setText(cursor.getString(dzIndex));
                    }catch(Exception e){}

                }
            }while(cursor.moveToNext());
        }

    }

    public void saveClick(View v){

        dbHelper_days = new DBHelper_days(this);
        SQLiteDatabase db = dbHelper_days.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.query(DBHelper_days.TABLE_DAYS2, null, null, null, null, null, null);

        if(cursor.moveToFirst()){

            note = (EditText)findViewById(R.id.note);
            dz = (EditText)findViewById(R.id.DZ);

            int idIndex = cursor.getColumnIndex(DBHelper_days.KEY_ID);
            id = getIntent().getIntExtra("user", 1);

            do{
                Log.d("Log_d", "read");
                if(id==cursor.getInt(idIndex)){
                    try {
                        contentValues.put(DBHelper_days.KEY_NOTE, note.getText().toString());
                        Log.d("Log_d", note.getText().toString());
                        int a = db.update(DBHelper_days.TABLE_DAYS2, contentValues, DBHelper_days.KEY_ID+"= ?", new String[] {Integer.toString(id)});
                    }catch(Exception e){}
                    contentValues = new ContentValues();
                    try{
                        contentValues.put(DBHelper_days.KEY_DZ, dz.getText().toString());
                        Log.d("Log_d", "try2");
                        int a = db.update(DBHelper_days.TABLE_DAYS2, contentValues, DBHelper_days.KEY_ID + "= ?", new String[] {Integer.toString(id)});

                    }catch (Exception e){}

                    finish();

                    break;
                }
            }while(cursor.moveToNext());
        }

    }

    public void delClick(View v){

        dbHelper_days = new DBHelper_days(this);
        SQLiteDatabase db = dbHelper_days.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.query(DBHelper_days.TABLE_DAYS2, null, null, null, null, null, null);

        if(cursor.moveToFirst()){

            int idIndex = cursor.getColumnIndex(DBHelper_days.KEY_ID);
            id = getIntent().getIntExtra("user", 1);

            do{
                Log.d("Log_d", "read");
                if(id==cursor.getInt(idIndex)){

                    int a = db.delete(DBHelper_days.TABLE_DAYS2, DBHelper_days.KEY_ID + "= ?", new String[]{Integer.toString(id)});

                    finish();

                    break;
                }
            }while(cursor.moveToNext());
        }

    }

    public void backClick(View v){
        finish();
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

}
