package com.example.polihack2020bylos.UserApp.GetPrescription;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.polihack2020bylos.Entities.Medication;
import com.example.polihack2020bylos.R;
import com.example.polihack2020bylos.Databases.MedicationDatabase;

import java.util.List;


public class GetPrescriptionAdapter extends ArrayAdapter<Medication> {
    private Context mContext;
    int mResource;
    MedicationDatabase medicationDatabase;

    public GetPrescriptionAdapter(Context context, int resource, List<Medication> medication) {
        super(context, resource, medication);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        String getPrName = getItem(position).getMedicationName();
        Double getPrPrice = getItem(position).getMedicationPrice();
        Integer getPrQuantity  = getItem(position).getMedicationQuantity();
        String getPrDescription = getItem(position).getMedicationDescription();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);


        TextView tvGetPrName = convertView.findViewById(R.id.tvGetPrName);
        TextView tvGetPrQuantityPrice = convertView.findViewById(R.id.tvGetPrQuantityPrice);
        TextView tvGetPrDescription = convertView.findViewById(R.id.tvGetPrDescription);
        ImageView ivGetPrPicture = convertView.findViewById(R.id.ivGetPrPicture);


        tvGetPrName.setText(getPrName);
        tvGetPrQuantityPrice.setText(getPrQuantity + " x " + getPrPrice + "€");
        tvGetPrDescription.setText(getPrDescription);
        setUpList(getPrName, ivGetPrPicture);

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
