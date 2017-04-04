package com.herce.applicationthree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class parseaJSON extends AppCompatActivity {

    // Definimos los componentes de nuestra app
    private Button boton_obtieneObjetoJSON;
    private Button boton_obtieneArregloJSON;

    private final String EXTRA_JSON_OBJECT_INDEX = "com.herce.applicationthree.parseaJSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parseajson);

        boton_obtieneObjetoJSON = (Button) findViewById(R.id.button_objeto_json);
        boton_obtieneArregloJSON = (Button) findViewById(R.id.button_arreglo_json);


        // Como hemos hecho en otros ejemplos, el setOnClickListener nos permite
        // identificar si ha sido pulsado un boton en particular para llevarnos
        // a una actividad definida
        boton_obtieneObjetoJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), ParseaObjetoJSON.class);
                i.putExtra(EXTRA_JSON_OBJECT_INDEX, 0);
                startActivity(i);
            }
        });

        boton_obtieneArregloJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), ParseaArregloJSON.class);
                startActivity(i);
            }
        });

    }
}
