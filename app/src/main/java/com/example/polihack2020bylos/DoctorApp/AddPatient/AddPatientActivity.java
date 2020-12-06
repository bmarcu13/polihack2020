package com.example.polihack2020bylos.DoctorApp.AddPatient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.anurag.multiselectionspinner.MultiSelectionSpinnerDialog;
import com.anurag.multiselectionspinner.MultiSpinner;
import com.example.polihack2020bylos.R;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPatientActivity extends AppCompatActivity implements MultiSelectionSpinnerDialog.OnMultiSpinnerSelectionListener {

    private MaterialSpinner sexSpinner, bloodTypeSpinner;
    private MultiSpinnerSearch symptomsSpinner;
    private MultiSpinnerSearch medicationSpinner;
    private MultiSpinnerSearch sideEffectsSpinner;

    private ArrayList<String> symptomsList;
    private ArrayList<String> medicationList;
    private ArrayList<String> sideEffectsList;

    private ArrayList<KeyPairBoolData> kSymptompsList = new ArrayList<KeyPairBoolData>();
    private ArrayList<KeyPairBoolData> kMedicationList = new ArrayList<KeyPairBoolData>();
    private ArrayList<KeyPairBoolData> kSideEffectsList = new ArrayList<KeyPairBoolData>();

    private String name, bloodType, sex;
    private Integer age;
    private final ArrayList<String> chosenSymptomsList = new ArrayList<>();
    private final ArrayList<String> chosenMedicationList = new ArrayList<>();
    private final ArrayList<String> chosenSideEffectsList = new ArrayList<>();

    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private Button addPatientButton;

    private EditText nameField, ageField;

    private View backButton;
    private CheckBox patientRecoveredCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        nameField = findViewById(R.id.name_field);
        ageField = findViewById(R.id.age_field);

        backButton = findViewById(R.id.back_button);

        sexSpinner = findViewById(R.id.sex_spinner);
        bloodTypeSpinner = findViewById(R.id.blood_type_spinner);
        symptomsSpinner = findViewById(R.id.symptoms_spinner);
        medicationSpinner = findViewById(R.id.medication_spinner);
        sideEffectsSpinner = findViewById(R.id.side_effects_spinner);

        addPatientButton = findViewById(R.id.add_patient_button);
        patientRecoveredCheckBox = findViewById(R.id.got_better_checkpoint);

        bloodTypeSpinner.setItems("0I, Rh+", "0I, Rh- ", "AII, Rh+", "AII, Rh- ", "BIII, Rh+", "BIII, Rh- ", "ABIV, Rh+", "ABIV, Rh-");
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
        symptomsSpinner.setSearchEnabled(false);
        symptomsSpinner.setSearchHint("Symptomps");
        symptomsSpinner.setShowSelectAllButton(false);
        symptomsSpinner.setItems(kSymptompsList, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i = 0; i < selectedItems.size(); i++) {
                    chosenSymptomsList.add(selectedItems.get(i).getName());
                }
            }
        });


        setMedicationSpinner();
        medicationSpinner.setSearchEnabled(false);
        medicationSpinner.setSearchHint("Medication");
        medicationSpinner.setShowSelectAllButton(false);
        medicationSpinner.setItems(kMedicationList, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i = 0; i < selectedItems.size(); i++) {
                    chosenMedicationList.add(selectedItems.get(i).getName());
                }
            }
        });


        setSideEffectsSpinner();
        sideEffectsSpinner.setSearchEnabled(false);
        sideEffectsSpinner.setSearchHint("Side Effects");
        sideEffectsSpinner.setShowSelectAllButton(false);
        sideEffectsSpinner.setItems(kSideEffectsList, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for(int i = 0; i < selectedItems.size(); i++) {
                    chosenSideEffectsList.add(selectedItems.get(i).getName());
                }
            }
        });

        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameField.getText().toString().trim();
                String ageS = ageField.getText().toString().trim();
                try {
                    age = Integer.valueOf(ageS);
                    uploadToDb();
                }
                catch (Exception e) {
                    ageField.setError("Wrong Format");
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void uploadToDb() {
        Map<String, Object> patientData = new HashMap<>();
        String patiendId = createPatientId();
        DocumentReference patientDoc = fStore.collection("patients").document(patiendId);

        patientData.put("patient_id", patiendId);
        patientData.put("doctor_id", fAuth.getCurrentUser().getUid());
        patientData.put("name", name);
        patientData.put("age", age);
        patientData.put("sex", sex);
        patientData.put("blood_type", bloodType);
        patientData.put("symptomps", chosenSymptomsList);
        patientData.put("medication", chosenMedicationList);
        patientData.put("side_effects", chosenSideEffectsList);
        boolean gotBetter = patientRecoveredCheckBox.isChecked();
        patientData.put("got_better", gotBetter);

        patientDoc.set(patientData);
        finish();
    }

    private String createPatientId() {
        Calendar calendar = Calendar.getInstance();
        return fAuth.getCurrentUser().getUid().concat(calendar.getTimeInMillis() + "");
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

        for (int i = 0; i < symptomsList.size(); i++) {
            KeyPairBoolData k = new KeyPairBoolData();
            k.setName(symptomsList.get(i));
            k.setId(i);
            kSymptompsList.add(k);
        }
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

        for (int i = 0; i < medicationList.size(); i++) {
            KeyPairBoolData k = new KeyPairBoolData();
            k.setName(medicationList.get(i));
            k.setId(i);
            kMedicationList.add(k);
        }

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

        for (int i = 0; i < sideEffectsList.size(); i++) {
            KeyPairBoolData k = new KeyPairBoolData();
            k.setName(sideEffectsList.get(i));
            k.setId(i);
            kSideEffectsList.add(k);
        }
    }

    @Override
    public void OnMultiSpinnerItemSelected(List<String> chosenItems) {

    }
}