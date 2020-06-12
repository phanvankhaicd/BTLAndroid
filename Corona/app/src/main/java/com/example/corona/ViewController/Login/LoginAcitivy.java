package com.example.corona.ViewController.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;
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

    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    EditText edtUserName, edtPassword;
    Button btnLogin;
    LoadingDialog loadingDialog;
    CallbackManager callbackManager;
    LoginButton loginButton;
    int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivy);
        init();
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email");
        String token = getToken(this);
        if(token!=""){
            navigateHome();
        }
        loginGoogle();
        loginFacebook();
    }

    private void loginGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    private void loginFacebook() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginAcitivy.this, "oke", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
//
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d("Oke", "signInResult:failed code=");
            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("AAA", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }

    void init() {
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        loadingDialog = new LoadingDialog(LoginAcitivy.this );
        loginButton =  findViewById(R.id.login_button);
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
