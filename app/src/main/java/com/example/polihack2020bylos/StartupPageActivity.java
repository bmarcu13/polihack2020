package com.example.polihack2020bylos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.polihack2020bylos.AccountManagement.DoctorLoginActivity;
import com.example.polihack2020bylos.AccountManagement.UserLoginActivity;

public class StartupPageActivity extends AppCompatActivity {

    private Button patientButton, doctorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_page);

        patientButton = findViewById(R.id.patient_button);
        doctorButton = findViewById(R.id.doctor_button);

        doctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartupPageActivity.this, DoctorLoginActivity.class));
                finish();
            }
        });
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartupPageActivity.this, UserLoginActivity.class));
                finish();
            }
        });
    }


}