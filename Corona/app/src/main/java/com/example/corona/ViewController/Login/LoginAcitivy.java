package com.example.corona.ViewController.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corona.Model.Token;
import com.example.corona.Model.User;
import com.example.corona.Network.DataServices;
import com.example.corona.R;
import com.example.corona.Util.LoadingDialog;
import com.example.corona.ViewController.Home.MainActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.corona.Util.AppConfig.getToken;
import static com.example.corona.Util.AppConfig.setToken;

//import com.orhanobut.logger.Logger;
//
//import my.com.myapplication.Model.LoginResult;
//import my.com.myapplication.Network.DataServices;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import my.com.myapplication.R;
//import my.com.myapplication.ViewController.Product.ListProductActivity;

public class LoginAcitivy extends AppCompatActivity implements View.OnClickListener {

    EditText edtUserName, edtPassword;
    Button btnLogin;
    LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivy);
        init();

        String token = getToken(this);
        if(token!=""){
            navigateHome();
        }
    }

    void init() {
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        loadingDialog = new LoadingDialog(LoginAcitivy.this );
    }

    void navigateHome(){
        Intent intent = new Intent(LoginAcitivy.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    void loginAction(String username, String password) {

        User user =new User(username,password);
        DataServices.getAPIService().login(user)
                .enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if(response.isSuccessful()){
                            setToken(LoginAcitivy.this,response.body().getToken());
                            Toast.makeText(LoginAcitivy.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            loadingDialog.dismissLoadingDialog();
                            navigateHome();
                        }
                        else{
                            Toast.makeText(LoginAcitivy.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            loadingDialog.dismissLoadingDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Toast.makeText(LoginAcitivy.this, " k oke", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismissLoadingDialog();
                    }
                });
//        // valid data
//        final ProgressDialog dialog = ProgressDialog.show(this, "Đăng Nhập",
//                "Đang đăng nhập. Vui lòng đợi...", true);
//        // sent request
//        DataServices.getAPIService().login(edtUserName.getText().toString(),
//                edtPassword.getText().toString()).enqueue(new Callback<LoginResult>() {
//            @Override
//            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
//                dialog.dismiss();
//                Logger.d(response.body().message);
//                if (response.body().status == 1) {
//                    Toast.makeText(LoginAcitivy.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginAcitivy.this, ListProductActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResult> call, Throwable t) {
//                dialog.dismiss();
//                Toast.makeText(LoginAcitivy.this, "Đã có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
//                Logger.e(t.toString());
//            }
//        });
//
//
    }
//

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                loadingDialog.startLoadingDialog();
                loginAction(
                        edtUserName.getText().toString(),
                        edtPassword.getText().toString());
            }
            default:
                break;
        }
    }
}
