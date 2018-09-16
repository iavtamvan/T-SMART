package com.iav.id.ituteam.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;

public class BantuanDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvDetailBantuanPertanyaan;
    private TextView tvDetailBantuanJawaban;
    private TextView tvDetailBantuanYa;
    private TextView tvDetailBantuanTidakl;
    private FloatingActionButton fab;

    private String pertanyaan, jawaban;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(R.color.black);

        initView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        pertanyaan = getIntent().getStringExtra(Config.BUNDLE_PERTANYAAN_BANTUAN);
        jawaban = getIntent().getStringExtra(Config.BUNDLE_JAWABAN_BANTUAN);

        tvDetailBantuanPertanyaan.setText(pertanyaan);
        tvDetailBantuanJawaban.setText(jawaban);

        tvDetailBantuanYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BantuanDetailActivity.this, "Terimakassih, masukan anda terkirim.", Toast.LENGTH_SHORT).show();
            }
        });
        tvDetailBantuanTidakl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BantuanDetailActivity.this, "Terimakassih, masukan anda terkirim.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        tvDetailBantuanPertanyaan = findViewById(R.id.tv_detail_bantuan_pertanyaan);
        tvDetailBantuanJawaban = findViewById(R.id.tv_detail_bantuan_jawaban);
        tvDetailBantuanYa = findViewById(R.id.tv_detail_bantuan_ya);
        tvDetailBantuanTidakl = findViewById(R.id.tv_detail_bantuan_tidakl);
        fab = findViewById(R.id.fab);
    }
}
