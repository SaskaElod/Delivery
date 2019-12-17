package com.example.szoftverprojekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PizzaActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MyPizzaRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Pizza> pizzaList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        Pizza pizza1=new Pizza("asd","11","asdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Pizza pizza2=new Pizza("asdsd","12","asdasd");
        Pizza pizza3=new Pizza("asdadsa","15","asdasd");
        pizzaList.add(pizza1);
        pizzaList.add(pizza2);
        pizzaList.add(pizza3);
        recyclerView=findViewById(R.id.rvpizza);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyPizzaRecyclerViewAdapter(getBaseContext(),pizzaList);
        recyclerView.setAdapter(adapter);

    }
}
