package com.example.polihack2020bylos.DoctorApp.PersonalizedData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.polihack2020bylos.Entities.ProgressBarData;
import com.example.polihack2020bylos.R;
import com.example.polihack2020bylos.UserApp.MedicationCart.MedicationDatabase;

import java.util.List;

public class ProgressBarAdapter extends ArrayAdapter<ProgressBarData> {
    private Context mContext;
    int mResource;
    MedicationDatabase barData;

    public ProgressBarAdapter(@NonNull Context context, int resource, @NonNull List<ProgressBarData> barData) {
        super(context, resource, barData);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String barName = getItem(position).getName();
        int barValue = getItem(position).getValue();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        RoundCornerProgressBar progressBar = convertView.findViewById(R.id.progress_bar);
        progressBar.setProgress((float)barValue);
        TextView barTitle = convertView.findViewById(R.id.bar_title);
        barTitle.setText(barName);

        return convertView;
    }
}
