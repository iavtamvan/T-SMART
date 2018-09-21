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
import com.iav.id.ituteam.adapter.DonorASIAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.DonorASIModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorASIFragment extends Fragment {

    private ArrayList<DonorASIModel> donorASIModels;
    private DonorASIAdapter donorASIAdapter;

    private SharedPreferences sharedPreferences;
    private String id_user;
    private RecyclerView rvDonorDarah;

    public DonorASIFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donor_asi, container, false);
        initView(view);
        donorASIModels = new ArrayList<>();
        sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
        id_user = sharedPreferences.getString(Config.SHARED_ID_USER, "");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataRiwayat();
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        donorASIModels.clear();
//        donorASIAdapter = new DonorASIAdapter(donorASIModels, getActivity());
//        donorASIAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        donorASIModels.clear();
//    }

    private void getDataRiwayat() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getDataRiwayatASI(id_user, "r78348ryuiewr43huiUHEUI348975-3-q230-uerhi").enqueue(new Callback<ArrayList<DonorASIModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DonorASIModel>> call, Response<ArrayList<DonorASIModel>> response) {
                donorASIModels = response.body();
                for (int i = 0; i < donorASIModels.size(); i++) {
                    donorASIAdapter = new DonorASIAdapter(donorASIModels, getActivity());
                    rvDonorDarah.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvDonorDarah.setAdapter(donorASIAdapter);
                    donorASIAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DonorASIModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View view) {
        rvDonorDarah = view.findViewById(R.id.rv_donor_darah);
    }
}
