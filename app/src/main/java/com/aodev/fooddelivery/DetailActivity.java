package com.aodev.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aodev.fooddelivery.data.FeaturedModel;

public class DetailActivity extends AppCompatActivity {

    private LinearLayout addToCartBtn;
    private TextView nameTxt,desCriptionTxt,amountTxt,priceTxt;
    private ImageView foodImg,plusBtn,minusBtn,backBtn;
    private FeaturedModel item;
    private RadioButton radioButtonPepper,radioButtonSpinach,radioButtonMasroom;
    private boolean pepBoolean,spinBoolean,masBoolean = false;
    int amount = 1;

    private CartManager cartManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        cartManager = new CartManager(this);
        initView();
        getBundleItem();
    }

    private void getBundleItem() {
        item = (FeaturedModel) getIntent().getSerializableExtra("item_selected");
        foodImg.setImageResource(item.getImage());
        nameTxt.setText(item.getText());
        desCriptionTxt.setText(item.getDescription());
        priceTxt.setText(String.valueOf(item.getPrice()));
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.add_to_cart);
        nameTxt = findViewById(R.id.tv_food_name);
        desCriptionTxt = findViewById(R.id.tv_description);
        amountTxt = findViewById(R.id.item_amount);
        priceTxt = findViewById(R.id.tv_price);
        foodImg = findViewById(R.id.image_food);
        plusBtn = findViewById(R.id.plus);
        minusBtn = findViewById(R.id.minus);
        backBtn = findViewById(R.id.detail_back_btn);
        amountTxt.setText(String.valueOf(amount));
        radioButtonPepper = findViewById(R.id.radioButton_Pepper);
        radioButtonSpinach = findViewById(R.id.radioButton_Spinach);
        radioButtonMasroom = findViewById(R.id.radioButton_Masroom);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = amount + 1;
                amountTxt.setText(String.valueOf(amount));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amount>1) {
                    amount = amount - 1;
                }
                amountTxt.setText(String.valueOf(amount));
            }
        });

        radioButtonPepper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pepBoolean=!pepBoolean;
                radioButtonPepper.setChecked(pepBoolean);
            }
        });

        radioButtonSpinach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinBoolean=!spinBoolean;
                radioButtonSpinach.setChecked(spinBoolean);
            }
        });

        radioButtonMasroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masBoolean=!masBoolean;
                radioButtonMasroom.setChecked(masBoolean);
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double total_extra = 0;
                double item_price = item.getPrice();
                double total_price = 0;
                if(pepBoolean){
                    total_extra += 2.30;
                }
                if(spinBoolean){
                    total_extra += 2.30;
                }
                if(masBoolean){
                    total_extra += 2.30;
                }
                total_price = item_price * amount;
                item.setAmountIncart(amount);
                cartManager.insertFood(item);
//                Toast.makeText(DetailActivity.this, "total extra :"+total_extra+"/total price:"+total_price, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplication(), CartActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }
}