package com.iav.id.ituteam.activity.tukarBarang;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTukarBarangActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView ivDetailTukar;
    private TextView tvDetailTukarHarga;
    private TextView tvDetailTukarTanggal;
    private TextView tvDetailTukarNamaBarang;
    private TextView tvDetailTukarAlamatPenjual;
    private TextView tvDetailTukarAlamatPembeli;
    private CardView cvKlik;
    private TextView tvDetailTukarAlamatPenjualDown;
    private TextView tvDetailTukarDeskripsiProduk;
    private Button btnTukarkan;
    private FloatingActionButton fab;

    private String fotourl;
    private String namabarang;
    private String tukarkan;
    private String jenistukar;
    private String tglbarang;
    private String alamatpenjual;
    private String deskripsibarang;
    private String harga;
    private String namaPenjual;
    private String ongkir;
    private String riwayat;

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



    Date c;
    String formattedDate;
    SimpleDateFormat df;


    private ImageView ivDetailPoin;
    private ImageView ivDetailGold;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tukar_barang);
        initView();
        initShared();
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(R.color.black);
        fotourl = getIntent().getStringExtra(Config.BUNDLE_FOTO_URL);
        namabarang = getIntent().getStringExtra(Config.BUNDLE_NAMA_LENGKAP);
        tukarkan = getIntent().getStringExtra(Config.BUNDLE_TUKARKAN);
        jenistukar = getIntent().getStringExtra(Config.BUNDLE_JENIS_TUKAR);
        tglbarang = getIntent().getStringExtra(Config.BUNDLE_TANGGAL);
        alamatpenjual = getIntent().getStringExtra(Config.BUNDLE_ALAMAT_PENJUAL);
        alamat = getIntent().getStringExtra(Config.BUNDLE_ALAMAT);
        deskripsibarang = getIntent().getStringExtra(Config.BUNDLE_DESKRIPSI);
        harga = getIntent().getStringExtra(Config.BUNDLE_HARGA);
        namaPenjual = getIntent().getStringExtra(Config.BUNDLE_NAMA_PENJUAL);
        ongkir = getIntent().getStringExtra(Config.BUNDLE_ONGKIR);
        riwayat = getIntent().getStringExtra(Config.BUNDLE_TUKAR_RIWAYAT);

        if (riwayat.equals("riwayat")){
            btnTukarkan.setVisibility(View.GONE);
        } else {
            btnTukarkan.setVisibility(View.VISIBLE);
        }

        c = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate  = df.format(c);
        if (jenistukar.equals("gold")) {
            ivDetailPoin.setVisibility(View.GONE);
            ivDetailGold.setVisibility(View.VISIBLE);
        } else {
            ivDetailPoin.setVisibility(View.VISIBLE);
            ivDetailGold.setVisibility(View.GONE);
        }
        Glide.with(this).load(fotourl).into(ivDetailTukar);
        tvDetailTukarNamaBarang.setText(namabarang);
        tvDetailTukarTanggal.setText(tglbarang);
        tvDetailTukarHarga.setText("Rp." + harga);
        tvDetailTukarAlamatPenjual.setText(alamatpenjual);
        tvDetailTukarAlamatPembeli.setText(alamat);
        tvDetailTukarAlamatPenjualDown.setText(alamatpenjual);
        tvDetailTukarDeskripsiProduk.setText(deskripsibarang);

        btnTukarkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new OoOAlertDialog.Builder(DetailTukarBarangActivity.this)
                        .setTitle("Alert penukaran")
                        .setMessage("Apakah kamu yakin menukarkan barang ini?")
                        .setImage(R.drawable.logo)
                        .setAnimation(Animation.POP)
                        .setPositiveButton("YA", new OnClickListener() {
                            @Override
                            public void onClick() {
                                if (jenistukar.equals("gold")){
                                    // Parsingnya yg GOLD
                                    postDataKurangGold(tukarkan, namaPenjual, ongkir, alamatpenjual, namabarang, harga, jenistukar, alamat, tukarkan, deskripsibarang, fotourl, tglbarang, "324");
                                } else {
                                    // Parsingnya yg Point
                                    postDataKurangPoin(tukarkan, namaPenjual, ongkir, alamatpenjual, namabarang, harga, jenistukar, alamat, tukarkan, deskripsibarang, fotourl, tglbarang, "345435");
                                }
                            }
                        })
                        .setNegativeButton("TIDAK", new OnClickListener() {
                            @Override
                            public void onClick() {

                            }
                        })
                        .build();
            }
        });

    }

    private void postDataKurangPoin(String tukarPoint, String nama_penjual_barang, String ongkir,
                                    String alamat_penjual, String nama_barang, String harga_barang, String jenis_tukar, String alamat_user,
                                    String tukarkan, String deskripsi_barang, String foto_url, String tgl_barang, String tgl_penukaran_brang) {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.postTukarPoin("tukarPoin",id_user, tukarPoint, nama_penjual_barang, ongkir, alamat_penjual, nama_barang, harga_barang, jenis_tukar, alamat_user, tukarkan, deskripsi_barang, foto_url, tgl_barang, tgl_penukaran_brang)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String hasil_akhir_point = jsonObject.optString("hasil_akhir_point");
                                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString(Config.SHARED_TOTAL_POINT, hasil_akhir_point);

                                editor.apply();

                                Toast.makeText(DetailTukarBarangActivity.this, "Sukses menukarkan. Poin anda : " + hasil_akhir_point, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DetailTukarBarangActivity.this, "" + Config.ERROR_PENUKARAN_BARANG, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void postDataKurangGold(String tukarGold, String nama_penjual_barang, String ongkir,
                                    String alamat_penjual, String nama_barang, String harga_barang, String jenis_tukar, String alamat_user,
                                    String tukarkan, String deskripsi_barang, String foto_url, String tgl_barang, String tgl_penukaran_brang) {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.postTukarGold("tukarGold", id_user, tukarGold, nama_penjual_barang, ongkir, alamat_penjual, nama_barang, harga_barang, jenis_tukar, alamat_user, tukarkan, deskripsi_barang, foto_url, tgl_barang, tgl_penukaran_brang)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String hasil_akhir_gold = jsonObject.optString("hasil_akhir_gold");
                                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString(Config.SHARED_TOTAL_GOLD, hasil_akhir_gold);

                                editor.apply();
                                Toast.makeText(DetailTukarBarangActivity.this, "Sukses menukarkan. Gold anda : " + hasil_akhir_gold, Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DetailTukarBarangActivity.this, "" + Config.ERROR_PENUKARAN_BARANG, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        ivDetailTukar = findViewById(R.id.iv_detail_tukar);
        tvDetailTukarHarga = findViewById(R.id.tv_detail_tukar_harga);
        tvDetailTukarTanggal = findViewById(R.id.tv_detail_tukar_tanggal);
        tvDetailTukarNamaBarang = findViewById(R.id.tv_detail_tukar_nama_barang);
        tvDetailTukarAlamatPenjual = findViewById(R.id.tv_detail_tukar_alamat_penjual);
        tvDetailTukarAlamatPembeli = findViewById(R.id.tv_detail_tukar_alamat_pembeli);
        cvKlik = findViewById(R.id.cv_klik);
        tvDetailTukarAlamatPenjualDown = findViewById(R.id.tv_detail_tukar_alamat_penjual_down);
        tvDetailTukarDeskripsiProduk = findViewById(R.id.tv_detail_tukar_deskripsi_produk);
        btnTukarkan = findViewById(R.id.btn_tukarkan);
        fab = findViewById(R.id.fab);
        ivDetailPoin = findViewById(R.id.iv_detail_poin);
        ivDetailGold = findViewById(R.id.iv_detail_gold);
    }

    private void initShared() {
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
    }
}
