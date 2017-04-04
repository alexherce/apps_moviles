package com.example.aag.eval_01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  public static String SERVICIO_LOGIN = "http://201.122.77.1:8085/api/json/GetLogin?";

  public static final String USUARIO = "username";
  public static final String PASSWORD = "password";

  private EditText editTextUsuario;
  private EditText editTextPassword;

  private Button botonAcceder;

  private DatosUsuario userData;

  private String usuario;
  private String password;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    editTextUsuario = (EditText) findViewById(R.id.username);
    editTextPassword = (EditText) findViewById(R.id.password);

    botonAcceder = (Button) findViewById(R.id.btn_login);

    botonAcceder.setOnClickListener((View.OnClickListener) this);
  }

  private void usuarioLogin() {
    final ProgressDialog barraDeProgreso = new ProgressDialog(MainActivity.this);
    barraDeProgreso.setMessage(getString(R.string.text_loggingin));
    barraDeProgreso.show();

    SERVICIO_LOGIN = "http://201.122.77.1:8085/api/json/GetLogin?";
    usuario = editTextUsuario.getText().toString().trim();
    password = editTextPassword.getText().toString().trim();

    SERVICIO_LOGIN = SERVICIO_LOGIN + USUARIO + "=" + usuario + "&" + PASSWORD + "=" + password;

    JsonArrayRequest req = new JsonArrayRequest(SERVICIO_LOGIN, new Response.Listener<JSONArray>() {
      @Override public void onResponse(JSONArray response) {
        barraDeProgreso.hide();
        try {
          JSONObject auth = (JSONObject) response.get(0);
          String authCode = auth.getString("Code").toString();

          if (authCode.equals("01")) {
            JSONObject person = (JSONObject) response.get(2);

            userData.getInstance().setFirstName(person.getString("FirstName").toString());
            userData.getInstance().setLastName(person.getString("LastName").toString());
            userData.getInstance().setSecondLastName(person.getString("SecondLastName").toString());
            userData.getInstance().setAssociateId(person.getString("AssociateId").toString());
            userData.getInstance().setCompanyId(person.getLong("CompanyId"));
            userData.getInstance().setUsername(usuario);
            userData.getInstance().setPassword(password);

            Toast.makeText(MainActivity.this, getString(R.string.text_welcome) + " " + person.getString("FirstName").toString(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
            //intent.putExtra("DATA", response);
            startActivity(intent);
          } else if (authCode.equals("04")) {
            Toast.makeText(MainActivity.this, getString(R.string.text_wrong_credentials), Toast.LENGTH_LONG)
                .show();
          }
        } catch (JSONException e) {
          e.printStackTrace();
          Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG)
              .show();
        }
      }
    }, new Response.ErrorListener() {
      @Override public void onErrorResponse(VolleyError error) {
        barraDeProgreso.hide();
        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
      }
    }) {
      @Override protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> map = new HashMap<String, String>();
        map.put(USUARIO, usuario);
        map.put(PASSWORD, password);
        return map;
      }

      @Override public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        String credentials = usuario + ":" + password;
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", auth);
        return headers;
      }
    };

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(req);
  }

  public void onClick(View v) {
    usuarioLogin();
  }
}
