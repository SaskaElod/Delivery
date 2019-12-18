package com.example.szoftverprojekt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.szoftverprojekt.Object.Product;
import com.example.szoftverprojekt.Interface.ProductInterface;
import com.example.szoftverprojekt.R;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> mData;
    private LayoutInflater mInflater;
    private ProductInterface mClickListener;
    private String url;
    private String productname, productprice;
    private Product product;


    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, ArrayList<Product> data, String url, ProductInterface mClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.url = url;
        this.mClickListener = mClickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.productonerow, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product one = mData.get(position);
        Glide.with(context).load(url).into(holder.picture);
        holder.name.setText(one.getName());
        holder.description.setText(one.getDescription());
        holder.price.setText(one.getPrice());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView name, description, price;
        Button addbutton;

        ViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.pizzaimage);
            name = itemView.findViewById(R.id.pizzaname);
            description = itemView.findViewById(R.id.pizzadescription);
            price = itemView.findViewById(R.id.pizzaprice);
            addbutton = itemView.findViewById(R.id.addbutton);
            price=itemView.findViewById(R.id.pizzaprice);
            addbutton=itemView.findViewById(R.id.addbutton);
            addbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickButton(v);
                }
            });
        }

        public void onClickButton(View view) {
            int position=getLayoutPosition();
            product=mData.get(position);
            productname=product.getName();
            productprice=product.getPrice();
            mClickListener.onItemClick(productname,productprice);
            Toast.makeText(context, "Food Added", Toast.LENGTH_SHORT).show();
        }
    }


}
