package com.iav.id.ituteam.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.adapter.HealthAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.EventModel;
import com.iav.id.ituteam.model.ListHealthModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthActivity extends AppCompatActivity {

    private RecyclerView rvHealth;
    private HealthAdapter healthAdapter;
    private ArrayList<ListHealthModel> listHealthModels;
    private ArrayList<EventModel> eventModels;
    private LinearLayout divContatinerList;
    private String jenisKategori;
    private String token;
    private String key;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("HEALTH");
//        toolbar.setTitle("T-HEALTH");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
        listHealthModels = new ArrayList<>();
        eventModels = new ArrayList<>();
        jenisKategori = getIntent().getStringExtra(Config.BUNDLE_JENIS_KATEGORI);
        token = getIntent().getStringExtra(Config.BUNDLE_PINDAH_TOKEN);

        if (jenisKategori.equalsIgnoreCase("Kesehatan")){
            getSupportActionBar().setIcon(R.drawable.logo);
            toolbar.setTitleTextColor(R.color.yellow);
            getDataEventKesehatanAll();
            divContatinerList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogBoxSortBy();

                }
            });
        } else {
            getSupportActionBar().setIcon(R.drawable.tgold);
            toolbar.setTitleTextColor(R.color.yellow);
            getDataEventSampahAll();
            divContatinerList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogBoxSortBySampah();

                }
            });
        }



    }

    /// SAMPAH
    private void dialogBoxSortBySampah() {
        final CharSequence[] itemDaftarNews = {"Tampilkan Semua", "Sampah Organik", "Sampah An Organik", "Sampah Tidak Hancur"};

        AlertDialog.Builder builder = new AlertDialog.Builder(HealthActivity.this);
        builder.setTitle("Urut berdasarkan");
        builder.setItems(itemDaftarNews, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                key = String.valueOf(itemDaftarNews[i]);
                if (key == String.valueOf(itemDaftarNews[0])){
                    getDataEventSampahAll();
                }
                else {
                    getDataEventBySampah();
                }

            }
        }).show();
    }

    private void getDataEventBySampah() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getEvent(key, "jdhfjsdh6637yGGJSHJ-sfuHbsdfb74NJBB-UIE+sdjfu74893")
                .enqueue(new Callback<ArrayList<EventModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<EventModel>> call, Response<ArrayList<EventModel>> response) {
                        eventModels = response.body();
                        for (int i = 0; i < eventModels.size(); i++) {
                            healthAdapter = new HealthAdapter(HealthActivity.this, eventModels);
                            rvHealth.setLayoutManager(new GridLayoutManager(HealthActivity.this, 2));
                            rvHealth.setAdapter(healthAdapter);
                            healthAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<EventModel>> call, Throwable t) {
                        Toast.makeText(HealthActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getDataEventSampahAll() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getEventAll( "7377477fhshf66GHGHE784t78rjhb7-7hHbhb&-sff4", "Sampah")
                .enqueue(new Callback<ArrayList<EventModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<EventModel>> call, Response<ArrayList<EventModel>> response) {
                        eventModels = response.body();
                        for (int i = 0; i < eventModels.size(); i++) {
                            healthAdapter = new HealthAdapter(HealthActivity.this, eventModels);
                            rvHealth.setLayoutManager(new GridLayoutManager(HealthActivity.this, 2));
                            rvHealth.setAdapter(healthAdapter);
                            healthAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<EventModel>> call, Throwable t) {
                        Toast.makeText(HealthActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }




    // KESEHATAN
    private void dialogBoxSortBy() {
        final CharSequence[] itemDaftarNews = {"Tampilkan Semua", "ASI", "Donor Darah", "Kesehatan Anak", "Kesehatan Masyarakat"};

        AlertDialog.Builder builder = new AlertDialog.Builder(HealthActivity.this);
        builder.setTitle("Urut berdasarkan");
        builder.setItems(itemDaftarNews, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                key = String.valueOf(itemDaftarNews[i]);
                if (key == String.valueOf(itemDaftarNews[0])){
                    getDataEventKesehatanAll();
                }
                else {
                    getDataEvent();
                }

            }
        }).show();
    }

    private void getDataEvent() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getEvent(key, "jdhfjsdh6637yGGJSHJ-sfuHbsdfb74NJBB-UIE+sdjfu74893")
                .enqueue(new Callback<ArrayList<EventModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<EventModel>> call, Response<ArrayList<EventModel>> response) {
                        eventModels = response.body();
                        for (int i = 0; i < eventModels.size(); i++) {
                            healthAdapter = new HealthAdapter(HealthActivity.this, eventModels);
                            rvHealth.setLayoutManager(new GridLayoutManager(HealthActivity.this, 2));
                            rvHealth.setAdapter(healthAdapter);
                            healthAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<EventModel>> call, Throwable t) {
                        Toast.makeText(HealthActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void getDataEventKesehatanAll() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getEventAll( "7377477fhshf66GHGHE784t78rjhb7-7hHbhb&-sff4", "Kesehatan")
                .enqueue(new Callback<ArrayList<EventModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<EventModel>> call, Response<ArrayList<EventModel>> response) {
                        eventModels = response.body();
                        for (int i = 0; i < eventModels.size(); i++) {
                            healthAdapter = new HealthAdapter(HealthActivity.this, eventModels);
                            rvHealth.setLayoutManager(new GridLayoutManager(HealthActivity.this, 2));
                            rvHealth.setAdapter(healthAdapter);
                            healthAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<EventModel>> call, Throwable t) {
                        Toast.makeText(HealthActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void initView() {
        rvHealth = findViewById(R.id.rv_health);
        divContatinerList = findViewById(R.id.div_contatiner_list);
    }
}
