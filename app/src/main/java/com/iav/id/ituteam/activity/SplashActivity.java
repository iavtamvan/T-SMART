package com.iav.id.ituteam.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.iav.id.ituteam.MainActivity;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
                String username = sp.getString(Config.SHARED_USERNAME, "");
                String email = sp.getString(Config.SHARED_EMAIL, "");
                String rule = sp.getString(Config.SHARED_RULE, "");

                // TODO jika belum masuk ke LoginActivity
                if (username.equalsIgnoreCase("") || TextUtils.isEmpty(username)){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                // TODO jika sudah nantinya akan masuk ke Home
                else {
                    if (rule.contains("Pengguna")){
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
//                    else {
//                        startActivity(new Intent(getApplicationContext(), HomePetugasActivity.class));
//                    }

                }
            }
        }, 2000);
    }
}
