package com.iav.id.ituteam.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.daimajia.androidanimations.library.Techniques;
import com.iav.id.ituteam.MainActivity;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashActivity extends AwesomeSplash {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        getSupportActionBar().hide();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                SharedPreferences sp = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
//                String username = sp.getString(Config.SHARED_USERNAME, "");
//                String email = sp.getString(Config.SHARED_EMAIL, "");
//                String rule = sp.getString(Config.SHARED_RULE, "");
//
//                // TODO jika belum masuk ke LoginActivity
//                if (username.equalsIgnoreCase("") || TextUtils.isEmpty(username)){
//                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                }
//                // TODO jika sudah nantinya akan masuk ke Home
//                else {
//                    if (rule.contains("Pengguna")){
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        finish();
//                    }
////                    else {
////                        startActivity(new Intent(getApplicationContext(), HomePetugasActivity.class));
////                    }
//
//                }
//            }
//        }, 2000);
//    }

    @Override
    public void initSplash(ConfigSplash configSplash) {
        /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.logo); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        configSplash.setPathSplash(Config.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.yellow); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("T-SMART APPS");
        configSplash.setTitleTextColor(R.color.colorPrimaryDark);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
//        configSplash.setTitleFont(String.valueOf(R.font.viga)); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
