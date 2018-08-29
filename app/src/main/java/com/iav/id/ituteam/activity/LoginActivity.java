package com.iav.id.ituteam.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iav.id.ituteam.MainActivity;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.LoginModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginUsername;
    private EditText edtLoginPassword;
    private Button btnLogin;
    private LinearLayout divDaftar;
    private LoginModel loginModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initView();
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS
                , Manifest.permission.ACCESS_NETWORK_STATE};
        Permissions.check(this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {
                Toast.makeText(LoginActivity.this, "" + Config.ERROR_PERMISSION_SUCCES, Toast.LENGTH_SHORT).show();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(edtLoginUsername.getText().toString().trim(), edtLoginPassword.getText().toString().trim());
            }
        });
    }

    private void login(String username, String password) {

        if (edtLoginUsername.getText().toString().isEmpty() || edtLoginPassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, Config.ERROR_FORM, Toast.LENGTH_SHORT).show();
        } else {
            final ProgressDialog loading = ProgressDialog.show(LoginActivity.this, "Loading", "Mohon tunggu...", false, false);
            ApiService apiService = Client.getInstanceRetrofit();
            apiService.postLogin(username, password)
                    .enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                            loginModels = response.body();
                            if (loginModels.getErrorMsg().equalsIgnoreCase("Login Sukses")){

                                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(Config.SHARED_RULE, loginModels.getRule());
                                editor.putString(Config.SHARED_ID_USER, loginModels.getIdUser());
                                editor.putString(Config.SHARED_NAMA_LENGKAP, loginModels.getNamaLengkap());
                                editor.putString(Config.SHARED_EMAIL, loginModels.getEmail());
                                editor.putString(Config.SHARED_TEMPAT_TGL_LAHIR, loginModels.getTempatTglLahir());
                                editor.putString(Config.SHARED_ALAMAT, loginModels.getAlamat());
                                editor.putString(Config.SHARED_KOTA_KAB, loginModels.getKota_kab());
                                editor.putString(Config.SHARED_PROVINSI, loginModels.getProvinsi());
                                editor.putString(Config.SHARED_NO_HP, loginModels.getNoHp());
                                editor.putString(Config.SHARED_JENIS_KELAMIN, loginModels.getJenisKelamin());
                                editor.putString(Config.SHARED_AGAMA, loginModels.getAgama());
                                editor.putString(Config.SHARED_USERNAME, loginModels.getUsername());
                                editor.putString(Config.SHARED_FOTO_URL, loginModels.getFotoUrl());
                                editor.putString(Config.SHARED_STATUS_VERIFIKASI, loginModels.getStatusVerifikasi());
                                editor.putString(Config.SHARED_TOKEN, loginModels.getToken());
                                editor.putString(Config.SHARED_LAT, loginModels.getLat());
                                editor.putString(Config.SHARED_LNG, loginModels.getLng());
                                editor.putString(Config.SHARED_STATUS_APLIKASI, loginModels.getStatusAplikasi());

                                editor.apply();
                                loading.dismiss();
                                Toast.makeText(LoginActivity.this, "" + loginModels.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                            else {
                                loading.dismiss();
                                Toast.makeText(LoginActivity.this, "" + loginModels.getErrorMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) {
                            loading.dismiss();
                            Toast.makeText(LoginActivity.this, "" + Config.ERROR_LOGIN_GAGAL, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void initView() {
        edtLoginUsername = findViewById(R.id.edt_login_username);
        edtLoginPassword = findViewById(R.id.edt_login_password);
        btnLogin = findViewById(R.id.btn_login);
        divDaftar = findViewById(R.id.div_daftar);
    }
}
