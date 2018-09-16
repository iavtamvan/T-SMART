package com.iav.id.ituteam.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.RiwayatActivity;
import com.iav.id.ituteam.activity.tukarBarang.TukarRiwayatActivity;
import com.iav.id.ituteam.helper.Config;

import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private TextView tvProfileName;
    private TextView tvProfileEmail;
    private TextView tvProfileNoHp;
    private LinearLayout divContainerEdit;
    private TextView tvProfileTgold;
    private TextView tvProfileTpoints;
    private LinearLayout divContainerKodePromo;
    private LinearLayout divContainerBantuan;
    private LinearLayout divContainerTukarHadiah;
    private LinearLayout divContainerKetentuanLayanan;
    private LinearLayout divContainerKebijakanPrivasi;
    private LinearLayout divContainerRateApp;

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

    private LinearLayout divContainerRiwayatTukarHadiah;
    private LinearLayout divContainerRiwayatDonor;

    private FragmentManager fragmentManager;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        initShared();

        divContainerTukarHadiah.setVisibility(View.GONE);
        divContainerTukarHadiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        divContainerRiwayatDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RiwayatActivity.class));
            }
        });
        divContainerRiwayatTukarHadiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TukarRiwayatActivity.class));
            }
        });

        divContainerBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.div_container, new BantuanFragment()).commit();
            }
        });

        tvProfileName.setText(nama_lengkap);
        tvProfileEmail.setText(email);
        tvProfileNoHp.setText(no_hp);
        tvProfileTgold.setText("Rp." + gold);
        tvProfileTpoints.setText(point + " poin");


        return view;
    }

    private void initView(View view) {
        tvProfileName = view.findViewById(R.id.tv_profile_name);
        tvProfileEmail = view.findViewById(R.id.tv_profile_email);
        tvProfileNoHp = view.findViewById(R.id.tv_profile_no_hp);
        divContainerEdit = view.findViewById(R.id.div_container_edit);
        tvProfileTgold = view.findViewById(R.id.tv_profile_tgold);
        tvProfileTpoints = view.findViewById(R.id.tv_profile_tpoints);
        divContainerKodePromo = view.findViewById(R.id.div_container_kode_promo);
        divContainerBantuan = view.findViewById(R.id.div_container_bantuan);
        divContainerTukarHadiah = view.findViewById(R.id.div_container_tukar_hadiah);
        divContainerKetentuanLayanan = view.findViewById(R.id.div_container_ketentuan_layanan);
        divContainerKebijakanPrivasi = view.findViewById(R.id.div_container_kebijakan_privasi);
        divContainerRateApp = view.findViewById(R.id.div_container_rate_app);
        divContainerRiwayatTukarHadiah = view.findViewById(R.id.div_container_riwayat_tukar_hadiah);
        divContainerRiwayatDonor = view.findViewById(R.id.div_container_riwayat_donor);
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
