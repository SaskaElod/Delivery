package com.example.szoftverprojekt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.szoftverprojekt.R;

public class MenuActivity extends AppCompatActivity {

    Button pizza, burger, others, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        pizza = findViewById(R.id.pizza);
        burger = findViewById(R.id.burger);
        others = findViewById(R.id.other);
        result = findViewById(R.id.result);
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pizza = new Intent(getBaseContext(), PizzaActivity.class);
                startActivity(pizza);
            }
        });
        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent burger = new Intent(getBaseContext(), BurgerActivity.class);
                startActivity(burger);
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other = new Intent(getBaseContext(), OtherActivity.class);
                startActivity(other);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent(getBaseContext(), ResultActivity.class);
                startActivity(result);
            }
        });
    }
}
