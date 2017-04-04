package com.example.aag.eval_01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DiagnosticosTratamientosActivity extends AppCompatActivity {

  public static final String URL_BASE = "http://201.122.77.1:8085/api/json/GetExpedienteAssociate?";
  public static final String ASSOCIATEID = "associateId";
  public static final String COMPANYID = "companyId";
  public static final String USUARIO = "username";
  public static final String PASSWORD = "password";

  private DiagnosisAdapter adapter;
  private ProgressBar progressBar;

  private String usuario;
  private String password;
  private String associate;
  private Long company;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_diagnosticos_tratamientos);
    progressBar = (ProgressBar) findViewById(R.id.pbar);

    setTitle(getString(R.string.title_diagnostics_and_treatments));

    setUpList();
    makeRequest();
  }

  private void makeRequest() {
    progressBar.setVisibility(View.VISIBLE);

    usuario = DatosUsuario.getInstance().getUsername();
    password = DatosUsuario.getInstance().getPassword();
    associate = DatosUsuario.getInstance().getAssociateId();
    company = DatosUsuario.getInstance().getCompanyId();

    String url = URL_BASE + ASSOCIATEID + "=" + associate + "&" + COMPANYID + "=" + company;

    JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
      @Override public void onResponse(JSONArray response) {
        progressBar.setVisibility(View.INVISIBLE);

        try {
          JSONObject auth = (JSONObject) response.get(0);
          String authCode = auth.getString("Code").toString();

          if (authCode.equals("01")) {
            JSONArray arrayDatos = (JSONArray) response.get(1);

            for (int i = 0; i < arrayDatos.length(); i += 2) {
              JSONObject datos = (JSONObject) arrayDatos.get(i);
              JSONObject next = (JSONObject) arrayDatos.get(i + 1);

              Diagnosis diagnosis = new Diagnosis();
              diagnosis.setDate(datos.getString("DateFile").substring(0, 10));
              diagnosis.setNum(i + 1);
              diagnosis.setDoctor(datos.getString("NameEmployee"));
              diagnosis.setDiagnostic(datos.getString("Description"));
              diagnosis.setTreatment(next.getString("Description"));

              adapter.add(diagnosis);
            }
          } else if (authCode.equals("04")) {
            Toast.makeText(DiagnosticosTratamientosActivity.this, "Error: No se encontro informacion.",
                Toast.LENGTH_LONG).show();
          }
        } catch (JSONException e) {
          e.printStackTrace();
          Toast.makeText(DiagnosticosTratamientosActivity.this, "Error: " + e.getMessage(),
              Toast.LENGTH_LONG).show();
        }
      }
    }, new Response.ErrorListener() {
      @Override public void onErrorResponse(VolleyError error) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(DiagnosticosTratamientosActivity.this, error.getMessage(),
            Toast.LENGTH_SHORT).show();
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

    RequestQueue requestQueue = Volley.newRequestQueue(DiagnosticosTratamientosActivity.this);
    requestQueue.add(req);
  }

  private void setUpList() {
    List<Diagnosis> diagnosis = new ArrayList<>();
    adapter = new DiagnosisAdapter(this, diagnosis);
    ListView listView = (ListView) findViewById(R.id.list_diagnosis);
    listView.setAdapter(adapter);
  }
}
