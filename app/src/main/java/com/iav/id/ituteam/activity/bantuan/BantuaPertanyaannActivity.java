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
import com.iav.id.ituteam.adapter.BantuanPertanyaanAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.BantuanPertanyaanModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BantuaPertanyaannActivity extends AppCompatActivity {

    private RecyclerView rvBantuan;
    private ArrayList<BantuanPertanyaanModel> bantuanPertanyaanModels;
    private BantuanPertanyaanAdapter bantuanPertanyaanAdapter;

    private String jenisBantuan, pertanyaan, jawaban;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(R.color.black);
        initView();

        bantuanPertanyaanModels = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        jenisBantuan = getIntent().getStringExtra(Config.BUNDLE_JENIS_BANTUAN);
        pertanyaan = getIntent().getStringExtra(Config.BUNDLE_PERTANYAAN_BANTUAN);
        jawaban = getIntent().getStringExtra(Config.BUNDLE_JAWABAN_BANTUAN);

        getDataPertanyaanAdapter();




    }

    private void getDataPertanyaanAdapter() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getBantuanPertanyaan("sduhfurh487bm58bBBJbjd-4u3--43rtnmds--", jenisBantuan)
                .enqueue(new Callback<ArrayList<BantuanPertanyaanModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<BantuanPertanyaanModel>> call, Response<ArrayList<BantuanPertanyaanModel>> response) {
                        if (response.isSuccessful()){
                            bantuanPertanyaanModels = response.body();
                            bantuanPertanyaanAdapter = new BantuanPertanyaanAdapter(BantuaPertanyaannActivity.this, bantuanPertanyaanModels);
                            rvBantuan.setLayoutManager(new LinearLayoutManager(BantuaPertanyaannActivity.this));
                            rvBantuan.setAdapter(bantuanPertanyaanAdapter);
                            bantuanPertanyaanAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<BantuanPertanyaanModel>> call, Throwable t) {
                        Toast.makeText(BantuaPertanyaannActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void initView() {
        rvBantuan = findViewById(R.id.rv_bantuan);
    }
}
