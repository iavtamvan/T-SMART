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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.tukarBarang.DetailTukarBarangActivity;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.SumbanganSampahModel;

import java.util.ArrayList;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class SumbanganSampahdapter extends RecyclerView.Adapter<SumbanganSampahdapter.ViewHolder> {
    private ArrayList<SumbanganSampahModel> sumbanganSampahModels;
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


    public SumbanganSampahdapter(ArrayList<SumbanganSampahModel> SumbanganSampahModels, Context context) {
        this.sumbanganSampahModels = SumbanganSampahModels;
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

        Glide.with(context).load(sumbanganSampahModels.get(position).getGambar()).into(holder.ivListDonorDarah);
        holder.tvListDonorDarahTanggal.setText("Masukan Sampah pada : " + sumbanganSampahModels.get(position).getTglWaktuSampah());
        holder.tvListDonorDarahNamaLengkap.setText(nama_lengkap);
        holder.tvListDonorDarahGolonganTop.setText("Sampah (kg)");
        holder.tvListDonorDarahGolongan.setText(sumbanganSampahModels.get(position).getInputSampah() + " Rp." + sumbanganSampahModels.get(position).getHargaTotal());
        holder.tvListDonorStatus.setText(sumbanganSampahModels.get(position).getStatus());
        holder.divContainerJenisSampah.setVisibility(View.VISIBLE);
        holder.tvListDonorDarahJenisSampah.setText(sumbanganSampahModels.get(position).getJenisSampah());

        holder.cvKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTukarBarangActivity.class);
                intent.putExtra(Config.BUNDLE_FOTO_URL, foto_url);
                intent.putExtra(Config.BUNDLE_GAMBAR_SAMPAH, sumbanganSampahModels.get(position).getGambar());
                intent.putExtra(Config.BUNDLE_GAMBAR_PETUGAS_SAMPAH, sumbanganSampahModels.get(position).getGambarPetugas());
                intent.putExtra(Config.BUNDLE_NAMA_LENGKAP, nama_lengkap);
                intent.putExtra(Config.BUNDLE_TANGGAL, sumbanganSampahModels.get(position).getTglWaktuSampah());
                intent.putExtra(Config.BUNDLE_ALAMAT, sumbanganSampahModels.get(position).getTempat());
                intent.putExtra(Config.BUNDLE_INPUT_SAMPAH, sumbanganSampahModels.get(position).getInputSampah());
                intent.putExtra(Config.BUNDLE_JENIS_SAMPAH, sumbanganSampahModels.get(position).getJenisSampah());
                intent.putExtra(Config.BUNDLE_TOKEN, sumbanganSampahModels.get(position).getTokenRegSampah());
                intent.putExtra(Config.BUNDLE_SELECTION_DETAIL, "sampah");
                intent.putExtra(Config.BUNDLE_HARGA, sumbanganSampahModels.get(position).getHargaTotal());
                intent.putExtra(Config.BUNDLE_STATUS_SAMPAH, sumbanganSampahModels.get(position).getStatus());

                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return sumbanganSampahModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvListDonorDarahTanggal;
        private CircleImageView ivListDonorDarah;
        private TextView tvListDonorDarahNamaLengkap;
        private TextView tvListDonorDarahGolongan;
        private TextView tvListDonorDarahGolonganTop;
        private TextView tvListDonorStatus;
        private CardView cvKlik;

        private LinearLayout divContainerJenisSampah;
        private TextView tvListDonorDarahJenisSampahTop;
        private TextView tvListDonorDarahJenisSampah;

        public ViewHolder(View itemView) {
            super(itemView);

            tvListDonorDarahTanggal = itemView.findViewById(R.id.tv_list_donor_darah_tanggal);
            ivListDonorDarah = itemView.findViewById(R.id.iv_list_donor_darah);
            tvListDonorDarahNamaLengkap = itemView.findViewById(R.id.tv_list_donor_darah_nama_lengkap);
            tvListDonorDarahGolongan = itemView.findViewById(R.id.tv_list_donor_darah_golongan);
            tvListDonorStatus = itemView.findViewById(R.id.tv_list_donor_status);
            cvKlik = itemView.findViewById(R.id.cvklik);
            tvListDonorDarahGolonganTop = itemView.findViewById(R.id.tv_list_donor_darah_golongan_top);
            divContainerJenisSampah = itemView.findViewById(R.id.div_container_jenis_sampah);
            tvListDonorDarahJenisSampahTop = itemView.findViewById(R.id.tv_list_donor_darah_jenis_sampah_top);
            tvListDonorDarahJenisSampah = itemView.findViewById(R.id.tv_list_donor_darah_jenis_sampah);
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
