package com.aodev.fooddelivery.data;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aodev.fooddelivery.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>{

    private ArrayList<CategoriesModel> items;
    int item_index = -1;
    public CategoriesAdapter(ArrayList<CategoriesModel> items){
        this.items = items;
    }
    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item,parent,false);
        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoriesModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_index = position;
                notifyDataSetChanged();
            }
        });
        if(item_index==-1 && position==0){
            holder.linearLayout.setBackgroundResource(R.drawable.categories_selected_bg);
            holder.textView.setTextColor(Color.parseColor("#ffffff"));
        }else if(item_index == position){
            holder.linearLayout.setBackgroundResource(R.drawable.categories_selected_bg);
            holder.textView.setTextColor(Color.parseColor("#ffffff"));
        }else{
            holder.linearLayout.setBackgroundResource(R.drawable.categories_bg);
            holder.textView.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;
        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_Categories);
            imageView = itemView.findViewById(R.id.img_Categories);
            linearLayout = itemView.findViewById(R.id.lin_categories_item);
        }
    }

}
