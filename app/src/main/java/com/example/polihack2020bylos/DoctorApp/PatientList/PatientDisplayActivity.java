package com.example.polihack2020bylos.DoctorApp.PatientList;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.polihack2020bylos.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PatientDisplayActivity extends AppCompatActivity {

    private TextView nameTextView, ageTextView, sexTextView, bloodTypeTextView;
    private TextView symptomsTextView, sideEffectsTextView, medicationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_display);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Integer age = intent.getIntExtra("age", 404);
        String sex = intent.getStringExtra("sex");
        String blood_type = intent.getStringExtra("blood_type");
        Boolean gotBetetr = intent.getBooleanExtra("blood_type", true);
        ArrayList<String> symptoms = intent.getStringArrayListExtra("symptomps");
        ArrayList<String> medication = intent.getStringArrayListExtra("medication");
        ArrayList<String> sideEffects = intent.getStringArrayListExtra("side_effects");

        nameTextView = findViewById(R.id.patient_name);
        ageTextView = findViewById(R.id.patient_age);
        bloodTypeTextView = findViewById(R.id.patient_blood_type);
        sexTextView = findViewById(R.id.patient_sex);
        symptomsTextView = findViewById(R.id.symptoms_textview);
        sideEffectsTextView = findViewById(R.id.side_effects_textview);
        medicationTextView = findViewById(R.id.medication_textview);

        nameTextView.setText(name);
        ageTextView.setText(Long.toString(age));
        sexTextView.setText(sex);
        bloodTypeTextView.setText(blood_type);

        String symptomsList = "";
        for (int i = 0; i < symptoms.size(); i++) {
            symptomsList += symptoms.get(i);
            if (i < symptoms.size() - 1) {
                symptomsList += " • ";
            }
        }
        symptomsTextView.setText(symptomsList);

        String sideEffectsList = "";
        for (int i = 0; i < sideEffects.size(); i++) {
            sideEffectsList += sideEffects.get(i);
            if (i < sideEffects.size() - 1) {
                sideEffectsList += " • ";
            }
        }
        sideEffectsTextView.setText(sideEffectsList);

        String medicationList = "";
        for (int i = 0; i < medication.size(); i++) {
            medicationList += medication.get(i);
            if (i < medication.size() - 1) {
                medicationList += " • ";
            }
        }
        medicationTextView.setText(medicationList);

        Log.d("TAG", "onCreate: " + symptoms);
        Log.d("TAG", "onCreate: " + medication);
        Log.d("TAG", "onCreate: " + sideEffects);

        findViewById(R.id.back_arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}