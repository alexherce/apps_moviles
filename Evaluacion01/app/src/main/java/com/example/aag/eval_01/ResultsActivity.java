package com.example.aag.eval_01;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.TextView;
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

public class ResultsActivity extends AppCompatActivity {

  public static String SERVICIO_RESULTADOS = "http://201.122.77.1:8085/api/json/GetMyResults?";
  public static final String ASSOCIATEID = "associateId";
  public static final String COMPANYID = "companyId";
  public static final String USUARIO = "username";
  public static final String PASSWORD = "password";

  private String associate;
  private Long company;

  private String usuario;
  private String password;

  private DatosUsuario userData;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_results);

    resultadosUsuario();
  }

  private void resultadosUsuario() {
    final ProgressDialog barraDeProgreso = new ProgressDialog(ResultsActivity.this);
    barraDeProgreso.setMessage("Cargando datos...");
    barraDeProgreso.show();

    usuario = userData.getInstance().getUsername();
    password = userData.getInstance().getPassword();
    associate = userData.getInstance().getAssociateId();
    company = userData.getInstance().getCompanyId();

    SERVICIO_RESULTADOS =
        SERVICIO_RESULTADOS + ASSOCIATEID + "=" + associate + "&" + COMPANYID + "=" + company;

    JsonArrayRequest req =
        new JsonArrayRequest(SERVICIO_RESULTADOS, new Response.Listener<JSONArray>() {
          @Override public void onResponse(JSONArray response) {
            barraDeProgreso.hide();
            try {
              JSONObject auth = (JSONObject) response.get(0);
              String authCode = auth.getString("Code").toString();

              if (authCode.equals("01")) {
                JSONArray arrayDatos = (JSONArray) response.get(1);
                JSONObject datos = (JSONObject) arrayDatos.get(0);

                // PESO
                TextView pesoText = (TextView) findViewById(R.id.textPeso);
                TextView pesoTextDate = (TextView) findViewById(R.id.textPesoDate);

                JSONArray peso = datos.getJSONArray("Weight");
                // FOR
                JSONObject pesoUser = (JSONObject) peso.get(0);
                Double tmp = pesoUser.getDouble("Value");
                pesoText.setText(tmp.toString());
                pesoTextDate.setText(pesoUser.getString("Date").toString());

                // BMI
                TextView bmiText = (TextView) findViewById(R.id.textBMI);
                TextView bmiTextDate = (TextView) findViewById(R.id.textBMIDate);

                JSONArray bmi = datos.getJSONArray("Bmi");
                // FOR
                JSONObject bmiUser = (JSONObject) bmi.get(0);
                Double tmpBMI = bmiUser.getDouble("Value");
                bmiText.setText(tmpBMI.toString());
                bmiTextDate.setText(bmiUser.getString("Date").toString());
              } else if (authCode.equals("04")) {
                Toast.makeText(ResultsActivity.this, "Usuario incorrecto.", Toast.LENGTH_LONG)
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
}
