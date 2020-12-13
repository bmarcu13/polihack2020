package com.example.polihack2020bylos.DoctorApp.CreatePrescription;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.polihack2020bylos.Entities.Medication;
import com.example.polihack2020bylos.R;
import com.example.polihack2020bylos.Databases.MedicationDatabase;

import java.util.List;
import java.util.Random;

public class CreatePrescriptionCodeActivity extends AppCompatActivity {

    private ImageView backButton;
    private List<Medication> medicationCrCodeList;
    private MedicationDatabase medicationDatabase;
    private ListView medicationCrCodeListView;
    private Button buttonCreatePrescriptionCode;
    private TextView tvPrescriptionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_prescription_code);

        backBtn();
        setUpListMedsCrCode();
        createCode();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private  void backBtn(){
        backButton = findViewById(R.id.back_arrow_cr_pr_code);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setUpListMedsCrCode(){
        medicationDatabase = new MedicationDatabase(this);
        medicationCrCodeList = medicationDatabase.getMedication();

        medicationCrCodeListView = findViewById(R.id.medicationListDoctorCode);

        CreatePrescriptionCodeAdapter createPrescriptionCodeAdapter = new CreatePrescriptionCodeAdapter(this, R.layout.layout_meds_create_code, medicationCrCodeList);
        medicationCrCodeListView.setAdapter(createPrescriptionCodeAdapter);

        if(medicationCrCodeList.size() == 0) {
            Toast.makeText(CreatePrescriptionCodeActivity.this, "Your prescription list is empty.", Toast.LENGTH_LONG).show();
        }
    }

    private void createCode(){
        tvPrescriptionCode = findViewById(R.id.tvPrescriptinCode);

        buttonCreatePrescriptionCode = findViewById(R.id.buttonCreatePrescriptionCode);
        buttonCreatePrescriptionCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(medicationCrCodeList.size() == 0){
                    Toast.makeText(CreatePrescriptionCodeActivity.this, "Your prescription list is empty.", Toast.LENGTH_LONG).show();
                }else{
                    String prCode = "";
                    Random random = new Random();
                    int number = random.nextInt(999999);
                    prCode += String.format("%06d", number);

                    medicationDatabase = new MedicationDatabase(CreatePrescriptionCodeActivity.this);
                    medicationCrCodeList = medicationDatabase.getMedication();
                    for(int i = 0 ; i < medicationCrCodeList.size() ; i++){
                        String id = String.valueOf(medicationCrCodeList.get(i).getMedicationId());
                        String quantity = String.valueOf(medicationCrCodeList.get(i).getMedicationQuantity());

                        prCode += id;
                        prCode += quantity;
                    }


                    tvPrescriptionCode.setText(prCode);


                    medicationDatabase.cleanMedicationDatabase();
                    medicationCrCodeList = medicationDatabase.getMedication();
                    CreatePrescriptionCodeAdapter createPrescriptionCodeAdapter = new CreatePrescriptionCodeAdapter
                            (CreatePrescriptionCodeActivity.this, R.layout.layout_meds_create_code, medicationCrCodeList);
                    medicationCrCodeListView.setAdapter(createPrescriptionCodeAdapter);

                    Toast.makeText(CreatePrescriptionCodeActivity.this, "Your code has been generated.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}