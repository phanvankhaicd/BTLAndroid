package com.example.corona.ViewController.UserInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corona.Model.Update.UpdateAccountRS;
import com.example.corona.Model.UserInfoRS.UserInfo;
import com.example.corona.Network.Body.UpdateAccount;
import com.example.corona.Network.DataServices;
import com.example.corona.R;
import com.example.corona.Util.LoadingDialog;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.corona.Util.AppConfig.getToken;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtCMND, edtPhone, edtEmail, edtBHXH, edtAddress;
    TextView tvBirthDay;
    int mYear, mMonth, mDay;
    String dateTime;
    Toolbar toolbar;
    RadioButton rdNam, rdNu;
    LoadingDialog loadingDialog;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_screen);
        init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        handleDateTime();
        handleOnClick();
        getUserInfo();

    }

    private void handleOnClick() {

        tvBirthDay.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    private void init() {
        loadingDialog = new LoadingDialog(UserInfoActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edtName = findViewById(R.id.edt_name);
        edtAddress = findViewById(R.id.edt_address);
        edtBHXH = findViewById(R.id.edt_bhxh);
        edtCMND = findViewById(R.id.edt_cmnd);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        tvBirthDay = findViewById(R.id.tv_birthday);
        rdNam = findViewById(R.id.rd_nam);
        rdNu = findViewById(R.id.rd_nu);
        btnUpdate = findViewById(R.id.btn_sent);
    }

    private void getUserInfo() {
        loadingDialog.startLoadingDialog();
        DataServices.getAPIService().getUser(getToken(UserInfoActivity.this))
                .enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        if (response.isSuccessful()) {
                            edtName.setText(response.body().getData().getUserFullname());
                            edtAddress.setText(response.body().getData().getUserAddress());
                            edtBHXH.setText(response.body().getData().getUserBhxh());
                            edtCMND.setText(response.body().getData().getUserCmt());
                            edtEmail.setText(response.body().getData().getUserEmail());
                            edtPhone.setText(response.body().getData().getUserPhoneNo());
                            tvBirthDay.setText(response.body().getData().getUserBirthday());
                            if (response.body().getData().getUserGender() == null ||
                                    response.body().getData().getUserGender() == 0)
                                rdNu.setChecked(true);
                            else
                                rdNam.setChecked(true);
                            loadingDialog.dismissLoadingDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        Toast.makeText(UserInfoActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismissLoadingDialog();
                    }
                });
    }

    private void updateUser() {
        UpdateAccount account = new UpdateAccount(
                edtName.getText().toString(),
                edtEmail.getText().toString(),
                tvBirthDay.getText().toString(),
                edtAddress.getText().toString(),
                edtPhone.getText().toString(),
                edtCMND.getText().toString(),
                edtBHXH.getText().toString(),
                rdNam.isChecked() ? "1" : "0"
        );
        loadingDialog.startLoadingDialog();
        DataServices.getAPIService().updateAccount(account, getToken(this))
                .enqueue(new Callback<UpdateAccountRS>() {
                    @Override
                    public void onResponse(Call<UpdateAccountRS> call, Response<UpdateAccountRS> response) {
                        Toast.makeText(UserInfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        loadingDialog.dismissLoadingDialog();
                    }

                    @Override
                    public void onFailure(Call<UpdateAccountRS> call, Throwable t) {
                        Toast.makeText(UserInfoActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismissLoadingDialog();
                    }
                });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_birthday:
                showDatePickerDialog();
                break;
            case R.id.btn_sent:
                updateUser();
                break;
        }
    }

    void handleDateTime() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

    }

    public void showDatePickerDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(UserInfoActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateTime = year
                                + "-" + ((monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : (monthOfYear + 1))
                                + "-" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
                        tvBirthDay.setText(dateTime);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
