package com.example.corona.ViewController.Declare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.corona.Model.Declare;
import com.example.corona.Model.HistoryDeclare;
import com.example.corona.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.corona.Util.AppConfig.timeConverse;

public class HistoryDeclareAdapter extends BaseAdapter {
    ArrayList<Declare> data;
    Context context;

    public HistoryDeclareAdapter(ArrayList<Declare> data, Context context) {
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

        Declare i = data.get(position);
        String[] time = timeConverse(i.getCreatedAt()).split(" ");

        tvDate.setText(time[0]);
        tvTime.setText(time[1]);
        tvStatus.setText(i.getStatus().equals("BAD") ? "Nguy cơ nhiễm bệnh" : "Bình thường");
        tvStatus.setBackgroundResource(i.getStatus().equals("BAD") ? R.drawable.custom_history_bad : R.drawable.custom_history_safe);
        tvInfo.setText(i.getDescription());

        return convertView;
    }
}
