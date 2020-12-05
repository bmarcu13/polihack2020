package com.example.polihack2020bylos.DoctorApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.polihack2020bylos.DoctorApp.PatientList.AddPatientActivity;
import com.example.polihack2020bylos.DoctorApp.PatientList.PatientsListActivity;
import com.example.polihack2020bylos.R;
import com.example.polihack2020bylos.StartupPageActivity;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorMenuActivity extends AppCompatActivity {

    TextView buttonViewPatientsList;
    TextView buttonAddPatient;
    private Button logoutButton;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_menu);

        manageButtons();
        logOut();

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

    private void logOut() {
        fAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.buttonLogOutDoctor);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();

                Intent intent = new Intent(DoctorMenuActivity.this, StartupPageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}