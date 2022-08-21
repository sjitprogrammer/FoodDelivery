package com.aodev.fooddelivery;

import android.content.Context;
import android.widget.Toast;

import com.aodev.fooddelivery.data.FeaturedModel;

import java.util.ArrayList;

public class CartManager {

    private Context context;
    private CartDB cartDB;

    public CartManager(Context context){
        this.context = context;
        this.cartDB = new CartDB(context);
    }

    public void insertFood(FeaturedModel item){
        ArrayList<FeaturedModel> list = getListCart();
        boolean exitAlready = false;
        int n = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getText().equals(item.getText())){
                exitAlready = true;
                n=i;
                break;
            }
        }

        if(exitAlready){
            list.get(n).setAmountIncart(item.getAmountIncart());
        }else{
            list.add(item);
        }
        
        cartDB.putListObject("CardList",list);
        Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FeaturedModel> getListCart() {
        return cartDB.getListObject("CardList");
    }

    public void minusAmount(ArrayList<FeaturedModel> list,int position,ChangeAmountItemListener changeAmountItemListener){
        if(list.get(position).getAmountIncart()==1){
            list.remove(position);
        }else{
            list.get(position).setAmountIncart(list.get(position).getAmountIncart()-1);
        }
        cartDB.putListObject("CardList",list);
        changeAmountItemListener.changed();
    }
    public void plusAmount(ArrayList<FeaturedModel> list,int position,ChangeAmountItemListener changeAmountItemListener){
        list.get(position).setAmountIncart(list.get(position).getAmountIncart()+1);
        cartDB.putListObject("CardList",list);
        changeAmountItemListener.changed();
    }
    public double getFeePrice(){
        ArrayList<FeaturedModel> listItem = getListCart();
        double fee=0;
        for (int i = 0; i < listItem.size(); i++) {
            fee=fee+(listItem.get(i).getPrice()*listItem.get(i).getAmountIncart());
        }
        return fee;
    }
    public double getFeedelivery(){
        ArrayList<FeaturedModel> listItem = getListCart();
        double fee=0;
        for (int i = 0; i < listItem.size(); i++) {
            fee=fee+(listItem.get(i).getDelivery()*listItem.get(i).getAmountIncart());
        }
        return fee;
    }

    public void removeItem(ArrayList<FeaturedModel> list,int position,ChangeAmountItemListener changeAmountItemListener){
        list.remove(position);
        cartDB.putListObject("CardList",list);
        changeAmountItemListener.changed();
    }
    public int getCountItems(){
        ArrayList<FeaturedModel> listItem = getListCart();
        int count=0;
        for (int i = 0; i < listItem.size(); i++) {
            count=count+listItem.get(i).getAmountIncart();
        }
        return count;
    }
}
