package com.iav.id.ituteam.activity.healthUI;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.iav.id.ituteam.MainActivity;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonorDarahActivity extends AppCompatActivity {

    private CircleImageView ivCircleGolDarah;
    private TextView tvDonorDarahNama;
    private TextView tvDonorDarahTempatTglLahir;
    private TextView tvDonorDarahKotaKabupatenAlamat;
    private TextView tvDonorDarahJenisKelamin;
    private LinearLayout divContainerGolDarah;
    private TextView tvDonorDarahGolongan;
    private LinearLayout divContainerDonorDarahTgl;
    private TextView tvDonorDarahTanggal;
    private LinearLayout divContainerDonorDarahTglJatuhTempo;
    private TextView tvDonorDarahTanggalJatuhTempo;
    private EditText edtDonorDarahNoregPmi;
    private TextView tvDonorDarahToken;
    private Button btnDonorDarahDaftar;

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

    private int mSelectedYear;
    private int mSelectedMonth;
    private int mSelectedDay;

    private CharSequence[] itemDaftarNews;
    private LinearLayout divContainerGolDarahRhesus;
    private TextView tvDonorDarahGolonganRhesus;
    private TextView tvDonorDarahPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_darah);
        getSupportActionBar().setIcon(R.drawable.thealth);
        initView();
        initShared();


        divContainerGolDarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDaftarNews = new CharSequence[]{"A", "B", "AB", "O", "Tidak Tahu"};
                dialogBoxGolonganDarah();

            }
        });

        btnDonorDarahDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDonorPasien();
            }
        });
        divContainerDonorDarahTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog();
            }
        });

        divContainerGolDarahRhesus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDaftarNews = new CharSequence[]{"+", "-", "Tidak Tahu"};
                dialogBoxRhesus();
            }
        });

        edtDonorDarahNoregPmi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString(Config.SHARED_REG_PMI, edtDonorDarahNoregPmi.getText().toString().trim());
                editor.apply();
            }
        });



    }

    private void initShared() {
        sharedPreferences = this.getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
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
        if (gol_darah.isEmpty()) {
            Log.d("", "onCreate: ksoong");
        } else {
            tvDonorDarahGolongan.setText(gol_darah);
        }

        if (rhesus.isEmpty()){
            Log.d("", "initShared: ksoogn rhesusnya");
        }
        else {
            tvDonorDarahGolonganRhesus.setText(rhesus);
        }

        if (no_reg_pmi.isEmpty()){
            Log.d("", "initShared: reg pmi kosong");
        }
        else {
            edtDonorDarahNoregPmi.setText(no_reg_pmi);
        }
        if (tgl_donor.isEmpty()){
            Log.d("", "initShared: tgl donor kosong");
        }
        else {
            tvDonorDarahTanggal.setText(tgl_donor);
        }
        if (tgl_jatuh_tempo.isEmpty()){
            Log.d("", "initShared: jatuh tempo kosong");
        }
        else {
            tvDonorDarahTanggalJatuhTempo.setText(tgl_jatuh_tempo);
        }
        tvDonorDarahNama.setText(nama_lengkap);
        tvDonorDarahTempatTglLahir.setText(tempat_tgl_lahir);
        tvDonorDarahKotaKabupatenAlamat.setText(provinsi + ", " + kota_kab + ", " + alamat);
        tvDonorDarahJenisKelamin.setText(jenis_kelamin);
        uuid = UUID.randomUUID().toString();
        tvDonorDarahToken.setText(uuid);
        final ImagePopup imagePopup = new ImagePopup(this);
        imagePopup.setWindowHeight(800); // Optional
        imagePopup.setWindowWidth(800); // Optional
        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
        imagePopup.setFullScreen(true); // Optional
        imagePopup.setHideCloseIcon(true);  // Optional
        imagePopup.setImageOnClickClose(true);  // Optional
        imagePopup.initiatePopupWithGlide(foto_url);
        Glide.with(this).load(foto_url).into(ivCircleGolDarah);
        ivCircleGolDarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePopup.viewPopup();
            }
        });


    }
    private void postDonorPasien() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.postDonorDarahPasien(id_user, tvDonorDarahGolongan.getText().toString().trim(),
                tvDonorDarahTanggal.getText().toString().trim(), "90 Hari lagi", tvDonorDarahGolonganRhesus.getText().toString().trim(),
                edtDonorDarahNoregPmi.getText().toString().trim(), "Tidak Terdaftar", uuid, "Darah", kota_kab, "Menunggu")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String error_msg = jsonObject.optString("error_msg");
