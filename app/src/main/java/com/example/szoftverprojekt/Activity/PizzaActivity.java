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

public class PizzaActivity extends AppCompatActivity {

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
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Product> pizzaList=new ArrayList<>();
    DatabaseReference databaseUsers;
    private String url ="http://www.pizzativoli.ro/images/menu/Chicken-Pizza.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        databaseUsers = FirebaseDatabase.getInstance().getReference("pizzas");
        Product pizza1=new Product("Mexicana","19 lej","Streaky bacon rashers, smoky honey ham, pepperoni, Italian sausage, lightly seasoned beef on rich BBQ sauce on a Personal Pan");
        Product pizza2=new Product("Hawai","22 lej","Smoky honey ham, juicy pineapple & mozzarella");
        Product pizza3=new Product("Magyaros","25 lej","Tender chicken, streaky bacon rashers, diced tomato, onion  & mozzarella finished with smoky BBQ drizzle");
        Product pizza4=new Product("The Carnivore","20 lej","With pepperoni, sausage, salami, mushrooms, onions, and green peppers.");
        Product pizza5=new Product("Susage Supreme","22 lej","Cajun sausage, red peppers, onions, and mushrooms.");
        Product pizza6=new Product("Chipotle Chicken","25 lej","Grilled chicken in chipotle marinade with sausage, jalapenos, onions, mushrooms, and bell peppers.");
        Product pizza7=new Product("Hyphy Pizza","26 lej","Jalapenos, pepperoni, olives, and pineapples.");
        Product pizza8=new Product("Magic Mushroom","23","Portobello, crimini, shiitake, white button, and olives.");
        Product pizza9=new Product("Garlic Chicken","24 lej","Grilled chicken, red pepper, basil, and garlic.");
        Product pizza10=new Product("Amici's Combo Pizza","18 lej","Pepperoni, meatball, bacon, sautéed mushrooms, and black olives.");
        Product pizza11=new Product("Spicy Pepper Chicken Pizza","20 lej","Sliced chicken breast, roasted red peppers, caramelized onions, cilantro, oregano, and hot red pepper flakes. No tomato sauce.");
        Product pizza12=new Product("Trentino Pizza","25 lej","Mozzarella, parmesan, crumbled feta, baby spinach, red onions, pancetta, herbs, and meyer lemon olive oil. No tomato sauce.");
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
                    Product burger = new Product(name,price,description);
                    pizzaList.add(burger);



                }
                Log.d("FFFFFFF", String.valueOf(pizzaList));
                recyclerView=findViewById(R.id.rvpizza);
                recyclerView.setLayoutManager(new LinearLayoutManager(PizzaActivity.this));
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
