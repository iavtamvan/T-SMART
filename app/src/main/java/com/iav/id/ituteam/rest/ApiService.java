package com.iav.id.ituteam.rest;

import com.iav.id.ituteam.model.BantuanListModel;
import com.iav.id.ituteam.model.BantuanPertanyaanModel;
import com.iav.id.ituteam.model.DonorASIModel;
import com.iav.id.ituteam.model.DonorDarahModel;
import com.iav.id.ituteam.model.EventModel;
import com.iav.id.ituteam.model.GoldPointModel;
import com.iav.id.ituteam.model.GoogleModel.RootLodgingModel;
import com.iav.id.ituteam.model.HelpListModel;
import com.iav.id.ituteam.model.HelpModel;
import com.iav.id.ituteam.model.ListHealthModel;
import com.iav.id.ituteam.model.LoginModel;
import com.iav.id.ituteam.model.SumbanganSampahModel;
import com.iav.id.ituteam.model.TukarModel;
import com.iav.id.ituteam.model.TukarRiwayatModel;
import com.iav.id.ituteam.model.UserPetugasModel;
import com.iav.id.ituteam.model.newsModel.NewsModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("api_login.php")
    Call<LoginModel> postLogin(@Field("username") String username,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<ListHealthModel>> getListHealth(@Field("jenis_kategori") String jenis,
                                                   @Field("pindah") String pindah);

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<EventModel>> getEvent(@Field("jenis_event") String jenis,
                                         @Field("pindah") String pindah);

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<EventModel>> getEventAll(@Field("pindah") String pindah,
                                            @Field("jenis_kategori") String jenis_kategori);

    @FormUrlEncoded
    @POST("api_pasien_donor_darah.php")
    Call<ResponseBody> postDonorDarahPasien(@Field("id_user") String id_user,
                                            @Field("gol_darah") String gol_darah,
                                            @Field("tgl_donor") String tgl_donor,
                                            @Field("jatuh_tempo_donor") String jatuh_tempo_donor,
                                            @Field("rhesus_gol") String rhesus_gol,
                                            @Field("no_reg_pmi") String no_reg_pmi,
                                            @Field("status_reg_pasien") String status_reg_pasien,
                                            @Field("token_reg_donor") String token_reg_donor,
                                            @Field("nama_petugas") String nama_petugas,
                                            @Field("jenis_donor") String point,
                                            @Field("kota") String kota,
                                            @Field("status_donor") String status_donor);

    @FormUrlEncoded
    @POST("api_pasien_donor_asi.php")
    Call<ResponseBody> postDonorASIPasien(
            @Field("id_user") String id_user,
            @Field("tgl_perah") String tgl_perah,
            @Field("lat") String lat,
            @Field("lng") String lng,
            @Field("no_hp_wa") String no_hp_wa,
            @Field("token_asi") String token_asi,
            @Field("jenis_donor") String jenis_donor
    );

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ResponseBody> getDataMain(
            @Field("id_user") String id_user,
            @Field("pindah") String pindah,
            @Field("kota") String kota
    );

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<DonorDarahModel>> getDataRiwayatDarah(
            @Field("id_user") String id_user,
            @Field("pindah") String pindah);

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<DonorASIModel>> getDataRiwayatASI(
            @Field("id_user") String id_user,
            @Field("pindah") String pindah);


    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<EventModel>> getEvenKotatBeranda(
            @Field("kota") String kota,
            @Field("pindah") String pindah);

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<GoldPointModel>> getGolddanPoint(
            @Field("pindah") String pindah,
            @Field("id_user") String idUser);

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<TukarModel>> getTukarPoin(
            @Field("pindah") String pindah);

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<TukarModel>> getTukarGold(
            @Field("pindah") String pindah);

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<TukarModel>> getTukarAll(
            @Field("pindah") String pindah);

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<TukarRiwayatModel>> getTukarRiwayat(
            @Field("pindah") String pindah,
            @Field("id_user") String idUser
    );

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<BantuanListModel>> getBantuanList(
            @Field("pindah") String pindah
    );

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<BantuanPertanyaanModel>> getBantuanPertanyaan(
            @Field("pindah") String pindah,
            @Field("jenis_bantuan") String jenis_bantuan
    );

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<SumbanganSampahModel>> getSumbanganSampah(
            @Field("pindah") String pindah,
            @Field("id_user") String jenis_bantuan
    );

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<HelpModel>> getHelpSort(
            @Field("pindah") String pindah,
            @Field("jenis_help_list") String jenis_help_list
    );

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<HelpListModel>> getHelpList(
            @Field("pindah") String pindah
    );

    @FormUrlEncoded
    @POST("api_tukar_point.php")
    Call<ResponseBody> postTukarPoin(
            @Field("pindah") String pindah,
            @Field("id_user") String id_user,
            @Field("kurang") String kurang,
            @Field("nama_penjual_barang") String nama_penjual_barang,
            @Field("ongkir") String ongkir,
            @Field("alamat_penjual") String alamat_penjual,
            @Field("nama_barang") String nama_barang,
            @Field("harga_barang") String harga_barang,
            @Field("jenis_tukar") String jenis_tukar,
            @Field("alamat_user") String alamat_user,
            @Field("tukarkan") String tukarkan,
            @Field("deskripsi_barang") String deskripsi_barang,
            @Field("foto_url") String foto_url,
            @Field("tgl_barang") String tgl_barang,
            @Field("tgl_penukaran_brang") String tgl_penukaran_brang
    );

    @FormUrlEncoded
    @POST("api_tukar_point.php")
    Call<ResponseBody> postTukarGold(
            @Field("pindah") String pindah,
            @Field("id_user") String id_user,
            @Field("kurang") String kurang,
            @Field("nama_penjual_barang") String nama_penjual_barang,
            @Field("ongkir") String ongkir,
            @Field("alamat_penjual") String alamat_penjual,
            @Field("nama_barang") String nama_barang,
            @Field("harga_barang") String harga_barang,
            @Field("jenis_tukar") String jenis_tukar,
            @Field("alamat_user") String alamat_user,
            @Field("tukarkan") String tukarkan,
            @Field("deskripsi_barang") String deskripsi_barang,
            @Field("foto_url") String foto_url,
            @Field("tgl_barang") String tgl_barang,
            @Field("tgl_penukaran_brang") String tgl_penukaran_brang
    );


    @FormUrlEncoded
    @POST("api_input_sampah.php")
    Call<ResponseBody> postDataSampah(
            @Field("id_user") String id_user,
            @Field("tgl_waktu_sampah") String tgl_waktu_sampah,
            @Field("tempat") String tempat,
            @Field("input_sampah") String input_sampah,
            @Field("jenis_sampah") String jenis_sampah,
            @Field("harga_total") String harga_total,
            @Field("kode_vocer") String kode_vocer,
            @Field("gambar") String gambar,
            @Field("status") String status,
            @Field("token_reg_sampah") String token_reg_sampah,
            @Field("lat") double lat,
            @Field("lng") double lng
    );


    @FormUrlEncoded
    @POST("api_register.php")
    Call<ResponseBody> potRegister(
            @Field("nama_lengkap") String nama_lengkap,
            @Field("email") String email,
            @Field("tempat_tgl_lahir") String tempat_tgl_lahir,
            @Field("alamat") String alamat,
            @Field("kota_kab") String kota_kab,
            @Field("provinsi") String provinsi,
            @Field("no_hp") String no_hp,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("agama") String agama,
            @Field("username") String username,
            @Field("password") String password,
            @Field("foto_url") String foto_url,
            @Field("rule") String rule,
            @Field("status_verifikasi") String status_verifikasi,
            @Field("token") String token,
            @Field("lat") double lat,
            @Field("lng") double lng,
            @Field("status_aplikasi") String status_aplikasi,
            @Field("token_firebase") String token_firebase);

    @GET("top-headlines?country=id&category=health&apiKey=12850cd010b54441aaeff6749dc99cd0")
    Call<NewsModel> getNewsVertical();

    @GET("everything?domains=tribunnews.com&apiKey=12850cd010b54441aaeff6749dc99cd0")
    Call<NewsModel> getNewsHorizontal();

    @GET("top-headlines?country=id&sortBy=popularity&apiKey=12850cd010b54441aaeff6749dc99cd0")
    Call<NewsModel> getNewsBeranda();


    //    q=denpasar&from=2018-08-31&to=2018-08-31&sortBy=popularity&apiKey=12850cd010b54441aaeff6749dc99cd0
    @GET("everything?")
    Call<NewsModel> getNewsByDaerah(@Query("q") String daerah,
                                    @Query("from") String dari_tanggal,
                                    @Query("to") String sampai_tanggal,
                                    @Query("sortBy") String sortBy,
                                    @Query("apiKey") String apiKey);

    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-7.5178785,110.5624369&rankby=distance&types=lodging&key=AIzaSyDK9afBrpN0wHnA5T_O_opQsbhui-PYF_c
    @GET("nearbysearch/json?")
    Call<RootLodgingModel> getLodging(@Query("location") String location,
                                      @Query("rankby") String rankby,
                                      @Query("types") String types,
                                      @Query("key") String key);

    @GET("nearbysearch/json?")
    Call<ResponseBody> getLodgingResponseBody(@Query("location") String location,
                                              @Query("rankby") String rankby,
                                              @Query("types") String types,
                                              @Query("key") String key);

    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<UserPetugasModel>> getUserPetugas(@Field("pindah") String pindah);

    @FormUrlEncoded
    @POST("api_update.php")
    Call<ResponseBody> updateTokenFirebase(@Field("pindah") String pindah,
                                           @Field("id_user") String id_user,
                                           @Field("token_firebase") String token_firebase);


}