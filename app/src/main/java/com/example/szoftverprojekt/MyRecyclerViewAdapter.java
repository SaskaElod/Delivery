package com.example.szoftverprojekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private String url;


    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, ArrayList<Product> data, String url) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context=context;
        this.url=url;
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
        //db=new DatabaseFull(getCon);
        //holder.myTextView.setText(animal);
        Product one=mData.get(position);
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
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView picture;
        TextView name,description,price;
        Button addbutton;

        ViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.pizzaimage);
            name = itemView.findViewById(R.id.pizzaname);
            description = itemView.findViewById(R.id.pizzadescription);
            price=itemView.findViewById(R.id.pizzaprice);
            addbutton=itemView.findViewById(R.id.addbutton);
            addbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(name.getText().toString(),price.getText().toString());
        }
    }

    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData.get(id);
//    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(String pizzaname,String pizzaprice);
    }
}
