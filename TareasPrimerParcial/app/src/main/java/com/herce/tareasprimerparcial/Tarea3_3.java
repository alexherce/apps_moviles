package com.herce.tareasprimerparcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tarea3_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea3_3);
        setTitle("Tarea 3: Boton 3");

        Button btnRegresar = (Button)findViewById(R.id.button);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(Tarea3_3.this, Tarea3.class);
                startActivity(actividad);
            }
        });
    }
}
