package com.example.polihack2020bylos.UserApp.MedicationCart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.polihack2020bylos.R;

import java.util.List;

public class MedicationListAdapter extends ArrayAdapter<Medication> {
    private Context mContext;
    int mResource;
    MedicationDatabase medicationDatabase;

    public MedicationListAdapter(Context context, int resource, List<Medication> medication) {
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


        Button buttonRemove = convertView.findViewById(R.id.buttonRemove);
        TextView tvMedicationName = convertView.findViewById(R.id.tvMedicationName);
        TextView tvMedicationQuantityPrice = convertView.findViewById(R.id.tvMedicationQuantityPrice);
        TextView tvMedicationDescription = convertView.findViewById(R.id.tvMedicationDescription);
        ImageView ivMedicationPicture = convertView.findViewById(R.id.ivMedicationPicture);


        tvMedicationName.setText(medicationName);
        tvMedicationQuantityPrice.setText(medicationQuantity + " x " + medicationPrice + "€");
        tvMedicationDescription.setText(medicationDescription);

        /////////////////////////set picture



        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();

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

}
