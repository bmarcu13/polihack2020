package com.example.polihack2020bylos.DoctorApp.PatientList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.polihack2020bylos.DoctorApp.AddPatient.Patient;

import java.util.List;

public class PatientsListAdapter extends ArrayAdapter<Patient> {
    private Context mContext;
    int mResource;

    public PatientsListAdapter(Context context, int resource, List<Patient> patientList) {
        super(context, resource, patientList);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String patientName = getItem(position).getPatientName();
        Integer patientAge = getItem(position).getPatientAge();
        String patientSex = getItem(position).getPatientSex();
        String patientBloodType = getItem(position).getPatientBloodType();
        String patientSymptomesDescription = getItem(position).getPatientSymptomesDescription();
        String patientTreatment = getItem(position).getPatientTreatment();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);


        /*
        //declararea partilor din xml
        ImageView ivObjectTypePicture = (ImageView) convertView.findViewById(R.id.ivObjectTypePicture);
        TextView tvObjectTypeName = (TextView) convertView.findViewById(R.id.tvObjectTypeName);
        TextView tvObjectTypeDisinfectionTime = (TextView) convertView.findViewById(R.id.tvObjectTypeDisinfectionTime);


        //atribuirea valorilor
        ivObjectTypePicture.setImageResource(objectTypePicture);
        tvObjectTypeName.setText(objectTypeName);
        tvObjectTypeDisinfectionTime.setText("Disinfection time: " + calculateMinutesSeconds(objectTypeDisinfectionTime));
        */


        return convertView;
    }

}
