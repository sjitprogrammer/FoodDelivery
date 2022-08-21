package com.aodev.fooddelivery.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aodev.fooddelivery.CartManager;
import com.aodev.fooddelivery.ChangeAmountItemListener;
import com.aodev.fooddelivery.R;

import java.util.ArrayList;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.CardItemViewHolder>{
    private CartManager cartManager;
    ChangeAmountItemListener changeAmountItemListener;
    private ArrayList<FeaturedModel> items;
    int item_index = -1;
    public CardItemAdapter(ArrayList<FeaturedModel> items, Context context,ChangeAmountItemListener changeAmountItemListener){
        this.items = items;
        cartManager = new CartManager(context);
        this.changeAmountItemListener=changeAmountItemListener;
    }
    @NonNull
    @Override
    public CardItemAdapter.CardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        CardItemAdapter.CardItemViewHolder CardItemViewHolder = new CardItemAdapter.CardItemViewHolder(view);
        return CardItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardItemAdapter.CardItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FeaturedModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());
        holder.itemAmount.setText(String.valueOf(currentItem.getAmountIncart()));
        holder.itemPrice.setText(String.valueOf(currentItem.getPrice()));

        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartManager.plusAmount(items, position, () -> {
                    notifyDataSetChanged();
                    changeAmountItemListener.changed();
                });
            }
        });

        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartManager.minusAmount(items, position, () -> {
                    notifyDataSetChanged();
                    changeAmountItemListener.changed();
                });
            }
        });

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartManager.removeItem(items, position, () -> {
                    notifyDataSetChanged();
                    changeAmountItemListener.changed();
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CardItemViewHolder extends RecyclerView.ViewHolder{

        TextView textView,itemAmount,itemPrice;
        ImageView imageView,plusBtn,minusBtn,removeBtn;
        public CardItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cart_item_name);
            imageView = itemView.findViewById(R.id.cart_item_image);
            itemAmount = itemView.findViewById(R.id.cart_item_amount);
            itemPrice = itemView.findViewById(R.id.cart_item_price);
            plusBtn = itemView.findViewById(R.id.item_plus);
            minusBtn = itemView.findViewById(R.id.item_minus);
            removeBtn = itemView.findViewById(R.id.remove_item);
        }
    }
}
