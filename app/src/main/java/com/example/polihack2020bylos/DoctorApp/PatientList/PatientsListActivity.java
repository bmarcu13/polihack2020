package com.example.polihack2020bylos.DoctorApp.PatientList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.example.polihack2020bylos.DoctorApp.AddPatient.Patient;
import com.example.polihack2020bylos.R;

import java.util.ArrayList;
import java.util.List;

public class PatientsListActivity extends AppCompatActivity {

    Context context = this;
    List<Patient> patientsList;
    ListView patientListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);


    }


    private void setUpPatientsList(){
        patientsList = new ArrayList<>();

        //creare lista cu pacienti
    }

    private void setUpPatientListAdapter(){
        PatientsListAdapter patientsListAdapter = new PatientsListAdapter(context, R.layout.layout_patient, patientsList);
        patientListView = findViewById(R.id.lvPatientsList);
        patientListView.setAdapter(patientsListAdapter);


        /*
        //pentru selectarea unui anumit obiect
        objectTypesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(ObjectTypeMenuActivity.this, ObjectMenuActivity.class);
                ObjectType selectedItem = (ObjectType) objectTypesListView.getItemAtPosition(position);

                intent.putExtra("Object", (Serializable) selectedItem);
                startActivity(intent);
            }
        });
         */
    }
}