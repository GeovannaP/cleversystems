package com.example.aplicativo.Activity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.aplicativo.DataBase.DadosOpenHelper;
import com.example.aplicativo.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class TelaLoginActivity extends AppCompatActivity {
    private TextView text_FormLogin;
    private EditText edit_email, edit_senha;
    private Button bt_entrar;
    private ConstraintLayout layoutContentMain;

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;

    String[] mensagens = {"Preencha todos os campos obrigatórios", "Login efetuado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);


        IniciarComponentes();

        text_FormLogin.setOnClickListener(v -> {


            Intent intent = new Intent(TelaLoginActivity.this, TelaCadastroActivity.class);
            startActivity(intent);

            layoutContentMain = (ConstraintLayout) findViewById(R.id.layoutContentMain);

            criarConexao();

        });


        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();


                if (email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();


                } else {
                    Intent it = new Intent(TelaLoginActivity.this, MainActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });


    }

    private void getSupportActionBar(boolean b) {
    }

    private void IniciarComponentes() {
        text_FormLogin = findViewById(R.id.text_tela_cadastro);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_entrar = findViewById(R.id.bt_entrar);

    }

    private void criarConexao(){
        try{
            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(layoutContentMain, R.string.menssage_conexao_criada_com_sucesso, Snackbar.LENGTH_LONG)
                    .setAction(R.string.actions_ok, null).show();

    }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle (getString(R.string.title_erro));
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.actions_ok, null);
            dlg.show();


        }
    }
}