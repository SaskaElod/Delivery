package com.example.szoftverprojekt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.szoftverprojekt.Object.Product;
import com.example.szoftverprojekt.R;
import com.example.szoftverprojekt.Object.ResultSingleton;

import java.util.ArrayList;

public class ResultRecyclerViewAdapter extends RecyclerView.Adapter<ResultRecyclerViewAdapter.ViewHolder> {
    Context context;
    private ArrayList<Product> mData;
    private LayoutInflater mInflater;
    TextView name, price;
    Button resultbutton;
    private ResultSingleton result = ResultSingleton.getResult();

    public ResultRecyclerViewAdapter(Context context, ArrayList<Product> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ResultRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.resultonerow, parent, false);
        return new ResultRecyclerViewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ResultRecyclerViewAdapter.ViewHolder holder, int position) {
        //db=new DatabaseFull(getCon);
        //holder.myTextView.setText(animal);
        Product one = mData.get(position);
        holder.name.setText(one.getName());
        holder.price.setText(one.getPrice());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        Button removebutton;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.resultname);
            price = itemView.findViewById(R.id.resultprice);
            removebutton = itemView.findViewById(R.id.removebutton);
            removebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickButton(v);
                }
            });
            //itemView.setOnClickListener(this);
        }

        public void onClickButton(View view) {
            int position = getLayoutPosition();
            Product product = mData.get(position);
            result.removeresult(product);
            notifyDataSetChanged();
        }
    }
}
