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
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Sessoes> sessoes;
    float media = 0, mediana = 0;
    float valY[] = new float[100];

    public String teste(){
        return "TESTEEEEEE";
    }

    public void definiMedia(final TextView textView){
        sessoes = new ArrayList<Sessoes>();

        db.collection("sessoes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() { // Colsulta simples Java Android/Sem Listener/ Usar o FireStore
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                sessoes.add((Sessoes) document.toObject(Sessoes.class));
                                Log.i("FireStore", document.getId() + " => " + document.getData());
                            }

                           calculaMedia();

                            DecimalFormat f = new DecimalFormat("0.00");

                             System.out.println("Média: "+getMedia());
                             textView.setText(f.format(media)+"%");
                            System.out.println("A THREAD FINALIZOU SUA EXECUÇÃO");
                        } else {
                            Log.i("FireStore", "Error getting documents: ", task.getException());
                        }

                    }

                });
    }

    public void definiMediana(final TextView textView){
        sessoes = new ArrayList<Sessoes>();

        db.collection("sessoes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() { // Colsulta simples Java Android/Sem Listener/ Usar o FireStore
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                sessoes.add((Sessoes) document.toObject(Sessoes.class));
                                Log.i("+", document.getId() + " => " + document.getData());
                            }

                            calculaMediana();

                            DecimalFormat f = new DecimalFormat("0.00");

                            System.out.println("Mediana: "+getMediana());
                            textView.setText(f.format(mediana)+"%");

                            System.out.println("A THREAD FINALIZOU SUA EXECUÇÃO");
                        } else {
                            Log.i("FireStore", "Error getting documents: ", task.getException());
                        }

                    }

                });
    }

    public float getMediana(){
        return mediana;
    }

    public float getMedia(){
        return media;
    }

    private void calculaMedia(){
        for(int i = 0; i < sessoes.size(); i++){
            media = media+sessoes.get(i).getPorcentagem();
        }
        media = media/sessoes.size();

        System.out.println("PASSEI AQUI"+media);
    }

    private void calculaMediana(){
        for (int i = 0; i < sessoes.size(); i++) {
            valY[i] = sessoes.get(i).getPorcentagem();
        }
        ordena();
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

    private void ordena(){
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