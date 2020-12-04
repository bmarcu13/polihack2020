package com.example.polihack2020bylos;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserSignupActivity extends AppCompatActivity {

    private EditText emailField, passField, confirmPassField;
    private Button signupButton;
    private FirebaseAuth fAuth;
    private FrameLayout loadingScreen;
    private TextView goToLogin;

    private View emailFieldActivityBar, passwordFieldActivityBar, passwordConfirmationActivityBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        fAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.email_field);
        passField = findViewById(R.id.password_field);
        confirmPassField = findViewById(R.id.confirm_password_field);

        signupButton = findViewById(R.id.signup_button);
        loadingScreen = findViewById(R.id.loadingOverlay);

        goToLogin = findViewById(R.id.go_to_login);

        emailFieldActivityBar = findViewById(R.id.email_field_bar);
        passwordFieldActivityBar = findViewById(R.id.password_field_bar);
        passwordConfirmationActivityBar = findViewById(R.id.confirm_password_bar);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, passwordConfirmation;

                email = emailField.getText().toString().trim();
                password = passField.getText().toString().trim();
                passwordConfirmation = confirmPassField.getText().toString().trim();

                if (email.isEmpty()) {
                    emailField.setError("This field can't be empty");
                    return;
                }

                if (password.isEmpty()){
                    passField.setError("This field can't be empty");
                    return;
                }
                if (passwordConfirmation.isEmpty()) {
                    confirmPassField.setError("This field can't be empty");
                    return;
                }
                if (!password.equals(passwordConfirmation)) {
                    passField.setError("The passwords don't match");
                    return;
                }
                if (password.length() < 6) {
                    passField.setError("Password must be at least 6 characters long");
                    return;
                }
                loadingScreen.setVisibility(ConstraintLayout.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            startActivity(new Intent(UserSignupActivity.this, MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(UserSignupActivity.this, "Error:" + task.getException(), Toast.LENGTH_SHORT).show();
                            loadingScreen.setVisibility(ConstraintLayout.INVISIBLE);
                        }
                    }
                });
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Util.setInputFieldActivityStatus(emailField, emailFieldActivityBar);
        Util.setInputFieldActivityStatus(passField, passwordFieldActivityBar);
    }
}