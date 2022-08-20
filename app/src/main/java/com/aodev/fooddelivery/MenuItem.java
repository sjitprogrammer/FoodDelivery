package com.aodev.fooddelivery;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItem extends DrawerItem<MenuItem.ViewHolder> {

    private int selectedItemIconTint;
    private int selectedItemTextTint;

    private int normalItemIconTint;
    private int normalItemTextTint;

    private Drawable icon;
    private String title;

    public MenuItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {
        holder.menu_title.setText(title);
        holder.menu_icon.setImageDrawable(icon);

        holder.menu_title.setTextColor(isChecked ? selectedItemTextTint : normalItemTextTint);
        holder.menu_icon.setColorFilter(isChecked ? selectedItemIconTint : normalItemIconTint);
    }

    public MenuItem withSelectedIconTint(int selectedItemIconTint) {
        this.selectedItemIconTint = selectedItemIconTint;
        return this;
    }

    public MenuItem withSelectedTextTint(int selectedItemTextTint) {
        this.selectedItemTextTint = selectedItemTextTint;
        return this;
    }

    public MenuItem withIconTint(int normalItemIconTint) {
        this.normalItemIconTint = normalItemIconTint;
        return this;
    }

    public MenuItem withTextTint(int normalItemTextTint) {
        this.normalItemTextTint = normalItemTextTint;
        return this;
    }

    static class ViewHolder extends DrawerAdapter.ViewHolder {

        private ImageView menu_icon;
        private TextView menu_title;

        public ViewHolder(View itemView) {
            super(itemView);
            menu_icon = itemView.findViewById(R.id.menu_icon);
            menu_title = itemView.findViewById(R.id.menu_title);
        }
    }
}