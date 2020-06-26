package com.example.corona.ViewController.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.corona.Model.VN.Timeline;
import com.example.corona.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AnalyticActivity extends AppCompatActivity {
    protected String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "June"
    };
    BarChart barChart;
    ArrayList<String> date = new ArrayList<>();
    ArrayList<BarEntry> data1 = new ArrayList<>();
    ArrayList<BarEntry> data2 = new ArrayList<>();
    ArrayList<BarEntry> data3 = new ArrayList<>();
    ArrayList<Timeline> timelineVN =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytic);

        init();
        BarDataSet barDataSet1 = setBarData(data1, "Nhiễm bệnh", R.color.colorRed);
        BarDataSet barDataSet2 = setBarData(data2, "Tử vong",R.color.colorYellow);
        BarDataSet barDataSet3 = setBarData(data3, "Bình phục",R.color.colorGreen);
        BarData barData = new BarData(barDataSet1,barDataSet2,barDataSet3);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Corona 7 ngay gan day");
        barChart.animateY(1000);

        XAxis axis = barChart.getXAxis();
        axis.setValueFormatter(new IndexAxisValueFormatter(date));
        axis.setCenterAxisLabels(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setGranularity(1);
        axis.setGranularityEnabled(true);

        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(3);

        barData.setBarWidth(0.10f);

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0+barChart.getBarData().getGroupWidth(0.08f,0.44f)*7);
        barChart.getAxisLeft().setAxisMinimum(0);

        barChart.groupBars(0,0.44f,0.08f);
        barChart.invalidate();

    }

    private BarDataSet setBarData(ArrayList<BarEntry> data, String str, int color) {
        BarDataSet barDataSet = new BarDataSet(data, str);
        barDataSet.setColors(getResources().getColor(color));
        barDataSet.setValueTextColor(color);
        barDataSet.setValueTextSize(14f);
        return barDataSet;
    }

    private ArrayList<BarEntry> add() {
        ArrayList<BarEntry> data = new ArrayList<>();
        for(int i = 0 ; i < 14;i++){
            data1.add(new BarEntry(i,timelineVN.get(i).getConfirmed()));
            data2.add(new BarEntry(i,timelineVN.get(i).getDeaths()));
            data3.add(new BarEntry(i,timelineVN.get(i).getRecovered()));
            date.add(timelineVN.get(i).getDate());
        }
//        ArrayList<BarEntry> data = new ArrayList<>();
//        data.add(new BarEntry(1, 890));
//        data.add(new BarEntry(2, 120));
//        data.add(new BarEntry(3, 345));
//        data.add(new BarEntry(4, 548));
//        data.add(new BarEntry(5, 123));
//        data.add(new BarEntry(6, 1000));
//        data.add(new BarEntry(7, 1000));
        return data;
    }

    private void init() {
        barChart = findViewById(R.id.barchart);
        timelineVN = (ArrayList<Timeline>) getIntent().getSerializableExtra("timeline");
        add();
    }

}
