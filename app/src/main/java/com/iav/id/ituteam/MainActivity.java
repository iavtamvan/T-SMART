package com.iav.id.ituteam;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.evernote.android.state.State;
import com.evernote.android.state.StateSaver;
import com.iav.id.ituteam.fragment.BantuanFragment;
import com.iav.id.ituteam.fragment.BerandaFragment;
import com.iav.id.ituteam.fragment.NewsFragment;
import com.iav.id.ituteam.fragment.ProfileFragment;
import com.iav.id.ituteam.fragment.TukarHadiahFragment;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;
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

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FragmentManager fragmentManager;
    private Toolbar toolbar;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String point;
    private String idUser;
    private String kota;

    @State
    public int mValue;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getData();
                    divContainerPoints.setVisibility(View.VISIBLE);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.div_container, new BerandaFragment()).commit();

//                    getSupportActionBar().setTitle(R.string.name_fragment_now_playing);
                    return true;
                case R.id.navigation_news:
                    divContainerPoints.setVisibility(View.GONE);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.div_container, new NewsFragment()).commit();
                    return true;
                case R.id.navigation_tukar_hadiah:
                    divContainerPoints.setVisibility(View.GONE);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.div_container, new TukarHadiahFragment()).commit();
                    return true;
                case R.id.navigation_bantuan:
                    divContainerPoints.setVisibility(View.GONE);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.div_container, new BantuanFragment()).commit();
                    return true;
                case R.id.navigation_akun:
                    divContainerPoints.setVisibility(View.GONE);
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.div_container, new ProfileFragment()).commit();
                    return true;
            }
            return false;
        }
    };
    private TextView tvBerandaPoin;
    private LinearLayout divContainerPoints;
    private String TAG = "firebases";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StateSaver.restoreInstanceState(this, savedInstanceState);
        initView();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.div_container, new BerandaFragment()).commit();
        sharedPreferences = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
        point = sharedPreferences.getString(Config.SHARED_TOTAL_POINT, "");
        idUser = sharedPreferences.getString(Config.SHARED_ID_USER, "");
        kota = sharedPreferences.getString(Config.SHARED_KOTA_KAB, "");
        tvBerandaPoin.setText(point);

        getData();

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        StateSaver.saveInstanceState(this, outState);
    }

    public void getData() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getDataMain(idUser, "77748gfieu-3487hjdfghur4Hhjheriirh", kota)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String totalPointv = jsonObject.optString("total_point");
                                String total_gold = jsonObject.optString("total_gold");
                                String jumlah_donor_darah = jsonObject.optString("jumlah_donor_darah");
                                String jumlah_donor_asi = jsonObject.optString("jumlah_donor_asi");
                                String jumlah_event = jsonObject.optString("jumlah_event");
                                Log.d("", "onResponse: " + totalPointv);
                                tvBerandaPoin.setText(totalPointv);

                                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString(Config.SHARED_TOTAL_POINT, totalPointv);
                                editor.putString(Config.SHARED_TOTAL_GOLD, total_gold);

                                editor.apply();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        tvBerandaPoin = findViewById(R.id.tv_beranda_poin);
        divContainerPoints = findViewById(R.id.div_container_points);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .setTitle("Tutup aplikasi ini? ")
////                .setMessage("Kamu tidak jadi donor? kenapa? :(")
//                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent startMain = new Intent(Intent.ACTION_MAIN);
//                        startMain.addCategory(Intent.CATEGORY_HOME);
//                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(startMain);
//                    }
//
//                })
//                .setNegativeButton("Tidak", null)
//                .show();

        new FancyAlertDialog.Builder(this)
                .setTitle("T-SMART")
                .setBackgroundColor(Color.parseColor("#7f0000"))  //Don't pass R.color.colorvalue
                .setMessage("Tutup aplikasi ini?")
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
                        finishAffinity();
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),"Yeah",Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }

}
