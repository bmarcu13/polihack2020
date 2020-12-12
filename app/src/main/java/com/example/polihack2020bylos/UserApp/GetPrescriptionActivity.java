package com.example.polihack2020bylos.UserApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.polihack2020bylos.Entities.Drug;
import com.example.polihack2020bylos.R;

import java.util.ArrayList;
import java.util.HashMap;

public class GetPrescriptionActivity extends AppCompatActivity {

    private HashMap<Integer, String> medicationList = new HashMap<>();
    private ArrayList<Drug> drugQuantities= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_prescription2);

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
                for (int i = 6; i < code.length(); i += 2 ) {
                    drugQuantities.add(new Drug(medicationList.get(Character.getNumericValue(code.charAt(i))),  Character.getNumericValue(code.charAt(i + 1))));
                    Log.d("TAG", "onClick: " + medicationList.get(Character.getNumericValue(code.charAt(i))) + Character.getNumericValue(code.charAt(i + 1)));
                }
            }
        });
    }
}