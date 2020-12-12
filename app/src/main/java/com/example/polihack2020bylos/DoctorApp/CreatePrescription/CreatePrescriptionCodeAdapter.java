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


public class CreatePrescriptionCodeAdapter extends ArrayAdapter<Medication> {
    private Context mContext;
    int mResource;
    MedicationDatabase medicationDatabase;

    public CreatePrescriptionCodeAdapter(Context context, int resource, List<Medication> medication) {
        super(context, resource, medication);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Long medicationId = getItem(position).getMedicationId();
        String medicationName = getItem(position).getMedicationName();
        Double medicationPrice = getItem(position).getMedicationPrice();
        Integer medicationQuantity  = getItem(position).getMedicationQuantity();
        String medicationDescription = getItem(position).getMedicationDescription();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);


        Button buttonCrCodeRemove = convertView.findViewById(R.id.buttonCrCodeRemove);
        TextView tvCrCodeName = convertView.findViewById(R.id.tvCrCodeName);
        TextView tvCrCodeQuantityPrice = convertView.findViewById(R.id.tvCrCodeQuantityPrice);
        TextView tvCrCodeDescription = convertView.findViewById(R.id.tvCrCodeDescription);
        ImageView ivCrCodePicture = convertView.findViewById(R.id.ivCrCodePicture);


        tvCrCodeName.setText(medicationName);
        tvCrCodeQuantityPrice.setText(medicationQuantity + " x " + medicationPrice + "€");
        tvCrCodeDescription.setText(medicationDescription);
        setUpList(medicationName, ivCrCodePicture);


        buttonCrCodeRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();

                medicationDatabase = new MedicationDatabase(mContext);
                Medication existingMedication = medicationDatabase.getMedicationById(medicationId);

                if(existingMedication.getMedicationQuantity() > 1){
                    medicationDatabase.removeMedicationById(existingMedication.getMedicationId());

                    existingMedication.setMedicationQuantity(existingMedication.getMedicationQuantity() - 1);

                    medicationDatabase.addToMedicationDatabase(existingMedication);
                }else{
                    medicationDatabase.removeMedicationById(medicationId);
                }



                addAll(medicationDatabase.getMedication());
                notifyDataSetChanged();
            }
        });

        return convertView;
    }


    private void setUpList(String medicationName, ImageView ivMedPicture){
        if(medicationName.equals("Remdesivir")){
            ivMedPicture.setImageResource(R.drawable.reme);
        }

        if(medicationName.equals("Lopinavir")){
            ivMedPicture.setImageResource(R.drawable.lopi);
        }

        if(medicationName.equals("Ritonavir")){
            ivMedPicture.setImageResource(R.drawable.rito);
        }

        if(medicationName.equals("Ivermectin")){
            ivMedPicture.setImageResource(R.drawable.iver);
        }

        if(medicationName.equals("Nurofen")){
            ivMedPicture.setImageResource(R.drawable.nuro);
        }

        if(medicationName.equals("Aspirin")){
            ivMedPicture.setImageResource(R.drawable.aspi);
        }

        if(medicationName.equals("Vitamin C")){
            ivMedPicture.setImageResource(R.drawable.vitaminc);
        }

        if(medicationName.equals("Mask")){
            ivMedPicture.setImageResource(R.drawable.masca2);
        }
    }

}

