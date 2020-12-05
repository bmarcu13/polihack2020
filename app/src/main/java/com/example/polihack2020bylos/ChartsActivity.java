package com.example.polihack2020bylos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartsActivity extends AppCompatActivity {

    private float agePieChartXPosition, agePieChartYPosition;
    private View topView;

    private PieChart age_pie_chart;

    private int[] colorsArray = new int[]{Color.LTGRAY, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);


        setTitle("");

        age_pie_chart = findViewById(R.id.pie_chart1);
        topView = findViewById(R.id.top_view);

        agePieChartXPosition = topView.getWidth() / 2;
        agePieChartYPosition = topView.getHeight() + 10f;

        PieDataSet pieDataSet = new PieDataSet(dataValues1(), "");
        pieDataSet.setColors((ColorTemplate.COLORFUL_COLORS));
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setDrawValues(false);

        PieData pieData = new PieData(pieDataSet);

        setDescription(age_pie_chart.getDescription());

        age_pie_chart.setData(pieData);
        age_pie_chart.setCenterText("AGE");
        age_pie_chart.setCenterTextSize(30f);
        age_pie_chart.animate();
        age_pie_chart.getLegend().setEnabled(false);
        age_pie_chart.setEntryLabelTextSize(15f);
        age_pie_chart.setEntryLabelColor(Color.BLACK);
        age_pie_chart.setTouchEnabled(false);

    }

    private void setDescription(Description description) {
        description.setText("Age groups of risk");
        description.setTextSize(15f);
    }

    private ArrayList<PieEntry> dataValues1() {
        ArrayList<PieEntry> dataVals = new ArrayList<>();

        dataVals.add(new PieEntry(50, "70+"));
        dataVals.add(new PieEntry(45, "60-69"));
        dataVals.add(new PieEntry(40, "50-59"));
        dataVals.add(new PieEntry(40, "40-49"));
        dataVals.add(new PieEntry(30, "30-39"));
        dataVals.add(new PieEntry(27, "20-29"));
        dataVals.add(new PieEntry(18, "10-19"));
        dataVals.add(new PieEntry(10, "0-9"));

        return dataVals;

    }
}