package com.example.asianscosmetics;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

public class Cart_Activity<CartActivity> extends AppCompatActivity {


        RecyclerView recycler_itemlist;
        public static TextView tv_total;
        CartListAdapter cartListAdapter = new CartListAdapter(CartActivity.this,ItemListAdapter.selecteditems);
        public static int total=0;
        String jsonCartList;

    public Cart_Activity() {
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cart);

            //Set back button to activity
            android.support.v7.app.ActionBar actionBar=getSupportActionBar();
            if(actionBar!=null){
                actionBar.setHomeButtonEnabled(true);
                actionBar.setTitle("Cart");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            tv_total =(TextView) findViewById(R.id.tv_total);

            recycler_itemlist =(RecyclerView) findViewById(R.id.recycler_cart);
            recycler_itemlist.setHasFixedSize(true);
            recycler_itemlist.setRecycledViewPool(new RecyclerView.RecycledViewPool());
            recycler_itemlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
            recycler_itemlist.getRecycledViewPool().setMaxRecycledViews(0, 0);

            recycler_itemlist.setAdapter(cartListAdapter);

            getIntentData();

            calculateTotal();
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    // app icon in action bar clicked; goto parent activity.
                    this.finish();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        private void getIntentData(){
            if(getIntent()!=null && getIntent().getExtras()!=null){
                // Get the Required Parameters for sending Order to server.
            }
        }

        public static void calculateTotal(){
            int i=0;
            total=0;
            while(i<ItemListAdapter.selecteditems.size()){
                total=total + ( Integer.valueOf(ItemListAdapter.selecteditems.get(i).getRate()) * Integer.valueOf(ItemListAdapter.selecteditems.get(i).getQuantity()) );
                i++;
            }
            tv_total.setText(""+total);
        }

        public void insertOrder(View view){

            if(total>0){

                Gson gson = new Gson();
                jsonCartList = gson.toJson(ItemListAdapter.selecteditems);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                placeOrderRequest();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setMessage("Do you want to place Order ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }else{
                Toast.makeText(CartActivity.this,
                        "No items in Cart !",
                        Toast.LENGTH_LONG).show();
            }


        }


        private void placeOrderRequest(){
            //Send Request to Server with required Parameters
    /*
   jsonCartList - Consists of Objects of all product selected.
    */

        }

    }
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_);

        button = findViewById(R.id.payment_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cart_Activity.this, Payment_Activity.class));
            }
        });
    }
}