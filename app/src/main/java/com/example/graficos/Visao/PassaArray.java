package com.example.graficos.Visao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.graficos.Persistencia.Sessoes;
import com.example.graficos.R;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PassaArray extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passa_array);

        final ArrayList<Sessoes> sessoes = new ArrayList<Sessoes>();
        final Intent intent = new Intent(this, DefineGrafico.class);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        System.out.println("PASSOU AQUIIIII");

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

                            /*
                            for(int i = 0; i < sessoes.size(); i++) {

                                System.out.println("i: "+i);
                                System.out.println("GET IDSESSAO: "+sessoes.get(i).getIdSessao());
                                System.out.println("GET PORCENTAGEM: "+sessoes.get(i).getPorcentagem());
                            }*/

                            intent.putExtra("ArraysSessoes", sessoes);
                            startActivity(intent);
                            System.out.println("A THREAD FINALIZOU SUA EXECUÇÃO");
                        } else {
                            Log.i("FireStore", "Error getting documents: ", task.getException());
                        }

                    }

                });


    }
}