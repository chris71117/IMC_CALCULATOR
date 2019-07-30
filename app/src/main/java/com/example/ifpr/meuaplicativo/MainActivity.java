package com.example.ifpr.meuaplicativo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnProsseguirMainClick(View v){
        EditText editNome = (EditText) findViewById(R.id.editTextNome);
        EditText editPeso = (EditText) findViewById(R.id.editTextPeso);
        EditText editAltura = (EditText) findViewById(R.id.editTextAltura);

        String pesoS = editPeso.getText().toString();
        String alturaS = editAltura.getText().toString();
        String nome = editNome.getText().toString();


        float peso;
        float altura;
        try {
            peso = Float.parseFloat(pesoS);
            altura = Float.parseFloat(alturaS);
        } catch (NumberFormatException e){
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("nome", nome);
        bundle.putFloat("peso", peso);
        bundle.putFloat("altura", altura);

        Intent intent = new Intent(this, PalpiteAcitivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
