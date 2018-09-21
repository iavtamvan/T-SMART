package com.iav.id.ituteam.activity.bantuan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.adapter.BantuanAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.BantuanListModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BantuanProfilActivity extends AppCompatActivity {

    private RecyclerView rvBantuan;
    private ArrayList<BantuanListModel> bantuanListModels;
    private BantuanAdapter bantuanAdapter;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan_profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.color.black);
        initView();
        bantuanListModels = new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getDataListBantuan();
    }

    private void getDataListBantuan() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getBantuanList("HDBHBE-iern94ksdNJDN-sernew--")
                .enqueue(new Callback<ArrayList<BantuanListModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<BantuanListModel>> call, Response<ArrayList<BantuanListModel>> response) {
                        if (response.isSuccessful()){
                            bantuanListModels = response.body();
                            bantuanAdapter = new BantuanAdapter(BantuanProfilActivity.this, bantuanListModels);
                            rvBantuan.setLayoutManager(new LinearLayoutManager(BantuanProfilActivity.this));
                            rvBantuan.setAdapter(bantuanAdapter);
                            bantuanAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<BantuanListModel>> call, Throwable t) {
                        Toast.makeText(BantuanProfilActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void initView() {
        rvBantuan = findViewById(R.id.rv_bantuan);
    }
}
