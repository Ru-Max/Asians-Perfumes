package com.example.asianscosmetics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> Products;

    public ProductAdapter(ArrayList<Product> products) {
        Products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        return new ProductAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(Products.get(position).image);

        holder.price.setText(Products.get(position).Price);

    }

    @Override
    public int getItemCount() {
        return Products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_item);
            price = itemView.findViewById(R.id.item_price);


            itemView.setTag(this);
            //itmView.setOnClickListener(listener);e
        }
    }
}
