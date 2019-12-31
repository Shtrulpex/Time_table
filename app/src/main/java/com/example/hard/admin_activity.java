package com.example.hard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_activity extends AppCompatActivity {

    LinearLayout users, usersout;
    EditText et;
    TextView twp, twl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setContentView(R.layout.activity_admin);

        DBHelper_auth dbHelper_a = new DBHelper_auth(this);
        SQLiteDatabase db = dbHelper_a.getWritableDatabase();
        Cursor cursor = db.query(DBHelper_auth.TABLE_CONTACTS, null, null, null, null, null, null);

        int logIndex = cursor.getColumnIndex(DBHelper_auth.KEY_LOGIN);
        int passIndex = cursor.getColumnIndex(DBHelper_auth.KEY_PASSWORD);

        if(cursor.moveToFirst()){
            do{

                LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

                usersout = new LinearLayout(this);
                usersout.setOrientation(LinearLayout.HORIZONTAL);
                users = (LinearLayout)findViewById(R.id.users);
                users.addView(usersout, llParams);

                twl = new TextView(this);
                twl.setText(cursor.getString(logIndex));
                usersout.addView(twl, lParams);

                TextView tw = new TextView(this);
                tw.setText("    ");
                usersout.addView(tw, lParams);

                twp = new TextView(this);
                twp.setText(cursor.getString(passIndex));
                usersout.addView(twp);

            }while(cursor.moveToNext());
        }

    }

    public void onButton13(View v) {
        et = (EditText) findViewById(R.id.editText2);

        String login = et.getText().toString();

        DBHelper_auth dbHelper_a = new DBHelper_auth(this);
        SQLiteDatabase db = dbHelper_a.getWritableDatabase();
        Cursor cursor = db.query(DBHelper_auth.TABLE_CONTACTS, null, null, null, null, null, null);

        int logIndex = cursor.getColumnIndex(DBHelper_auth.KEY_LOGIN);

        point:
        try {
            if (cursor.moveToFirst()) {
                do {
                    if (login.equals(cursor.getString(logIndex))) {
                            int a = db.delete(DBHelper_auth.TABLE_CONTACTS, DBHelper_days.KEY_LOGIN + "= ?", new String[]{login});
                        }
                } while (cursor.moveToNext());

                cursor.close();

                DBHelper_days dbHelper = new DBHelper_days(this);
                db = dbHelper.getWritableDatabase();
                cursor = db.query(DBHelper_days.TABLE_DAYS2, null, null, null, null, null, null);

                logIndex = cursor.getColumnIndex(DBHelper_days.KEY_LOGIN);

                if (cursor.moveToFirst()) {
                    do {
                        if (cursor.getString(logIndex).equals(login)) {
                            int a = db.delete(DBHelper_days.TABLE_DAYS2, DBHelper_days.KEY_LOGIN + "= ?", new String[]{login});
                        }
                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) { }
        usersTable();
    }

    public void onButton14(View v){
        finish();
    }

    void usersTable(){
        setContentView(R.layout.activity_admin);

        DBHelper_auth dbHelper_a = new DBHelper_auth(this);
        SQLiteDatabase db = dbHelper_a.getWritableDatabase();
        Cursor cursor = db.query(DBHelper_auth.TABLE_CONTACTS, null, null, null, null, null, null);

        int logIndex = cursor.getColumnIndex(DBHelper_auth.KEY_LOGIN);
        int passIndex = cursor.getColumnIndex(DBHelper_auth.KEY_PASSWORD);

        if(cursor.moveToFirst()){
            do{

                LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

                usersout = new LinearLayout(this);
                usersout.setOrientation(LinearLayout.HORIZONTAL);
                users = (LinearLayout)findViewById(R.id.users);
                users.addView(usersout, llParams);

                twl = new TextView(this);
                twl.setText(cursor.getString(logIndex));
                usersout.addView(twl, lParams);

                TextView tw = new TextView(this);
                tw.setText("    ");
                usersout.addView(tw, lParams);

                twp = new TextView(this);
                twp.setText(cursor.getString(passIndex));
                usersout.addView(twp);

            }while(cursor.moveToNext());
        }

    }
}
