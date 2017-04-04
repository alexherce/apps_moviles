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
 * Created by herce on 2/24/17.
 */

public class ActividadesMedicasAdapter extends ArrayAdapter<ActividadesMedicas> {

    public ActividadesMedicasAdapter(Context context, List<ActividadesMedicas> medicalactivities) {
        super(context, 0, medicalactivities);
    }

    @NonNull
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        ActividadesMedicas a = getItem(position);

        if (convertView == null) {
            convertView =
                    LayoutInflater.from(getContext()).inflate(R.layout.item_actividadesmedicas, parent, false);
        }

        TextView num = (TextView) convertView.findViewById(R.id.txt_num);
        TextView date = (TextView) convertView.findViewById(R.id.txt_date);
        TextView description = (TextView) convertView.findViewById(R.id.txt_description);
        TextView observations = (TextView) convertView.findViewById(R.id.txt_observations);

        assert a != null;
        num.setText(String.valueOf(a.getNum()));
        date.setText(a.getDate());
        observations.setText(a.getObservations());
        description.setText(a.getDetailsDescription());

        return convertView;
    }
}
