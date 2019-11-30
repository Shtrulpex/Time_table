package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity  extends AppCompatActivity {
    int d = 0;
    int n = 0;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void onRegisterButtonClick(View v) throws Exception{
        setContentView(R.layout.register_activity);
    }

    public void onRetButtonClick(View v){
        setContentView(R.layout.activity_main);
    }

    public void onRegButtonClick(View v) throws Exception {


        TextView pass_uncor = (TextView) findViewById(R.id.pass_uncorr);
        TextView log_uncor = (TextView) findViewById(R.id.log_uncor);
        String log = ((EditText) findViewById(R.id.reg_log)).getText().toString();
        String password1 = ((EditText) findViewById(R.id.reg_pass1)).getText().toString();
        String password2 = ((EditText) findViewById(R.id.reg_pass2)).getText().toString();


        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

        point:if(!password1.equals(password2)){
            pass_uncor.setText("Пароль не совпадает");
        }else {
                if(cursor.moveToFirst()){
                    int loginIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
                    do {
                        if(cursor.getString(loginIndex).equals(log)){
                            log_uncor.setText("Такой логин уже существует");
                            break point;
                        }
                    }while(cursor.moveToNext());
                }
                n++;
                contentValues.put(DBHelper.KEY_LOGIN, log);
                contentValues.put(DBHelper.KEY_PASSWORD, password1);
                db.insert(DBHelper.TABLE_CONTACTS, null, contentValues);

                setContentView(R.layout.activity_main);
            }

        }


    public void onEnterButtonClick(View v){


        TextView uncorrect = (TextView) findViewById(R.id.uncorrect);
        EditText log = (EditText)findViewById(R.id.log);
        EditText pass = (EditText)findViewById(R.id.pass);
        String login = ((EditText) findViewById(R.id.log)).getText().toString();
        String password = ((EditText)findViewById(R.id.pass)).getText().toString();

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            int loginIndex = cursor.getColumnIndex(DBHelper.KEY_LOGIN);
            int passswordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
            do{
                if(cursor.getString(loginIndex).equals(login)&&cursor.getString(passswordIndex).equals(password)){
                    Intent i = new Intent(MainActivity.this, Time_table_activity.class);
                    startActivity(i);
                }else d++;
            }while(cursor.moveToNext());

        }
        if(d==n){
            log.setText("");
            pass.setText("");
            uncorrect.setText("Неверный логин или пароль");
            d=0;
        }
    }
}
