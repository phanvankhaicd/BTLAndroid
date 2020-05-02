package com.example.corona.ViewController.Declare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.corona.Model.HistoryDeclare;
import com.example.corona.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryDeclareAdapter extends BaseAdapter {
    ArrayList<HistoryDeclare> data;
    Context context;

    public HistoryDeclareAdapter(ArrayList<HistoryDeclare> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.history_declare_item,parent,false);

        TextView tvDate, tvTime,tvInfo,tvStatus;

        tvDate = convertView.findViewById(R.id.tv_date);
        tvTime = convertView.findViewById(R.id.tv_time);
        tvStatus = convertView.findViewById(R.id.tv_status);
        tvInfo = convertView.findViewById(R.id.tv_info);

        HistoryDeclare i = data.get(position);

        tvDate.setText("01/01/2020");
        tvTime.setText("20:20");
        tvStatus.setText(i.getStatus()== "BAD" ? "Nguy cơ nhiễm bệnh" : "Bình thường");
        tvInfo.setText(i.getDescription());

        return convertView;
    }
}
