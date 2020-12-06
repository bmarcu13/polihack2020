package com.example.polihack2020bylos.DoctorApp.PatientList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.polihack2020bylos.DoctorApp.AddPatient.Patient;
import com.example.polihack2020bylos.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class PatientsListActivity extends AppCompatActivity {

    private FirebaseFirestore fStore;
    private RecyclerView patientsListRv;
    private FirestoreRecyclerAdapter adapter;
    private CardView addPatientButton;
    private FirebaseAuth fAuth;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);

        addPatientButton = findViewById(R.id.add_patient_button);
        fAuth = FirebaseAuth.getInstance();
        backButton = findViewById(R.id.back_arrow);

        patientsListRv = findViewById(R.id.patients_list_rv);
        fStore = FirebaseFirestore.getInstance();
        String clickUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = fStore.collection("patients").whereEqualTo("doctor_id", fAuth.getCurrentUser().getUid());
        FirestoreRecyclerOptions<Patient> options = new FirestoreRecyclerOptions.Builder<Patient>().setQuery(query, Patient.class).build();

        adapter = new FirestoreRecyclerAdapter<Patient, PatientsViewHolder>(options) {
            @NonNull
            @Override
            public PatientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_patient, parent, false);
                return new PatientsViewHolder(view);
            }

            @SuppressLint("SetTextI18n")
            @Override
            protected void onBindViewHolder(@NonNull PatientsViewHolder holder, int position, @NonNull Patient model) {
                holder.name.setText(model.getName());
//              holder.age.setText(model.getAge().toString());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PatientsListActivity.this, PatientDisplayActivity.class);
                        intent.putExtra("name", model.getName());
                        intent.putExtra("age", model.getAge());
                        intent.putExtra("sex", model.getSex());
                        intent.putExtra("got_better", model.isgot_better());
                        intent.putExtra("blood_type", model.getBlood_type());
                        intent.putStringArrayListExtra("symptomps", model.getSymptomps());
                        intent.putStringArrayListExtra("medication", model.getmedication());
                        intent.putStringArrayListExtra("side_effects", model.getSide_effects());

                        startActivity(intent);
                    }
                });
            }
        };

        patientsListRv.setHasFixedSize(true);
        patientsListRv.setLayoutManager(new LinearLayoutManager(this));
        patientsListRv.setAdapter(adapter);

        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientsListActivity.this, AddPatientActivity.class));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private class PatientsViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
//        private TextView age;
//        private TextView bloodType;
//        private TextView sex;

        public PatientsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.patient_name);
//          age = itemView.findViewById(R.id.patient_age);
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

}