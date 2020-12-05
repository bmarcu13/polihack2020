package com.example.polihack2020bylos.UserApp.MedicationCart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_cart);

        medicationDatabase = new MedicationDatabase(this);
        medicationList = medicationDatabase.getMedication();

        medicationListView.findViewById(R.id.medicationListCart);

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
                finalPrice.setText("Final price: " + price.toString() + "€");
                handler.postDelayed(this, 100); // set time here to refresh textView
            }
        });
    }


    private void addInMedicationList(String medicationName){

        MedicationDatabase medicationDatabase = new MedicationDatabase(this);

        if(medicationName.equals("Remdesivir")){
            Medication existingMedication = medicationDatabase.getMedicationById(1L);

            if(existingMedication == null){
                Medication medication = new Medication();
                medication.setMedicationId(1L);
                medication.setMedicationName(medicationName);
                medication.setMedicationDescription("100mg");
                medication.setMedicationPrice(400.0);
                medication.setMedicationQuantity(1);

                medicationDatabase.addToMedicationDatabase(medication);
            }else{
                 medicationDatabase.removeMedicationById(1L);

                 existingMedication.setMedicationQuantity(existingMedication.getMedicationQuantity() + 1);

                 medicationDatabase.addToMedicationDatabase(existingMedication);
            }

        }



        if(medicationName.equals("Lopinavir")){
            Medication existingMedication = medicationDatabase.getMedicationById(2L);

            if(existingMedication == null){
                Medication medication = new Medication();
                medication.setMedicationId(2L);
                medication.setMedicationName(medicationName);
                medication.setMedicationDescription("160ml");
                medication.setMedicationPrice(379.99);
                medication.setMedicationQuantity(1);

                medicationDatabase.addToMedicationDatabase(medication);
            }else{
                medicationDatabase.removeMedicationById(2L);

                existingMedication.setMedicationQuantity(existingMedication.getMedicationQuantity() + 1);

                medicationDatabase.addToMedicationDatabase(existingMedication);
            }

        }



        if(medicationName.equals("Ritonavir")){
            Medication existingMedication = medicationDatabase.getMedicationById(3L);

            if(existingMedication == null){
                Medication medication = new Medication();
                medication.setMedicationId(3L);
                medication.setMedicationName(medicationName);
                medication.setMedicationDescription("100mg, 30 tablets");
                medication.setMedicationPrice(90.0);
                medication.setMedicationQuantity(1);

                medicationDatabase.addToMedicationDatabase(medication);
            }else{
                medicationDatabase.removeMedicationById(3L);

                existingMedication.setMedicationQuantity(existingMedication.getMedicationQuantity() + 1);

                medicationDatabase.addToMedicationDatabase(existingMedication);
            }

        }


        if(medicationName.equals("Ivermectin")){
            Medication existingMedication = medicationDatabase.getMedicationById(4L);

            if(existingMedication == null){
                Medication medication = new Medication();
                medication.setMedicationId(4L);
                medication.setMedicationName(medicationName);
                medication.setMedicationDescription("20 tablets");
                medication.setMedicationPrice(80.5);
                medication.setMedicationQuantity(1);

                medicationDatabase.addToMedicationDatabase(medication);
            }else{
                medicationDatabase.removeMedicationById(4L);

                existingMedication.setMedicationQuantity(existingMedication.getMedicationQuantity() + 1);

                medicationDatabase.addToMedicationDatabase(existingMedication);
            }

        }


        if(medicationName.equals("Nurofen")){
            Medication existingMedication = medicationDatabase.getMedicationById(5L);

            if(existingMedication == null){
                Medication medication = new Medication();
                medication.setMedicationId(5L);
                medication.setMedicationName(medicationName);
                medication.setMedicationDescription("250mg, 80 capsules");
                medication.setMedicationPrice(15.99);
                medication.setMedicationQuantity(1);

                medicationDatabase.addToMedicationDatabase(medication);
            }else{
                medicationDatabase.removeMedicationById(5L);

                existingMedication.setMedicationQuantity(existingMedication.getMedicationQuantity() + 1);

                medicationDatabase.addToMedicationDatabase(existingMedication);
            }

        }

        if(medicationName.equals("Aspirin")){
            Medication existingMedication = medicationDatabase.getMedicationById(6L);

            if(existingMedication == null){
                Medication medication = new Medication();
                medication.setMedicationId(6L);
                medication.setMedicationName(medicationName);
                medication.setMedicationDescription("100mg, 30 capsules");
                medication.setMedicationPrice(12.75);
                medication.setMedicationQuantity(1);

                medicationDatabase.addToMedicationDatabase(medication);
            }else{
                medicationDatabase.removeMedicationById(6L);

                existingMedication.setMedicationQuantity(existingMedication.getMedicationQuantity() + 1);

                medicationDatabase.addToMedicationDatabase(existingMedication);
            }

        }

        if(medicationName.equals("Vitamin C")){
            Medication existingMedication = medicationDatabase.getMedicationById(7L);

            if(existingMedication == null){
                Medication medication = new Medication();
                medication.setMedicationId(7L);
                medication.setMedicationName(medicationName);
                medication.setMedicationDescription("280g, 180 capsules");
                medication.setMedicationPrice(26.99);
                medication.setMedicationQuantity(1);

                medicationDatabase.addToMedicationDatabase(medication);
            }else{
                medicationDatabase.removeMedicationById(7L);

                existingMedication.setMedicationQuantity(existingMedication.getMedicationQuantity() + 1);

                medicationDatabase.addToMedicationDatabase(existingMedication);
            }

        }


        if(medicationName.equals("Mask")){
            Medication existingMedication = medicationDatabase.getMedicationById(8L);

            if(existingMedication == null){
                Medication medication = new Medication();
                medication.setMedicationId(8L);
                medication.setMedicationName(medicationName);
                medication.setMedicationDescription("50 pcs, 3 layers");
                medication.setMedicationPrice(11.99);
                medication.setMedicationQuantity(1);

                medicationDatabase.addToMedicationDatabase(medication);
            }else{
                medicationDatabase.removeMedicationById(8L);

                existingMedication.setMedicationQuantity(existingMedication.getMedicationQuantity() + 1);

                medicationDatabase.addToMedicationDatabase(existingMedication);
            }

        }

    }

}