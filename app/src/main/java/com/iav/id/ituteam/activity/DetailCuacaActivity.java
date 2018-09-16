package com.iav.id.ituteam.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;

public class DetailCuacaActivity extends AppCompatActivity {
    private String cuacaDerajat;
    private String wilayah;
    private String kondisi;
    private String kekuatanAngin;
    private String updateLast;
    private TextView tvDetailCuacaWilayah;
    private TextView tvDetailCuacaUpdatelast;
    private TextView tvDetailCuacaKondisi;
    private TextView tvDetailCuacaKekuatanAngin;
    private TextView tvDetailCuacaDerajat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cuaca);
        getSupportActionBar().hide();
        initView();
        cuacaDerajat = getIntent().getStringExtra(Config.BUNDLE_CUACA_DERAJAT);
        wilayah = getIntent().getStringExtra(Config.BUNDLE_CUACA_WILAYAH);
        kondisi = getIntent().getStringExtra(Config.BUNDLE_CUACA_KONDISI);
        kekuatanAngin = getIntent().getStringExtra(Config.BUNDLE_CUACA_KEKUATANANGIN);
        updateLast = getIntent().getStringExtra(Config.BUNDLE_CUACA_UPDATELAST);

        tvDetailCuacaWilayah.setText(wilayah);
        tvDetailCuacaUpdatelast.setText(updateLast);
        tvDetailCuacaKondisi.setText("Condition : " +kondisi);
        tvDetailCuacaKekuatanAngin.setText("Wind : " + kekuatanAngin + " km/h");
        tvDetailCuacaDerajat.setText(cuacaDerajat + "°C");




    }

    private void initView() {
        tvDetailCuacaWilayah = findViewById(R.id.tv_detail_cuaca_wilayah);
        tvDetailCuacaUpdatelast = findViewById(R.id.tv_detail_cuaca_updatelast);
        tvDetailCuacaKondisi = findViewById(R.id.tv_detail_cuaca_kondisi);
        tvDetailCuacaKekuatanAngin = findViewById(R.id.tv_detail_cuaca_kekuatan_angin);
        tvDetailCuacaDerajat = findViewById(R.id.tv_detail_cuaca_derajat);
    }
}
