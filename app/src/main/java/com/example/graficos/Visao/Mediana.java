package com.example.graficos.Visao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.graficos.Dominio.Estatistica;
import com.example.graficos.R;

public class Mediana extends AppCompatActivity {

    Estatistica t;
    TextView textViewMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediana);
        textViewMed = findViewById(R.id.textView2);
        t = new Estatistica();

        t.definiMediana(textViewMed);
        //textView.setText(Float.toString(t.getMedia()));

    }
}