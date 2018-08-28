package com.iav.id.ituteam.rest;

import com.iav.id.ituteam.model.EventModel;
import com.iav.id.ituteam.model.ListHealthModel;
import com.iav.id.ituteam.model.LoginModel;

import java.util.ArrayList;

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

}
