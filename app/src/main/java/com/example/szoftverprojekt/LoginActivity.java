package com.example.szoftverprojekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button login;
    DatabaseReference databaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.edt_Email);
        password=findViewById(R.id.edt_Password);
        login=(Button)findViewById(R.id.btn_Login);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext,passwordtext;

                emailtext=email.getText().toString().trim();
                passwordtext=password.getText().toString().trim();

                if(!check(emailtext,passwordtext))
                {

                    databaseUsers .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                            Log.d("messages Referencev "," ==>"+dataSnapshot.toString());

                            User map = dataSnapshot.getValue(User.class);
                            String emailtxt = map.getUserEmail();
                            String userName = map.getUserName();

                            Log.d("message ","  ==>"+emailtxt);  // Here I am getting null value

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(getBaseContext(),"Login  succesful!",Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Login  failed!",Toast.LENGTH_LONG).show();

                }
            }
        });


    }


    boolean check(String emailtext,String passwordtext)
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(emailtext.isEmpty())
        {
            Toast.makeText(getBaseContext(),"Email field is empty!",Toast.LENGTH_LONG).show();

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
