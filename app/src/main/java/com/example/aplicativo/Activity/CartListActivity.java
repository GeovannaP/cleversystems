package com.example.aplicativo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.le.ScanRecord;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.aplicativo.Adaptor.CartListAdapter;
import com.example.aplicativo.Helper.ManagementCart;
import com.example.aplicativo.Interface.ChangeNumberItemsListenner;
import com.example.aplicativo.Model.CleverModel;
import com.example.aplicativo.PlaceYourOrderActivity;
import com.example.aplicativo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, taxTxt,serviceTxt,totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);


        managementCart=new ManagementCart( this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();


        TextView bt = findViewById(R.id.textView15);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent (CartListActivity.this, PlaceYourOrderActivity.class);
                startActivity(it);
            }
        });

    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,MainActivity.class));
            }
        });
    }
    private void initView() {
        recyclerViewList=findViewById(R.id.recyclerView);
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        serviceTxt=findViewById(R.id.serviceTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListenner() {
            @Override
            public void changed() {
                CalculateCart();

            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);

        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {
        double percentTax=0.02;
        double service = 10;

        tax= Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100;
        double total= Math.round((managementCart.getTotalFee() + tax + service) * 100) / 100;
        double itemTotal= Math.round(managementCart.getTotalFee() * 100) / 100;


        totalFeeTxt.setText("R$" + itemTotal);
        taxTxt.setText("R$" + tax);
        serviceTxt.setText("R$"+service);
        totalTxt.setText("R$"+total);


    }

}
