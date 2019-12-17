package com.example.szoftverprojekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class PizzaActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Product> productList =new ArrayList<>();
    private String url ="http://www.pizzativoli.ro/images/menu/Chicken-Pizza.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        Product product1 =new Product("asd","11 lei","asdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Product product2 =new Product("asdsd","12 lei","asdasd");
        Product product3 =new Product("asdadsa","15 lei","asdasd");
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        recyclerView=findViewById(R.id.rvpizza);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyRecyclerViewAdapter(getBaseContext(), productList,url);
        recyclerView.setAdapter(adapter);

    }
}
