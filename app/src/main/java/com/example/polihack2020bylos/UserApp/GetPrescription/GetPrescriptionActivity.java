package com.example.polihack2020bylos.UserApp.GetPrescription;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.polihack2020bylos.Entities.Drug;
import com.example.polihack2020bylos.Entities.Medication;
import com.example.polihack2020bylos.R;
import com.example.polihack2020bylos.UserApp.MedicationCart.MedicationCartActivity;
import com.example.polihack2020bylos.UserApp.MedicationCart.MedicationListAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetPrescriptionActivity extends AppCompatActivity {

    private HashMap<Integer, String> medicationList = new HashMap<>();
    private ArrayList<Drug> drugQuantities= new ArrayList<>();
    ArrayList<Medication> prescriptionList;
    private ListView getPrListView;
    TextView finalPrice;
    ImageView backButton;
    Button buttonBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_prescription2);

        prescriptionList = new ArrayList<>();
        sumOfPrices(prescriptionList);


        medicationList.put(1, "Remdesivir");
        medicationList.put(2, "Lopinavir");
        medicationList.put(3, "Ritonavir");
        medicationList.put(4, "Ivermectin");
        medicationList.put(5, "Nurofen");
        medicationList.put(6, "Aspirin");
        medicationList.put(7, "Vitamin C");

        EditText codeField = findViewById(R.id.code_field);
        Button button = findViewById(R.id.submit_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeField.getText().toString();
                if(code.length() % 2 == 0 && code.length() > 6){
                    prescriptionList.clear();
                    setUpPrescriptionList();
                    codeField.setText("");

                    for (int i = 6; i < code.length(); i += 2 ) {
                        if(Character.getNumericValue(code.charAt(i)) > 0 && Character.getNumericValue(code.charAt(i)) < 8){
                            addInMedsList(medicationList.get(Character.getNumericValue(code.charAt(i))), Character.getNumericValue(code.charAt(i + 1)), prescriptionList);
                            Log.d("TAG", "onClick: " + medicationList.get(Character.getNumericValue(code.charAt(i))) + Character.getNumericValue(code.charAt(i + 1)));
                        }

                    }

                    sumOfPrices(prescriptionList);
                    setUpPrescriptionList();

                }else if(code.length() % 2 == 1 || code.length() <= 6){
                    Toast.makeText(GetPrescriptionActivity.this, "Invalid code.", Toast.LENGTH_LONG).show();
                }

            }
        });

        back();
        buy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void back(){
        backButton = findViewById(R.id.back_arrow_get_pr);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void buy(){
        buttonBuy = findViewById(R.id.buttonBuy);
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prescriptionList.size() > 0){
                    getPrListView = findViewById(R.id.getPrescriptionList);
                    prescriptionList.clear();

                    GetPrescriptionAdapter getPrescriptionAdapter = new GetPrescriptionAdapter(GetPrescriptionActivity.this, R.layout.layout_get_prescription, prescriptionList);
                    getPrListView.setAdapter(getPrescriptionAdapter);

                    sumOfPrices(prescriptionList);

                    Toast.makeText(GetPrescriptionActivity.this, "Your order has been sent.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(GetPrescriptionActivity.this, "Your prescription list is empty.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setUpPrescriptionList(){
        getPrListView = findViewById(R.id.getPrescriptionList);

        GetPrescriptionAdapter getPrescriptionAdapter = new GetPrescriptionAdapter(this, R.layout.layout_get_prescription, prescriptionList);
        getPrListView.setAdapter(getPrescriptionAdapter);
    }

    private void addInMedsList(String name, Integer quantity, List<Medication> prescriptionList){
        Medication medication = new Medication();

        if(name.equals("Remdesivir")){
            medication.setMedicationId(1L);
            medication.setMedicationName(name);
            medication.setMedicationDescription("100mg");
            medication.setMedicationPrice(400.0);
            medication.setMedicationQuantity(quantity);
        }

        if(name.equals("Lopinavir")){
            medication.setMedicationId(2L);
            medication.setMedicationName(name);
            medication.setMedicationDescription("160ml");
            medication.setMedicationPrice(379.99);
            medication.setMedicationQuantity(quantity);
        }

        if(name.equals("Ritonavir")){
            medication.setMedicationId(3L);
            medication.setMedicationName(name);
            medication.setMedicationDescription("100mg, 30 tablets");
            medication.setMedicationPrice(90.0);
            medication.setMedicationQuantity(quantity);
        }

        if(name.equals("Ivermectin")){
            medication.setMedicationId(4L);
            medication.setMedicationName(name);
            medication.setMedicationDescription("20 tablets");
            medication.setMedicationPrice(80.5);
            medication.setMedicationQuantity(quantity);
        }

        if(name.equals("Nurofen")){
            medication.setMedicationId(5L);
            medication.setMedicationName(name);
            medication.setMedicationDescription("250mg, 80 capsules");
            medication.setMedicationPrice(15.99);
            medication.setMedicationQuantity(quantity);
        }

        if(name.equals("Aspirin")){
            medication.setMedicationId(6L);
            medication.setMedicationName(name);
            medication.setMedicationDescription("100mg, 30 capsules");
            medication.setMedicationPrice(12.75);
            medication.setMedicationQuantity(quantity);
        }

        if(name.equals("Vitamin C")){
            medication.setMedicationId(7L);
            medication.setMedicationName(name);
            medication.setMedicationDescription("280g, 180 capsules");
            medication.setMedicationPrice(26.99);
            medication.setMedicationQuantity(quantity);
        }

        prescriptionList.add(medication);
    }

    private void sumOfPrices(List<Medication> prescriptionList) {
        finalPrice = findViewById(R.id.finalPriceGetPr);

        Double price = 0.0;
        for (int i = 0; i < prescriptionList.size(); i++) {
            Double a = prescriptionList.get(i).getMedicationPrice();
            Integer b = prescriptionList.get(i).getMedicationQuantity();
            price = price + a * b;
        }

        String price2dec = new DecimalFormat("#.##").format(price);

        finalPrice.setText("Total price: " + price2dec + "€");
    }

}