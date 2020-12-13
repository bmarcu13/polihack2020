package com.example.polihack2020bylos.DoctorApp.CreatePrescription;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.polihack2020bylos.Entities.Medication;
import com.example.polihack2020bylos.R;
import com.example.polihack2020bylos.UserApp.MedicationCart.MedicationDatabase;

import java.util.List;


public class CreatePrescriptionAdapter extends ArrayAdapter<String> {
    private Context mContext;
    int mResource;
    MedicationDatabase medicationDatabase;

    public CreatePrescriptionAdapter(Context context, int resource, List<String> name) {
        super(context, resource, name);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String nameMed = getItem(position);


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);


        Button buttonCrPrAdd = convertView.findViewById(R.id.buttonCrPrAdd);
        TextView tvCrPrName = convertView.findViewById(R.id.tvCrPrName);
        TextView tvCrPrDescription = convertView.findViewById(R.id.tvCrPrDescription);
        ImageView ivCrPrPicture = convertView.findViewById(R.id.ivCrPrPicture);


        tvCrPrName.setText(nameMed);
        setUpAdapter(nameMed, tvCrPrDescription, ivCrPrPicture);

        buttonCrPrAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInMedicationList(nameMed);
            }
        });

        return convertView;
    }


    private void setUpAdapter(String medicationName, TextView tvMedDescription, ImageView ivMedPicture){
        if(medicationName.equals("Remdesivir")){
            tvMedDescription.setText("100mg, 400€");
            ivMedPicture.setImageResource(R.drawable.reme);
        }

        if(medicationName.equals("Lopinavir")){
            tvMedDescription.setText("160ml, 379.99€");
            ivMedPicture.setImageResource(R.drawable.lopi);
        }

        if(medicationName.equals("Ritonavir")){
            tvMedDescription.setText("100mg, 30 tablets, 90€");
            ivMedPicture.setImageResource(R.drawable.rito);
        }

        if(medicationName.equals("Ivermectin")){
            tvMedDescription.setText("20 tablets, 80.5€");
            ivMedPicture.setImageResource(R.drawable.iver);
        }

        if(medicationName.equals("Nurofen")){
            tvMedDescription.setText("250mg, 80 capsules, 15.99€");
            ivMedPicture.setImageResource(R.drawable.nuro);
        }

        if(medicationName.equals("Aspirin")){
            tvMedDescription.setText("100mg, 30 capsules, 12.75€");
            ivMedPicture.setImageResource(R.drawable.aspi);
        }

        if(medicationName.equals("Vitamin C")){
            tvMedDescription.setText("280g, 180 capsules, 26.99€");
            ivMedPicture.setImageResource(R.drawable.vitaminc);
        }

        if(medicationName.equals("Mask")){
            tvMedDescription.setText("50 pcs, 3 layers, 11.99€");
            ivMedPicture.setImageResource(R.drawable.masca2);
        }
    }



    private void addInMedicationList(String medicationName){

        MedicationDatabase medicationDatabase = new MedicationDatabase(mContext);

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

