package com.example.corona.ViewController.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corona.Model.Login.UserInfo;
import com.example.corona.Model.TokenFirebase.DeviceTokenFireBase;
import com.example.corona.Model.User;
import com.example.corona.Network.Body.DeviceToken;
import com.example.corona.Network.Body.Register.NewAccount;
import com.example.corona.Network.Body.SocialAccount;
import com.example.corona.Network.DataServices;
import com.example.corona.R;
import com.example.corona.Util.LoadingDialog;
import com.example.corona.ViewController.Home.MainActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.corona.Util.AppConfig.getToken;
import static com.example.corona.Util.AppConfig.handleTokenFirebase;
import static com.example.corona.Util.AppConfig.setLoginWithSocial;
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

    private static final int REGISTER_OK = 2;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    EditText edtUserName, edtPassword;
    Button btnLogin;
    LoadingDialog loadingDialog;
    CallbackManager callbackManager;
    LoginButton loginButton;
    int RC_SIGN_IN = 1;
    TextView tvSignup;
    String tokenFireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivy);
        init();
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email");
        String token = getToken(this);
        if (token != "") {
            navigateHome();
        }
        loginGoogle();
        loginFacebook();


    }


    private void loginGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("690417525971-22iokmecsej2msrjt8r0063pvsb0ivmu.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
//        GoogleSignIn.silentSignIn()
//                .addOnCompleteListener(
//                        this,
//                        new OnCompleteListener<GoogleSignInAccount>() {
//                            @Override
//                            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
//                                handleSignInResult(task);
//                            }
//                        });
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
                loginWithFacebook(loginResult.getAccessToken().getToken());

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

    private void loginWithFacebook(String token) {
        loadingDialog.startLoadingDialog();
        SocialAccount account = new SocialAccount(token);
        DataServices.getAPIService().loginFacebook(account).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.body().getErrorCode() == 0) {
                    setToken(LoginAcitivy.this, response.body().getData().getToken());
                    Toast.makeText(LoginAcitivy.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    loadingDialog.dismissLoadingDialog();
                    setLoginWithSocial(LoginAcitivy.this);
                    handleTokenFirebase();
                } else {
                    Toast.makeText(LoginAcitivy.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    loadingDialog.dismissLoadingDialog();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText(LoginAcitivy.this, " k oke", Toast.LENGTH_SHORT).show();
                loadingDialog.dismissLoadingDialog();
            }
        });
    }

    private void addDeviceID(String deviceID) {
        loadingDialog.startLoadingDialog();

        DataServices.getAPIService().updateFirebaseToken(new DeviceToken(deviceID),getToken(this))
                .enqueue(new Callback<DeviceTokenFireBase>() {
                    @Override
                    public void onResponse(Call<DeviceTokenFireBase> call, Response<DeviceTokenFireBase> response) {
                        loadingDialog.dismissLoadingDialog();
                        navigateHome();

                    }

                    @Override
                    public void onFailure(Call<DeviceTokenFireBase> call, Throwable t) {
                        Toast.makeText(LoginAcitivy.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismissLoadingDialog();

                    }
                });
    }
    public  void handleTokenFirebase() {

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("AA", "getInstanceId failed", task.getException());
                            return;
                        }
                        tokenFireBase = task.getResult().getToken();
                        addDeviceID(tokenFireBase);

                    }
                });
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
        if (requestCode == REGISTER_OK) {
            if (resultCode == Activity.RESULT_OK) {
                NewAccount account = (NewAccount) data.getSerializableExtra("account");

                edtUserName.setText(account.getUsername());
                edtPassword.setText(account.getPassword());


            } else {
            }
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String token = account.getIdToken();
            Log.d("Oke", "signInResult:failed code=" + token);
            // Signed in successfully, show authenticated UI.
//            updateUI(account);
            loginWithGoogle(token);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("AAA", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }

    private void loginWithGoogle(String token) {
        loadingDialog.startLoadingDialog();
        SocialAccount account = new SocialAccount(token);
        DataServices.getAPIService().loginGoogle(account)
                .enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        if (response.body().getErrorCode() == 0) {
                            setToken(LoginAcitivy.this, response.body().getData().getToken());
                            Toast.makeText(LoginAcitivy.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dismissLoadingDialog();
                            setLoginWithSocial(LoginAcitivy.this);
                            handleTokenFirebase();

                        } else {
                            Toast.makeText(LoginAcitivy.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dismissLoadingDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        Toast.makeText(LoginAcitivy.this, " k oke", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismissLoadingDialog();
                    }
                });
    }

    void init() {
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        loadingDialog = new LoadingDialog(LoginAcitivy.this);
        loginButton = findViewById(R.id.login_button);
        tvSignup = findViewById(R.id.link_signup);

        btnLogin.setOnClickListener(this);
        tvSignup.setOnClickListener(this);
    }

    void navigateHome() {
        Intent intent = new Intent(LoginAcitivy.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    void loginAction(String username, String password) {

        User user = new User(username, password);
        DataServices.getAPIService().login(user)
                .enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
//                        if(response.isSuccessful()){
                        if (response.body().getErrorCode() == 0) {
                            setToken(LoginAcitivy.this, response.body().getData().getToken());
                            Toast.makeText(LoginAcitivy.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dismissLoadingDialog();
                            handleTokenFirebase();
                        } else {
                            Toast.makeText(LoginAcitivy.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            loadingDialog.dismissLoadingDialog();
                        }
//                        }
//                        else{
//                            Toast.makeText(LoginAcitivy.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
//                            loadingDialog.dismissLoadingDialog();
//                        }
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
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
                break;
            }
            case R.id.link_signup: {
                startActivityForResult(new Intent(LoginAcitivy.this, RegisterActivity.class), REGISTER_OK);
                break;
            }
            default:
                break;
        }
    }

}
