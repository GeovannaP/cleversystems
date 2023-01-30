package com.example.aplicativo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicativo.Adaptor.CategoryAdaptor;
import com.example.aplicativo.Adaptor.PoplurarAdaptor;
import com.example.aplicativo.Domain.CategoryDomain;
import com.example.aplicativo.Domain.CleverDomain;
import com.example.aplicativo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private RecyclerView.Adapter adapter;
    private PoplurarAdaptor adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }

    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();

        category.add(new CategoryDomain("Softwares", "plus"));
        category.add(new CategoryDomain("Hardwares", "hard"));
        category.add(new CategoryDomain("Azure", "azure"));
        category.add(new CategoryDomain("Informática", "info"));
        category.add(new CategoryDomain("CSP", "microsoft"));


        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);


    }
    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<CleverDomain> cleverList=new ArrayList<>();

        cleverList.add(new CleverDomain("Visual Studio Professional","visual","O Visual Studio permite que você conclua todo o ciclo de desenvolvimento em um único lugar. Por exemplo, você pode editar, depurar, testar, controlar a versão e implantar na nuvem.",750.00,80));
        cleverList.add(new CleverDomain("SQL Server 2019 Standard","sql","O SQL Server 2019 pode gerenciar quaisquer dados, em qualquer lugar e a qualquer hora, sendo eles estruturados, semi-estruturados e não estruturados, como imagens.",650.76,00));
        cleverList.add(new CleverDomain("Intel Xeon Platinum 9200", "processador","CPUs normalmente oferecem mais núcleos e threads do que os processadores que equipam PCs convencionais, mas as velocidades de clock são próximas dos processadores Core i7 e i9.",1.987));
        cleverList.add(new CleverDomain("Azure Active Directory", "azure1","Suporte à administração avançada, como grupos dinâmicos, gerenciamento de grupos de autoatendimento, Microsoft Identity Manager e recursos de write-back de nuvem, que permitem a redefinição de senha por autoatendimento para os usuários locais.",589.55));
        cleverList.add(new CleverDomain("Razer Kaira HyperSpeed", "fone", "Headset gamer sem fio que garante imersão de ponta e uma liberdade sem limites. Com uma tecnologia de áudio wireless inigualável de 2,4 GHz específica para games", 432.98));
        cleverList.add(new CleverDomain("Controler & Quick","control","Carregamento magnético seguro - combina perfeitamente com os controles sem fio do Xbox - alimentado por USB - vermelho pulsante (controlador vendido separadamente)", 224.47));
        cleverList.add(new CleverDomain("Cloud Solution Provider", "csp","O Microsoft Cloud Solution Provider Program (CSP) é um programa de licenciamento de produtos e serviços, que possibilita que os provedores de soluções Microsoft ofereçam licenciamentos, serviços e suporte aos seus clientes em um único e prático contrato.", 150.00));

        adapter2=new PoplurarAdaptor(cleverList);
        recyclerViewPopularList.setAdapter(adapter2);

    }

}

