package com.iav.id.ituteam.activity.tukarBarang;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailTukarBarangActivity extends AppCompatActivity {

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
    private TextView tvTukarSampahnamaTop;
    private TextView tvTukarSampahNama;
    private TextView tvTukarSampahTanggal;
    private TextView tvTukarSampahTukarAlamat;
    private TextView tvTukarSampah;
    private LinearLayout divContainerBeratSampah;
    private TextView tvTukarSampahBerat;
    private LinearLayout divContainerJenisSampah;
    private TextView tvTukarSampahJenis;
    private LinearLayout divContainerHarga;
    private TextView tvTukarSampahHargaTop;
    private TextView tvTukarSampahHarga;
    private TextView tvTukarSampahTokenSampah;
    private TextView tvTukarSampahGold;
    private TextView tvTukarSampahStatus;
    private CircleImageView ivCircleTukarSampah;

    String foto_url_sampah;
    String gambar_sampah_sampah;
    String gambar_petugas_sampah_sampah;
    String nama_lengkap_sampah;
    String tanggal_sampah;
    String alamat_sampah;
    String input_sampah_sampah;
    String jenis_sampah_sampah;
    String token_sampah;
    String selection_detail_sampah;
    String harga_sampah;
    String status_sampah_sampah;
    private ImageView ivPenukarSampah;
    private ImageView ivPetugasSampah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tukar_barangs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        initView();

        foto_url_sampah = getIntent().getStringExtra(Config.BUNDLE_FOTO_URL);
        gambar_sampah_sampah = getIntent().getStringExtra(Config.BUNDLE_GAMBAR_SAMPAH);
        gambar_petugas_sampah_sampah = getIntent().getStringExtra(Config.BUNDLE_GAMBAR_PETUGAS_SAMPAH);
        nama_lengkap_sampah = getIntent().getStringExtra(Config.BUNDLE_NAMA_LENGKAP);
        tanggal_sampah = getIntent().getStringExtra(Config.BUNDLE_TANGGAL);
        alamat_sampah = getIntent().getStringExtra(Config.BUNDLE_ALAMAT);
        input_sampah_sampah = getIntent().getStringExtra(Config.BUNDLE_INPUT_SAMPAH);
        jenis_sampah_sampah = getIntent().getStringExtra(Config.BUNDLE_JENIS_SAMPAH);
        token_sampah = getIntent().getStringExtra(Config.BUNDLE_TOKEN);
        selection_detail_sampah = getIntent().getStringExtra(Config.BUNDLE_SELECTION_DETAIL);
        harga_sampah = getIntent().getStringExtra(Config.BUNDLE_HARGA);
        status_sampah_sampah = getIntent().getStringExtra(Config.BUNDLE_STATUS_SAMPAH);

        tvTukarSampahNama.setText(nama_lengkap_sampah);
        tvTukarSampahTanggal.setText(tanggal_sampah);
        tvTukarSampahTukarAlamat.setText(alamat_sampah);
        tvTukarSampahBerat.setText(input_sampah_sampah);
        tvTukarSampahJenis.setText(jenis_sampah_sampah);
        tvTukarSampahHarga.setText(harga_sampah);
        tvTukarSampahTokenSampah.setText(token_sampah);
        tvTukarSampahStatus.setText(status_sampah_sampah);
        Glide.with(DetailTukarBarangActivity.this).load(gambar_sampah_sampah).into(ivPenukarSampah);
        Glide.with(DetailTukarBarangActivity.this).load(gambar_petugas_sampah_sampah).into(ivPetugasSampah);



    }


    public void initShared() {
        sharedPreferences = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
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

    private void initView() {
        tvTukarSampahnamaTop = findViewById(R.id.tv_tukar_sampahnama_top);
        tvTukarSampahNama = findViewById(R.id.tv_tukar_sampah_nama);
        tvTukarSampahTanggal = findViewById(R.id.tv_tukar_sampah_tanggal);
        tvTukarSampahTukarAlamat = findViewById(R.id.tv_tukar_sampah_alamat);
        tvTukarSampah = findViewById(R.id.tv_tukar_sampah);
        divContainerBeratSampah = findViewById(R.id.div_container_berat_sampah);
        tvTukarSampahBerat = findViewById(R.id.tv_tukar_sampah_berat);
        divContainerJenisSampah = findViewById(R.id.div_container_jenis_sampah);
        tvTukarSampahJenis = findViewById(R.id.tv_tukar_sampah_jenis);
        divContainerHarga = findViewById(R.id.div_container_harga);
        tvTukarSampahHargaTop = findViewById(R.id.tv_tukar_sampah_harga_top);
        tvTukarSampahHarga = findViewById(R.id.tv_tukar_sampah_harga);
        tvTukarSampahTokenSampah = findViewById(R.id.tv_tukar_sampah_token_sampah);
        tvTukarSampahGold = findViewById(R.id.tv_tukar_sampah_gold);
        tvTukarSampahStatus = findViewById(R.id.tv_tukar_sampah_status);
        ivCircleTukarSampah = findViewById(R.id.iv_circle_tukar_sampah);
        ivPenukarSampah = findViewById(R.id.iv_penukar_sampah);
        ivPetugasSampah = findViewById(R.id.iv_petugas_sampah);
    }
}
