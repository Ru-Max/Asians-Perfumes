package com.example.asianscosmetics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Menu_Activity extends AppCompatActivity {
    private Button button;
    ProductAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Product> Products = new ArrayList<>();
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_);

        recyclerView = findViewById(R.id.item_recycler_view);
        //LoadData();
        getData();

        button = findViewById(R.id.cart_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_Activity.this, Cart_Activity.class));
            }
        });

        generateRecyclerView(Products);

    }

    public void getData() {
        firestore = FirebaseFirestore.getInstance();
        Products.clear();
        firestore.collection("items").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                        Product item = queryDocumentSnapshot.toObject(Product.class);
                        item.setId(queryDocumentSnapshot.getId());
                        Products.add(item);
                    }
                    generateRecyclerView(Products);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    public void LoadData() {
//        //Items data
//        //import as many data of product you want....
//
//        Product product = new Product();
//        //replace the name "item" with product image you have
//        product.image = R.drawable.item;
//        //change price of product as required...
//        product.Price = "$100";
//        Products.add(product);
//
//        product = new Product();
//        product.image = R.drawable.item_1;
//        product.Price = "$80";
//        Products.add(product);
//
//        product = new Product();
//        product.image = R.drawable.item_2;
//        product.Price = "$130";
//        Products.add(product);
//
//        product = new Product();
//        product.image = R.drawable.item_3;
//        product.Price = "$150";
//        Products.add(product);
//
//        product = new Product();
//        product.image = R.drawable.item_4;
//        product.Price = "$50";
//        Products.add(product);
//
//        product = new Product();
//        product.image = R.drawable.item_5;
//        product.Price = "$100";
//        Products.add(product);
//
//        product = new Product();
//        product.image = R.drawable.item_6;
//        product.Price = "$100";
//        Products.add(product);
//
//        product = new Product();
//        product.image = R.drawable.item_7;
//        product.Price = "$100";
//        Products.add(product);
//
//        product = new Product();
//        product.image = R.drawable.item_8;
//        product.Price = "$100";
//        Products.add(product);
//    }

    public void generateRecyclerView(ArrayList<Product> products) {

        adapter = new ProductAdapter(products);
        GridLayoutManager gridLayout = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setAdapter(adapter);
        //adapter.setOnItemClickListner(onItemClick);
    }
}