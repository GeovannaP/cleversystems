package com.example.aplicativo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.aplicativo.Activity.MainActivity;
import com.example.aplicativo.Activity.TelaCadastroActivity;
import com.example.aplicativo.Adaptor.PlaceYourOrderAdapter;
import com.example.aplicativo.Model.CleverModel;
import com.example.aplicativo.Model.Menu;
import com.google.android.material.snackbar.Snackbar;

public class PlaceYourOrderActivity extends AppCompatActivity {

    private EditText inputName, inputAddress, inputCity, inputZip,inputCardNumber, inputCardExpiry, inputCardPin ;
    private RecyclerView cartItemsRecyclerView;
    private TextView tvSubtotalAmount, tvCleverChargeAmount, tvCleverCharge, tvTotalAmount, buttonPlaceYourOrder;
    private boolean isCleverOn;
    private PlaceYourOrderAdapter placeYourOrderAdapter;
    private boolean cleverModels;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_your_order);



        inputName = findViewById(R.id.inputName);
        inputAddress = findViewById(R.id.inputAddress);
        inputCity = findViewById(R.id.inputCity);
        inputZip = findViewById(R.id.inputZip);
        inputCardNumber = findViewById(R.id.inputCardNumber);
        inputCardExpiry = findViewById(R.id.inputCardExpiry);
        inputCardPin = findViewById(R.id.inputCardPin);
        tvCleverChargeAmount = findViewById(R.id.tvCleverChargeAmount);
        buttonPlaceYourOrder = findViewById(R.id.buttonPlaceYourOrder);




        String[] mensagens = {"Preencha todos os campos obrigatórios", "Cadastro realizado com sucesso"};


        buttonPlaceYourOrder = findViewById(R.id.buttonPlaceYourOrder);

        buttonPlaceYourOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String card = inputCardNumber.getText().toString();
                String expiry = inputCardExpiry.getText().toString();
                String Pin = inputCardPin.getText().toString();
                String Adress = inputAddress.getText().toString();
                String City = inputCity.getText().toString();
                String Zip = inputZip.getText().toString();

                if (name.isEmpty() || card.isEmpty() || expiry.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }

                else{

                    Intent it = new Intent (PlaceYourOrderActivity.this, OrderSucceessActivity.class);
                    startActivity(it);
                    finish();
                }

            }


        });

    }

    private void onPlaceOrderButtonClick(CleverModel  CleverModel) {
        if(TextUtils.isEmpty(inputName.getText().toString())) {
            inputName.setError("Please enter name ");
            return;
        } else if(isCleverOn && TextUtils.isEmpty(inputAddress.getText().toString())) {
            inputAddress.setError("Please enter address ");
            return;
        }else if(isCleverOn && TextUtils.isEmpty(inputCity.getText().toString())) {
            inputCity.setError("Please enter city ");
            return;
        }else if(isCleverOn && TextUtils.isEmpty(inputZip.getText().toString())) {
            inputZip.setError("Please enter zip ");
            return;
        }else if( TextUtils.isEmpty(inputCardNumber.getText().toString())) {
            inputCardNumber.setError("Please enter card number ");
            return;
        }else if( TextUtils.isEmpty(inputCardExpiry.getText().toString())) {
            inputCardExpiry.setError("Please enter card expiry ");
            return;
        }else if( TextUtils.isEmpty(inputCardPin.getText().toString())) {
            inputCardPin.setError("Please enter card pin/cvv ");
            return;
        }

        Intent i = new Intent(PlaceYourOrderActivity.this, OrderSucceessActivity.class);
        i.putExtra("CleverModel", CleverModel);
        startActivityForResult (i, 1000);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}
