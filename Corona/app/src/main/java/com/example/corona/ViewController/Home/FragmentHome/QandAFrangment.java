package com.example.corona.ViewController.Home.FragmentHome;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.corona.Model.ReflectionRS.Reflection;
import com.example.corona.Network.Body.ReflectionInfo;
import com.example.corona.Network.DataServices;
import com.example.corona.R;
import com.example.corona.Util.LoadingDialog;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.corona.Util.AppConfig.getToken;


/**
 * A simple {@link Fragment} subclass.
 */
public class QandAFrangment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    public QandAFrangment() {
        // Required empty public constructor
    }

    LinearLayout lnCall, lnDate;
    Button btnSent;
    CheckBox cbDieuKhoan, cbqt1, cbqt2, cbqt3;
    EditText edtDes, edtAddress;
    String dateTime;
    TextView tvDateTime;
    int mYear, mMonth, mDay, mHour, mMinute, mSecond;
    LoadingDialog loadingDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_and_a, container, false);
        init(view);
        onbtnClick();
        handleDateTime();
//        showDatePickerDialog(view);
        return view;
    }

    void uncheck() {
        cbDieuKhoan.setChecked(false);
        cbqt1.setChecked(false);
        cbqt2.setChecked(false);
        cbqt3.setChecked(false);
        edtDes.setText("");
        edtAddress.setText("");
    }

    private void onbtnClick() {
        cbDieuKhoan.setOnCheckedChangeListener(this);
        lnCall.setOnClickListener(this);
        lnDate.setOnClickListener(this);
        btnSent.setOnClickListener(this);
    }

    private void init(View view) {
        lnCall = view.findViewById(R.id.ln_call);
        btnSent = view.findViewById(R.id.btn_sent);
        cbDieuKhoan = view.findViewById(R.id.checkbox_dieukhoan);
        handleCheckbox(cbDieuKhoan.isChecked());
        cbqt1 = view.findViewById(R.id.checkbox_qt1);
        cbqt2 = view.findViewById(R.id.checkbox_qt2);
        cbqt3 = view.findViewById(R.id.checkbox_qt3);
        edtDes = view.findViewById(R.id.edt_des);
        edtAddress = view.findViewById(R.id.edt_address);
        lnDate = view.findViewById(R.id.ln_date);
        tvDateTime = view.findViewById(R.id.tv_date);
        loadingDialog = new LoadingDialog(getActivity());
    }

    void handleDateTime() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        dateTime = mYear
                + "-" + ((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)) + "-" +
                (mDay < 10 ? "0" + mDay : mDay)
                + "T" + (mHour < 10 ? "0" + mHour : mHour)
                + ":" + (mMinute < 10 ? "0" + mMinute : mMinute) + ":00";
        tvDateTime.setText(dateTime);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkbox_dieukhoan:
                handleCheckbox(isChecked);
                break;
        }
    }

    public void showDatePickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateTime = year
                                + "-" + ((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1))
                                + "-" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth) + "T" + dateTime;
                        tvDateTime.setText(dateTime);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void showTimePickerDialog() {


        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        dateTime = (hourOfDay < 10 ? "0" + hourOfDay : hourOfDay)
                                + ":" + (minute < 10 ? "0" + minute : minute) + ":00";
                        showDatePickerDialog();
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    void submitReflectionInfo() {
        ReflectionInfo info = new ReflectionInfo(
                cbqt1.isChecked() ? 1 : 0,
                cbqt2.isChecked() ? 1 : 0,
                cbqt3.isChecked() ? 1 : 0,
                edtDes.getText().toString(),
                dateTime,
                edtAddress.getText().toString()
        );

        loadingDialog.startLoadingDialog();
        DataServices.getAPIService().createReflectionInfo(info, getToken(getContext()))
                .enqueue(new Callback<Reflection>() {
                    @Override
                    public void onResponse(Call<Reflection> call, Response<Reflection> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "Gửi thông tin thành công", Toast.LENGTH_SHORT).show();
                            uncheck();
                        } else
                            Toast.makeText(getContext(), "Gửi thông tin thất bại", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismissLoadingDialog();

                    }

                    @Override
                    public void onFailure(Call<Reflection> call, Throwable t) {
                        Toast.makeText(getContext(), "Vui lòng kiểm tra lại đường truyền", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismissLoadingDialog();

                    }
                });
    }

    public void handleCheckbox(boolean isCheck) {
        btnSent.setBackgroundResource(isCheck ? R.drawable.custom_btn_send : R.drawable.custom_btn_send_disable);
        btnSent.setEnabled(isCheck);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ln_call:
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:19009095"));
                break;
            case R.id.ln_date:
                showTimePickerDialog();
                break;
            case R.id.btn_sent:
                submitReflectionInfo();
                break;
        }
    }
}
