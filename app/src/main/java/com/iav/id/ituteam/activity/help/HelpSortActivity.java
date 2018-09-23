package com.iav.id.ituteam.activity.help;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.adapter.HelpSortAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.HelpModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpSortActivity extends AppCompatActivity {

    private ArrayList<HelpModel> helpModels;
    private HelpSortAdapter helpSortAdapter;
    private RecyclerView rvHelpSort;

    private String jenisHelp;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_sort);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        jenisHelp = getIntent().getStringExtra(Config.BUNDLE_JENIS_HELP);
        toolbar.setTitle(jenisHelp);
        toolbar.setTitleTextColor(R.color.black);
        initView();


        getDataSortHelp();
    }

    private void getDataSortHelp() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getHelpSort("HHSN8485jkoksdf-sdjkfnND-nksdfn--", jenisHelp)
                .enqueue(new Callback<ArrayList<HelpModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<HelpModel>> call, Response<ArrayList<HelpModel>> response) {
                        if (response.isSuccessful()){
                            helpModels = response.body();
                            helpSortAdapter = new HelpSortAdapter(helpModels, HelpSortActivity.this);
                            rvHelpSort.setLayoutManager(new GridLayoutManager(HelpSortActivity.this, 2));
                            rvHelpSort.setAdapter(helpSortAdapter);
                            helpSortAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<HelpModel>> call, Throwable t) {
                        getDataSortHelp();
                        Toast.makeText(HelpSortActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        rvHelpSort = findViewById(R.id.rv_help_sort);
    }
}
