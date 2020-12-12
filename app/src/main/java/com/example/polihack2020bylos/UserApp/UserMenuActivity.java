package com.example.polihack2020bylos.UserApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.polihack2020bylos.R;
import com.example.polihack2020bylos.StartupPageActivity;
import com.example.polihack2020bylos.UserApp.ChartShop.ChartsActivity;
import com.google.firebase.auth.FirebaseAuth;

public class UserMenuActivity extends AppCompatActivity {

    private Button logoutButton, personalizeDataButton;
    private TextView goToCharts;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        fAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logout);
        goToCharts = findViewById(R.id.go_to_charts);
        personalizeDataButton = findViewById(R.id.go_to_personalized_data);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                startActivity(new Intent(UserMenuActivity.this, StartupPageActivity.class));
                finish();
            }
        });

        findViewById(R.id.go_to_decoding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMenuActivity.this, GetPrescriptionActivity.class));
            }
        });

        goToCharts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserMenuActivity.this, ChartsActivity.class));
            }
        });
    }
}