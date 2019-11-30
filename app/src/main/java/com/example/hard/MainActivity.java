package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity  extends AppCompatActivity {
    int m = 0;
    int d = 0;
    int n = 0;
    String users[][]=new String[1000][2];


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

        point:if(!password1.equals(password2)){
            pass_uncor.setText("Пароль не совпадает");
        }else {
            for (int k = 0; k < n; k++) {
                if (log.equals(users[k][0])) {
                    log_uncor.setText("Такой логин уже существует");
                    break point;
                }
            }
            n++;
            users[n - 1][0] = log;
            users[n - 1][1] = password1;
            setContentView(R.layout.activity_main);
        }
    }

    public void onEnterButtonClick(View v){


        TextView uncorrect = (TextView) findViewById(R.id.uncorrect);
        EditText log = (EditText)findViewById(R.id.log);
        EditText pass = (EditText)findViewById(R.id.pass);
        String login = ((EditText) findViewById(R.id.log)).getText().toString();
        String password = ((EditText)findViewById(R.id.pass)).getText().toString();
        Toast toast =Toast.makeText(MainActivity.this, users[0][0]+"   "+users[0][1],Toast.LENGTH_LONG);
        toast.show();

        for(int k=0;k<n;k++){
            if(login.equals(users[k][0])&&password.equals(users[k][1])){
                Intent i = new Intent(MainActivity.this, Time_table_activity.class);
                startActivity(i);
            }else d++;
        }
        if(d==n){
            log.setText("");
            pass.setText("");
            uncorrect.setText("Неверный логин или пароль");
            d=0;
        }
    }
}
