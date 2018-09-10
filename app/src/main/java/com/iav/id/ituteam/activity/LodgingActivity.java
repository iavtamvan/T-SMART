package com.iav.id.ituteam.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.model.GoogleModel.ResultsItem;
import com.iav.id.ituteam.model.GoogleModel.RootLodgingModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LodgingActivity extends AppCompatActivity {
    private double lat, lng;
    private ArrayList<ResultsItem>  resultsItems;
    private String TAG = "GOGOLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodging);
        resultsItems = new ArrayList<>();

//        getLodging();
        getLodgingResponseBody();
    }

    private void getLodgingResponseBody() {
        ApiService apiService = Client.getInstanceRetrofitGOOGLE();
        apiService.getLodgingResponseBody("-7.5178785,110.5624369", "distance", "lodging", "AIzaSyDK9afBrpN0wHnA5T_O_opQsbhui-PYF_c")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                JSONArray jsonArrayResults = jsonObject.optJSONArray("results");
                                for (int i = 0; i < jsonArrayResults.length(); i++) {
                                    JSONObject jsonObjectResult = jsonArrayResults.getJSONObject(i);
                                    JSONObject geometry = jsonObjectResult.optJSONObject("geometry");
                                    JSONObject locationGeometry = geometry.optJSONObject("location");
                                    String geoLocLat = locationGeometry.optString("lat");
                                    String geoLoclng = locationGeometry.optString("lng");
                                    Log.d(TAG, "onResponseGeoLoc: " + geoLocLat + geoLoclng);

                                    JSONObject viewPortGeometry = geometry.optJSONObject("viewport");
                                    JSONObject northeastGeometry = viewPortGeometry.optJSONObject("northeast");
                                    String viewportLocLat = northeastGeometry.optString("lat");
                                    String viewportLoclng = northeastGeometry.optString("lng");
                                    Log.d(TAG, "onResponseGeoView: " + viewportLocLat+ viewportLoclng);

                                    JSONObject southwestGeometry = viewPortGeometry.optJSONObject("southwest");
                                    String southwestLocLat = southwestGeometry.optString("lat");
                                    String southwestLoclng = southwestGeometry.optString("lng");
                                    Log.d(TAG, "onResponseGeosouthwest: " + southwestLocLat+ southwestLoclng);


                                    String icon = jsonObjectResult.optString("icon");
                                    String id = jsonObjectResult.optString("id");
                                    String name = jsonObjectResult.optString("name");
                                    Log.d(TAG, "onResponseIconIDName: " + icon + id + name);

                                    JSONObject jsonObjectOpening = jsonObjectResult.optJSONObject("opening_hours");
                                    if (jsonObjectOpening == null){
                                        Log.d(TAG, "onResponseOpening: Null");
                                    } else {
                                        String open_now = jsonObjectOpening.optString("open_now");
                                        Log.d(TAG, "onResponseOpenNow: " + open_now);
                                    }
                                    JSONArray jsonArrayPhotos = jsonObjectResult.optJSONArray("photos");
                                    if (jsonArrayPhotos == null){
                                        Log.d(TAG, "onResponsePhotos: null");
                                    } else {
                                        JSONObject jsonObjectPhotos = jsonArrayPhotos.optJSONObject(0);
                                        String photo_reference = jsonObjectPhotos.optString("photo_reference");
                                        Log.d(TAG, "onResponsePhotosReference: " + photo_reference);
                                    }
                                    String place_id = jsonObjectResult.optString("place_id");
                                    Toast.makeText(LodgingActivity.this, "" + place_id, Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "onResponse: " + place_id);

                                    JSONObject jsonObjectPlusCode = jsonObjectResult.optJSONObject("plus_code");
                                    String compound_code = jsonObjectPlusCode.optString("compound_code");
                                    Log.d(TAG, "onResponseAdressCode: " + compound_code);

                                    String rating = jsonObjectResult.optString("rating");
                                    String reference = jsonObjectResult.optString("reference");
                                    String scope = jsonObjectResult.optString("scope");
                                    Log.d(TAG, "onResponseRatingReferenceScope: " + rating + ", " + reference + ", " + scope);

                                    JSONArray jsonArrayTypes = jsonObjectResult.optJSONArray("types");
                                    for (int j = 0; j < jsonArrayTypes.length(); j++) {
                                        Log.d(TAG, "onResponseTypesJsonArray: " + jsonArrayTypes.get(i));
                                    }
                                    String vicinity = jsonObjectResult.optString("vicinity");
                                    Log.d(TAG, "onResponsevicinity: " + vicinity);



                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LodgingActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-7.5178785,110.5624369&rankby=distance&types=lodging&key=AIzaSyDK9afBrpN0wHnA5T_O_opQsbhui-PYF_c
    private void getLodging() {
        ApiService apiService = Client.getInstanceRetrofitGOOGLE();
//        Toast.makeText(this, "" + apiService, Toast.LENGTH_SHORT).show();
        apiService.getLodging("-7.5178785,110.5624369", "distance", "lodging", "AIzaSyDK9afBrpN0wHnA5T_O_opQsbhui-PYF_c")
                .enqueue(new Callback<RootLodgingModel>() {
                    @Override
                    public void onResponse(Call<RootLodgingModel> call, Response<RootLodgingModel> response) {
                        resultsItems = response.body().getResults();
                        Toast.makeText(LodgingActivity.this, "" + resultsItems, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<RootLodgingModel> call, Throwable t) {
                        Toast.makeText(LodgingActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error", "onFailure: " + t.getMessage());
                    }
                });
    }
}
