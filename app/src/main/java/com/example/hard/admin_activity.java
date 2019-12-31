package com.example.hard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    public void onButton13(View v){

    }

    public void onButton14(View v){
        finish();
    }
}
