package com.example.polihack2020bylos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.util.Log;
import android.view.View;

import com.example.polihack2020bylos.Entities.PatientEntity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartsActivity extends AppCompatActivity {

    private String currentUserID;

    private View topView;


    private PatientEntity patientEntity;
    ArrayList<Integer> intConversion = new ArrayList<>();

    private ArrayList<PatientEntity> patientsList;

    private String age, sex, bloodType;

    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        currentUserID = fAuth.getCurrentUser().getUid();

        patientEntity = new PatientEntity();

        setTitle("");

        topView = findViewById(R.id.top_view);
        barChart = findViewById(R.id.chart);

        patientsList = new ArrayList<>();

        ArrayList<String> medicationList = new ArrayList<>();
        medicationList.add("Remdesivir");
        medicationList.add("Lopinavir");
        medicationList.add("Ritonavir");
        medicationList.add("Ivermectin");
        medicationList.add("Nurofen");
        medicationList.add("Aspirin");
        medicationList.add("Vitamin C");


        fStore.collection("patients").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        PatientEntity patientEntity = new PatientEntity();

                        List<String> medicationList = (List<String>)documentSnapshot.get("medication");
                        ArrayList<String> medicationArrayList = new ArrayList<>();
                        for (int i = 0; i < medicationList.size(); i++) {
                            medicationArrayList.add(medicationList.get(i));
                        }

                        List<String> sideEffectsList = (List<String>)documentSnapshot.get("side_effects");
                        ArrayList<String> sideEffectsArrayList = new ArrayList<>();
                        for (int i = 0; i < sideEffectsList.size(); i++) {
                            sideEffectsArrayList.add(sideEffectsList.get(i));
                        }

                        List<String> symptompsList = (List<String>)documentSnapshot.get("symptomps");
                        ArrayList<String> symptomsArrayList = new ArrayList<>();
                        for (int i = 0; i < symptompsList.size(); i++) {
                            symptomsArrayList.add(symptompsList.get(i));
                        }

                        patientEntity.setAge(Integer.getInteger(documentSnapshot.get("age").toString()));

                        MaterialSpinner bloodTypeSpinner = findViewById(R.id.blood_type_spinner);
                        bloodTypeSpinner.setItems("0I, Rh+", "0I, Rh- ", "AII, Rh+", "AII, Rh- ", "BIII, Rh+", "BIII, Rh- ", "ABIV, Rh+", "ABIV, Rh-");
                        MaterialSpinner sexSpinner = findViewById(R.id.sex_spinner);
                        sexSpinner.setItems("M", "F");
                        MaterialSpinner ageSpinner = findViewById(R.id.age_spinner);
                        ageSpinner.setItems("0-9", "10-19", "20-29", "30-39", "40-54", "55-69", "70+");

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
                        sexSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                                intConversion.clear();
                                age = item.toString();
                                if (age.contains("-")) {
                                    String[] intc;
                                    intc = age.split("-");
                                    intConversion.add(Integer.valueOf(intc[0]));
                                    intConversion.add(Integer.valueOf(intc[1]));
                                }
                                else {
                                    String[] intc;
                                    intc = age.split("\\+");
                                    intConversion.add(Integer.valueOf(intc[0]));
                                }
                            }
                        });

                        patientEntity.setBloodType(documentSnapshot.get("blood_type").toString());
                        patientEntity.setSex(documentSnapshot.get("sex").toString());
                        patientEntity.setName(documentSnapshot.get("name").toString());
                        patientEntity.setMedication(medicationArrayList);
                        patientEntity.setSideEffects(sideEffectsArrayList);
                        patientEntity.setSymptomps(symptomsArrayList);
                        patientEntity.setGotBetter(Boolean.valueOf(documentSnapshot.get("got_better").toString().trim()));

                        patientsList.add(patientEntity);
                    }
                    final DocumentReference currentUser = fStore.collection("users").document(currentUserID);
                    currentUser.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            patientEntity.setSex(documentSnapshot.getString("sex"));
                            patientEntity.setSex(documentSnapshot.getString("age"));
                            patientEntity.setSex(documentSnapshot.getString("blood_type"));
                        }
                    });

                    ArrayList<PatientEntity> filteredList = dataFiltering();
                    ArrayList<Integer> drugEfficiencyList = getDrugEfficiencyList(medicationList, filteredList);

                    ArrayList<BarEntry> entries = new ArrayList<>();
                    for (int i = 0; i < drugEfficiencyList.size(); i++) {
                        entries.add(new BarEntry(drugEfficiencyList.get(i), i));
                    }
                    BarDataSet barDataSet = new BarDataSet(entries, "Meds ");


                    ArrayList<String> meds = new ArrayList<>();
                    for (int i = 0; i < medicationList.size(); i++) {
                        meds.add(medicationList.get(i));
                    }

                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setBarSpacePercent(50f);
                    BarData barData = new BarData(meds, barDataSet);
                    barChart.setData(barData);

                    barChart.setTouchEnabled(true);
                    barChart.setDragEnabled(true);
                    barChart.setScaleEnabled(true);

                }
            }
        });

        medicationList.add("Mask");

    }


    private Integer getDrugEfficiency(String drugName, ArrayList<PatientEntity> filteredList) {
        Integer totalApp = 0;
        Integer gotBetter = 0;
        for (int i = 0; i < filteredList.size(); i++) {
            if (filteredList.get(i).getMedication().contains(drugName)) {
                totalApp ++;
                if (filteredList.get(i).getGotBetter()) {
                    gotBetter ++;
                }
            }
        }
        return totalApp == 0 ? 0 :gotBetter * 100 / totalApp;
    }

    private ArrayList<Integer> getDrugEfficiencyList(ArrayList<String> medicationList, ArrayList<PatientEntity> filteredList) {
        ArrayList<Integer> drugsEfficiencyList = new ArrayList<>();

        for (int i = 0; i < medicationList.size(); i++) {
            drugsEfficiencyList.add(getDrugEfficiency(medicationList.get(i), filteredList));
        }
        return drugsEfficiencyList;
    }

    private ArrayList<PatientEntity> dataFiltering() {

        ArrayList<PatientEntity> filteredList = new ArrayList<>();

        for (int i = 0; i < patientsList.size(); i++) {
            PatientEntity currentPatient = patientsList.get(i);
            if (bloodType != null && sex != null && intConversion.size() != 0) {
                if (currentPatient.getBloodType().toUpperCase() == bloodType.toUpperCase() &&
                        currentPatient.getSex().toUpperCase() == sex.toUpperCase() &&
                        currentPatient.getAge() > intConversion.get(0) &&
                        currentPatient.getAge() < intConversion.get(1)) {
                    filteredList.add(currentPatient);
                }
            }
            if (bloodType == null && sex != null && intConversion.size() != 0) {
                if (currentPatient.getSex().toUpperCase() == sex.toUpperCase() &&
                        currentPatient.getAge() > intConversion.get(0) &&
                        currentPatient.getAge() < intConversion.get(1)) {
                    filteredList.add(currentPatient);
                }
            }
            if (bloodType == null && sex == null && intConversion.size() != 0) {
                if (currentPatient.getAge() > intConversion.get(0) &&
                        currentPatient.getAge() < intConversion.get(1)) {
                    filteredList.add(currentPatient);
                }
            }
            if (bloodType == null && sex != null && intConversion.size() == 0) {
                if (currentPatient.getSex().toUpperCase() == sex.toUpperCase()){
                    filteredList.add(currentPatient);
                }
            }
            if (bloodType != null && sex == null && intConversion.size() != 0) {
                if (currentPatient.getBloodType().toUpperCase() == bloodType.toUpperCase() &&
                        currentPatient.getAge() > intConversion.get(0) &&
                        currentPatient.getAge() < intConversion.get(1)) {
                    filteredList.add(currentPatient);
                }
            }
            if (bloodType != null && sex == null && intConversion.size() == 0) {
                if (currentPatient.getBloodType().toUpperCase() == bloodType.toUpperCase()) {
                    filteredList.add(currentPatient);
                }
            }
            if (bloodType != null && sex != null && intConversion.size() == 0) {
                if (currentPatient.getBloodType().toUpperCase() == bloodType.toUpperCase() &&
                        currentPatient.getSex().toUpperCase() == sex.toUpperCase()) {
                    filteredList.add(currentPatient);
                }
            }
            if (bloodType == null && sex == null && intConversion.size() == 0) {
                filteredList.add(currentPatient);
            }
        }
        return filteredList;
    }


}