//                                String total_point = jsonObject.optString("total_point");
                                String total_donorMeunggu = jsonObject.optString("total_donor_menunggu");
                                String total_donor_disetujui = jsonObject.optString("total_donor_disetujui");
//                                editor.putString(Config.SHARED_POINt_DONOR, total_point);
                                editor.putString(Config.SHARED_TOTAL_DONOR_DISETUJUI, total_donor_disetujui);
                                editor.putString(Config.SHARED_TOTAL_DONOR_MENUNGGU, total_donorMeunggu);
                                editor.apply();
                                Toast.makeText(DonorDarahActivity.this, "" + error_msg, Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DonorDarahActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void dateDialog() {
        final Calendar c = Calendar.getInstance();
        mSelectedYear = c.get(Calendar.YEAR);
        mSelectedMonth = c.get(Calendar.MONTH);
        mSelectedDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(DonorDarahActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvDonorDarahTanggal.setText(Config.formatDMY(year, (monthOfYear + 1), dayOfMonth));

                        String jatuhTempo = String.valueOf(dayOfMonth + 90);
                        tvDonorDarahTanggalJatuhTempo.setText("90 Hari lagi");
                        editor.putString(Config.SHARED_TANGGAL_DONOR_DARAH, tvDonorDarahTanggal.getText().toString().trim());
                        editor.putString(Config.SHARED_JATUH_TEMPO_TANGGAL_DONOR, tvDonorDarahTanggalJatuhTempo.getText().toString().trim());

                        editor.apply();

                    }
                }, mSelectedYear, mSelectedMonth, mSelectedDay);
        datePickerDialog.show();
    }
    private void dialogBoxGolonganDarah() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DonorDarahActivity.this);
        builder.setTitle("Urut berdasarkan");
        builder.setItems(itemDaftarNews, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                key = String.valueOf(itemDaftarNews[i]);
                tvDonorDarahGolongan.setText(key);
                editor.putString(Config.SHARED_GOLONGAN_DARAH, tvDonorDarahGolongan.getText().toString().trim());
                editor.apply();
            }
        }).show();
    }
    private void dialogBoxRhesus() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DonorDarahActivity.this);
        builder.setTitle("Urut berdasarkan");
        builder.setItems(itemDaftarNews, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                key = String.valueOf(itemDaftarNews[i]);
                tvDonorDarahGolonganRhesus.setText(key);
                editor.putString(Config.SHARED_RHESUS, tvDonorDarahGolonganRhesus.getText().toString().trim());
                editor.apply();
            }
        }).show();
    }
    private void initView() {
        ivCircleGolDarah = findViewById(R.id.iv_circle_gol_darah);
        tvDonorDarahNama = findViewById(R.id.tv_donor_darah_nama);
        tvDonorDarahTempatTglLahir = findViewById(R.id.tv_donor_darah_tempat_tgl_lahir);
        tvDonorDarahKotaKabupatenAlamat = findViewById(R.id.tv_donor_darah_kota_kabupaten_alamat);
        tvDonorDarahJenisKelamin = findViewById(R.id.tv_donor_darah_jenis_kelamin);
        divContainerGolDarah = findViewById(R.id.div_container_gol_darah);
        tvDonorDarahGolongan = findViewById(R.id.tv_donor_darah_golongan);
        divContainerDonorDarahTgl = findViewById(R.id.div_container_donor_darah_tgl);
        tvDonorDarahTanggal = findViewById(R.id.tv_donor_darah_tanggal);
        divContainerDonorDarahTglJatuhTempo = findViewById(R.id.div_container_donor_darah_tgl_jatuh_tempo);
        tvDonorDarahTanggalJatuhTempo = findViewById(R.id.tv_donor_darah_tanggal_jatuh_tempo);
        edtDonorDarahNoregPmi = findViewById(R.id.edt_donor_darah_noreg_pmi);
        tvDonorDarahToken = findViewById(R.id.tv_donor_darah_token);
        btnDonorDarahDaftar = findViewById(R.id.btn_donor_darah_daftar);
        divContainerGolDarahRhesus = findViewById(R.id.div_container_gol_darah_rhesus);
        tvDonorDarahGolonganRhesus = findViewById(R.id.tv_donor_darah_golongan_rhesus);
        tvDonorDarahPoint = findViewById(R.id.tv_donor_darah_point);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("LOH? ")
                .setMessage(nama_lengkap +" tidak jadi donor? kenapa? :(")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                    }

                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}
