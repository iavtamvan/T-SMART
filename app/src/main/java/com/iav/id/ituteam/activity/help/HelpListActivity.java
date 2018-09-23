package com.iav.id.ituteam.activity.help;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.adapter.HelpListAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.HelpListModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpListActivity extends AppCompatActivity {

    private RecyclerView rvHelpList;
    private ArrayList<HelpListModel> helpListModels;
    private HelpListAdapter helpListAdapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("T-HELP");
        toolbar.setTitleTextColor(R.color.black);
        initView();

        getDataHelpList();

    }

    private void getDataHelpList() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getHelpList("Hghdsfbh74b-sdfnrJD--ekewsdfZSr--")
                .enqueue(new Callback<ArrayList<HelpListModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<HelpListModel>> call, Response<ArrayList<HelpListModel>> response) {
                        if (response.isSuccessful()){
                            helpListModels = response.body();
                            helpListAdapter = new HelpListAdapter(helpListModels, HelpListActivity.this);
                            rvHelpList.setLayoutManager(new LinearLayoutManager(HelpListActivity.this));
                            rvHelpList.setAdapter(helpListAdapter);
                            helpListAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<HelpListModel>> call, Throwable t) {
                        getDataHelpList();
                        Toast.makeText(HelpListActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        rvHelpList = findViewById(R.id.rv_help_list);
    }
}
