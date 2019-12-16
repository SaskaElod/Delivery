package com.example.szoftverprojekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText name,password;
    Button login;
    FirebaseDatabase databaseUsers;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=findViewById(R.id.edt_Name);
        password=findViewById(R.id.edt_Password);
        login=(Button)findViewById(R.id.btn_Login);
        ref = FirebaseDatabase.getInstance().getReference().child("users");
       /* String pin;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin(v);
            }
        });*/


    }
    String pin;
    String userID;
    public void btnLogin(View view)
    {
        userID=name.getText().toString();
        pin=password.getText().toString();
        if(ref.child(userID)!=null){
        ref.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                if(pin.equals(user.getPassword()))
                {
                    Toast.makeText(getBaseContext(),"Login was succesful!",Toast.LENGTH_LONG).show();
                    Intent start=new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(start);
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Enter the correct pin...!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    else
        {
            Toast.makeText(getBaseContext(),"Student doesn't exist!",Toast.LENGTH_LONG).show();
        }
    }
/*
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
    }*/
}
