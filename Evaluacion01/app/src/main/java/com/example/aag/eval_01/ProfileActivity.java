package com.example.aag.eval_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

  private DatosUsuario userData;
  private Button botonResults;
  private Button botonHealth;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    TextView nombre = (TextView) findViewById(R.id.textNombre);

    nombre.setText(userData.getInstance().getFirstName());

    botonResults = (Button) findViewById(R.id.buttonResults);
    botonResults.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent i = new Intent(ProfileActivity.this, ResultsActivity.class);
        startActivity(i);
      }
    });

    botonHealth = (Button) findViewById(R.id.buttonHealth);
    botonHealth.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent i = new Intent(ProfileActivity.this, Health.class);
        startActivity(i);
      }
    });
  }
}
