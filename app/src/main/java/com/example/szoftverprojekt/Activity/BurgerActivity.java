package com.example.szoftverprojekt.Activity;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.szoftverprojekt.Adapter.MyRecyclerViewAdapter;
import com.example.szoftverprojekt.Interface.ProductInterface;
import com.example.szoftverprojekt.Object.Product;
import com.example.szoftverprojekt.Object.ResultSingleton;
import com.example.szoftverprojekt.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BurgerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Product> pizzaList = new ArrayList<>();
    DatabaseReference databaseUsers;
    String name, price, description;
    private ResultSingleton result = ResultSingleton.getResult();
    ProductInterface productInterface = new ProductInterface() {
        @Override
        public void onItemClick(String pizzaname, String pizzaprice) {
            name = pizzaname;
            price = pizzaprice;
            Product product = new Product(name, price);
            result.insertresult(product);
            ArrayList<Product> xy = result.getresult();
            Product x = xy.get(1);
            //Log.d("xxxxx",x.name);
        }
    };
    private String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPapHuA3IthrqYBu2egup_iQuHu2SdveMF23tX7TiOMOZZ92fRXA&s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);
        databaseUsers = FirebaseDatabase.getInstance().getReference("burgers");
        databaseUsers = FirebaseDatabase.getInstance().getReference().child("burgers");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child("name").getValue(String.class);
                    String price = ds.child("price").getValue(String.class);
                    String description = ds.child("description").getValue(String.class);
                    Product burger = new Product(name, price, description);
                    pizzaList.add(burger);


                }
                recyclerView = findViewById(R.id.rvpizza);
                recyclerView.setLayoutManager(new LinearLayoutManager(BurgerActivity.this));
                adapter = new MyRecyclerViewAdapter(getBaseContext(), pizzaList, url, productInterface);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        databaseUsers.addListenerForSingleValueEvent(eventListener);


    }
}
