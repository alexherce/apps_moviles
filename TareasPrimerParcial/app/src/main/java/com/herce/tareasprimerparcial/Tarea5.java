package com.herce.tareasprimerparcial;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Tarea5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea5);
        setTitle("Tarea 5: Intentos y llamadas al sistema");

        Button answerBtn = (Button) findViewById(R.id.answer);
        Button callBtn = (Button) findViewById(R.id.call);
        Button deleteBtn = (Button) findViewById(R.id.delete);
        Button dialBtn = (Button) findViewById(R.id.dial);
        Button editBtn = (Button) findViewById(R.id.edit);
        Button insertBtn = (Button) findViewById(R.id.insert);
        Button pickBtn = (Button) findViewById(R.id.pick);
        Button searchBtn = (Button) findViewById(R.id.search);
        Button sendtoBtn = (Button) findViewById(R.id.sendto);
        Button viewBtn = (Button) findViewById(R.id.view);
        Button websearchBtn = (Button) findViewById(R.id.websearch);

        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivityForResult(new Intent(Intent.ACTION_ANSWER), 0);
                } catch (Exception e) {
                    Log.e("LOG", e.toString());
                    Toast.makeText(Tarea5.this, "Error: No se encontro una actividad para este boton.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(android.content.Intent.ACTION_DIAL,
                        Uri.parse("tel:+5591778230"));
                startActivity(actividad);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    startActivityForResult(new Intent(Intent.ACTION_DELETE), 0);
                }
                catch (Exception e)
                {
                    Log.e("LOG",e.toString());
                    Toast.makeText(Tarea5.this,"Error: No se encontro una actividad para este boton.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actividad = new Intent(android.content.Intent.ACTION_DIAL,
                        Uri.parse("tel:+5591778230"));
                startActivity(actividad);
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    startActivityForResult(new Intent(Intent.ACTION_EDIT), 0);
                }
                catch (Exception e)
                {
                    Log.e("LOG",e.toString());
                    Toast.makeText(Tarea5.this,"Error: No se encontro una actividad para este boton.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        insertBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    startActivityForResult(new Intent(Intent.ACTION_INSERT), 0);
                }
                catch (Exception e)
                {
                    Log.e("LOG",e.toString());
                    Toast.makeText(Tarea5.this,"Error: No se encontro una actividad para este boton.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        pickBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    startActivityForResult(new Intent(Intent.ACTION_PICK), 0);
                }
                catch (Exception e)
                {
                    Log.e("LOG",e.toString());
                    Toast.makeText(Tarea5.this,"Error: No se encontro una actividad para este boton.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    startActivityForResult(new Intent(Intent.ACTION_SEARCH), 0);
                }
                catch (Exception e)
                {
                    Log.e("LOG",e.toString());
                    Toast.makeText(Tarea5.this,"Error: No se encontro una actividad para este boton.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sendtoBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    startActivityForResult(new Intent(Intent.ACTION_SENDTO), 0);
                }
                catch (Exception e)
                {
                    Log.e("LOG",e.toString());
                    Toast.makeText(Tarea5.this,"Error: No se encontro una actividad para este boton.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    startActivityForResult(new Intent(Intent.ACTION_VIEW), 0);
                }
                catch (Exception e)
                {
                    Log.e("LOG",e.toString());
                    Toast.makeText(Tarea5.this,"Error: No se encontro una actividad para este boton.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        websearchBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    startActivityForResult(new Intent(Intent.ACTION_WEB_SEARCH), 0);
                }
                catch (Exception e)
                {
                    Log.e("LOG",e.toString());
                    Toast.makeText(Tarea5.this,"Error: No se encontro una actividad para este boton.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
