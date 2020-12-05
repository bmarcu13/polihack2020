package com.example.polihack2020bylos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.polihack2020bylos.Entities.PatientEntity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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

    private PieChart age_pie_chart;

    private PatientEntity patientEntity;
    ArrayList<Integer> intConversion = new ArrayList<>();

    private ArrayList<PatientEntity> patientsList;
    private ArrayList<PatientEntity> filteredList;

    private String age, sex, bloodType;

    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        currentUserID = fAuth.getCurrentUser().getUid();

        patientEntity = new PatientEntity();

        setTitle("");

        age_pie_chart = findViewById(R.id.pie_chart1);
        topView = findViewById(R.id.top_view);

        Map<String, Integer> ageMap = new HashMap<>();
        patientsList = new ArrayList<>();

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
                        bloodTypeSpinner.setItems("AB+", "AB-", "A+", "A-", "B+", "B-", "0+", "0-");
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

//                        if (patientEntity.getAge() < 10) {
//                            int count = ageMap.containsKey("0-9") ? ageMap.get("0-9") : 0;
//                            ageMap.put("0-9", count + 1);
//                        }
//                        if (patientEntity.getAge() < 20 && patientEntity.getAge() >= 10 ) {
//                            int count = ageMap.containsKey("10-19") ? ageMap.get("10-19") : 0;
//                            ageMap.put("10-19", count + 1);
//                        }
//                        if (patientEntity.getAge() < 30 && patientEntity.getAge() >= 20) {
//                            int count = ageMap.containsKey("20-29") ? ageMap.get("20-29") : 0;
//                            ageMap.put("20-29", count + 1);
//                        }
//                        if (patientEntity.getAge() < 40 && patientEntity.getAge() >= 30 ) {
//                            int count = ageMap.containsKey("30-49") ? ageMap.get("30-49") : 0;
//                            ageMap.put("30-49", count + 1);
//                        }
//                        if (patientEntity.getAge() < 55 && patientEntity.getAge() >= 40) {
//                            int count = ageMap.containsKey("40-54") ? ageMap.get("40-54") : 0;
//                            ageMap.put("40-54", count + 1);
//                        }
//                        if (patientEntity.getAge() < 70 && patientEntity.getAge() >= 55) {
//                            int count = ageMap.containsKey("55-69") ? ageMap.get("55-69") : 0;
//                            ageMap.put("55-69", count + 1);
//                        }
//                        if (patientEntity.getAge() >= 70) {
//                            int count = ageMap.containsKey("70+") ? ageMap.get("70+") : 0;
//                            ageMap.put("70+", count + 1);
//                        }

                        patientEntity.setBloodType(documentSnapshot.get("blood_type").toString());
                        patientEntity.setSex(documentSnapshot.get("sex").toString());
                        patientEntity.setName(documentSnapshot.get("name").toString());
                        patientEntity.setMedication(medicationArrayList);
                        patientEntity.setSideEffects(sideEffectsArrayList);
                        patientEntity.setSymptomps(symptomsArrayList);

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

                }
            }
        });

        PieDataSet pieDataSet = new PieDataSet(dataValues1(), "");
        pieDataSet.setColors((ColorTemplate.COLORFUL_COLORS));
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setDrawValues(false);

        PieData pieData = new PieData(pieDataSet);

        setDescription(age_pie_chart.getDescription());

        age_pie_chart.setData(pieData);
        age_pie_chart.setCenterText("AGE");
        age_pie_chart.setCenterTextSize(30f);
        age_pie_chart.animate();
        age_pie_chart.getLegend().setEnabled(false);
        age_pie_chart.setEntryLabelTextSize(15f);
        age_pie_chart.setEntryLabelColor(Color.BLACK);
        age_pie_chart.setTouchEnabled(false);

        dataFiltering();

    }

    private ArrayList<PatientEntity> dataFiltering() {
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

    private void setDescription(Description description) {
        description.setText("Age groups of risk");
        description.setTextSize(15f);
    }

    private ArrayList<PieEntry> dataValues1() {
        ArrayList<PieEntry> dataVals = new ArrayList<>();

        dataVals.add(new PieEntry(1, "70+"));
        dataVals.add(new PieEntry(3, "55-69"));
//        dataVals.add(new PieEntry(40, "40-54"));
//        dataVals.add(new PieEntry(30, "30-39"));
//        dataVals.add(new PieEntry(27, "20-29"));
//        dataVals.add(new PieEntry(18, "10-19"));
//        dataVals.add(new PieEntry(10, "0-9"));

        return dataVals;
    }

}