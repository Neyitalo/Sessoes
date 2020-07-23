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

import java.text.DecimalFormat;
import java.util.ArrayList;


public class DefineGrafico extends AppCompatActivity {
    Button graficoLinhaBtn;
    //Button graficoBarraBtn;
    TextView textViewMedia, textViewMediana, textSessao;
    Estatistica t;
    float valX[] = new float[100];
    float valY[] = new float[100];


    Context contextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_grafico);

        inicializar();
        setTextMedia();
        setTextMediana();
        setTextSessaoSucesso();
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

        /*graficoBarraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contextActivity, GraficoBarra.class);
                intent.putExtra("ArraysSessoes", getSessoes());
                startActivity(intent);
            }
        });*/
    }

    private ArrayList<Sessoes> getSessoes(){
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        ArrayList<Sessoes> sessoes = (ArrayList<Sessoes>) b.getSerializable("ArraysSessoes");
        return sessoes;
    }

    private void setTextMedia(){
        t = new Estatistica();
        DecimalFormat f = new DecimalFormat("0.00");
        textViewMedia.setText("Média: "+f.format(t.getMedia(getSessoes()))+"%");
    }

    private void setTextMediana(){
        t = new Estatistica();
        DecimalFormat f = new DecimalFormat("0.00");
        textViewMediana.setText("Mediana: "+f.format(t.getMediana(getSessoes()))+"%");
    }

    private void setTextSessaoSucesso(){

        ArrayList<Sessoes> sessoes = (ArrayList<Sessoes>) getSessoes();
        for(int i = 0; i < sessoes.size(); i++) {
            valX[i] = sessoes.get(i).getIdSessao();
            valY[i] = sessoes.get(i).getPorcentagem();
        }
        ordena(sessoes);
        if(valY[sessoes.size()-1] >= 80 && valY[sessoes.size()-2] >= 80){
            textSessao.setText("Sessão: "+valX[sessoes.size()-1]);
        }else
            textSessao.setText("Sessão: ");
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

    private void inicializar() {
        graficoLinhaBtn = findViewById(R.id.graficoLinhaBtn);
        //graficoBarraBtn = findViewById(R.id.graficoBarraBtn);
        textViewMedia = findViewById(R.id.textMedia);
        textViewMediana = findViewById(R.id.textMediana);
        textSessao= findViewById(R.id.textSessaoSuscesso);

        contextActivity = this;
    }

}
