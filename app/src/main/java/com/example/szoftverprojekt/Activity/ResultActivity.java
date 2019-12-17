package com.example.szoftverprojekt.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.szoftverprojekt.Adapter.ResultRecyclerViewAdapter;
import com.example.szoftverprojekt.Object.Product;
import com.example.szoftverprojekt.R;
import com.example.szoftverprojekt.Object.ResultSingleton;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ResultRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ResultSingleton result=ResultSingleton.getResult();
    Button order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView=findViewById(R.id.rvresult);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Product> resultlist= result.getresult();
        adapter=new ResultRecyclerViewAdapter(getBaseContext(),resultlist);
        recyclerView.setAdapter(adapter);
        
    }
}
