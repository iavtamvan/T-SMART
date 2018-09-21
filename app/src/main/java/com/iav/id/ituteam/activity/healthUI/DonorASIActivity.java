package com.iav.id.ituteam.activity.healthUI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.Calendar;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonorASIActivity extends AppCompatActivity {

    private CircleImageView ivCircleGolDarah;
    private TextView tvDonorAsiNama;
    private LinearLayout divContainerTanggalPerahAsi;
    private TextView tvDonorAsiTanggalPerahAsi;
    private TextView tvDonorAsiStatusPublikasi;
    private LinearLayout divContainerPenerimaAsi;
    private TextView tvDonorAsiPenerima;
    private TextView tvDonorAsiNoHpPenerimaAsi;
    private TextView tvDonorAsiAlamatPenerimaAsi;
    private TextView tvDonorAsiToken;
    private TextView tvDonorAsiPoint;
    private Button btnDonorAsiDaftar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_asi);
        getSupportActionBar().hide();

        initView();
        initShared();
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

        tvDonorAsiNama.setText(nama_lengkap);

        divContainerTanggalPerahAsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog();
            }
        });

        tvDonorAsiToken.setText(uuid);
        btnDonorAsiDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postASI();
            }
        });


    }

    private void postASI() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.postDonorASIPasien(id_user, tvDonorAsiTanggalPerahAsi.getText().toString().trim(), lat, lng, no_hp, uuid, "ASI")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(DonorASIActivity.this, "Daftar Donor Darah Sukses" , Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
//                        try {
//                            JSONObject jsonObject = new JSONObject(response.body().string());
//                            //                                String total_point_asi = jsonObject.optString("total_point_asi");
//                            String total_donor_asi_disetujui = jsonObject.optString("total_donor_asi_disetujui");
//                            String total_donor_asi_menunggu = jsonObject.optString("total_donor_asi_menunggu");
//                            String error_msg = jsonObject.optString("error_msg");
//
////                                editor.putString(Config.SHARED_TOTAL_POINT_ASI, total_point_asi);
//                            editor.putString(Config.SHARED_TOTAL_DONOR_ASI_DISETUJUI, total_donor_asi_disetujui);
//                            editor.putString(Config.SHARED_TOTAL_DONOR_ASI_MENUNGGU, total_donor_asi_menunggu);
//                            editor.apply();
//
//                            Toast.makeText(DonorASIActivity.this, "" + error_msg, Toast.LENGTH_SHORT).show();
//                            finish();
//                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    private void dateDialog() {
        final Calendar c = Calendar.getInstance();
        mSelectedYear = c.get(Calendar.YEAR);
        mSelectedMonth = c.get(Calendar.MONTH);
        mSelectedDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(DonorASIActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvDonorAsiTanggalPerahAsi.setText(Config.formatDMY(year, (monthOfYear + 1), dayOfMonth));
                        editor.putString(Config.SHARED_TANGGAL_PERAH_ASI, tvDonorAsiTanggalPerahAsi.getText().toString().trim());

                        editor.apply();

                    }
                }, mSelectedYear, mSelectedMonth, mSelectedDay);
        datePickerDialog.show();
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
        uuid = UUID.randomUUID().toString();

    }

    private void initView() {
        ivCircleGolDarah = findViewById(R.id.iv_circle_gol_darah);
        tvDonorAsiNama = findViewById(R.id.tv_donor_asi_nama);
        divContainerTanggalPerahAsi = findViewById(R.id.div_container_tanggal_perah_asi);
        tvDonorAsiTanggalPerahAsi = findViewById(R.id.tv_donor_asi_tanggal_perah_asi);
        tvDonorAsiStatusPublikasi = findViewById(R.id.tv_donor_asi_status_publikasi);
        divContainerPenerimaAsi = findViewById(R.id.div_container_penerima_asi);
        tvDonorAsiPenerima = findViewById(R.id.tv_donor_asi_penerima);
        tvDonorAsiNoHpPenerimaAsi = findViewById(R.id.tv_donor_asi_no_hp_penerima_asi);
        tvDonorAsiAlamatPenerimaAsi = findViewById(R.id.tv_donor_asi_alamat_penerima_asi);
        tvDonorAsiToken = findViewById(R.id.tv_donor_asi_token);
        tvDonorAsiPoint = findViewById(R.id.tv_donor_asi_point);
        btnDonorAsiDaftar = findViewById(R.id.btn_donor_asi_daftar);
    }

    @Override
    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .setTitle("LOH? ")
//                .setMessage(nama_lengkap +" tidak jadi donor? kenapa? :(")
//                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        Intent startMain = new Intent(Intent.ACTION_MAIN);
////                        startMain.addCategory(Intent.CATEGORY_HOME);
////                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        startActivity(startMain);
//                        finishAffinity();
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    }
//
//                })
//                .setNegativeButton("Tidak", null)
//                .show();
        new FancyAlertDialog.Builder(this)
                .setTitle("T-HEALTH")
                .setBackgroundColor(Color.parseColor("#7f0000"))  //Don't pass R.color.colorvalue
                .setMessage(nama_lengkap + " tidak jadi donor? :(")
                .setNegativeBtnText("Tidak")
                .setPositiveBtnBackground(Color.parseColor("#7f0000"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("Ya")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.SIDE)
                .isCancellable(true)
                .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),"Yeah",Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }
}
