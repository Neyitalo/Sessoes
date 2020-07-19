package com.example.graficos.Visao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.graficos.Dominio.Graficos;
import com.example.graficos.Persistencia.Sessoes;
import com.example.graficos.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import java.util.ArrayList;

public class GraficoLinha extends AppCompatActivity {

    LineChart graficoLinha;
    Graficos g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();
        layoutGraficoLinha();

                        g.getGraficoLinha(graficoLinha, getSessoes());

                    }

    private void layoutGraficoLinha() {
        Legend legend = graficoLinha.getLegend();
        legend.setTextSize(15f);

        XAxis xAxis = graficoLinha.getXAxis(); // retorna o eixo 'x' do grafico graficoLinha.get...
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);// BOTTOM -> eixo X na parte inferior do gráfico
        xAxis.setAxisMinimum(0f);// valor inicial mínimo - eixo 'x'
        //xAxis.setAxisMaximum(30f);// valor inicial máximo - eixo 'x'
        xAxis.setGranularity(1f);// intervalo mínimo entre os valores - eixo 'x'

        YAxis yAxis = graficoLinha.getAxisLeft();// retorna o eixo Esquerdo 'y' do grafico graficoLinha.get...
        YAxis yAxis1 = graficoLinha.getAxisRight();
        yAxis.setGranularity(1f);
        yAxis.setAxisMinimum(0);
        yAxis1.setEnabled(false);// Eixo da Direita não é exibido se 'false'

        graficoLinha.setDragEnabled(true);
        graficoLinha.setScaleEnabled(false);
        graficoLinha.setPinchZoom(true);// Se 'false' -> ativa zoom para eixo 'x' e 'y' SEPARADAMENTE
        graficoLinha.setScaleEnabled(true);// Se 'true' ativa as escalas dos eixos
        //graficoLinha.setVisibleXRangeMaximum(10f);// valor inicial mínimo - eixo 'x' - Visível
    }

    private ArrayList<Sessoes> getSessoes(){
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        ArrayList<Sessoes> sessoes = (ArrayList<Sessoes>) b.getSerializable("ArraysSessoes");
        return sessoes;
    }

    private void inicializar() {
        g = new Graficos();
        graficoLinha = (LineChart) findViewById(R.id.graficoLinha);
    }

}

