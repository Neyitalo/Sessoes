package com.example.graficos.Visao;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graficos.Dominio.Estatistica;
import com.example.graficos.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Media extends AppCompatActivity {
    Estatistica t;
    TextView textViewMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        textViewMedia = findViewById(R.id.textViewMedia);
        t = new Estatistica();

        t.definiMedia(textView);
        //textView.setText(Float.toString(t.getMedia()));

    }
}