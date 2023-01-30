package com.example.aplicativo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aplicativo.R;

public class IntroActivity extends AppCompatActivity {
//private ConstraintLayout starBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button starBtn = (Button) findViewById(R.id.id_button);
        starBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(IntroActivity.this, MainActivity.class);

                startActivity(it);
            }
        });
    }
}
