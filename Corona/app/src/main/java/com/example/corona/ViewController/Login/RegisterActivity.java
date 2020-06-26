package com.example.corona.ViewController.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corona.Model.Register.Register;
import com.example.corona.Network.Body.Register.NewAccount;
import com.example.corona.Network.DataServices;
import com.example.corona.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUserName, edtPass, edtEmail, edtFullName;
    Button btnRegister;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        handleToolbar();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtUserName.getText().toString().trim().equals("")
                        && !edtPass.getText().toString().trim().equals("")
                        && !edtEmail.getText().toString().trim().equals("")
                        && !edtFullName.getText().toString().trim().equals(""))
                    register();
                else{
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
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

    void register() {

        final NewAccount account = new NewAccount(
                edtUserName.getText().toString()
                , edtPass.getText().toString()
                , edtFullName.getText().toString()
                , edtEmail.getText().toString());

        DataServices.getAPIService().register(account)
                .enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {
                        if (response.body().getErrorCode() == 0) {
                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();

                            intent.putExtra("account", account);

                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "k oke", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void init() {
        edtUserName = findViewById(R.id.edt_username);
        edtPass = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edt_email);
        edtFullName = findViewById(R.id.edt_fullname);
        btnRegister = findViewById(R.id.btn_register);
        toolbar = findViewById(R.id.toolbar);
    }
}
