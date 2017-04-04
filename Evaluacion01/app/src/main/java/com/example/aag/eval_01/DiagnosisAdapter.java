package com.example.aag.eval_01;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by gerardogtn on 2/23/17.
 */

public class DiagnosisAdapter extends ArrayAdapter<Diagnosis> {

  public DiagnosisAdapter(Context context, List<Diagnosis> diagnostics) {
    super(context, 0, diagnostics);
  }

  @NonNull @Override public View getView(int position, View convertView, ViewGroup parent) {
    Diagnosis d = getItem(position);

    if (convertView == null) {
      convertView =
          LayoutInflater.from(getContext()).inflate(R.layout.item_diagnosis, parent, false);
    }

    TextView num = (TextView) convertView.findViewById(R.id.txt_num);
    TextView date = (TextView) convertView.findViewById(R.id.txt_date);
    TextView doctor = (TextView) convertView.findViewById(R.id.txt_doctor);
    TextView diagnostic = (TextView) convertView.findViewById(R.id.txt_diagnosis);
    TextView treatment = (TextView) convertView.findViewById(R.id.txt_treatment);

    assert d != null;
    num.setText(String.valueOf(d.getNum()));
    date.setText(d.getDate());
    doctor.setText(d.getDoctor());
    diagnostic.setText(d.getDiagnostic());
    treatment.setText(d.getTreatment());

    return convertView;
  }
}
