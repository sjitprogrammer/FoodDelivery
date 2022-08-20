package com.aodev.fooddelivery.data;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aodev.fooddelivery.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder>{

    private ArrayList<FeaturedModel> items;
    public FeaturedAdapter(ArrayList<FeaturedModel> items){
        this.items = items;
    }
    @NonNull
    @Override
    public FeaturedAdapter.FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_item,parent,false);
        FeaturedAdapter.FeaturedViewHolder categoriesViewHolder = new FeaturedAdapter.FeaturedViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.star.setText(String. valueOf(currentItem.getStar()));
        holder.name.setText(currentItem.getText());
        holder.time.setText(currentItem.getTime()+" mins");
        holder.delivery.setText(currentItem.getDelivery()+" delivery");

        String[] tags = currentItem.getTags();
        holder.linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < tags.length ; i++) {
            TextView textView = new TextView(holder.linearLayout.getContext());
            Log.e("tags",tags[i]);
            textView.setText(tags[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,10,0);
            textView.setLayoutParams(params);
            textView.setPadding(7,5,7,5);
            textView.setTextSize(12);
            textView.setTextColor(Color.parseColor("#5B5B5E"));
            textView.setBackgroundResource(R.drawable.custom_bg_text);
            holder.linearLayout.addView(textView);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView time;
        TextView star;
        TextView delivery;
        ImageView imageView;
        LinearLayout linearLayout;
        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.featured_imageView);
            name = itemView.findViewById(R.id.featured_name);
            time = itemView.findViewById(R.id.featured_time);
            delivery = itemView.findViewById(R.id.featured_delivery);
            star = itemView.findViewById(R.id.featured_star);
            linearLayout = itemView.findViewById(R.id.lin_tags);
        }
    }
}
