package com.iav.id.ituteam.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

public final class Config {

    public static final String ERROR_FORM = "Lengkapi akun anda";
    public static final String ERROR_LOGIN_GAGAL = "Login gagal";
    public static final String ERROR_LOAD = "Cek koneksi anda";
    public static final String ERROR_PERMISSION_SUCCES = "Permission Sukses";
    public static final String ERROR_PERMISSION_FAIL = "Permission Gagal";
    public static final String ERROR_PENUKARAN_BARANG = "Penukaran_barang";



    //BUNDLE
    public static final String BUNDLE_JENIS_KATEGORI = "jenis kategori";
    public static final String BUNDLE_PINDAH_TOKEN = "token";
    public static final String BUNDLE_SELECTION_DETAIL = "selection_detail";

    public static final String BUNDLE_NAME = "bundle_DORA";
    public static final String BUNDLE_ERROR = "bundle_error";
    public static final String BUNDLE_ERROR_MSG = "bundle_error_msg";
    public static final String BUNDLE_RULE = "bundle_rule";
    public static final String BUNDLE_ID_USER = "bundle_id_user";
    public static final String BUNDLE_NAMA_LENGKAP = "bundle_nama_lengkap";
    public static final String BUNDLE_EMAIL = "bundle_email";
    public static final String BUNDLE_TEMPAT_TGL_LAHIR = "bundle_tempat_tgl_lahir";
    public static final String BUNDLE_ALAMAT = "bundle_alamat";
    public static final String BUNDLE_KOTA_KAB = "bundle_kota_kab";
    public static final String BUNDLE_PROVINSI = "bundle_provinsi";
    public static final String BUNDLE_NO_HP = "bundle_no_hp";
    public static final String BUNDLE_JENIS_KELAMIN = "bundle_jenis_kelamin";
    public static final String BUNDLE_AGAMA = "bundle_agama";
    public static final String BUNDLE_USERNAME = "bundle_username";
    public static final String BUNDLE_PASSWORD = "bundle_password";
    public static final String BUNDLE_FOTO_URL = "bundle_foto_url";
    public static final String BUNDLE_STATUS_VERIFIKASI = "bundle_status_verifikasi";
    public static final String BUNDLE_TOKEN = "bundle_token";
    public static final String BUNDLE_LAT = "bundle_lat";
    public static final String BUNDLE_LNG = "bundle_lng";
    public static final String BUNDLE_STATUS_APLIKASI = "bundle_status_aplikasi";
    public static final String BUNDLE_GOLONGAN_DARAH = "bundle_golongan_darah";
    public static final String BUNDLE_REG_PMI = "bundle_reg_pmi";
    public static final String BUNDLE_RHESUS = "bundle_rhesus";
    public static final String BUNDLE_TANGGAL_DONOR_DARAH = "bundle_donor_darah_tgl";
    public static final String BUNDLE_JATUH_TEMPO_TANGGAL_DONOR = "bundle_donor_darah_tgl_jatuh_tempo";
    public static final String BUNDLE_POINt_DONOR = "bundle_point_donor";
    public static final String BUNDLE_TOTAL_DONOR_DISETUJUI = "bundle_Ttotal_donor_disetujui";
    public static final String BUNDLE_TOTAL_DONOR_MENUNGGU = "bundle_Ttotal_donor_menunggu";
    public static final String BUNDLE_TANGGAL_PERAH_ASI = "bundle_tanggal_perah_asi";
    public static final String BUNDLE_TOTAL_POINT_ASI = "bundle_total_point_asi";
    public static final String BUNDLE_TOTAL_DONOR_ASI_DISETUJUI = "bundle_total_donor_asi_disetujui";
    public static final String BUNDLE_TOTAL_DONOR_ASI_MENUNGGU = "bundle_total_donor_asi_menunggu  ";
    public static final String BUNDLE_STATUS_DONOR = "bundle_total_status_donor_darah";
    public static final String BUNDLE_NAMA_PETUGAS = "nama_petugas";
    public static final String BUNDLE_DESKRIPSI = "deskripsi_event";
    public static final String BUNDLE_TANGGAL_WAKTU_EVENT = "tgl_event";
    public static final String BUNDLE_JENIS_EVENT = "jenis_event";
    public static final String BUNDLE_LIKES_EVENT = "likes_event";
    public static final String BUNDLE_STATUS_EVENT = "status_event";

