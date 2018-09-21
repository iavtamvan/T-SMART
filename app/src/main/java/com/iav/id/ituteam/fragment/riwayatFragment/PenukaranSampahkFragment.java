package com.iav.id.ituteam.fragment.riwayatFragment;


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
import com.iav.id.ituteam.adapter.SumbanganSampahdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.SumbanganSampahModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PenukaranSampahkFragment extends Fragment {


    private RecyclerView rvSumbanganSampah;
    private ArrayList<SumbanganSampahModel> sumbanganSampahModels;
    private SumbanganSampahdapter sumbanganSampahdapter;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String rule;
    private String id_user;
    private String nama_lengkap;
    private String email;
    private String tempat_tgl_lahir;
    private String alamat;
    private String kota_kab;
    private String provinsi;
    private String no_hp;
    private String jenis_kelamin;
    private String agama;
    private String username;
    private String foto_url;
    private String status_verifikasi;
    private String token;
    private String lat;
    private String lng;
    private String status_aplikasi;
    private String gol_darah;
    private String rhesus;
    private String no_reg_pmi;
    private String tgl_donor;
    private String tgl_jatuh_tempo;
    private String key;
    private String uuid;
    private String point;
    private String gold;

    public PenukaranSampahkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_penukaran_sampahk, container, false);
        initView(view);
        initShared();
        sumbanganSampahModels = new ArrayList<>();

        getDatSampah();
        return view;
    }

    private void getDatSampah() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getSumbanganSampah("7457HBHD934bdbfsjJHDJB0943--sndjfn-", id_user)
                .enqueue(new Callback<ArrayList<SumbanganSampahModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SumbanganSampahModel>> call, Response<ArrayList<SumbanganSampahModel>> response) {
                        if (response.isSuccessful()){
                            sumbanganSampahModels = response.body();
                            sumbanganSampahdapter = new SumbanganSampahdapter(sumbanganSampahModels, getActivity());
                            rvSumbanganSampah.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rvSumbanganSampah.setAdapter(sumbanganSampahdapter);
                            sumbanganSampahdapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SumbanganSampahModel>> call, Throwable t) {
                        Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView(View view) {
        rvSumbanganSampah = view.findViewById(R.id.rv_sumbangan_sampah);
    }

    private void initShared() {
        sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        rule = sharedPreferences.getString(Config.SHARED_RULE, "");
        id_user = sharedPreferences.getString(Config.SHARED_ID_USER, "");
        nama_lengkap = sharedPreferences.getString(Config.SHARED_NAMA_LENGKAP, "");
        email = sharedPreferences.getString(Config.SHARED_EMAIL, "");
        tempat_tgl_lahir = sharedPreferences.getString(Config.SHARED_TEMPAT_TGL_LAHIR, "");
        alamat = sharedPreferences.getString(Config.SHARED_ALAMAT, "");
        kota_kab = sharedPreferences.getString(Config.SHARED_KOTA_KAB, "");
        provinsi = sharedPreferences.getString(Config.SHARED_PROVINSI, "");
        no_hp = sharedPreferences.getString(Config.SHARED_NO_HP, "");
        jenis_kelamin = sharedPreferences.getString(Config.SHARED_JENIS_KELAMIN, "");
        agama = sharedPreferences.getString(Config.SHARED_AGAMA, "");
        username = sharedPreferences.getString(Config.SHARED_USERNAME, "");
        foto_url = sharedPreferences.getString(Config.SHARED_FOTO_URL, "");
        status_verifikasi = sharedPreferences.getString(Config.SHARED_STATUS_VERIFIKASI, "");
        token = sharedPreferences.getString(Config.SHARED_TOKEN, "");
        lat = sharedPreferences.getString(Config.SHARED_LAT, "");
        lng = sharedPreferences.getString(Config.SHARED_LNG, "");
        status_aplikasi = sharedPreferences.getString(Config.SHARED_STATUS_APLIKASI, "");
        gol_darah = sharedPreferences.getString(Config.SHARED_GOLONGAN_DARAH, "");
        rhesus = sharedPreferences.getString(Config.SHARED_RHESUS, "");
        no_reg_pmi = sharedPreferences.getString(Config.SHARED_REG_PMI, "");
        tgl_donor = sharedPreferences.getString(Config.SHARED_TANGGAL_DONOR_DARAH, "");
        tgl_jatuh_tempo = sharedPreferences.getString(Config.SHARED_JATUH_TEMPO_TANGGAL_DONOR, "");
        uuid = UUID.randomUUID().toString();
        point = sharedPreferences.getString(Config.SHARED_TOTAL_POINT, "");
        gold = sharedPreferences.getString(Config.SHARED_TOTAL_GOLD, "");
    }
}
