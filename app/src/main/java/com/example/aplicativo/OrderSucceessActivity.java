package com.example.aplicativo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aplicativo.Activity.MainActivity;
import com.example.aplicativo.Model.CleverModel;

public class OrderSucceessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_succeess);


        CleverModel restaurantModel = getIntent().getParcelableExtra("CleverModel");
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle(restaurantModel.getName());
        //actionBar.setSubtitle(restaurantModel.getAddress());
        //actionBar.setDisplayHomeAsUpEnabled(false);


        TextView buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (OrderSucceessActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });
    }
}