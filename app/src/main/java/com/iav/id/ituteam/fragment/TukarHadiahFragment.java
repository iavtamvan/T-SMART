package com.iav.id.ituteam.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class TukarHadiahFragment extends Fragment {


    private RecyclerView rvTukarPoint;
    private TukarAdapter tukarAdapter;
    private ArrayList<TukarModel> tukarModels;

    public TukarHadiahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tukar_hadiah, container, false);
        initView(view);
        getDataTukarBarang();
        return view;
    }

    private void getDataTukarBarang() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getTukarAll("999POEJB-uygsdygdf66-op4o--njknsdjfnu4bh-")
                .enqueue(new Callback<ArrayList<TukarModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<TukarModel>> call, Response<ArrayList<TukarModel>> response) {
                        if (response.isSuccessful()){
                            tukarModels = response.body();
                            tukarAdapter = new TukarAdapter(tukarModels, getActivity());
                            rvTukarPoint.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            rvTukarPoint.setAdapter(tukarAdapter);
                            tukarAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<TukarModel>> call, Throwable t) {
                        Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView(View view) {
        rvTukarPoint = view.findViewById(R.id.rv_tukar_point);
    }
}
