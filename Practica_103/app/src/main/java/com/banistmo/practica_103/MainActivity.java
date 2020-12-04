package com.banistmo.practica_103;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void visualizarElemento(View view) {
        Intent i = new Intent(this, MostrarActivity.class);
        startActivity(i);
    }

    public void guardarElemento(View view) {
        Intent i = new Intent(this, CapturarActivity.class);
        startActivity(i);
    }
}