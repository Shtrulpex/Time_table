package com.example.hard;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class chanp_activity extends AppCompatActivity {

    String login;
    EditText pass1, pass2, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chanp);
    }

    public void onretbclick(View v){
        finish();
    }

    public void onSaveclick(View v){

        login = getIntent().getStringExtra("login");
        Log.d("Log_d", login);
        pass = (EditText)findViewById(R.id.chanpassp);
        pass1 = (EditText)findViewById(R.id.chanpass1);
        pass2 = (EditText)findViewById(R.id.chanpass2);

        DBHelper_auth dbHelper_a = new DBHelper_auth(this);
        SQLiteDatabase db = dbHelper_a.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.query(DBHelper_auth.TABLE_CONTACTS, null, null, null, null, null, null);

        int logIndex = cursor.getColumnIndex(DBHelper_auth.KEY_LOGIN);
        int passIndex = cursor.getColumnIndex(DBHelper_auth.KEY_PASSWORD);


        point:if(cursor.moveToFirst()){
            do{
                if(login.equals(cursor.getString(logIndex))){
                    try{

                        Log.d("Log_d", "found");

                        if(!pass.getText().toString().equals(cursor.getString(passIndex))){
                            Toast.makeText(this, "Неверный пароль", Toast.LENGTH_LONG).show();
                            break point;
                        }

                        if(!pass1.getText().toString().equals(pass2.getText().toString())){
                            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_LONG).show();
                            break point;
                        }

                        if(pass1.getText().toString().length()<4){
                            Toast.makeText(this, "Пароль слишком короткий", Toast.LENGTH_LONG).show();
                            break point;
                        }

                        contentValues.put(DBHelper_auth.KEY_PASSWORD, pass1.getText().toString());
                        int a = db.update(DBHelper_auth.TABLE_CONTACTS, contentValues, DBHelper_auth.KEY_PASSWORD+"= ?", new String[] {pass.getText().toString()});
                        finish();
                    }catch(Exception e){

                    }
                }
            }while(cursor.moveToNext());
        }

    }

}
