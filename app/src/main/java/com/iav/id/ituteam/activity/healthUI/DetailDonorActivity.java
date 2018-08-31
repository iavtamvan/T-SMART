package com.iav.id.ituteam.activity.healthUI;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailDonorActivity extends AppCompatActivity {

    private AppBarLayout appBar;
    private CollapsingToolbarLayout toolbarLayout;
    private Toolbar toolbar;
    private TextView tvDonorDarahNama;
    private TextView tvDonorDarahTempatTglLahir;
    private TextView tvDonorDarahKotaKabupatenAlamat;
    private TextView tvDonorDarahJenisKelamin;
    private LinearLayout divContainerGolDarah;
    private TextView tvDonorDarahGolongan;
    private LinearLayout divContainerGolDarahRhesus;
    private TextView tvDonorDarahGolonganRhesus;
    private LinearLayout divContainerDonorDarahTgl;
    private TextView tvDonorDarahTanggal;
    private LinearLayout divContainerDonorDarahTglJatuhTempo;
    private TextView tvDonorDarahTanggalJatuhTempo;
    private TextView tvDonorDarahNoRegPmi;
    private TextView tvDonorDarahToken;
    private TextView tvDonorDarahPoint;
    private CircleImageView ivCircleGolDarah;

    private String foto_url;
    private String nama_lengkap;
    private String email;
    private String tempat_tgl_lahir;
    private String alamat;
    private String no_hp;
    private String jenis_kelamin;
    private String golongan_darah;
    private String rhesus;
    private String tanggal_donor_darah;
    private String jatuh_tempo_tanggal_donor;
    private String reg_pmi;
    private String token;
    private String status_donor;
    private String selection;
    private String tgl_perah_asi;


    private TextView tvDonorAsiStatus;
    private TextView tvDonorDarahNamaTop;
    private LinearLayout divContainerDonorDarahRegPmi;
    private TextView tvDonorDarahTanggalTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_donor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        initView();

        selection = getIntent().getStringExtra(Config.BUNDLE_SELECTION_DETAIL);
        foto_url = getIntent().getStringExtra(Config.BUNDLE_FOTO_URL);
        nama_lengkap = getIntent().getStringExtra(Config.BUNDLE_NAMA_LENGKAP);
        email = getIntent().getStringExtra(Config.BUNDLE_EMAIL);
        tempat_tgl_lahir = getIntent().getStringExtra(Config.BUNDLE_TEMPAT_TGL_LAHIR);
        alamat = getIntent().getStringExtra(Config.BUNDLE_ALAMAT);
        no_hp = getIntent().getStringExtra(Config.BUNDLE_NO_HP);
        jenis_kelamin = getIntent().getStringExtra(Config.BUNDLE_JENIS_KELAMIN);
        token = getIntent().getStringExtra(Config.BUNDLE_TOKEN);
        status_donor = getIntent().getStringExtra(Config.BUNDLE_STATUS_DONOR);
        if (selection.equalsIgnoreCase("ASI")) {
            tgl_perah_asi = getIntent().getStringExtra(Config.BUNDLE_TANGGAL_PERAH_ASI);
            divContainerDonorDarahRegPmi.setVisibility(View.GONE);
            divContainerDonorDarahTglJatuhTempo.setVisibility(View.GONE);
            divContainerGolDarah.setVisibility(View.GONE);
            divContainerGolDarahRhesus.setVisibility(View.GONE);

            Glide.with(this).load(foto_url).into(ivCircleGolDarah);
            tvDonorDarahNamaTop.setText("Pendonor ASI");
            tvDonorDarahPoint.setText("3");
            tvDonorDarahTanggalTop.setText("Tanggal Perah ASI");
            tvDonorDarahTanggal.setText(tgl_perah_asi);
            tvDonorDarahNama.setText(nama_lengkap);
            tvDonorDarahTempatTglLahir.setText(tempat_tgl_lahir);
            tvDonorDarahKotaKabupatenAlamat.setText(alamat);
            tvDonorDarahJenisKelamin.setText(jenis_kelamin);
            tvDonorDarahToken.setText(token);
            tvDonorAsiStatus.setText("Status Donor : " + status_donor);

        } else if (selection.equalsIgnoreCase("Darah")) {
            golongan_darah = getIntent().getStringExtra(Config.BUNDLE_GOLONGAN_DARAH);
            rhesus = getIntent().getStringExtra(Config.BUNDLE_RHESUS);
            tanggal_donor_darah = getIntent().getStringExtra(Config.BUNDLE_TANGGAL_DONOR_DARAH);
            jatuh_tempo_tanggal_donor = getIntent().getStringExtra(Config.BUNDLE_JATUH_TEMPO_TANGGAL_DONOR);
            reg_pmi = getIntent().getStringExtra(Config.BUNDLE_REG_PMI);


            Glide.with(this).load(foto_url).into(ivCircleGolDarah);
            tvDonorDarahNama.setText(nama_lengkap);
            tvDonorDarahTempatTglLahir.setText(tempat_tgl_lahir);
            tvDonorDarahKotaKabupatenAlamat.setText(alamat);
            tvDonorDarahJenisKelamin.setText(jenis_kelamin);
            tvDonorDarahGolongan.setText(golongan_darah);
            tvDonorDarahGolonganRhesus.setText(rhesus);
            tvDonorDarahTanggal.setText(tanggal_donor_darah);
            tvDonorDarahTanggalJatuhTempo.setText(jatuh_tempo_tanggal_donor);
            tvDonorDarahNoRegPmi.setText(reg_pmi);
            tvDonorDarahToken.setText(token);
            tvDonorAsiStatus.setText("Status Donor : " + status_donor);
        }


    }

    private void initView() {
        appBar = findViewById(R.id.app_bar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        toolbar = findViewById(R.id.toolbar);
        tvDonorDarahNama = findViewById(R.id.tv_donor_darah_nama);
        tvDonorDarahTempatTglLahir = findViewById(R.id.tv_donor_darah_tempat_tgl_lahir);
        tvDonorDarahKotaKabupatenAlamat = findViewById(R.id.tv_donor_darah_kota_kabupaten_alamat);
        tvDonorDarahJenisKelamin = findViewById(R.id.tv_donor_darah_jenis_kelamin);
        divContainerGolDarah = findViewById(R.id.div_container_gol_darah);
        tvDonorDarahGolongan = findViewById(R.id.tv_donor_darah_golongan);
        divContainerGolDarahRhesus = findViewById(R.id.div_container_gol_darah_rhesus);
        tvDonorDarahGolonganRhesus = findViewById(R.id.tv_donor_darah_golongan_rhesus);
        divContainerDonorDarahTgl = findViewById(R.id.div_container_donor_darah_tgl);
        tvDonorDarahTanggal = findViewById(R.id.tv_donor_darah_tanggal);
        divContainerDonorDarahTglJatuhTempo = findViewById(R.id.div_container_donor_darah_tgl_jatuh_tempo);
        tvDonorDarahTanggalJatuhTempo = findViewById(R.id.tv_donor_darah_tanggal_jatuh_tempo);
        tvDonorDarahNoRegPmi = findViewById(R.id.tv_donor_darah_no_reg_pmi);
        tvDonorDarahToken = findViewById(R.id.tv_donor_darah_token);
        tvDonorDarahPoint = findViewById(R.id.tv_donor_darah_point);
        ivCircleGolDarah = findViewById(R.id.iv_circle_gol_darah);
        tvDonorAsiStatus = findViewById(R.id.tv_donor_asi_status);
        tvDonorDarahNamaTop = findViewById(R.id.tv_donor_darah_nama_top);
        divContainerDonorDarahRegPmi = findViewById(R.id.div_container_donor_darah_reg_pmi);
        tvDonorDarahTanggalTop = findViewById(R.id.tv_donor_darah_tanggal_top);
    }
}
