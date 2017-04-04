package com.example.aag.eval_01;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
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

public class ActividadesMedicasActivity extends AppCompatActivity {
  private static final String TAG = "ActividadesMedicasActiv";
  public static String SERVICIO_ACTIVIDADMEDICA =
      "http://201.122.77.1:8085/api/json/GetControlChecKAssociate?";
  public static final String ASSOCIATEID = "associateId";
  public static final String COMPANYID = "companyId";
  public static final String USUARIO = "username";
  public static final String PASSWORD = "password";

    private ActividadesMedicasAdapter adapter;
    private ProgressBar progressBar;

    private String associate;
    private Long company;

  private String usuario;
  private String password;

  TextView itemNumber, descripcionActMed, fechaActMed;
  TableRow tr;
  TableLayout tl;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_actividades_medicas);
      progressBar = (ProgressBar) findViewById(R.id.pbar);
      setTitle(getString(R.string.title_medical_activities));

      setUpList();
    resultadosUsuario();
  }

  private void resultadosUsuario() {
      progressBar.setVisibility(View.VISIBLE);

    usuario = DatosUsuario.getInstance().getUsername();
    password = DatosUsuario.getInstance().getPassword();
    associate = DatosUsuario.getInstance().getAssociateId();
    company = DatosUsuario.getInstance().getCompanyId();
    Log.d(TAG, "associateId: " + associate);
    Log.d(TAG, "companyId: " + company);

    SERVICIO_ACTIVIDADMEDICA =
        SERVICIO_ACTIVIDADMEDICA + ASSOCIATEID + "=" + associate + "&" + COMPANYID + "=" + company;

    JsonArrayRequest req =
        new JsonArrayRequest(SERVICIO_ACTIVIDADMEDICA, new Response.Listener<JSONArray>() {
          @Override public void onResponse(JSONArray response) {
              progressBar.setVisibility(View.INVISIBLE);
            try {
              JSONObject auth = (JSONObject) response.get(0);
              String authCode = auth.getString("Code").toString();

              if (authCode.equals("01")) {
                JSONArray arrayDatos = (JSONArray) response.get(1);

                for (int i = 0; i < arrayDatos.length(); i++) {
                  JSONObject datos = (JSONObject) arrayDatos.get(i);

                    ActividadesMedicas actividadesMedicas = new ActividadesMedicas();
                    if(datos.getString("FechaAplico").equals("null"))
                        actividadesMedicas.setDate(getResources().getString(R.string.title_notapplied));
                    else
                        actividadesMedicas.setDate(datos.getString("FechaAplico").substring(0, 10));
                    actividadesMedicas.setNum(i + 1);
                    actividadesMedicas.setDetailsDescription(datos.getString("DescriptionReasonDetail"));
                    actividadesMedicas.setObservations(datos.getString("Observacion"));

                    adapter.add(actividadesMedicas);
                }
              } else if (authCode.equals("04")) {
                Toast.makeText(ActividadesMedicasActivity.this,
                    "Error: No se encontro informacion.", Toast.LENGTH_LONG).show();
              }
            } catch (JSONException e) {
              e.printStackTrace();
              Toast.makeText(ActividadesMedicasActivity.this, "Error: " + e.getMessage(),
                  Toast.LENGTH_LONG).show();
            }
          }
        }, new Response.ErrorListener() {
          @Override public void onErrorResponse(VolleyError error) {
              progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(ActividadesMedicasActivity.this, error.getMessage(), Toast.LENGTH_SHORT)
                .show();
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

    RequestQueue requestQueue = Volley.newRequestQueue(ActividadesMedicasActivity.this);
    requestQueue.add(req);
  }

    private void setUpList() {
        List<ActividadesMedicas> actividadesMedicases = new ArrayList<>();
        adapter = new ActividadesMedicasAdapter(this, actividadesMedicases);
        ListView listView = (ListView) findViewById(R.id.list_medicalactivities);
        listView.setAdapter(adapter);
    }
}
