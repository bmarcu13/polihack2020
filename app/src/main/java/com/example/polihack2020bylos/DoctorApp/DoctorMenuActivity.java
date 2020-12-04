package com.example.polihack2020bylos.DoctorApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.polihack2020bylos.DoctorApp.PatientList.AddPatientActivity;
import com.example.polihack2020bylos.DoctorApp.PatientList.PatientsListActivity;
import com.example.polihack2020bylos.R;

public class DoctorMenuActivity extends AppCompatActivity {

    Button buttonViewPatientsList;
    Button buttonAddPatient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_menu);

        manageButtons();

    }

    private void manageButtons(){
        buttonViewPatientsList = findViewById(R.id.buttonViewPatientsList);
        buttonViewPatientsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorMenuActivity.this, PatientsListActivity.class);
                startActivity(intent);
            }
        });

        buttonAddPatient = findViewById(R.id.buttonAddPatient);
        buttonAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorMenuActivity.this, AddPatientActivity.class);
                startActivity(intent);
            }
        });
    }
}