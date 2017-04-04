package com.herce.apploginyregistro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {

    public static final String SERVICIO_LOGIN = "http://ubiquitous.csf.itesm.mx/~pddm-1021150/content/parcial2/2017-02-16/servicio.login.php";

    public static final String USUARIO = "usr";
    public static final String PASSWORD = "pswrd";

    private EditText editTextUsuario;
    private EditText editTextPassword;

    private Button botonAcceder;

    private String usuario;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsuario = (EditText) findViewById(R.id.editTextUsuarioLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordLogin);

        botonAcceder = (Button) findViewById(R.id.buttonAccederLogin);

        botonAcceder.setOnClickListener(this);
    }


    private void usuarioLogin() {
        usuario = editTextUsuario.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        JsonArrayRequest peticion = new JsonArrayRequest(Request.Method.POST, SERVICIO_LOGIN,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.trim().equals("Acceso_Concedido")){
                            Profile();
                        }else{
                            Toast.makeText(Login.this,"Welcome",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(USUARIO,usuario);
                map.put(PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(peticion);
    }

    private void Profile(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(USUARIO, usuario);
        startActivity(intent);
    }

    public void onClick(View v) {
        usuarioLogin();
    }
}
