package com.iav.id.ituteam.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.iav.id.ituteam.MainActivity;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;
import com.iav.id.ituteam.rest.api.APIService;
import com.iav.id.ituteam.rest.api.Result;
import com.iav.id.ituteam.rest.api.RetroClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GarbageActivity extends AppCompatActivity {

    private TextView tvSampahNamaLengkap;
    private LinearLayout divContainerSampahTanggal;
    private TextView tvSampahTanggal;
    private EditText edtSampahAlamatOriginal;
    private ImageView ivSampahAlamatGoogle;
    private TextView tvSampahAlamatKota;
    private LinearLayout divContainerSampahJenis;
    private TextView tvSampahJenis;
    private TextView tvSampahHargaTotal;
    private TextView tvSampahToken;
    private Button btnSampahKirimkan;

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
    private EditText edtSampahBerat;
    private String h;
    String imagePath;
    private String hitungTotalHarga;
    private double latalamat, longalamat;

    private int PLACE_PICKER_REQUEST = 1;
    private CharSequence[] itemDaftarNews;
    private CircleImageView ciSampah;
    private int mSelectedYear;
    private int mSelectedMonth;
    private int mSelectedDay;
    private ImageView ivSampahUpload;
    private TextView tvSampahNamaFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbage);
        initView();
        initShared();
        Glide.with(this).load(foto_url).into(ciSampah);
        tvSampahNamaLengkap.setText(nama_lengkap);
        tvSampahAlamatKota.setText(kota_kab);
        btnSampahKirimkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataSampah();
            }
        });

        ivSampahAlamatGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(GarbageActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        divContainerSampahJenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBoxJenisKelamin();
            }
        });

        divContainerSampahTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateDialog();
            }
        });
        edtSampahBerat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (edtSampahBerat.getText().toString().isEmpty()) {
                    edtSampahBerat.setText("0");
                } else {

                    hitungTotalHarga = String.valueOf(Integer.parseInt(edtSampahBerat.getText().toString().trim()) * 1000);
                    tvSampahHargaTotal.setText(hitungTotalHarga);
                }
            }
        });

        tvSampahToken.setText(UUID.randomUUID().toString());
        ivSampahUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePopup(view);
            }
        });

    }

    private void dialogBoxJenisKelamin() {
        itemDaftarNews = new CharSequence[]{"Organik", "An Organik", "Beracun"};
        AlertDialog.Builder builder = new AlertDialog.Builder(GarbageActivity.this);
        builder.setTitle("Urut berdasarkan");
        builder.setItems(itemDaftarNews, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvSampahJenis.setText(itemDaftarNews[i]);
            }
        }).show();
    }

    private void dateDialog() {
        final Calendar c = Calendar.getInstance();
        mSelectedYear = c.get(Calendar.YEAR);
        mSelectedMonth = c.get(Calendar.MONTH);
        mSelectedDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(GarbageActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvSampahTanggal.setText(Config.formatDMY(year, (monthOfYear + 1), dayOfMonth));

                    }
                }, mSelectedYear, mSelectedMonth, mSelectedDay);
        datePickerDialog.show();
    }

    private void uploadImage() {

        final ProgressDialog p;
        p = new ProgressDialog(this);
        p.setMessage("Upload Foto");
        p.show();

        APIService s = RetroClient.getService();

        File f = new File(imagePath);
        h = f.getName();
        tvSampahNamaFile.setText(h);
        Log.d("", "uploadImage: " + f.getName());


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);

        MultipartBody.Part part = MultipartBody.Part.createFormData("uploaded_file", f.getName(), requestFile);
        Call<Result> resultCAll = s.postIMmage(part);
        resultCAll.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                p.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getResult().equals("success")) {
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(GarbageActivity.this, "upload Success", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GarbageActivity.this, "Upload Image Gagal", Toast.LENGTH_SHORT).show();
                }

                imagePath = "";
//                te.setVisibility(View.VISIBLE);
//                imageVi.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                p.dismiss();


            }
        });
    }

    public void showImagePopup(View v) {

        Intent qq = new Intent(Intent.ACTION_PICK);
        qq.setType("image/*");
        startActivityForResult(Intent.createChooser(qq, "Pilih Foto"), 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format(
                        "Place: %s \n" +
                                "Alamat: %s \n" +
                                "Latlng %s \n", place.getName(), place.getAddress(), place.getLatLng().latitude + " " + place.getLatLng().longitude);
                edtSampahAlamatOriginal.setText(place.getAddress());
                latalamat = place.getLatLng().latitude;
                longalamat = place.getLatLng().longitude;
            }
        } else {
            if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
                if (data == null) {
                    Toast.makeText(this, "unable to pick Image", Toast.LENGTH_SHORT).show();
                    return;

                } else {
//                Toast.makeText(this, "image dapat", Toast.LENGTH_SHORT).show();

                    Uri selectImageUri = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor c = getContentResolver().query(selectImageUri, filePathColumn, null, null, null);
                    if (c != null) {
                        c.moveToFirst();

                        int columnIndex = c.getColumnIndex(filePathColumn[0]);
                        imagePath = c.getString(columnIndex);
//                Picasso.with(context).load(new File(imagePath)).into(imageVi);
                        Glide.with(this).load(new File(imagePath)).into(ivSampahUpload);

                        h = new File(imagePath).getName();
                        Toast.makeText(this, "" + h, Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, "" + h, Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, "" + h, Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, "" + h, Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, "String Reselect", Toast.LENGTH_SHORT).show();
                        c.close();
                        ivSampahUpload.setVisibility(View.VISIBLE);
                    } else {
//                        ivRegisterFoto.setVisibility(View.GONE);
                        Toast.makeText(this, "unable to load Image", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void postDataSampah() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.postDataSampah(id_user, tvSampahTanggal.getText().toString().trim(), edtSampahAlamatOriginal.getText().toString().trim(), edtSampahBerat.getText().toString().trim()
                , tvSampahJenis.getText().toString().trim(), tvSampahHargaTotal.getText().toString().trim(), "123", "http://devlop.can.web.id/uploads/client_profile_images/3/" + h, "Menunggu", uuid, latalamat, longalamat)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String error_msg = jsonObject.optString("error_msg");
                                String error = jsonObject.optString("error");
                                uploadImage();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(GarbageActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        tvSampahNamaLengkap = findViewById(R.id.tv_sampah_nama_lengkap);
        divContainerSampahTanggal = findViewById(R.id.div_container_sampah_tanggal);
        tvSampahTanggal = findViewById(R.id.tv_sampah_tanggal);
        edtSampahAlamatOriginal = findViewById(R.id.edt_sampah_alamat_original);
        ivSampahAlamatGoogle = findViewById(R.id.iv_sampah_alamat_google);
        tvSampahAlamatKota = findViewById(R.id.tv_sampah_alamat_kota);
        divContainerSampahJenis = findViewById(R.id.div_container_sampah_jenis);
        tvSampahJenis = findViewById(R.id.tv_sampah_jenis);
        tvSampahHargaTotal = findViewById(R.id.tv_sampah_harga_total);
        tvSampahToken = findViewById(R.id.tv_sampah_token);
        btnSampahKirimkan = findViewById(R.id.btn_sampah_kirimkan);
        edtSampahBerat = findViewById(R.id.edt_sampah_berat);
        ciSampah = findViewById(R.id.ci_sampah);
        ivSampahUpload = findViewById(R.id.iv_sampah_upload);
        tvSampahNamaFile = findViewById(R.id.tv_sampah_nama_file);
    }

    private void initShared() {
        sharedPreferences = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
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
