package com.iav.id.ituteam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.evernote.android.state.State;
import com.evernote.android.state.StateSaver;
import com.iav.id.ituteam.fragment.BerandaFragment;
import com.iav.id.ituteam.fragment.RiwayatFragment;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

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


    @State
    public int mValue;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.div_container, new BerandaFragment()).commit();
//                    getSupportActionBar().setTitle(R.string.name_fragment_now_playing);
                    return true;
                case R.id.navigation_dashboard:

                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.div_container, new RiwayatFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };
    private TextView tvBerandaPoin;

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
        point = sharedPreferences.getString(Config.SHARED_POINt_DONOR, "");
        idUser =sharedPreferences.getString(Config.SHARED_ID_USER, "");
        getData();
//        if (point.equalsIgnoreCase("")){
//            Log.d("", "onCreate: kosong");
//            tvBerandaPoin.setText("0");
//        }
//        else {
//            tvBerandaPoin.setText(point);
//        }



    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        StateSaver.saveInstanceState(this, outState);
    }

    private void getData() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getDataMain(idUser, "77748gfieu-3487hjdfghur4Hhjheriirh")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String totalPointv  = jsonObject.optString("total_point");
                                Log.d("", "onResponse: " + totalPointv);
                                tvBerandaPoin.setText(totalPointv);

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
    }
}
