package com.iav.id.ituteam.activity.tukarBarang;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.adapter.TukarAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.TukarModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TukarActivity extends AppCompatActivity {

    private RecyclerView rvTukarPoint;
    private TukarAdapter tukarAdapter;
    private ArrayList<TukarModel> tukarModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tukar);
        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tukarModels = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getDataTukarBarang();


    }

    private void getDataTukarBarang() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getTukarAll("999POEJB-uygsdygdf66-op4o--njknsdjfnu4bh-")
                .enqueue(new Callback<ArrayList<TukarModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<TukarModel>> call, Response<ArrayList<TukarModel>> response) {
                        if (response.isSuccessful()){
                            tukarModels = response.body();
                            tukarAdapter = new TukarAdapter(tukarModels, TukarActivity.this);
                            rvTukarPoint.setLayoutManager(new GridLayoutManager(TukarActivity.this, 2));
                            rvTukarPoint.setAdapter(tukarAdapter);
                            tukarAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<TukarModel>> call, Throwable t) {
                        Toast.makeText(TukarActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        rvTukarPoint = findViewById(R.id.rv_tukar_point);
    }
}
