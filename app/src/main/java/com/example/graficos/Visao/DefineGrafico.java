package com.example.graficos.Visao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.graficos.Dominio.Estatistica;
import com.example.graficos.Persistencia.Sessoes;
import com.example.graficos.R;

import java.util.ArrayList;


public class DefineGrafico extends AppCompatActivity {
    Button graficoLinhaBtn, graficoBarraBtn, MediaBtn, MedianaBtn;
    TextView textView;
    Estatistica t;

    Context contextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_grafico);

        inicializar();
        listener();
    }

    private void listener() {
        graficoLinhaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contextActivity, GraficoLinha.class);
                intent.putExtra("ArraysSessoes", getSessoes());
                startActivity(intent);
            }
        });

        graficoBarraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contextActivity, GraficoBarra.class);
                intent.putExtra("ArraysSessoes", getSessoes());
                startActivity(intent);
            }
        });
        MediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contextActivity, Media.class);
                intent.putExtra("ArraysSessoes", getSessoes());
                startActivity(intent);
            }
        });
        MedianaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contextActivity, Mediana.class);
                intent.putExtra("ArraysSessoes", getSessoes());
                startActivity(intent);
            }
        });
    }

    private ArrayList<Sessoes> getSessoes(){
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        ArrayList<Sessoes> sessoes = (ArrayList<Sessoes>) b.getSerializable("ArraysSessoes");
        return sessoes;
    }

    private void inicializar() {
        graficoLinhaBtn = findViewById(R.id.graficoLinhaBtn);
        graficoBarraBtn = findViewById(R.id.graficoBarraBtn);
        MediaBtn = findViewById(R.id.MediaBtn);
        MedianaBtn = findViewById(R.id.MedianaBtn);

        contextActivity = this;
    }

}
