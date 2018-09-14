package com.iav.id.ituteam.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.LodgingActivity;
import com.iav.id.ituteam.activity.healthUI.HealthActivity;
import com.iav.id.ituteam.adapter.EventBerandaAdapter;
import com.iav.id.ituteam.adapter.NewsHorizontalAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.helper.location.GPSTracker;
import com.iav.id.ituteam.model.EventModel;
import com.iav.id.ituteam.model.newsModel.ArticlesItem;
import com.iav.id.ituteam.model.newsModel.NewsModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;
import com.kwabenaberko.openweathermaplib.Lang;
import com.kwabenaberko.openweathermaplib.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {


    private LinearLayout divHealth;
    private LinearLayout divGarbage;
    private LinearLayout divSecurity;
    private LinearLayout divEconomy;
    private LinearLayout divLodging;
    private LinearLayout divTravel;

    private String pointDonor;
    private String totalDonor;

    private String gold;
    private String idUser;
    private String kota;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView tvTotalDonorDarah;
    private TextView tv_beranda_poin;
    private RecyclerView rvBerandaHariIniEvent;
    private RecyclerView rvBerandaHariIniBerita;
    private TextView tvTotalDonorAsi;
    private TextView tvTotalEvent;

    private ArrayList<ArticlesItem> articlesItems;
    private NewsHorizontalAdapter newsHorizontalAdapter;

    private ArrayList<EventModel> eventModels;
    private EventBerandaAdapter eventBerandaAdapter;

    private OpenWeatherMapHelper openWeatherMapHelper;

    private double Lat, Long;
    GPSTracker gpsTracker;
    private TextView tvCloudCelcius;
    private TextView tvCloudDeskripsi;
    private LottieAnimationView lottieAnimationView;

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        initView(view);
        articlesItems = new ArrayList<>();
        eventModels = new ArrayList<>();
        openWeatherMapHelper = new OpenWeatherMapHelper();
        gpsTracker = new GPSTracker(getActivity());
        lottieAnimationView.playAnimation();
        if (gpsTracker.canGetLocation()) {
            Lat = gpsTracker.getLatitude();
            Long = gpsTracker.getLongitude();
            Log.d("GPS", "gps: " + Lat + " >>> " + Long);

        } else {
            gpsTracker.showSettingsAlert();
        }
        openWeatherMapHelper.setApiKey("6be6815084754becf520194051365a87");
        openWeatherMapHelper.setUnits(Units.METRIC);
        openWeatherMapHelper.setLang(Lang.ENGLISH);

        openWeatherMapHelper.getCurrentWeatherByGeoCoordinates(Lat, Long, new OpenWeatherMapHelper.CurrentWeatherCallback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(CurrentWeather currentWeather) {
                tvCloudCelcius.setText(currentWeather.getMain().getTempMin() + "° ~ " + currentWeather.getMain().getTempMax() +"°");
                tvCloudDeskripsi.setText(currentWeather.getWeatherArray().get(0).getDescription() + ". " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry());

                Log.v(TAG,
                        "Coordinates: " + currentWeather.getCoord().getLat() + ", " + currentWeather.getCoord().getLon() + "\n"
                                + "Weather Description: " + currentWeather.getWeatherArray().get(0).getDescription() + "\n"
                                + "Max Temperature: " + currentWeather.getMain().getTempMax() + "\n"
                                + "Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                                + "City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
                );
            }

            @Override
            public void onFailure(Throwable throwable) {
//                Toast.makeText(getContext(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        divHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), HealthActivity.class);
                intent.putExtra(Config.BUNDLE_JENIS_KATEGORI, "Kesehatan");
                intent.putExtra(Config.BUNDLE_PINDAH_TOKEN, "u43uy78esufh4-344ru9jskjfd-i34rwrb23o4ndfXsddD");
                getActivity().startActivity(intent);
            }
        });
        divGarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthActivity.class);
                intent.putExtra(Config.BUNDLE_JENIS_KATEGORI, "Sampah");
                intent.putExtra(Config.BUNDLE_PINDAH_TOKEN, "jdhfjsdh6637yGGJSHJ-sfuHbsdfb74NJBB-UIE+sdjfu74893");
                getActivity().startActivity(intent);
            }
        });

        divLodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LodgingActivity.class));
            }
        });
        sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_NAME, Context.MODE_PRIVATE);
        idUser = sharedPreferences.getString(Config.SHARED_ID_USER, "");
        kota = sharedPreferences.getString(Config.SHARED_KOTA_KAB, "");
        gold = sharedPreferences.getString(Config.SHARED_TOTAL_GOLD, "");
        tv_beranda_poin.setText("Rp." +gold);
        getData();

        return view;
    }

    private void getEventBeranda() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getEvenKotatBeranda("Semarang", "98498uijJHhijnbf9854Ooije88403jid-3-q230-uerhi")
                .enqueue(new Callback<ArrayList<EventModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<EventModel>> call, Response<ArrayList<EventModel>> response) {
                        if (response.isSuccessful()) {
                            eventModels = response.body();
                            eventBerandaAdapter = new EventBerandaAdapter(eventModels, getActivity());
                            rvBerandaHariIniEvent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            rvBerandaHariIniEvent.setAdapter(eventBerandaAdapter);
                            eventBerandaAdapter.notifyDataSetChanged();
                            lottieAnimationView.setVisibility(View.GONE);
                            getBeritaBeranda();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<EventModel>> call, Throwable t) {
                        lottieAnimationView.setVisibility(View.GONE);
//                        Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                        getEventBeranda();
                    }
                });
    }

    private void getBeritaBeranda() {
        ApiService apiService = Client.getInstanceRetrofitNews();
        apiService.getNewsBeranda().enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful()) {
                    articlesItems = response.body().getArticles();
                    newsHorizontalAdapter = new NewsHorizontalAdapter(getActivity(), articlesItems);
                    rvBerandaHariIniBerita.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    rvBerandaHariIniBerita.setAdapter(newsHorizontalAdapter);
                    newsHorizontalAdapter.notifyDataSetChanged();
                    lottieAnimationView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                lottieAnimationView.setVisibility(View.GONE);
//                Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                getBeritaBeranda();
            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        getData();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        getData();
//    }

    private void getData() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.getDataMain(idUser, "77748gfieu-3487hjdfghur4Hhjheriirh", kota)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String jumlah_donor_darah = jsonObject.optString("jumlah_donor_darah");
                                String jumlah_donor_asi = jsonObject.optString("jumlah_donor_asi");
                                String jumlah_event = jsonObject.optString("jumlah_event");
                                Log.d("", "onResponse: " + jumlah_donor_asi);
                                tvTotalDonorDarah.setText(jumlah_donor_darah + " kali");
                                tvTotalDonorAsi.setText(jumlah_donor_asi);
                                tvTotalEvent.setText(jumlah_event);
                                lottieAnimationView.setVisibility(View.GONE);
                                getEventBeranda();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        lottieAnimationView.setVisibility(View.GONE);
//                        Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                        getData();
                    }
                });
    }

    private void initView(View view) {
        divHealth = view.findViewById(R.id.div_health);
        divGarbage = view.findViewById(R.id.div_garbage);
        divSecurity = view.findViewById(R.id.div_security);
        divEconomy = view.findViewById(R.id.div_economy);
        divLodging = view.findViewById(R.id.div_lodging);
        divTravel = view.findViewById(R.id.div_travel);
        tvTotalDonorDarah = view.findViewById(R.id.tv_total_donor_darah);
        rvBerandaHariIniEvent = view.findViewById(R.id.rv_beranda_hari_ini_event);
        rvBerandaHariIniBerita = view.findViewById(R.id.rv_beranda_hari_ini_berita);
        tvTotalDonorAsi = view.findViewById(R.id.tv_total_donor_asi);
        tvTotalEvent = view.findViewById(R.id.tv_total_event);
        tvCloudCelcius = view.findViewById(R.id.tv_cloud_celcius);
        tvCloudDeskripsi = view.findViewById(R.id.tv_cloud_deskripsi);
        lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
        tv_beranda_poin = view.findViewById(R.id.tv_beranda_poin);
    }
}
