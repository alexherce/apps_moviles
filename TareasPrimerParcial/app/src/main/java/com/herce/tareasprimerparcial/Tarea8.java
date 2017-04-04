package com.herce.tareasprimerparcial;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


public class Tarea8 extends AppCompatActivity {

    String url = "http://ubiquitous.csf.itesm.mx/~pddm-1021150/content/parcial1/09012017/ejercicios/img.jpg";
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea8);
        setTitle("Tarea 8A: Imagen usando Volley");

        final ProgressDialog barraDeProgreso = new ProgressDialog(Tarea8.this);
        barraDeProgreso.setMessage("Cargando imagen...");
        barraDeProgreso.show();

        NetworkImageView imageView = (NetworkImageView) findViewById(R.id.imageView);

        imageLoader = VolleyRequest.getInstance(this.getApplicationContext()).getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(imageView, R.drawable.image, android.R.drawable.ic_dialog_alert));
        imageView.setImageUrl(url, imageLoader);
        barraDeProgreso.hide();


    }
}