    // SHARED
    public static final String SHARED_NAME = "DORA";
    public static final String SHARED_ERROR = "error";
    public static final String SHARED_ERROR_MSG = "error_msg";
    public static final String SHARED_RULE = "rule";
    public static final String SHARED_ID_USER = "id_user";
    public static final String SHARED_NAMA_LENGKAP = "nama_lengkap";
    public static final String SHARED_EMAIL = "email";
    public static final String SHARED_TEMPAT_TGL_LAHIR = "tempat_tgl_lahir";
    public static final String SHARED_ALAMAT = "alamat";
    public static final String SHARED_KOTA_KAB = "kota_kab";
    public static final String SHARED_PROVINSI = "provinsi";
    public static final String SHARED_NO_HP = "no_hp";
    public static final String SHARED_JENIS_KELAMIN = "jenis_kelamin";
    public static final String SHARED_AGAMA = "agama";
    public static final String SHARED_USERNAME = "username";
    public static final String SHARED_PASSWORD = "password";
    public static final String SHARED_FOTO_URL = "foto_url";
    public static final String SHARED_STATUS_VERIFIKASI = "status_verifikasi";
    public static final String SHARED_TOKEN = "token";
    public static final String SHARED_LAT = "lat";
    public static final String SHARED_LNG = "lng";
    public static final String SHARED_STATUS_APLIKASI = "status_aplikasi";
    public static final String SHARED_GOLONGAN_DARAH = "golongan_darah";
    public static final String SHARED_REG_PMI = "reg_pmi";
    public static final String SHARED_RHESUS = "rhesus";
    public static final String SHARED_TANGGAL_DONOR_DARAH = "donor_darah_tgl";
    public static final String SHARED_JATUH_TEMPO_TANGGAL_DONOR = "donor_darah_tgl_jatuh_tempo";
    public static final String SHARED_POINt_DONOR = "point_donor";
    public static final String SHARED_TOTAL_DONOR_DISETUJUI = "Ttotal_donor_disetujui";
    public static final String SHARED_TOTAL_DONOR_MENUNGGU = "Ttotal_donor_menunggu";
    public static final String SHARED_TANGGAL_PERAH_ASI = "tanggal_perah_asi";
    public static final String SHARED_TOTAL_POINT_ASI = "total_point_asi";
    public static final String SHARED_TOTAL_DONOR_ASI_DISETUJUI = "total_donor_asi_disetujui";
    public static final String SHARED_TOTAL_DONOR_ASI_MENUNGGU = "total_donor_asi_menunggu  ";
    public static final String SHARED_TOTAL_POINT = "total_point";
    public static final String SHARED_TOTAL_GOLD = "total_gold";

    public static String BUNDLE_TUKARKAN = "tukarkan";
    public static String BUNDLE_JENIS_TUKAR = "jenis tukar";
    public static String BUNDLE_TANGGAL = "tanggal_barang";
    public static String BUNDLE_ALAMAT_PENJUAL = "alamat penjual";
    public static String BUNDLE_HARGA = "harga";
    public static String BUNDLE_NAMA_PENJUAL = "nama_penjual";
    public static String BUNDLE_ONGKIR = "ongkir";
    public static String BUNDLE_TUKAR_RIWAYAT ="tukar_riwayat";
    public static String BUNDLE_JENIS_BANTUAN = "jenis_bantuan";
    public static String BUNDLE_PERTANYAAN_BANTUAN = "pertabnyaan_bantuan";
    public static String BUNDLE_JAWABAN_BANTUAN = "jawaban bantuan";
    public static String BUNDLE_CUACA_DERAJAT = "cuaca derajat";
    public static String BUNDLE_CUACA_WILAYAH = "cuaca wilayah";
    public static String BUNDLE_CUACA_KONDISI = "cuaca kondisi";
    public static String BUNDLE_CUACA_KEKUATANANGIN = "cuaca kekuatan angin";
    public static String BUNDLE_CUACA_UPDATELAST = "cuaca last";
    public static String BUNDLE_URL_NEWS = "url_news";
    public static String BUNDLE_GAMBAR_SAMPAH = "gambar sampah";
    public static String BUNDLE_INPUT_SAMPAH = "input sampah";
    public static String BUNDLE_JENIS_SAMPAH = "jenis sampah";
    public static String BUNDLE_STATUS_SAMPAH = "status sampah";
    public static String BUNDLE_GAMBAR_PETUGAS_SAMPAH = "gambar petugas sampah";
    public static String SHARE_FIREBASE_TOKEN = "firebase token" ;
    public static String BUNDLE_JENIS_HELP = "jenis help";


    public static String formatDMY(int year, int month, int date) {
        String formattedDate = "";

        if(date < 10)   {
            formattedDate += Integer.toString(year);
        }
        else {
            formattedDate += Integer.toString(year);
        }
        formattedDate += "-";

        if(month < 10) {
            formattedDate += "0" + Integer.toString(month);
        }
        else {
            formattedDate += Integer.toString(month);
        }
        formattedDate += "-";

        formattedDate += Integer.toString(date);

        return formattedDate;
    }


    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "ah_firebase";



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
    private Context context;

    public void initShared() {
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
        point = sharedPreferences.getString(Config.SHARED_TOTAL_POINT, "");
        gold = sharedPreferences.getString(Config.SHARED_TOTAL_GOLD, "");
    }

}
