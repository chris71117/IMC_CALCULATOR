package com.example.ifpr.meuaplicativo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class PalpiteAcitivity extends AppCompatActivity {

    private int escolha;

    private String nome;
    private float altura;
    private float peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palpite);

        Bundle bundle = getIntent().getExtras();
        nome = bundle.getString("nome");
        altura = bundle.getFloat("altura");
        peso = bundle.getFloat("peso");

        ListView lista = (ListView) findViewById(R.id.lista_palpite);
        ArrayList<String> opcoes = new ArrayList<String>();
        opcoes.add("Abaixo do peso");
        opcoes.add("Peso normal");
        opcoes.add("Sobrepeso");
        opcoes.add("Obesidade grau 1");
        opcoes.add("Obesidade grau 2");
        opcoes.add("Obesidade grau 3");
        opcoes.add("Obesidade grau Francisco");

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                escolha = position;
                prosseguir();
            }
        });
    }

    private void prosseguir(){
        Intent intent = new Intent(this, ResultadoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("palpite", escolha);
        bundle.putString("nome", nome);
        bundle.putFloat("peso", peso);
        bundle.putFloat("altura", altura);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
