package com.example.szoftverprojekt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.szoftverprojekt.Adapter.MyRecyclerViewAdapter;
import com.example.szoftverprojekt.Object.Product;
import com.example.szoftverprojekt.Interface.ProductInterface;
import com.example.szoftverprojekt.R;
import com.example.szoftverprojekt.Object.ResultSingleton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OtherActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Product> pizzaList=new ArrayList<>();
    DatabaseReference databaseUsers;
    String name,price,description;
    private ResultSingleton result=ResultSingleton.getResult();
    ProductInterface productInterface=new ProductInterface() {
        @Override
        public void onItemClick(String pizzaname, String pizzaprice) {
            name=pizzaname;
            price=pizzaprice;
            Product product=new Product(name,price);
            result.insertresult(product);
            ArrayList<Product> xy=result.getresult();
            Product x=xy.get(0);
            //Log.d("xxxxx",x.name);
        }
    };
    private String url ="https://image.shutterstock.com/image-photo/los-angeles-usa-february-18-260nw-371239504.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        databaseUsers = FirebaseDatabase.getInstance().getReference("others");
        Product other1=new Product("Cola","10lej","2 L");
        Product other2=new Product("Fanta","10 lej","2 L");
        Product other3=new Product("Sprite","10 lej","2 L");
        Product other4=new Product("Water","10 lej","2 L");
        Product other5=new Product("Beer","10 lej","2 L");
        Product other6=new Product("Cola","5 lej","0.5 L");
        Product other7=new Product("Fanta","5 lej","0.5 L");
        Product other8=new Product("Sprite","5 lej","0.5 L");
        Product other9=new Product("Water","5 lej","0.5 L");
        Product other10=new Product("Beer","5 lej","0.5 L");

        databaseUsers=FirebaseDatabase.getInstance().getReference().child("others");
        String id=databaseUsers.push().getKey();
        databaseUsers.child(id).setValue(other1);
        databaseUsers.child(id+1).setValue(other2);
        databaseUsers.child(id+2).setValue(other3);
        databaseUsers.child(id+3).setValue(other4);
        databaseUsers.child(id+4).setValue(other5);
        databaseUsers.child(id+5).setValue(other6);
        databaseUsers.child(id+7).setValue(other7);
        databaseUsers.child(id+8).setValue(other8);
        databaseUsers.child(id+9).setValue(other9);
        databaseUsers.child(id+10).setValue(other10);
        ValueEventListener eventListener= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String name=ds.child("name").getValue(String.class);
                    String price=ds.child("price").getValue(String.class);
                    String description=ds.child("description").getValue(String.class);
                    Log.d("TAAAAAAAG", name);
                    Log.d("BBBBBBBBBBB", price);
                    Log.d("CCCCCCCCCCC", description);
                    Product other = new Product(name,price,description);
                    pizzaList.add(other);



                }
                Log.d("FFFFFFF", String.valueOf(pizzaList));
                recyclerView=findViewById(R.id.rvpizza);
                recyclerView.setLayoutManager(new LinearLayoutManager(OtherActivity.this));
                adapter=new MyRecyclerViewAdapter(getBaseContext(),pizzaList,url,productInterface);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        Log.d("GGGGGGGGGGGGGGG", String.valueOf(pizzaList));
        databaseUsers.addListenerForSingleValueEvent(eventListener);


    }/*
    private void addPizza()
    {

        //String useremail=email.getText().toString().trim();
        //String userpassword=password.getText().toString().trim();
       // final String username=name.getText().toString().trim();
       String id=databaseUsers.push().getKey();
       Pizza pizza=new Pizza(name,price,description);
       databaseUsers.child(username).setValue(user);
       Toast.makeText(this,"Pizza added",Toast.LENGTH_LONG).show();





    }*/
}
