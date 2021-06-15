package com.inha.longstone;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Frag_chart extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_chart,container,false);

        // BarChart 코드
        BarChart barChart = view.findViewById(R.id.barChart1);
        BarChart barChart2 = view.findViewById(R.id.barChart2);
        BarChart barChart3 = view.findViewById(R.id.barChart3);

        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(00,0));
        visitors.add(new BarEntry(02,10));
        visitors.add(new BarEntry(04,15));
        visitors.add(new BarEntry(06,20));
        visitors.add(new BarEntry(8,30));
        visitors.add(new BarEntry(10,50));
        visitors.add(new BarEntry(12,60));
        visitors.add(new BarEntry(14,65));
        visitors.add(new BarEntry(16,65));
        visitors.add(new BarEntry(18,70));
        visitors.add(new BarEntry(20,65));
        visitors.add(new BarEntry(22,55));
        visitors.add(new BarEntry(24,50));

        ArrayList<Integer> barChartColors = new ArrayList<>();
        barChartColors.add(Color.parseColor("#B0C2EE"));    //파랑
        barChartColors.add(Color.parseColor("#B0C2EE"));    //파랑
        barChartColors.add(Color.parseColor("#B0C2EE"));    //파랑
        barChartColors.add(Color.parseColor("#B0C2EE"));    //파랑
        barChartColors.add(Color.parseColor("#B0C2EE"));    //파랑

        barChartColors.add(Color.parseColor("#9CECD9"));    //연두

        barChartColors.add(Color.parseColor("#F7D9B0"));    //노랑

        barChartColors.add(Color.parseColor("#F59696"));    //빨강
        barChartColors.add(Color.parseColor("#F59696"));    //빨강
        barChartColors.add(Color.parseColor("#F59696"));    //빨강
        barChartColors.add(Color.parseColor("#F59696"));    //빨강

        barChartColors.add(Color.parseColor("#9CECD9"));    //연두
        barChartColors.add(Color.parseColor("#9CECD9"));    //연두


        BarDataSet barDataSet = new BarDataSet(visitors,"사용 인원");
        //barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        barDataSet.setColors(barChartColors);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(8f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("정석학술정보관 - 제 1열람실");
        barChart.animateY(1300);

        barChart2.setFitBars(true);
        barChart2.setData(barData);
        barChart2.getDescription().setText("정석학술정보관 - 제 2열람실");
        barChart2.animateY(1300);

        barChart3.setFitBars(true);
        barChart3.setData(barData);
        barChart3.getDescription().setText("해동 라운지");
        barChart3.animateY(1300);


        // Pie Chart 코드
        PieChart pieChart = view.findViewById(R.id.pieChart);
        PieChart pieChart2 = view.findViewById(R.id.pieChart2);
        PieChart pieChart3 = view.findViewById(R.id.pieChart3);


        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(15,"의심좌석"));
        pieEntries.add(new PieEntry(60,"이용중"));
        pieEntries.add(new PieEntry(30,"이용가능"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "좌석 현황");
        //pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FF9D9D"));//#e8adb6
        colors.add(Color.parseColor("#C9C3C3"));//#999393
        colors.add(Color.parseColor("#B0B8E8"));//#B4BADC

        pieDataSet.setColors(colors);
        //pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("좌석 현황");
        pieChart.setCenterTextSize(11f);
        pieChart.animate();
        pieChart.animateY(1400, Easing.EaseInOutQuad);
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(10f);
        pieChart.setHoleRadius(20);
        pieChart.setTransparentCircleRadius(25);
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        pieChart2.setData(pieData);
        pieChart2.getDescription().setEnabled(false);
        pieChart2.setCenterText("좌석 현황");
        pieChart2.setCenterTextSize(11f);
        pieChart2.animate();
        pieChart2.animateY(1400, Easing.EaseInOutQuad);
        pieChart2.setEntryLabelColor(Color.WHITE);
        pieChart2.setEntryLabelTextSize(10f);
        pieChart2.setHoleRadius(20);
        pieChart2.setTransparentCircleRadius(25);
        Legend legend2 = pieChart2.getLegend();
        legend2.setEnabled(false);


        pieChart3.setData(pieData);
        pieChart3.getDescription().setEnabled(false);
        pieChart3.setCenterText("좌석 현황");
        pieChart3.setCenterTextSize(11f);
        pieChart3.animate();
        pieChart3.animateY(1400, Easing.EaseInOutQuad);
        pieChart3.setEntryLabelColor(Color.WHITE);
        pieChart3.setEntryLabelTextSize(10f);
        pieChart3.setHoleRadius(20);
        pieChart3.setTransparentCircleRadius(30);
        Legend legend3 = pieChart3.getLegend();
        legend3.setEnabled(false);

        return view;
    }
}
