package com.example.aplicativo.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.aplicativo.Domain.CleverDomain;
import com.example.aplicativo.Interface.ChangeNumberItemsListenner;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;


    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertClever(CleverDomain item) {
        ArrayList<CleverDomain> listClever = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listClever.size(); i++) {
            if (listClever.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listClever.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listClever.add(item);
        }
        tinyDB.putListObject("CartList", listClever);
        Toast.makeText(context, "Adicionado ao seu carrinho", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<CleverDomain> getListCart(){

        return tinyDB.getListObject("CartList");
    }
    public void plusNumberClever(ArrayList<CleverDomain>listClever, int position, ChangeNumberItemsListenner changeNumberItemsListenner){
        listClever.get(position).setNumberInCart(listClever.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList", listClever);
        changeNumberItemsListenner.changed();
    }
    public void minusNumberClever(ArrayList<CleverDomain>listClever,int position, ChangeNumberItemsListenner changeNumberItemsListenner){
        if (listClever.get(position).getNumberInCart()==1) {
            if(listClever.get(position).getNumberInCart() == 1) {
                listClever.remove(position);
            }
        }else{
            if(listClever.get(position).getNumberInCart() == 1) {
                listClever.remove(position);
            }
            else {
                listClever.get(position).setNumberInCart(listClever.get(position).getNumberInCart() - 1);
            }
        }
        tinyDB.putListObject("CartList",listClever);
        changeNumberItemsListenner.changed();

    }


    public Double getTotalFee(){
        ArrayList<CleverDomain> listClever=getListCart();
        double fee =0;
        for (int i = 0; i<listClever.size(); i++) {
            fee=fee+(listClever.get(i).getFee()*listClever.get(i).getNumberInCart());
        }
        return fee;
    }
}


