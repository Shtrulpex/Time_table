package com.example.hard;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Register_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
    }
    data data = new data();
    public void onRegButtonClick(View v) {
        TextView pass_uncor = (TextView) findViewById(R.id.pass_uncorr);
        TextView log_uncor = (TextView) findViewById(R.id.log_uncor);
        String login = ((EditText) findViewById(R.id.reg_log)).getText().toString();
        String password1 = ((EditText) findViewById(R.id.reg_pass1)).getText().toString();
        String password2 = ((EditText) findViewById(R.id.reg_pass2)).getText().toString();

        if(!password1.equals(password2)){
            pass_uncor.setText("Пароль не совпадает");
        }else {
        for(int k=0; k<data.n; k++){
                if (login.equals(data.users[k][0])){
                    log_uncor.setText("Такой логин уже существует");
                    break;
                }
            }
        data.n++;
        data.users[data.n-1][0]=login;
            data.users[data.n-1][1]=password1;
        }
    }
}