package com.herce.tareasprimerparcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    private long tiempoEspera = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask tarea = new TimerTask() {
            public void run() {
                finish();
                Intent intentoPrincipal = new Intent().setClass(Splash.this, MainActivity.class);
                startActivity(intentoPrincipal);
            }
        };

        Timer timer = new Timer();
        timer.schedule(tarea, tiempoEspera);
    }

}
