package com.example.corona.ViewController.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corona.Model.ChangePass.ChangePassRespone;
import com.example.corona.Network.Body.ChangePass;
import com.example.corona.Network.DataServices;
import com.example.corona.R;
import com.example.corona.Util.AppConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassActivity extends AppCompatActivity {

    EditText edtOldPass, edtNewPass, edtRetype;
    Button btnChange;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        init();
        handleToolbar();
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtNewPass.getText().toString().equals("")
                        && !edtOldPass.getText().toString().equals("")
                        && !edtRetype.getText().toString().equals("")) {
                    if(!edtNewPass.getText().toString().equals(edtRetype.getText().toString())){
                        Toast.makeText(ChangePassActivity.this, "Mật khẩu không trùng nhau.", Toast.LENGTH_SHORT).show();
                    }else{
                        changePass();
                    }
                }else{
                    Toast.makeText(ChangePassActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void handleToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void changePass() {
        ChangePass pass = new ChangePass(edtOldPass.getText().toString(),edtNewPass.getText().toString());

        DataServices.getAPIService().changePass(pass, AppConfig.getToken(this))
                .enqueue(new Callback<ChangePassRespone>() {
                    @Override
                    public void onResponse(Call<ChangePassRespone> call, Response<ChangePassRespone> response) {
                        if(response.body().getErrorCode()==0)
                            Toast.makeText(ChangePassActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(ChangePassActivity.this, "Thay đổi thất bại vui lòng thử lại", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ChangePassRespone> call, Throwable t) {
                        Toast.makeText(ChangePassActivity.this, "Thay đổi thất bại vui lòng thử lại", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void init() {
        edtNewPass = findViewById(R.id.edt_new_password);
        edtOldPass = findViewById(R.id.edt_old_password);
        edtRetype = findViewById(R.id.edt_retype_password);
        btnChange = findViewById(R.id.btn_change_pass);
        toolbar = findViewById(R.id.toolbar);
    }
}
