package com.example.polihack2020bylos.AccountManagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.polihack2020bylos.DoctorApp.DoctorMenuActivity;
import com.example.polihack2020bylos.R;
import com.example.polihack2020bylos.Util.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorLoginActivity extends AppCompatActivity {

    private EditText emailField, passField;
    private Button loginButton;
    private FirebaseAuth fAuth;
    private FrameLayout loadingScreen;
    private TextView create_an_account_button;

    private View emailFieldActivityBar, passwordFieldActivityBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        fAuth = FirebaseAuth.getInstance();
        emailField = findViewById(R.id.email_field);
        passField = findViewById(R.id.password_field);
        loginButton = findViewById(R.id.login_button);
        loadingScreen = findViewById(R.id.loadingOverlay);
        create_an_account_button = findViewById(R.id.create_account_button);

        emailFieldActivityBar = findViewById(R.id.email_field_bar);
        passwordFieldActivityBar = findViewById(R.id.password_field_bar);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), DoctorMenuActivity.class));
            finish();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;

                email = emailField.getText().toString().trim();
                password = passField.getText().toString().trim();

                if (email.isEmpty()) {
                    emailField.setError("This field can't be empty.");
                    return;
                }
                if (password.isEmpty()) {
                    passField.setError("This field can't be empty.");
                    return;
                }

                loadingScreen.setVisibility(ConstraintLayout.VISIBLE);

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), DoctorMenuActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(DoctorLoginActivity.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                            loadingScreen.setVisibility(ConstraintLayout.INVISIBLE);
                        }
                    }
                });

            }
        });

        Util.setInputFieldActivityStatus(emailField, emailFieldActivityBar);
        Util.setInputFieldActivityStatus(passField, passwordFieldActivityBar);
    }
}