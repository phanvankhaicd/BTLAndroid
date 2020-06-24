package com.example.corona.ViewController.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.corona.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class AnalyticActivity extends AppCompatActivity {
    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "June"
    };
    LineChart lineChart;
    ArrayList<String> xAXES = new ArrayList<>();
    ArrayList<Entry> yAXESsin = new ArrayList<>();
    ArrayList<Entry> yAXEScos = new ArrayList<>();
    double x = 0;
    int numDataPoints = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytic);

        init();

    }

    private void init() {
        lineChart = findViewById(R.id.lineChart);
    }

}
