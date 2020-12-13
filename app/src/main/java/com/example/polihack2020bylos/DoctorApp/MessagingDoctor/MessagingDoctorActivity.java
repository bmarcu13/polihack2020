package com.example.polihack2020bylos.DoctorApp.MessagingDoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.polihack2020bylos.DoctorApp.DoctorMenuActivity;
import com.example.polihack2020bylos.R;

public class MessagingDoctorActivity extends AppCompatActivity {

    ImageView back;
    TextView tvPC1, tvPC2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_doctor);

        back = findViewById(R.id.back_arrow_messaging_doctor);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvPC1 = findViewById(R.id.tvPatientChat1);
        tvPC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessagingDoctorActivity.this, MessagingDoctorOnePersonActivity.class);
                startActivity(intent);
            }
        });

        tvPC2 = findViewById(R.id.tvPatientChat2);
        tvPC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessagingDoctorActivity.this, MessagingDoctorOnePersonActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}