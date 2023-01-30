package com.example.aplicativo.Activity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.aplicativo.DataBase.DadosOpenHelper;
import com.example.aplicativo.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class TelaCadastroActivity extends AppCompatActivity {
    private ConstraintLayout starBtn;
    private EditText edit_nome, edit_email, edit_senha;
    private Button bt_cadastrar;
    private ConstraintLayout layoutContentCadastro;

    private SQLiteDatabase conexao;
    DadosOpenHelper dadosOpenHelper;


    String[] mensagens = {"Preencha todos os campos obrigatórios", "Cadastro realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        IniciarComponentes();

        bt_cadastrar.setOnClickListener(v -> {

            Intent intent = new Intent (TelaCadastroActivity.this, MainActivity.class);
            startActivity(intent);

        });
        final ActionBar ab = getSupportActionBar();

        bt_cadastrar = findViewById(R.id.bt_cadastrar);


        bt_cadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }

            else{

                Intent it = new Intent (TelaCadastroActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }




            }

        });


        layoutContentCadastro = (ConstraintLayout) findViewById(R.id.layoutContentCadastro);

        dadosOpenHelper = new DadosOpenHelper(this);

        //criarConexao();

    }
        private void criarConexao(){

            try {
                dadosOpenHelper = new DadosOpenHelper(this);

                conexao = dadosOpenHelper.getWritableDatabase();
                Snackbar.make(layoutContentCadastro, "Conexão criada com sucesso!", Snackbar.LENGTH_SHORT)
                        .setAction("OK", null).show();
            } catch (SQLException ex) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("ERRO");
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton("OK", null);
                dlg.show();
                dadosOpenHelper = new DadosOpenHelper(this);

                conexao = dadosOpenHelper.getWritableDatabase();
                Snackbar.make(layoutContentCadastro, "Conexão criada com sucesso!", Snackbar.LENGTH_SHORT)
                        .setAction("OK", null).show();
            }










            }


            private void IniciarComponentes() {
                edit_nome = findViewById(R.id.edit_nome);
                edit_email = findViewById(R.id.edit_email);
                edit_senha = findViewById(R.id.edit_senha);
                bt_cadastrar = findViewById(R.id.bt_cadastrar);
            }




        }



    //private void criarConexao() {
   // }
//}