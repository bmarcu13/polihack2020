package com.example.polihack2020bylos.UserApp.MedicationCart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.polihack2020bylos.R;

import java.util.ArrayList;
import java.util.List;

public class MedicationCartActivity extends AppCompatActivity {

    private List<Medication> medicationList;
    private MedicationDatabase medicationDatabase;
    private ListView medicationListView;
    TextView finalPrice;
    Handler handler;
    Button buttonBuy;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_cart);

        backButton = findViewById(R.id.back_arrow);

        medicationDatabase = new MedicationDatabase(this);
        medicationList = medicationDatabase.getMedication();

        medicationListView = findViewById(R.id.medicationListCart);

        MedicationListAdapter medicationListAdapter = new MedicationListAdapter(this, R.layout.layout_medication_list_cart, medicationList);
        medicationListView.setAdapter(medicationListAdapter);


        if(medicationList.size() == 0)
        {
            Toast.makeText(MedicationCartActivity.this, "Your cart is empty.", Toast.LENGTH_LONG).show();
        }

        sumOfPrices();


        buttonBuy = findViewById(R.id.buttonBuy);
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(medicationList.size() > 0){
                    medicationDatabase.cleanMedicationDatabase();
                    medicationList = medicationDatabase.getMedication();
                    MedicationListAdapter medicationListAdapter = new MedicationListAdapter(MedicationCartActivity.this, R.layout.layout_medication_list_cart, medicationList);
                    medicationListView.setAdapter(medicationListAdapter);

                    Toast.makeText(MedicationCartActivity.this, "Your order has been sent.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MedicationCartActivity.this, "Your cart is empty.", Toast.LENGTH_LONG).show();
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

    @Override
    public void onBackPressed() {
        finish();
    }

    private void sumOfPrices(){
        finalPrice = findViewById(R.id.finalPrice);

        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Double price = 0.0;
                for (int i = 0; i < medicationList.size(); i++) {
                    Double a = medicationList.get(i).getMedicationPrice();
                    Integer b = medicationList.get(i).getMedicationQuantity();
                    price = price + a * b;
                }
                finalPrice.setText("Total price: " + price.toString() + "€");
                handler.postDelayed(this, 100); // set time here to refresh textView
            }
        });
    }




}