package com.herce.tareasprimerparcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tarea8B extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea8b);
        setTitle("Tarea 8B: Componentes");

        Button btnTextFields = (Button)findViewById(R.id.textfieldsBtn);


        btnTextFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(Tarea8B.this, Tarea8BTextFields.class);
                startActivity(actividad);
            }
        });
    }
}
