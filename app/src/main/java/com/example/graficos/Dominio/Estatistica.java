package com.example.graficos.Dominio;

import com.example.graficos.Persistencia.Sessoes;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class Estatistica {
    float media = 0, mediana = 0;
    float valY[] = new float[100];
    DecimalFormat f = new DecimalFormat("0.00");
    ArrayList<Sessoes> sessoes;

    public Estatistica(ArrayList<Sessoes> sessoes){
        this.sessoes = sessoes;
    }

    public String getMedia(){
        calculaMedia(sessoes);
        return f.format(media);
    }

    public String getMediana(){
        calculaMediana(sessoes);
        return f.format(mediana);
    }

    private void calculaMedia(ArrayList<Sessoes> sessoes){
        for(int i = 0; i < sessoes.size(); i++){
            media = media+sessoes.get(i).getPorcentagem();
        }
        media = media/sessoes.size();

        System.out.println("PASSEI AQUI"+media);
    }

    private void calculaMediana(ArrayList<Sessoes> sessoes){
        for (int i = 0; i < sessoes.size(); i++) {
            valY[i] = sessoes.get(i).getPorcentagem();
        }
        ordena(sessoes);
        for (int i = 0; i < sessoes.size(); i++) {
            System.out.println("Porcentagem: "+valY[i]);
        }

        if(sessoes.size() % 2 == 0){
            int posicao = ((sessoes.size()/2) - 1);
            mediana = (valY[posicao]+valY[posicao+1])/2;
            System.out.println("Par: ValY["+posicao+"] = "+valY[posicao]);
        }else {
            int posicao = ((sessoes.size() - 1) / 2);
            System.out.println("Impar: ValY["+posicao+"] = "+valY[posicao]);
            mediana = valY[posicao];
        }
    }

    private void ordena(ArrayList<Sessoes> sessoes){
        for (int i = 0; i < sessoes.size(); i++)
        {
            for (int j = 0; j < sessoes.size(); j++)
            {
                if (valY[i] < valY[j])
                {
                    //aqui acontece a troca. O maior vai para a direita e o menor para a esquerda
                    float auxY = valY[i];

                    valY[i] = valY[j];

                    valY[j] = auxY;
                }
            }
        }
    }//Termina método
}