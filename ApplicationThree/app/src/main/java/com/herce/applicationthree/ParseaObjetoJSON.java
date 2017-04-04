package com.herce.applicationthree;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ParseaObjetoJSON extends AppCompatActivity {

    // definimos un tar para el log
    private static final String TAG ="ParseaObjetoJSON";
    private final String EXTRA_JSON_OBJECT_INDEX = "com.herce.applicationthree.parseajson";

    // definimos elementos de la app
    private Auto miAuto;
    private TextView TextViewMarca;
    private TextView TextViewAuto;
    private ImageView ImageViewUrl;
    private String imagen;

    // definimos end pont del objeto JSON
    String url = "http://ubiquitous.csf.itesm.mx/~pddm-1021150/content/parcial1/02022017/servicio.autos.php";

    // referenciamos elementos del layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parseaobjetojson);

        // mapeamos componentes de la interfaz
        TextViewMarca =(TextView)findViewById(R.id.edit_text_marca);
        TextViewAuto =(TextView)findViewById(R.id.edit_text_auto);
        ImageViewUrl = (ImageView)findViewById(R.id.image_view_auto);

        // definimos la barra de progreso
        final ProgressDialog barraDeProgreso = new ProgressDialog(ParseaObjetoJSON.this);
        barraDeProgreso.setMessage("Cargando datos...");
        barraDeProgreso.show();

        // Hacemos una peticion de red por medio de Volley para obtener una respuesta
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,url, null,new Response.Listener<JSONObject>() {



            @Override
            public void onResponse(JSONObject response) {

                miAuto = parserJSON.parseaObjeto(response);

                // y luego definimos los valores que nos regresa a un textview y a un
                // imageview
                TextViewMarca.setText("Marca :" + miAuto.getMarca());
                TextViewAuto.setText("Auto :" + miAuto.getAuto());
                imagen = (miAuto.getImagen());

                ImageLoader cargaImagen = new ImageLoader(Volley.newRequestQueue(getApplicationContext()),
                        new com.herce.applicationthree.ImageLoader());

                // Si estamos usando un ImageView normal, entonces...
                cargaImagen.get(imagen, new ImageLoader.ImageListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error al cargar imagen: " + error.getMessage());
                    }

                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                        if (response.getBitmap() != null) {
                            // carga la imagen en el  imageview
                            ImageViewUrl.setImageBitmap(response.getBitmap());
                            barraDeProgreso.hide();

                        }
                    }
                });

                Log.d(TAG, response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error en: " + error.getMessage());
                // oculta la barra de progreso
                barraDeProgreso.hide();
            }
        });

        // Anexamos un request a la cola
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjReq);

    }
}
