package com.herce.tareasprimerparcial;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Tareas Primer Parcial A01021150");

        Button btnTarea3 = (Button)findViewById(R.id.tarea3);
        Button btnTarea4 = (Button)findViewById(R.id.tarea4);
        Button btnTarea5 = (Button)findViewById(R.id.tarea5);
        Button btnTarea7 = (Button)findViewById(R.id.tarea7);
        Button btnTarea8 = (Button)findViewById(R.id.tarea8);
        Button btnTarea8b = (Button)findViewById(R.id.tarea8b);

        btnTarea3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(MainActivity.this, Tarea3.class);
                startActivity(actividad);
            }
        });

        btnTarea4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(android.content.Intent.ACTION_DIAL,
                        Uri.parse("tel:+5591778230"));
                startActivity(actividad);
            }
        });

        btnTarea5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(MainActivity.this, Tarea5.class);
                startActivity(actividad);
            }
        });

        btnTarea7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(MainActivity.this, SplashVideo.class);
                startActivity(actividad);
            }
        });

        btnTarea8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(MainActivity.this, Tarea8.class);
                startActivity(actividad);
            }
        });

        btnTarea8b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(MainActivity.this, Tarea8B.class);
                startActivity(actividad);
            }
        });
    }
}
