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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.healthUI.DetailDonorActivity;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.DonorDarahModel;
import com.iav.id.ituteam.model.EventModel;

import java.util.ArrayList;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

public class EventBerandaAdapter extends RecyclerView.Adapter<EventBerandaAdapter.ViewHolder> {
    private ArrayList<EventModel> eventModels;
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

    public EventBerandaAdapter(ArrayList<EventModel> eventModels, Context context) {
        this.eventModels = eventModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_event_horizontal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        initShared();
        Glide.with(context).load(eventModels.get(position).getGambarEvent()).into(holder.ivBerandaEvent);
        holder.tvJudulEvent.setText(eventModels.get(position).getJudulEvent());

//        holder.cvKlick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailDonorActivity.class);
//                intent.putExtra(Config.BUNDLE_FOTO_URL, eventModels.get(position).getGambarEvent());
//                intent.putExtra(Config.BUNDLE_NAMA_PETUGAS, eventModels.get(position).getNamaPetugas());
//                intent.putExtra(Config.BUNDLE_DESKRIPSI, eventModels.get(position).getDeskripsiEvent());
//                intent.putExtra(Config.BUNDLE_TANGGAL_WAKTU_EVENT, eventModels.get(position).getTglEvent());
//                intent.putExtra(Config.BUNDLE_KOTA_KAB, eventModels.get(position).getKota());
//                intent.putExtra(Config.BUNDLE_ALAMAT, eventModels.get(position).getTempatAlamat());
//                intent.putExtra(Config.BUNDLE_LAT, eventModels.get(position).getLat());
//                intent.putExtra(Config.BUNDLE_LNG, eventModels.get(position).getLng());
//                intent.putExtra(Config.BUNDLE_JENIS_EVENT, eventModels.get(position).getJenisEvent());
//                intent.putExtra(Config.BUNDLE_LIKES_EVENT, eventModels.get(position).getLikes());
//                intent.putExtra(Config.BUNDLE_NO_HP, eventModels.get(position).getNoHp());
//                intent.putExtra(Config.BUNDLE_STATUS_EVENT, eventModels.get(position).getStatusEvent());
//
//                context.startActivity(intent);
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cvKlick;
        private ImageView ivBerandaEvent;
        private TextView tvJudulEvent;

        public ViewHolder(View itemView) {
            super(itemView);

            cvKlick = itemView.findViewById(R.id.cv_klick);
            ivBerandaEvent = itemView.findViewById(R.id.iv_beranda_event);
            tvJudulEvent = itemView.findViewById(R.id.tv_judul_event);
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
