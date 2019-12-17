package com.example.szoftverprojekt.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class BurgerActivity extends AppCompatActivity {
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
            Product x=xy.get(1);
            //Log.d("xxxxx",x.name);
        }
    };
    private String url ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPapHuA3IthrqYBu2egup_iQuHu2SdveMF23tX7TiOMOZZ92fRXA&s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        databaseUsers = FirebaseDatabase.getInstance().getReference("burgers");
        Product burger1=new Product("American","19 lej","Streaky bacon rashers, smoky honey ham, pepperoni, Italian sausage, lightly seasoned beef on rich BBQ sauce on a Personal Pan");
        Product burger2=new Product("Juicy Lucy Burger","22 lej","Chifla cu unt,100%carne de manzat,rosii,rondele de ceapa,crema de avocado,cascaval cedar");
        Product burger3=new Product("DoubleAmerican","25 lej","Tender chicken, streaky bacon rashers, diced tomato, onion  & mozzarella finished with smoky BBQ drizzle");
        Product burger4=new Product("Vegan","20 lej","With pepperoni, sausage, salami, mushrooms, onions, and green peppers.");
        Product burger5=new Product("Vega","22 lej","Cajun sausage, red peppers, onions, and mushrooms.");
        Product burger6=new Product("Smoky BBQ Burger","25 lej", "Chifla with butter, 100% beef, BBQ sauce, cedar cheese, onion rounds, bacon, iceberg salad .");
        Product burger7=new Product("Mini Classic Burger","22 lej","Chifa with butter, 100% beef, tomatoes, pickled cucumbers, iceberg salad, sauce.");
        Product burger8=new Product("Premium Burger","35 lej","Chifla with butter, 100% beef, grilled camembert cheese, iceberg salad, butcher sauce");
        Product burger9=new Product("Gluten Free","30 lej", "Whichever burger you can order without bread, with lots of vegetables");
        Product burger10=new Product("Camembert cheese burger","26 lej","Chifla with butter, grilled camembert cheese, tomatoes, iceberg salad, fried seeds sauce");
/*
        String id=databaseUsers.push().getKey();
        databaseUsers.child(id).setValue(burger1);
        databaseUsers.child(id+1).setValue(burger2);
        databaseUsers.child(id+2).setValue(burger3);
        databaseUsers.child(id+3).setValue(burger4);
        databaseUsers.child(id+4).setValue(burger5);
        databaseUsers.child(id+5).setValue(burger6);
        databaseUsers.child(id+6).setValue(burger7);
        databaseUsers.child(id+7).setValue(burger8);
        databaseUsers.child(id+8).setValue(burger9);
        databaseUsers.child(id+9).setValue(burger10);*/


        Toast.makeText(this,"Burger added",Toast.LENGTH_LONG).show();
        databaseUsers=FirebaseDatabase.getInstance().getReference().child("burgers");

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
                    Product burger = new Product(name,price,description);
                    pizzaList.add(burger);



                }
                Log.d("FFFFFFF", String.valueOf(pizzaList));
                recyclerView=findViewById(R.id.rvpizza);
                recyclerView.setLayoutManager(new LinearLayoutManager(BurgerActivity.this));
                adapter=new MyRecyclerViewAdapter(getBaseContext(),pizzaList,url,productInterface);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        Log.d("GGGGGGGGGGGGGGG", String.valueOf(pizzaList));
        databaseUsers.addListenerForSingleValueEvent(eventListener);


    }
}
