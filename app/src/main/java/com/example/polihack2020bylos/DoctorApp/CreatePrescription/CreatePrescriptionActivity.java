package com.example.polihack2020bylos.DoctorApp.CreatePrescription;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.polihack2020bylos.R;
import com.example.polihack2020bylos.UserApp.ChartShop.ChartsActivity;
import com.example.polihack2020bylos.UserApp.ChartShop.MedsBuyListAdapter;
import com.example.polihack2020bylos.UserApp.MedicationCart.MedicationCartActivity;

import java.util.ArrayList;

public class CreatePrescriptionActivity extends AppCompatActivity {

    private ImageView backButton;
    private ListView medsCrPrListView;
    private ArrayList<String> medicationList;
    private ImageView buttonGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_prescription);

        backBtn();
        setUpCrPrListAdapter();
        goToCart();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private  void backBtn(){
        backButton = findViewById(R.id.back_arrow_cr_pr);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void goToCart(){
        buttonGoToCart = findViewById(R.id.buttonGoToCartCrPr);
        buttonGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreatePrescriptionActivity.this, CreatePrescriptionCodeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpCrPrListAdapter(){
        medicationList = new ArrayList<>();
        medicationList.add("Remdesivir");
        medicationList.add("Lopinavir");
        medicationList.add("Ritonavir");
        medicationList.add("Ivermectin");
        medicationList.add("Nurofen");
        medicationList.add("Aspirin");
        medicationList.add("Vitamin C");

        medsCrPrListView = findViewById(R.id.mediacationListDoctor);

        CreatePrescriptionAdapter createPrescriptionAdapter = new CreatePrescriptionAdapter(this, R.layout.layout_meds_create_prescription, medicationList);
        medsCrPrListView.setAdapter(createPrescriptionAdapter);
    }


}