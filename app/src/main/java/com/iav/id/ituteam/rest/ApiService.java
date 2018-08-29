package com.iav.id.ituteam.rest;

import com.iav.id.ituteam.model.EventModel;
import com.iav.id.ituteam.model.ListHealthModel;
import com.iav.id.ituteam.model.LoginModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
    Call<ResponseBody> postDonorPasien(@Field("id_user") String id_user,
                                   @Field("gol_darah") String gol_darah,
                                   @Field("tgl_donor") String tgl_donor,
                                   @Field("jatuh_tempo_donor") String jatuh_tempo_donor,
                                   @Field("rhesus_gol") String rhesus_gol,
                                   @Field("no_reg_pmi") String no_reg_pmi,
                                   @Field("status_reg_pasien") String status_reg_pasien,
                                   @Field("token_reg_donor") String token_reg_donor,
                                   @Field("point") String point);

}
