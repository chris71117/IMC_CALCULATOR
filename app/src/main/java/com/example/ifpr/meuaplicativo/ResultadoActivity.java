package com.example.ifpr.meuaplicativo;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    private int escolha;
    private float peso;
    private float altura;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Bundle bundle = getIntent().getExtras();
        escolha = bundle.getInt("palpite");
        peso = bundle.getFloat("peso");
        altura = bundle.getFloat("altura");
        nome = bundle.getString("nome");

        float IMC = peso / (altura * altura);

        String resultado;
        int resposta;
        if(IMC < 18.5){
            resultado = "Abaixo do peso";
            resposta = 0;
        } else if(IMC < 24.9){
            resultado = "Peso normal";
            resposta = 1;
        } else if(IMC < 29.9){
            resultado = "Sobrepeso";
            resposta = 2;
        } else if(IMC < 34.9){
            resultado = "Obesidade grau 1";
            resposta = 3;
        } else if(IMC < 39.9){
            resultado = "Obesidade grau 2";
            resposta = 4;
        } else if(IMC < 1000){
            resultado = "Obesidade grau 3";
            resposta = 5;
        } else {
            resultado = "Você é o Chicão";
            resposta = 6;
        }

        if(escolha == resposta){
            resultado = nome + " você acertou!!\n" + resultado;
        } else {
            resultado = nome + " você errou :/\n" + resultado;
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("my_channel_id", "my_channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nm.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "ID DO CHANNEL");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("RESULTADO");
        builder.setContentText(resultado);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(17, builder.build());

        AlertDialog.Builder abuilder = new AlertDialog.Builder(this);
        abuilder.setTitle("Resultado");
        abuilder.setMessage("Cheque as notificações");

        DialogInterface.OnClickListener ok = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };

        abuilder.setPositiveButton("Ok :)", ok);
        abuilder.create().show();
    }
}
