package com.iav.id.ituteam.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iav.id.ituteam.MainActivity;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.EventActivity;
import com.iav.id.ituteam.activity.RiwayatActivity;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {


    private LinearLayout divHealth;
    private LinearLayout divGarbage;
    private LinearLayout divSecurity;
    private LinearLayout divEconomy;
    private LinearLayout divLodging;
    private LinearLayout divTravel;

    private String pointDonor;
    private String totalDonor;

    private String point;
    private String idUser;
    private String kota;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView tvTotalDonorDarah;
    private RecyclerView rvBerandaHariIniEvent;
    private RecyclerView rvBerandaHariIniBerita;
    private TextView tvTotalDonorAsi;
    private TextView tvTotalEvent;

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        initView(view);

        divHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(Config.BUNDLE_JENIS_KATEGORI, "Kesehatan");
                intent.putExtra(Config.BUNDLE_PINDAH_TOKEN, "u43uy78esufh4-344ru9jskjfd-i34rwrb23o4ndfXsddD");
                getActivity().startActivity(intent);
            }
        });
        divGarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                intent.putExtra(Config.BUNDLE_JENIS_KATEGORI, "Sampah");
                intent.putExtra(Config.BUNDLE_PINDAH_TOKEN, "jdhfjsdh6637yGGJSHJ-sfuHbsdfb74NJBB-UIE+sdjfu74893");
                getActivity().startActivity(intent);
            }
        });

        divLodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RiwayatActivity.class));
            }
        });
        sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
        idUser = sharedPreferences.getString(Config.SHARED_ID_USER, "");
        kota = sharedPreferences.getString(Config.SHARED_KOTA_KAB, "");
        getData();

        return view;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        getData();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        getData();
//    }

    private void getData() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getDataMain(idUser, "77748gfieu-3487hjdfghur4Hhjheriirh", kota)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String jumlah_donor_darah = jsonObject.optString("jumlah_donor_darah");
                                String jumlah_donor_asi = jsonObject.optString("jumlah_donor_asi");
                                String jumlah_event = jsonObject.optString("jumlah_event");
                                Log.d("", "onResponse: " + jumlah_donor_asi);
                                tvTotalDonorDarah.setText(jumlah_donor_darah + " kali");
                                tvTotalDonorAsi.setText(jumlah_donor_asi);
                                tvTotalEvent.setText(jumlah_event);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView(View view) {
        divHealth = view.findViewById(R.id.div_health);
        divGarbage = view.findViewById(R.id.div_garbage);
        divSecurity = view.findViewById(R.id.div_security);
        divEconomy = view.findViewById(R.id.div_economy);
        divLodging = view.findViewById(R.id.div_lodging);
        divTravel = view.findViewById(R.id.div_travel);
        tvTotalDonorDarah = view.findViewById(R.id.tv_total_donor_darah);
        rvBerandaHariIniEvent = view.findViewById(R.id.rv_beranda_hari_ini_event);
        rvBerandaHariIniBerita = view.findViewById(R.id.rv_beranda_hari_ini_berita);
        tvTotalDonorAsi = view.findViewById(R.id.tv_total_donor_asi);
        tvTotalEvent = view.findViewById(R.id.tv_total_event);
    }
}
