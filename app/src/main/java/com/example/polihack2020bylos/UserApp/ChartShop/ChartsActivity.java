package com.example.polihack2020bylos.UserApp.ChartShop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.polihack2020bylos.Entities.PatientEntity;
import com.example.polihack2020bylos.R;
import com.example.polihack2020bylos.UserApp.MedicationCart.MedicationCartActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class ChartsActivity extends AppCompatActivity {

    private String currentUserID;

    private View topView;


    private PatientEntity patientEntity;
    int intervalLeft, intervalRight;

    private ArrayList<PatientEntity> patientsList;

    private String age, sex, bloodType;

    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;

    private BarChart barChartMedication, barChartSymptoms;

    private Button bineintelesButton;
    private ImageView buttonGoToCart;
    private ListView medsBuyListView;

    private ArrayList<String> medicationList;
    private ArrayList<String> symptomsList;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        backButton = findViewById(R.id.back_arrow);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        currentUserID = fAuth.getCurrentUser().getUid();

        patientEntity = new PatientEntity();

        setTitle("");

        topView = findViewById(R.id.top_view);
        barChartMedication = findViewById(R.id.bar_chart_medication);
        barChartSymptoms = findViewById(R.id.bar_chart_symptoms);

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
                    final DocumentReference currentUser = fStore.collection("users").document(currentUserID);
                    currentUser.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            patientEntity.setSex(documentSnapshot.getString("sex"));
                            patientEntity.setAge(Integer.parseInt(documentSnapshot.get("age").toString()));
                            patientEntity.setBloodType(documentSnapshot.getString("blood_type"));
                        }
                    });

                    ArrayList<PatientEntity> filteredList = dataFiltering();

                    ArrayList<Integer> drugEfficiencyList = getDrugEfficiencyList(medicationList, filteredList);
                    createBarChartMedication(drugEfficiencyList);

                    ArrayList<Integer> symptomsCommonaltyList = getSymptomsCommonaltyList(symptomsList, filteredList);
                    createBarChartSymptoms(symptomsCommonaltyList);
                }
            }
        });

        medicationList.add("Mask");

        goToCart();
        setUpMedBuyListAdapter(medicationList);

        //filter button

        MaterialSpinner bloodTypeSpinner = findViewById(R.id.blood_type_spinner);
        bloodTypeSpinner.setItems("0I, Rh+", "0I, Rh-", "AII, Rh+", "AII, Rh-", "BIII, Rh+", "BIII, Rh-", "ABIV, Rh+", "ABIV, Rh-");
        MaterialSpinner sexSpinner = findViewById(R.id.sex_spinner);
        sexSpinner.setItems("M", "F");
        MaterialSpinner ageSpinner = findViewById(R.id.age_spinner);
        ageSpinner.setItems("1-9", "10-19", "20-29", "30-39", "40-54", "55-69", "70+");

        bloodTypeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                bloodType = item.toString().trim();
                Log.d("In_spinner", "onItemSelected: " + bloodType);
            }
        });

        sexSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                sex = item.toString();
            }
        });
        ageSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                age = item.toString();
                if (age.contains("-")) {
                    String[] intc;
                    intc = age.split("-");
                    intervalLeft = Integer.parseInt(intc[0]);
                    intervalRight = Integer.parseInt(intc[1]);
                    Log.d("age_spinner", "onItemSelected: " + intervalLeft + " " + intervalRight);
                }
                else if (age.contains("+")){
                    String[] intc;
                    intc = age.split("\\+");
                    intervalLeft = Integer.parseInt(intc[0]);
                    Log.d("age_spinner", "onItemSelected: " + intc[0] + " " + intc[1]);
                }
            }
        });

        bineintelesButton = findViewById(R.id.filter_button);

        bineintelesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<PatientEntity> filteredList = dataFiltering();
                updateBarChartMedication(filteredList);
                Log.d("TAG", "onClick: " + filteredList);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }

    private void createBarChartSymptoms (ArrayList<Integer> symptomsCommonaltyList) {
        ArrayList<BarEntry> entriesSymptoms = new ArrayList<>();
        for (int i = 0; i < symptomsCommonaltyList.size(); i++) {
            entriesSymptoms.add(new BarEntry(symptomsCommonaltyList.get(i), i));
        }
        BarDataSet barDataSetSymptoms = new BarDataSet(entriesSymptoms, "Symptoms");

        ArrayList<String> symptoms = new ArrayList<>();
        for (int i = 0; i < symptomsList.size(); i++) {
            symptoms.add(symptomsList.get(i));
        }

        barDataSetSymptoms.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSetSymptoms.setValueTextColor(Color.BLACK);
        barDataSetSymptoms.setBarSpacePercent(50f);
        BarData barDataSymptoms = new BarData(symptoms, barDataSetSymptoms);
        barChartSymptoms.setData(barDataSymptoms);

        barChartSymptoms.setTouchEnabled(true);
        barChartSymptoms.setDragEnabled(true);
        barChartSymptoms.setScaleEnabled(true);
        barChartSymptoms.invalidate();
    }

    private void updateBarCharSymptoms (ArrayList<PatientEntity> filteredList) {
        ArrayList<Integer> symptomsCommonaltyList = getSymptomsCommonaltyList(symptomsList, filteredList);
        ArrayList<BarEntry> entriesSymptoms = new ArrayList<>();
        for (int i = 0; i < symptomsCommonaltyList.size(); i++) {
            entriesSymptoms.add(new BarEntry(symptomsCommonaltyList.get(i), i));
        }
        BarDataSet barDataSetSymptoms = new BarDataSet(entriesSymptoms, "Symptoms");

        ArrayList<String> symptoms = new ArrayList<>();
        for (int i = 0; i < symptomsList.size(); i++) {
            symptoms.add(symptomsList.get(i));
        }

        barDataSetSymptoms.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSetSymptoms.setValueTextColor(Color.BLACK);
        barDataSetSymptoms.setBarSpacePercent(50f);
        BarData barDataSymptoms = new BarData(symptoms, barDataSetSymptoms);
        barChartSymptoms.setData(barDataSymptoms);

        barChartSymptoms.setTouchEnabled(true);
        barChartSymptoms.setDragEnabled(true);
        barChartSymptoms.setScaleEnabled(true);
        barChartMedication.notifyDataSetChanged();
        barChartSymptoms.invalidate();
    }

    private void createBarChartMedication (ArrayList<Integer> drugEfficiencyList) {
        ArrayList<BarEntry> entriesMedication = new ArrayList<>();
        for (int i = 0; i < drugEfficiencyList.size(); i++) {
            entriesMedication.add(new BarEntry(drugEfficiencyList.get(i), i));
        }
        BarDataSet barDataSetMedication = new BarDataSet(entriesMedication, "Meds ");


        ArrayList<String> meds = new ArrayList<>();
        for (int i = 0; i < medicationList.size(); i++) {
            meds.add(medicationList.get(i));
        }

        barDataSetMedication.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSetMedication.setValueTextColor(Color.BLACK);
        barDataSetMedication.setBarSpacePercent(50f);
        BarData barDataMedication = new BarData(meds, barDataSetMedication);
        barChartMedication.setData(barDataMedication);

        barChartMedication.setTouchEnabled(true);
        barChartMedication.setDragEnabled(true);
        barChartMedication.setScaleEnabled(true);
        barChartMedication.invalidate();
    }

    private void updateBarChartMedication(ArrayList<PatientEntity> filteredList) {

        ArrayList<Integer> drugEfficiencyList = getDrugEfficiencyList(medicationList, filteredList);
        ArrayList<BarEntry> entriesMedication = new ArrayList<>();
        for (int i = 0; i < drugEfficiencyList.size(); i++) {
            entriesMedication.add(new BarEntry(drugEfficiencyList.get(i), i));
        }
        BarDataSet barDataSetMedication = new BarDataSet(entriesMedication, "Meds ");


        ArrayList<String> meds = new ArrayList<>();
        for (int i = 0; i < medicationList.size(); i++) {
            meds.add(medicationList.get(i));
        }

        barDataSetMedication.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSetMedication.setValueTextColor(Color.BLACK);
        barDataSetMedication.setBarSpacePercent(50f);
        BarData barDataMedication = new BarData(meds, barDataSetMedication);
        barChartMedication.setData(barDataMedication);

        barChartMedication.setTouchEnabled(true);
        barChartMedication.setDragEnabled(true);
        barChartMedication.setScaleEnabled(true);
        barChartMedication.notifyDataSetChanged();
        barChartMedication.invalidate();

    }

    private void goToCart(){
        buttonGoToCart = findViewById(R.id.buttonGoToCart);
        buttonGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChartsActivity.this, MedicationCartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpMedBuyListAdapter(ArrayList<String> medicationList){
        medsBuyListView = findViewById(R.id.lvMedsBuy);

        MedsBuyListAdapter medsBuyListAdapter = new MedsBuyListAdapter(this, R.layout.layout_meds_buy, medicationList);
        medsBuyListView.setAdapter(medsBuyListAdapter);
    }


    private Integer getSymptomsCommonalty (String symptomName, ArrayList<PatientEntity> filteredList) {
        Integer totalApp = 0;
        for (int i = 0; i < filteredList.size(); i++) {
            if (filteredList.get(i).getSymptomps().contains(symptomName)) {
                totalApp ++;
            }
        }
        return totalApp / filteredList.size() * 100;
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

    private ArrayList<Integer> getSymptomsCommonaltyList (ArrayList<String> symptomsList, ArrayList<PatientEntity> filteredList) {
        ArrayList<Integer> symptomsCommonaltyList = new ArrayList<>();

        for (int i = 0; i < symptomsList.size(); i++) {
            symptomsCommonaltyList.add(getSymptomsCommonalty(symptomsList.get(i), filteredList));
//            Log.d("dsada", "getSymptomsCommonaltyList: " + getSymptomsCommonalty(symptomsList.get(i), filteredList));
        }

        return symptomsCommonaltyList;
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

            Log.d("LOL", "dataFiltering: " + intervalLeft + " " +  intervalRight);
            if (bloodType != null && !currentPatient.getBloodType().equals(bloodType)) {
                Log.d("BLOOD", "dataFiltering: ");
            } else if (sex != null && !currentPatient.getSex().equals(sex)) {
                Log.d("SEX", "dataFiltering: ");
            } else if (intervalLeft > 0 &&
                    (Integer.compare(currentPatient.getAge(), intervalRight) == 1 || Integer.compare(currentPatient.getAge(), intervalLeft) == -1 )) {
                Log.d("AGE", "dataFiltering: ");
            } else {
                filteredList.add(currentPatient);
            }
        }

        return filteredList;
    }
}