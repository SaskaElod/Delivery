package com.example.szoftverprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.edt_Email);
        password=findViewById(R.id.edt_Password);
        login=(Button)findViewById(R.id.btn_Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext,passwordtext;

                emailtext=email.getText().toString().trim();
                passwordtext=password.getText().toString().trim();

                if(check(emailtext,passwordtext))
                {
                    Toast.makeText(getBaseContext(),"Login was succesful!",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });


    }
    boolean check(String emailtext,String passwordtext)
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(emailtext.isEmpty())
        {   Toast.makeText(getBaseContext(),"Email field is empty!",Toast.LENGTH_LONG).show();
            return false;
        }
        else
        if(!emailtext.matches(emailPattern))
        {
            Toast.makeText(getBaseContext(),"Email is invalid!",Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            if(passwordtext.isEmpty())
            {   Toast.makeText(getBaseContext(),"Password field is empty!",Toast.LENGTH_LONG).show();
                return false;
            }
            return false;

        }
    }
}
