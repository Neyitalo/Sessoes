package com.example.graficos.Dominio;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.graficos.Persistencia.Sessoes;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Graficos {

    ArrayList<BarEntry> entradas;
    ArrayList<Entry> entradasL;
    float valX[] = new float[100]; // declaração combinada
    float valY[] = new float[100];

    public Graficos() {

    }

    public void getGraficoBarras(final BarChart graficoBarra, final ArrayList<Sessoes> sessoes) {
        entradas = new ArrayList<BarEntry>();

        System.out.println("PASSOU AQUIIIII");
                            /*
                            for(int i = 0; i < sessoes.size(); i++) {

                                System.out.println("i: "+i);
                                System.out.println("GET IDSESSAO: "+sessoes.get(i).getIdSessao());
                                System.out.println("GET PORCENTAGEM: "+sessoes.get(i).getPorcentagem());
                            }*/
        BarData temp = new BarData(addEntradasBar(sessoes));
        temp.setDrawValues(false);
        temp.setValueTextSize(15f);
        temp.setBarWidth(1f);

        graficoBarra.setData(temp);// responsável por plotar os gráficos
        graficoBarra.invalidate(); // refresh
        System.out.println("A THREAD FINALIZOU SUA EXECUÇÃO");
        System.out.println("ENCERRANDO FUNÇÃO getGraficoBarras");
    }


    public void getGraficoLinha(final LineChart graficoLinha, final ArrayList<Sessoes> sessoes){

        entradasL = new ArrayList<Entry>();

                        LineData data = new LineData(addEntradasLin(sessoes)); // Objeto responsável por receber os valores dos dados/ Objeto usado para plotar os gráficos
                        data.setDrawValues(false);
                        data.setValueTextSize(15f);


                        graficoLinha.setData(data);
                        graficoLinha.invalidate();
                        System.out.println("A THREAD FINALIZOU SUA EXECUÇÃO");

    }

    private BarDataSet addEntradasBar(ArrayList<Sessoes> sessoes){
        for(int i = 0; i < sessoes.size(); i++) {
            valX[i] = sessoes.get(i).getIdSessao();
            valY[i] = sessoes.get(i).getPorcentagem();
            //System.out.println(valX[i]+" "+valY[i]);
            entradas.add(new BarEntry(valX[i], valY[i]));
        }

        BarDataSet barDataSet1 = new BarDataSet(entradas, "Bennie");
        barDataSet1.setColor(Color.BLUE);
        barDataSet1.setBarBorderWidth(2f);

        return barDataSet1;
    }
//Classe estatística getMédia - da taxa de acerto
// e getMediana - medianas - da taxa de acerto
    private LineDataSet addEntradasLin(ArrayList<Sessoes> sessoes){
        for (int i = 0; i < sessoes.size(); i++) {
            valX[i] = sessoes.get(i).getIdSessao();
            valY[i] = sessoes.get(i).getPorcentagem();
        }
        ordena(sessoes);
        for (int i = 0; i < sessoes.size(); i++) {
            entradasL.add(new Entry(valX[i], valY[i]));
        }
        LineDataSet linEntradas1 = new LineDataSet(entradasL, "Bennie");
        linEntradas1.setColor(Color.BLUE);
        linEntradas1.setLineWidth(5f);

        return linEntradas1;
    }

    private void ordena(ArrayList<Sessoes> sessoes){
        for (int i = 0; i < sessoes.size(); i++)
        {
            for (int j = 0; j < sessoes.size(); j++)
            {
                if (valX[i] < valX[j])
                {
                    //aqui acontece a troca. O maior vai para a direita e o menor para a esquerda
                    float aux = valX[i];
                    float auxY = valY[i];

                    valX[i] = valX[j];
                    valY[i] = valY[j];

                    valX[j] = aux;
                    valY[j] = auxY;
                }
            }
        }
    }//Termina método
}

