package com.iav.id.ituteam.activity;

import android.Manifest;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.iav.id.ituteam.MainActivity;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.helper.firebase.NotificationUtils;
import com.iav.id.ituteam.model.LoginModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginUsername;
    private EditText edtLoginPassword;
    private Button btnLogin;
    private LinearLayout divDaftar;
    private LoginModel loginModels;

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private String TAG = "Firebases";
    private String regId, id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initView();
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
        , Manifest.permission.RECORD_AUDIO};
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

        divDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                    Log.e(TAG, "onReceive: Push Message" + message);

                }
            }
        };

        displayFirebaseRegId();

    }

    private void updateTokenFirebase() {
        Toast.makeText(this, "ID user" + id_user, Toast.LENGTH_SHORT).show();
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.updateTokenFirebase("updateTokenFirebase", id_user, regId)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject1 = new JSONObject(response.body().string());
                                String error_msg = jsonObject1.optString("error");
                                Toast.makeText(LoginActivity.this, "" + error_msg, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        regId = pref.getString(Config.SHARE_FIREBASE_TOKEN, "");
        Toast.makeText(this, "" + regId + id_user, Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Firebase reg id: " + regId);
        if (!TextUtils.isEmpty(regId))
            Log.e(TAG, "Firebase Reg Id: " + regId);
        else
            Log.e(TAG, "Firebase Reg Id is not received yet!");
    }


    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
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
                                editor.putString(Config.SHARED_POINt_DONOR, loginModels.getPoint());
                                editor.putString(Config.SHARED_TOTAL_DONOR_DISETUJUI, loginModels.getTotal_donor_darah());

                                editor.apply();
                                loading.dismiss();
                                id_user = sharedPreferences.getString(Config.SHARED_ID_USER, "");
                                updateTokenFirebase();
//                                Toast.makeText(LoginActivity.this, "" + loginModels.getErrorMsg(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {

        new FancyAlertDialog.Builder(this)
                .setTitle("T-SMART")
                .setBackgroundColor(Color.parseColor("#7f0000"))  //Don't pass R.color.colorvalue
                .setMessage("Login dulu baru keluar ya :)")
                .setNegativeBtnText("Tidak")
                .setPositiveBtnBackground(Color.parseColor("#7f0000"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Ya")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.SIDE)
                .isCancellable(true)
                .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(LoginActivity.this, "Yeah", Toast.LENGTH_SHORT).show();
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        finishAffinity();
                    }
                })
                .build();
    }
}
