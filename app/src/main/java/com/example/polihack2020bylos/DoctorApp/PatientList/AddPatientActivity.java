package com.example.polihack2020bylos.DoctorApp.PatientList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anurag.multiselectionspinner.MultiSelectionSpinnerDialog;
import com.anurag.multiselectionspinner.MultiSpinner;
import com.example.polihack2020bylos.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class AddPatientActivity extends AppCompatActivity {

    private MaterialSpinner sexSpinner, bloodTypeSpinner;
    private MultiSpinner symptomsSpinner;
    private MultiSpinner medicationSpinner;
    private MultiSpinner sideEffectsSpinner;

    private ArrayList<String> symptomsList;
    private ArrayList<String> medicationList;
    private ArrayList<String> sideEffectsList;

    private String name, bloodType, sex;
    private int age;
    private ArrayList<String> chosenSymptomsList;
    private ArrayList<String> chosenMedicationList;
    private ArrayList<String> chosenSideEffectsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        sexSpinner = findViewById(R.id.sex_spinner);
        bloodTypeSpinner = findViewById(R.id.blood_type_spinner);
        symptomsSpinner = findViewById(R.id.symptoms_spinner);
        medicationSpinner = findViewById(R.id.medication_spinner);
        sideEffectsSpinner = findViewById(R.id.side_effects_spinner);

        bloodTypeSpinner.setItems("AB+", "AB-", "A+", "A-", "B+", "B-", "0+", "0-");
        sexSpinner.setItems("M", "F");

        bloodTypeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                bloodType = item.toString();
            }
        });

        sexSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                sex = item.toString();
            }
        });


        setSymptomsSpinner();
        symptomsSpinner.initMultiSpinner(this, symptomsSpinner);
        symptomsSpinner.setAdapterWithOutImage(this, symptomsList, new MultiSelectionSpinnerDialog.OnMultiSpinnerSelectionListener() {
            @Override
            public void OnMultiSpinnerItemSelected(List<String> chosenItems) {
                for(int i = 0 ; i < chosenItems.size() ; i++){
                    chosenSymptomsList.add(chosenItems.get(i));
                }
            }
        });



        setMedicationSpinner();
        medicationSpinner.setAdapterWithOutImage(this, medicationList, new MultiSelectionSpinnerDialog.OnMultiSpinnerSelectionListener() {
            @Override
            public void OnMultiSpinnerItemSelected(List<String> chosenItems) {
                for(int i = 0 ; i < chosenItems.size() ; i++){
                    chosenMedicationList.add(chosenItems.get(i));
                }
            }
        });


        setSideEffectsSpinner();
        sideEffectsSpinner.setAdapterWithOutImage(this, sideEffectsList, new MultiSelectionSpinnerDialog.OnMultiSpinnerSelectionListener() {
            @Override
            public void OnMultiSpinnerItemSelected(List<String> chosenItems) {
                for(int i = 0 ; i < chosenItems.size() ; i++){
                    chosenSideEffectsList.add(chosenItems.get(i));
                }
            }
        });

    }


    private void setSymptomsSpinner(){
        symptomsSpinner = findViewById(R.id.symptoms_spinner);
        symptomsList = new ArrayList<>();
        symptomsList.add("Fever");
        symptomsList.add("Dry caugh");
        symptomsList.add("Tiredness");
        symptomsList.add("Aches and Pains");
        symptomsList.add("Sore throat");
        symptomsList.add("Diarrhoea");
        symptomsList.add("Conjunctivitis");
        symptomsList.add("Headache");
        symptomsList.add("Loss of taste and smell");
        symptomsList.add("Rash on skin");
        symptomsList.add("Discoloration of fingers or toes");
        symptomsList.add("Shortness of breath");
        symptomsList.add("Chest pain and pressure");


    }

    private void setMedicationSpinner(){
        medicationSpinner = findViewById(R.id.medication_spinner);
        medicationList = new ArrayList<>();
        medicationList.add("Remdesivir");
        medicationList.add("Lopinavir");
        medicationList.add("Ritonavir");
        medicationList.add("Ivermectin");
        medicationList.add("Nurofen");
        medicationList.add("Aspirin");
        medicationList.add("Vitamin C");

    }

    private void setSideEffectsSpinner(){
        sideEffectsSpinner = findViewById(R.id.side_effects_spinner);
        sideEffectsList = new ArrayList<>();
        sideEffectsList.add("Nervousness");
        sideEffectsList.add("Poor concentration");
        sideEffectsList.add("Nausea");
        sideEffectsList.add("Vomiting");
        sideEffectsList.add("Diarrhoea");
        sideEffectsList.add("Stomach ache");
        sideEffectsList.add("Head ache");
        sideEffectsList.add("Sleeping difficulties");
        sideEffectsList.add("Skin reactions");
        sideEffectsList.add("Heart rhythm abnormalities");


    }
}