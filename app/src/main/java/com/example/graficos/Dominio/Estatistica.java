package com.example.graficos.Dominio;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.graficos.Persistencia.Sessoes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Estatistica {
    float media = 0, mediana = 0;
    float valY[] = new float[100];

    public float getMedia(final ArrayList<Sessoes> sessoes){
       calculaMedia(sessoes);
       return media;
    }

    public float getMediana(final ArrayList<Sessoes> sessoes){
       calculaMediana(sessoes);
       return mediana;
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