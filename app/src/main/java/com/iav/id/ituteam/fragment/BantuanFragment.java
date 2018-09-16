package com.iav.id.ituteam.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class BantuanFragment extends Fragment {


    private RecyclerView rvBantuan;
    private ArrayList<BantuanListModel>bantuanListModels;
    private BantuanAdapter bantuanAdapter;

    public BantuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bantuan, container, false);
        initView(view);
        bantuanListModels = new ArrayList<>();

        getDataListBantuan();


        return view;
    }

    private void getDataListBantuan() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getBantuanList("HDBHBE-iern94ksdNJDN-sernew--")
                .enqueue(new Callback<ArrayList<BantuanListModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<BantuanListModel>> call, Response<ArrayList<BantuanListModel>> response) {
                        if (response.isSuccessful()){
                            bantuanListModels = response.body();
                            bantuanAdapter = new BantuanAdapter(getActivity(), bantuanListModels);
                            rvBantuan.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rvBantuan.setAdapter(bantuanAdapter);
                            bantuanAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<BantuanListModel>> call, Throwable t) {
                        Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView(View view) {
        rvBantuan = view.findViewById(R.id.rv_bantuan);
    }
}
