package com.example.szoftverprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button pizza,burger,others;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        pizza=findViewById(R.id.pizza);
        burger=findViewById(R.id.burger);
        others=findViewById(R.id.other);
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pizza = new Intent(getBaseContext(),PizzaActivity.class);
                startActivity(pizza);
            }
        });
        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent burger = new Intent(getBaseContext(),BurgerActivity.class);
                startActivity(burger);
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other = new Intent(getBaseContext(),OtherActivity.class);
                startActivity(other);
            }
        });
    }
}
