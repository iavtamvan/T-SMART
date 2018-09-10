package com.iav.id.ituteam.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.helper.location.GPSTracker;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;
import com.iav.id.ituteam.rest.api.APIService;
import com.iav.id.ituteam.rest.api.Result;
import com.iav.id.ituteam.rest.api.RetroClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtRegisterNamaLengkap;
    private EditText edtRegisterEmail;
    private EditText edtRegisterTempatTglLahir;
    private EditText edtRegisterProvinsi;
    private EditText edtRegisterKota;
    private EditText edtRegisterAlamat;
    private LinearLayout divContainerRegisterJenisKelamin;
    private TextView tvRegisterJenisKelamin;
    private LinearLayout divContainerRegisterAgama;
    private TextView tvRegisterAgama;
    private EditText edtRegisterUsername;
    private EditText edtRegisterPassword;
    private CircleImageView ivRegisterFoto;
    private Button btnRegister;
    private String h;


    String imagePath;
    private static final String[] PERMISSION_READ_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    private CharSequence[] itemDaftarNews;
    private EditText edtRegisterNoHp;

    GPSTracker gpsTracker;
    private double Lat, Long;
    private GoogleMap mMap;
    private String lamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        gpsTracker = new GPSTracker(this);
        if (gpsTracker.canGetLocation()) {
            Lat = gpsTracker.getLatitude();
            Long = gpsTracker.getLongitude();
            Log.d("GPS", "gps: " + Lat + " >>> " + Long);

        } else {
            gpsTracker.showSettingsAlert();
        }



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(h)){
                    Toast.makeText(RegisterActivity.this, "Pilih foto terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else {
                    postRegisterTSMART();
                }



            }
        });

        ivRegisterFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePopup(v);
            }
        });

        divContainerRegisterJenisKelamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBoxJenisKelamin();
            }
        });

        divContainerRegisterAgama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBoxAgama();
            }
        });


    }

    private void postRegisterTSMART() {
        ApiService apiService = Client.getInstanceRetrofit();
        apiService.potRegister(edtRegisterNamaLengkap.getText().toString().trim(), edtRegisterEmail.getText().toString().trim(), edtRegisterTempatTglLahir.getText().toString().trim()
                , edtRegisterAlamat.getText().toString().trim(), edtRegisterKota.getText().toString().trim(), edtRegisterProvinsi.getText().toString().trim(), edtRegisterNoHp.getText().toString().trim(),
                tvRegisterJenisKelamin.getText().toString().trim(), tvRegisterAgama.getText().toString().trim(), edtRegisterUsername.getText().toString().trim(), edtRegisterPassword.getText().toString().trim()
        ,"http://devlop.can.web.id/uploads/client_profile_images/3/" + h,"Pengguna", "aktif", UUID.randomUUID().toString(), Lat,Long, "Aktif")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String error_msg = jsonObject.optString("error_msg");
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

                    }
                });
    }

    private void dialogBoxJenisKelamin() {
        itemDaftarNews = new CharSequence[]{"Laki - Laki", "Perempuan", "Tidak Tahu"};
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("Urut berdasarkan");
        builder.setItems(itemDaftarNews, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvRegisterJenisKelamin.setText(itemDaftarNews[i]);
            }
        }).show();
    }

    private void dialogBoxAgama() {
        itemDaftarNews = new CharSequence[]{"ISLAM", "KRITEN", "KATHOLIK", "HINDU", "BUDHA", "KONGHUCU", "LAINNYA"};
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("Urut berdasarkan");
        builder.setItems(itemDaftarNews, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvRegisterAgama.setText(itemDaftarNews[i]);
            }
        }).show();
    }


    private void uploadImage() {

        final ProgressDialog p;
        p = new ProgressDialog(this);
        p.setMessage("title iUpload Foto");
        p.show();

        APIService s = RetroClient.getService();

        File f = new File(imagePath);
        h = f.getName();
        Log.d("", "uploadImage: " + f.getName());


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);

        MultipartBody.Part part = MultipartBody.Part.createFormData("uploaded_file", f.getName(), requestFile);
        Call<Result> resultCAll = s.postIMmage(part);
        resultCAll.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                p.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getResult().equals("success")){
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "upload Success", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Upload Image Gagal", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
                    Glide.with(this).load(new File(imagePath)).into(ivRegisterFoto);

                    h = new File(imagePath).getName();
                    Toast.makeText(this, "" + h, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "" + h, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "" + h, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "" + h, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "String Reselect", Toast.LENGTH_SHORT).show();
                    c.close();
                    ivRegisterFoto.setVisibility(View.VISIBLE);
                } else {
                    ivRegisterFoto.setVisibility(View.GONE);
                    Toast.makeText(this, "unable to load Image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initView() {
        edtRegisterNamaLengkap = findViewById(R.id.edt_register_nama_lengkap);
        edtRegisterEmail = findViewById(R.id.edt_register_email);
        edtRegisterTempatTglLahir = findViewById(R.id.edt_register_tempat_tgl_lahir);
        edtRegisterProvinsi = findViewById(R.id.edt_register_provinsi);
        edtRegisterKota = findViewById(R.id.edt_register_kota);
        edtRegisterAlamat = findViewById(R.id.edt_register_alamat);
        divContainerRegisterJenisKelamin = findViewById(R.id.div_container_register_jenis_kelamin);
        tvRegisterJenisKelamin = findViewById(R.id.tv_register_jenis_kelamin);
        divContainerRegisterAgama = findViewById(R.id.div_container_register_agama);
        tvRegisterAgama = findViewById(R.id.tv_register_agama);
        edtRegisterUsername = findViewById(R.id.edt_register_username);
        edtRegisterPassword = findViewById(R.id.edt_register_password);
        ivRegisterFoto = findViewById(R.id.iv_register_foto);
        btnRegister = findViewById(R.id.btn_register);
        edtRegisterNoHp = findViewById(R.id.edt_register_no_hp);
    }
}
