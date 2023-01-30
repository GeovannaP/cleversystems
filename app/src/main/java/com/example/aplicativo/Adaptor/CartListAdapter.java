package com.example.aplicativo.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplicativo.Activity.CartListActivity;
import com.example.aplicativo.Domain.CleverDomain;
import com.example.aplicativo.Helper.ManagementCart;
import com.example.aplicativo.Interface.ChangeNumberItemsListenner;
import com.example.aplicativo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<CleverDomain> cleverDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListenner changeNumberItemsListenner;

    public CartListAdapter(ArrayList<CleverDomain> cleverDomains, Context context, ChangeNumberItemsListenner changeNumberItemsListenner) {
        this.cleverDomains = cleverDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListenner = changeNumberItemsListenner;
    }


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.title.setText(cleverDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(cleverDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((cleverDomains.get(position).getNumberInCart()*cleverDomains.get(position).getFee())*100)/100));
        holder.num.setText(String.valueOf(cleverDomains.get(position).getNumberInCart()));


        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(cleverDomains.get(position).getPic()
                ,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberClever(cleverDomains, position, new ChangeNumberItemsListenner() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListenner.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberClever(cleverDomains, position, new ChangeNumberItemsListenner() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListenner.changed();

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return cleverDomains.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView title,feeEachItem;
        ImageView pic, plusItem,minusItem;
        TextView totalEachItem,num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem= itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCart);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            num=itemView.findViewById(R.id.numberItemTxt);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);
        }
    }
}
