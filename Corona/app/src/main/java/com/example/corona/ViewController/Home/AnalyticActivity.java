package com.example.corona.ViewController.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.data.Mapping;
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
import java.util.List;

public class AnalyticActivity extends AppCompatActivity {
    protected String[] mMonths = new String[]{
            "Jan", "Feb", "Mar", "Apr", "May", "June"
    };

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

        AnyChartView anyChartView = findViewById(R.id.chartView);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Thống kê dịch bệnh ở Việt Nam");

        cartesian.yAxis(0).title("Đơn vị người");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);
        List<DataEntry> seriesData = new ArrayList<>();
        for(int i = timelineVN.size()-1 ; i >=0;i--){
            seriesData.add(new CustomDataEntry(timelineVN.get(i).getDate(), timelineVN.get(i).getConfirmed(), timelineVN.get(i).getDeaths(), timelineVN.get(i).getRecovered()));
        }
//        seriesData.add(new CustomDataEntry("1986", 3.6, 2.3, 2.8));
//        seriesData.add(new CustomDataEntry("1987", 7.1, 4.0, 4.1));
//        seriesData.add(new CustomDataEntry("1988", 8.5, 6.2, 5.1));
//        seriesData.add(new CustomDataEntry("1989", 9.2, 11.8, 6.5));
//        seriesData.add(new CustomDataEntry("1990", 10.1, 13.0, 12.5));
//        seriesData.add(new CustomDataEntry("1991", 11.6, 13.9, 18.0));
//        seriesData.add(new CustomDataEntry("1992", 16.4, 18.0, 21.0));
//        seriesData.add(new CustomDataEntry("1993", 18.0, 23.3, 20.3));
//        seriesData.add(new CustomDataEntry("1994", 13.2, 24.7, 19.2));
//        seriesData.add(new CustomDataEntry("1995", 12.0, 18.0, 14.4));
//        seriesData.add(new CustomDataEntry("1996", 3.2, 15.1, 9.2));
//        seriesData.add(new CustomDataEntry("1997", 4.1, 11.3, 5.9));
//        seriesData.add(new CustomDataEntry("1998", 6.3, 14.2, 5.2));
//        seriesData.add(new CustomDataEntry("1999", 9.4, 13.7, 4.7));
//        seriesData.add(new CustomDataEntry("2000", 11.5, 9.9, 4.2));
//        seriesData.add(new CustomDataEntry("2001", 13.5, 12.1, 1.2));
//        seriesData.add(new CustomDataEntry("2002", 14.8, 13.5, 5.4));
//        seriesData.add(new CustomDataEntry("2003", 16.6, 15.1, 6.3));
//        seriesData.add(new CustomDataEntry("2004", 18.1, 17.9, 8.9));
//        seriesData.add(new CustomDataEntry("2005", 17.0, 18.9, 10.1));
//        seriesData.add(new CustomDataEntry("2006", 16.6, 20.3, 11.5));
//        seriesData.add(new CustomDataEntry("2007", 14.1, 20.7, 12.2));
//        seriesData.add(new CustomDataEntry("2008", 15.7, 21.6, 10));
//        seriesData.add(new CustomDataEntry("2009", 12.0, 22.5, 8.9));

        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Nhiễm bệnh");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("Tử vong");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name("Bình phục");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);
    }
    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }


    private ArrayList<BarEntry> add() {
        ArrayList<BarEntry> data = new ArrayList<>();
        for(int i = 0 ; i < 14;i++){
            data1.add(new BarEntry(i,timelineVN.get(i).getConfirmed()));
            data2.add(new BarEntry(i,timelineVN.get(i).getDeaths()));
            data3.add(new BarEntry(i,timelineVN.get(i).getRecovered()));
            date.add(timelineVN.get(i).getDate());
        }

        return data;
    }

    private void init() {
        timelineVN = (ArrayList<Timeline>) getIntent().getSerializableExtra("timeline");
        add();
    }

}
