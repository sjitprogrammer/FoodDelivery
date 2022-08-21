package com.aodev.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aodev.fooddelivery.data.CardItemAdapter;

public class CartActivity extends AppCompatActivity {
    private ImageView backBtn;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private CartManager cartManager;
    double tax = 0;
    private TextView subtotalAmount,taxAmount,deliveryAmount,totalAmount,sumItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartManager = new CartManager(this);
        initView();
        initRecyclerView();
        callCartPrice();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CardItemAdapter(cartManager.getListCart(),this,new ChangeAmountItemListener(){
            @Override
            public void changed() {
                callCartPrice();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void callCartPrice() {
        double percent=0.02;
        double fee,total = 0;
        tax = Math.round((cartManager.getFeePrice()*percent)*100.0)/100.0;
        fee = Math.round((cartManager.getFeedelivery()+tax)*100.0)/100.0;
        total = Math.round((cartManager.getFeePrice())*100.0)/100.0;
        subtotalAmount.setText("$"+total);
        taxAmount.setText("$"+tax);
        deliveryAmount.setText("$"+fee);
        double sumTotal = total+tax+fee;
        String sum = String.format("%.02f", sumTotal);
        totalAmount.setText("$"+sum);

    }

    private void initView() {
        subtotalAmount = findViewById(R.id.subtotal_amount);
        taxAmount = findViewById(R.id.tax_amount);
        deliveryAmount = findViewById(R.id.delivery_amount);
        totalAmount = findViewById(R.id.total_amount);
        sumItem = findViewById(R.id.sum);
        backBtn = findViewById(R.id.back_btn);
        recyclerView = findViewById(R.id.recyc_cart_item);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}