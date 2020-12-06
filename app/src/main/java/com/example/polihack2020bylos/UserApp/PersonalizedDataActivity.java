package com.example.polihack2020bylos.UserApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.polihack2020bylos.Entities.PatientEntity;
import com.example.polihack2020bylos.Entities.ProgressBarData;
import com.example.polihack2020bylos.R;
import com.github.mikephil.charting.data.BarData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PersonalizedDataActivity extends AppCompatActivity {

    private ListView barListView;
    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    private ImageView backButton;

    private ArrayList<PatientEntity> patientsList;
    private ArrayList<String> medicationList;
    private ArrayList<String> symptomsList;

    private String currentUserID;
    private PatientEntity patientEntity;
    private ArrayList<PatientEntity> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_data);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        patientsList = new ArrayList<>();

        medicationList = new ArrayList<>();
        medicationList.add("Remdesivir");
        medicationList.add("Lopinavir");
        medicationList.add("Ritonavir");
        medicationList.add("Ivermectin");
        medicationList.add("Nurofen");
        medicationList.add("Aspirin");
        medicationList.add("Vitamin C");


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

        currentUserID = fAuth.getCurrentUser().getUid();

        patientEntity = new PatientEntity();

        String docID = fAuth.getCurrentUser().getUid();
        barListView = findViewById(R.id.list_view);

        fStore.collection("patients").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        if (documentSnapshot.get("doctor_id").equals(docID)) {
                            PatientEntity patientEntity = new PatientEntity();

                            List<String> medicationList = (List<String>) documentSnapshot.get("medication");
                            ArrayList<String> medicationArrayList = new ArrayList<>();
                            for (int i = 0; i < medicationList.size(); i++) {
                                medicationArrayList.add(medicationList.get(i));
                            }

                            List<String> sideEffectsList = (List<String>) documentSnapshot.get("side_effects");
                            ArrayList<String> sideEffectsArrayList = new ArrayList<>();
                            for (int i = 0; i < sideEffectsList.size(); i++) {
                                sideEffectsArrayList.add(sideEffectsList.get(i));
                            }

                            List<String> symptompsList = (List<String>) documentSnapshot.get("symptomps");
                            ArrayList<String> symptomsArrayList = new ArrayList<>();
                            for (int i = 0; i < symptompsList.size(); i++) {
                                symptomsArrayList.add(symptompsList.get(i));
                            }

                            patientEntity.setAge(Integer.parseInt(documentSnapshot.get("age").toString()));
                            patientEntity.setBloodType(documentSnapshot.get("blood_type").toString());
                            patientEntity.setSex(documentSnapshot.get("sex").toString());
                            patientEntity.setName(documentSnapshot.get("name").toString());
                            patientEntity.setMedication(medicationArrayList);
                            patientEntity.setSideEffects(sideEffectsArrayList);
                            patientEntity.setSymptomps(symptomsArrayList);
                            patientEntity.setGotBetter(Boolean.valueOf(documentSnapshot.get("got_better").toString().trim()));

                            patientsList.add(patientEntity);
                        }
                        filteredList = new ArrayList<>();
                        filteredList.addAll(patientsList);
                        ArrayList<ProgressBarData> drugEfficiencyList = getDrugEfficiencyList(medicationList, filteredList);
                        setUpBarList(drugEfficiencyList);
                    }
                }
            }
        });

        backButton = findViewById(R.id.back_arrow);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpBarList(ArrayList<ProgressBarData> medicationList){
        ProgressBarAdapter proBarAdapter = new ProgressBarAdapter(this, R.layout.layout_personalized_data_list, medicationList);
        barListView.setAdapter(proBarAdapter);
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

    private ArrayList<ProgressBarData> getDrugEfficiencyList(ArrayList<String> medicationList, ArrayList<PatientEntity> filteredList) {
        ArrayList<ProgressBarData> drugsEfficiencyList = new ArrayList<>();

        for (int i = 0; i < medicationList.size(); i++) {
            ProgressBarData progressBarData = new ProgressBarData();
            progressBarData.setValue(getDrugEfficiency(medicationList.get(i), filteredList));
            progressBarData.setName(medicationList.get(i));
            drugsEfficiencyList.add(progressBarData);
        }
        return drugsEfficiencyList;
    }
}