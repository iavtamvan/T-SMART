package com.iav.id.ituteam.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.tukarBarang.DetailTukarBarangActivity;
import com.iav.id.ituteam.activity.tukarBarang.TukarActivity;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.TukarModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class TukarAdapter extends RecyclerView.Adapter<TukarAdapter.ViewHolder> {
    private ArrayList<TukarModel> tukarModels;
    private Context context;

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

    private TukarActivity tukarActivity;
    public TukarAdapter(ArrayList<TukarModel> tukarModels, Context context) {
        this.tukarModels = tukarModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_tukar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        initShared();
        Glide.with(context).load(tukarModels.get(position).getFotoUrl()).into(holder.ivListTukar);
        holder.tvListTukarNama.setText(tukarModels.get(position).getNamaBarang());

        if (tukarModels.get(position).getJenisTukar().equalsIgnoreCase("gold")){
            holder.tvListTukarGold.setText(tukarModels.get(position).getTukarkan());
        } else {
            holder.tvListTukarPoin.setText(tukarModels.get(position).getTukarkan());
        }
        holder.tvListHarga.setText("Rp." +tukarModels.get(position).getHargaBarang());
        holder.cvKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailTukarBarangActivity.class);
                intent.putExtra(Config.BUNDLE_FOTO_URL, tukarModels.get(position).getFotoUrl());
                intent.putExtra(Config.BUNDLE_NAMA_LENGKAP, tukarModels.get(position).getNamaBarang());
                intent.putExtra(Config.BUNDLE_TUKARKAN, tukarModels.get(position).getTukarkan());
                intent.putExtra(Config.BUNDLE_JENIS_TUKAR, tukarModels.get(position).getJenisTukar());
                intent.putExtra(Config.BUNDLE_TANGGAL, tukarModels.get(position).getTglBarang());
                intent.putExtra(Config.BUNDLE_ALAMAT_PENJUAL, tukarModels.get(position).getAlamatPenjual());
                intent.putExtra(Config.BUNDLE_ALAMAT, alamat);
                intent.putExtra(Config.BUNDLE_DESKRIPSI, tukarModels.get(position).getDeskripsiBarang());
                intent.putExtra(Config.BUNDLE_HARGA, tukarModels.get(position).getHargaBarang());
                context.startActivity(intent);
            }
        });
        holder.btnTukarkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new OoOAlertDialog.Builder((Activity) context)
                        .setTitle("Alert penukaran")
                        .setMessage("Apakah kamu yakin menukarkan barang ini?")
                        .setImage(R.drawable.logo)
                        .setAnimation(Animation.POP)
                        .setPositiveButton("YA", new OnClickListener() {
                            @Override
                            public void onClick() {
                                if (tukarModels.get(position).getJenisTukar().equals("gold")){
                                    // Parsingnya yg GOLD
                                    postDataKurangGold(holder.tvListTukarGold.getText().toString().trim());
                                } else {
                                    // Parsingnya yg Point
                                    postDataKurangPoin(holder.tvListTukarPoin.getText().toString().trim());
                                }
                            }
                        })
                        .setNegativeButton("TIDAK", new OnClickListener() {
                            @Override
                            public void onClick() {

                            }
                        })
                        .build();
            }
        });


    }

    private void postDataKurangPoin(String tukarPoint) {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.postTukarPoin("tukarPoin",id_user, tukarPoint)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String hasil_akhir_point = jsonObject.optString("hasil_akhir_point");
                                SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString(Config.SHARED_TOTAL_POINT, hasil_akhir_point);

                                editor.apply();

                                Toast.makeText(context, "Sukses menukarkan. Poin anda : " + hasil_akhir_point, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "" + Config.ERROR_PENUKARAN_BARANG, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void postDataKurangGold(String tukarGold) {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.postTukarGold("tukarGold", id_user, tukarGold)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String hasil_akhir_gold = jsonObject.optString("hasil_akhir_gold");
                                SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString(Config.SHARED_TOTAL_GOLD, hasil_akhir_gold);

                                editor.apply();
                                Toast.makeText(context, "Sukses menukarkan. Gold anda : " + hasil_akhir_gold, Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "" + Config.ERROR_PENUKARAN_BARANG, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return tukarModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cvKlik;
        private ImageView ivListTukar;
        private TextView tvListTukarNama;
        private TextView tvListTukarPoin;
        private TextView tvListTukarGold;
        private Button btnTukarkan;
        private TextView tvListHarga;

        public ViewHolder(View itemView) {
            super(itemView);

            cvKlik = itemView.findViewById(R.id.cv_klik);
            ivListTukar = itemView.findViewById(R.id.iv_list_tukar);
            tvListTukarNama = itemView.findViewById(R.id.tv_list_tukar_nama);
            tvListTukarPoin = itemView.findViewById(R.id.tv_list_tukar_poin);
            tvListTukarGold = itemView.findViewById(R.id.tv_list_tukar_gold);
            btnTukarkan = itemView.findViewById(R.id.btn_tukarkan);
            tvListHarga = itemView.findViewById(R.id.tv_list_tukar_harga);
        }
    }

    private void initShared() {
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
    }
}
