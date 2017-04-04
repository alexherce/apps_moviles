package com.example.aag.eval_01;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class Health extends TabActivity {

  /**
   * Called when the activity is first created.
   */
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_health);

    TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
    TabHost.TabSpec spec; // Reusable TabSpec for each tab
    Intent intent; // Reusable Intent for each tab

    spec = tabHost.newTabSpec("actmed"); // Create a new TabSpec using tab host
    spec.setIndicator(getString(R.string.title_medical_activities)); // set the “HOME” as an indicator
    // Create an Intent to launch an Activity for the tab (to be reused)
    intent = new Intent(this, ActividadesMedicasActivity.class);
    spec.setContent(intent);
    tabHost.addTab(spec);

    // Do the same for the other tabs

    spec = tabHost.newTabSpec("DiagTra"); // Create a new TabSpec using tab host
    spec.setIndicator(getString(R.string.title_diagnostics_and_treatments)); // set the “CONTACT” as an indicator
    // Create an Intent to launch an Activity for the tab (to be reused)
    intent = new Intent(this, DiagnosticosTratamientosActivity.class);
    spec.setContent(intent);
    tabHost.addTab(spec);

    //set tab which one you want to open first time 0 or 1 or 2
    tabHost.setCurrentTab(0);
  }
}
