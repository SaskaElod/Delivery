package com.example.szoftverprojekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PizzaActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MyPizzaRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Pizza> pizzaList=new ArrayList<>();
    DatabaseReference databaseUsers;
    String name,price,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        databaseUsers = FirebaseDatabase.getInstance().getReference("pizzas");
        Pizza pizza1=new Pizza("Mexicana","19 lej","Streaky bacon rashers, smoky honey ham, pepperoni, Italian sausage, lightly seasoned beef on rich BBQ sauce on a Personal Pan");
        Pizza pizza2=new Pizza("Hawai","22 lej","Smoky honey ham, juicy pineapple & mozzarella");
        Pizza pizza3=new Pizza("Magyaros","25 lej","Tender chicken, streaky bacon rashers, diced tomato, onion  & mozzarella finished with smoky BBQ drizzle");
        Pizza pizza4=new Pizza("The Carnivore","20 lej","With pepperoni, sausage, salami, mushrooms, onions, and green peppers.");
        Pizza pizza5=new Pizza("Susage Supreme","22 lej","Cajun sausage, red peppers, onions, and mushrooms.");
        Pizza pizza6=new Pizza("Chipotle Chicken","25 lej","Grilled chicken in chipotle marinade with sausage, jalapenos, onions, mushrooms, and bell peppers.");
        Pizza pizza7=new Pizza("Hyphy Pizza","26 lej","Jalapenos, pepperoni, olives, and pineapples.");
        Pizza pizza8=new Pizza("Magic Mushroom","23","Portobello, crimini, shiitake, white button, and olives.");
        Pizza pizza9=new Pizza("Garlic Chicken","24 lej","Grilled chicken, red pepper, basil, and garlic.");
        Pizza pizza10=new Pizza("Amici's Combo Pizza","18 lej","Pepperoni, meatball, bacon, sautéed mushrooms, and black olives.");
        Pizza pizza11=new Pizza("Spicy Pepper Chicken Pizza","20 lej","Sliced chicken breast, roasted red peppers, caramelized onions, cilantro, oregano, and hot red pepper flakes. No tomato sauce.");
        Pizza pizza12=new Pizza("Trentino Pizza","25 lej","Mozzarella, parmesan, crumbled feta, baby spinach, red onions, pancetta, herbs, and meyer lemon olive oil. No tomato sauce.");

        databaseUsers=FirebaseDatabase.getInstance().getReference().child("pizzas");

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
                    Pizza pizza = new Pizza(name,price,description);
                    pizzaList.add(pizza);



                }
                Log.d("FFFFFFF", String.valueOf(pizzaList));
                recyclerView=findViewById(R.id.rvpizza);
                recyclerView.setLayoutManager(new LinearLayoutManager(PizzaActivity.this));
                adapter=new MyPizzaRecyclerViewAdapter(getBaseContext(),pizzaList);
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
