package com.iav.id.ituteam.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.DetailDonorActivity;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.DonorASIModel;

import java.util.ArrayList;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class DonorASIAdapter extends RecyclerView.Adapter<DonorASIAdapter.ViewHolder> {
    private ArrayList<DonorASIModel> donorASIModels;
    private Context context;

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


    public DonorASIAdapter(ArrayList<DonorASIModel> donorASIModels, Context context) {
        this.donorASIModels = donorASIModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_donor_darah, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        initShared();

        Glide.with(context).load(donorASIModels.get(position).getBuktiFoto()).error(R.drawable.logo).into(holder.ivListDonorDarah);
        holder.tvListDonorDarahTanggal.setText("Tangggal Perah : " + donorASIModels.get(position).getTglPerah());
        holder.tvListDonorDarahNamaLengkap.setText(nama_lengkap);
        holder.tvListDonorDarahGolonganTop.setText("No Hp");
        holder.tvListDonorDarahGolongan.setText(donorASIModels.get(position).getNoHpWa());
        holder.tvListDonorStatus.setText(donorASIModels.get(position).getStatusPublikasi());

        holder.cvKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDonorActivity.class);
                intent.putExtra(Config.BUNDLE_FOTO_URL, donorASIModels.get(position).getBuktiFoto());
                intent.putExtra(Config.BUNDLE_NAMA_LENGKAP, nama_lengkap);
                intent.putExtra(Config.BUNDLE_TEMPAT_TGL_LAHIR, tempat_tgl_lahir);
                intent.putExtra(Config.BUNDLE_ALAMAT, provinsi + " " +kota_kab + " " +alamat);
                intent.putExtra(Config.BUNDLE_JENIS_KELAMIN, jenis_kelamin);
                intent.putExtra(Config.BUNDLE_TANGGAL_PERAH_ASI, donorASIModels.get(position).getTglPerah());
                intent.putExtra(Config.BUNDLE_TOKEN, donorASIModels.get(position).getTokenAsi());
                intent.putExtra(Config.BUNDLE_SELECTION_DETAIL, "ASI");
                intent.putExtra(Config.BUNDLE_STATUS_DONOR, donorASIModels.get(position).getStatusPublikasi());



                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return donorASIModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvListDonorDarahTanggal;
        private CircleImageView ivListDonorDarah;
        private TextView tvListDonorDarahNamaLengkap;
        private TextView tvListDonorDarahGolongan;
        private TextView tvListDonorDarahGolonganTop;
        private TextView tvListDonorStatus;
        private CardView cvKlik;

        public ViewHolder(View itemView) {
            super(itemView);

            tvListDonorDarahTanggal = itemView.findViewById(R.id.tv_list_donor_darah_tanggal);
            ivListDonorDarah = itemView.findViewById(R.id.iv_list_donor_darah);
            tvListDonorDarahNamaLengkap = itemView.findViewById(R.id.tv_list_donor_darah_nama_lengkap);
            tvListDonorDarahGolongan = itemView.findViewById(R.id.tv_list_donor_darah_golongan);
            tvListDonorStatus = itemView.findViewById(R.id.tv_list_donor_status);
            cvKlik = itemView.findViewById(R.id.cvklik);
            tvListDonorDarahGolonganTop = itemView.findViewById(R.id.tv_list_donor_darah_golongan_top);
        }
    }

    private void initShared() {
        sharedPreferences = context.getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
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
    }
}
