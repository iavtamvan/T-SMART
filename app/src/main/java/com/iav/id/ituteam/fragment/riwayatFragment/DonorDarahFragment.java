package com.iav.id.ituteam.fragment.riwayatFragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.adapter.DonorDarahAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.DonorDarahModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorDarahFragment extends Fragment {
    private ArrayList<DonorDarahModel> donorDarahModels;
    private DonorDarahAdapter donorDarahAdapter;

    private SharedPreferences sharedPreferences;
    private String id_user;
    private RecyclerView rvDonorDarah;


    public DonorDarahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donor_darah, container, false);
        initView(view);
        donorDarahModels = new ArrayList<>();
        sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
        id_user = sharedPreferences.getString(Config.SHARED_ID_USER, "");

//        getDataRiwayat();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataRiwayat();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        getDataRiwayat();
//    }
    //    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        donorDarahModels.clear();
//        donorDarahAdapter = new DonorDarahAdapter(donorDarahModels, getActivity());
//        donorDarahAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        donorDarahModels.clear();
//    }

    private void getDataRiwayat() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getDataRiwayatDarah(id_user, "884395989029uuif984-URI4H98hNJHRHUTnjer9")
                .enqueue(new Callback<ArrayList<DonorDarahModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<DonorDarahModel>> call, Response<ArrayList<DonorDarahModel>> response) {
                        donorDarahModels = response.body();
                        for (int i = 0; i < donorDarahModels.size(); i++) {
                            donorDarahAdapter = new DonorDarahAdapter(donorDarahModels, getActivity());
                            rvDonorDarah.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rvDonorDarah.setAdapter(donorDarahAdapter);
                            donorDarahAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(Call<ArrayList<DonorDarahModel>> call, Throwable t) {
                        Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView(View view) {
        rvDonorDarah = view.findViewById(R.id.rv_donor_darah);
    }
}
