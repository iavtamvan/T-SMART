package com.iav.id.ituteam.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.adapter.NewsAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.newsModel.ArticlesItem;
import com.iav.id.ituteam.model.newsModel.NewsModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    private RecyclerView rvEventHorizontal;
    private RecyclerView rvEventVertical;
    private ArrayList<ArticlesItem> articlesItems;
    private NewsAdapter newsAdapter;
    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_riwayat, container, false);
        initView(view);
        articlesItems = new ArrayList<>();

        getNews();

        return view;
    }

    private void getNews() {
        ApiService apiService = Client.getInstanceRetrofitNews();
        apiService.getNews().enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful()){
                    articlesItems = response.body().getArticles();
                    newsAdapter = new NewsAdapter(getActivity(), articlesItems);
                    rvEventVertical.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvEventVertical.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initView(View view) {
        rvEventHorizontal = view.findViewById(R.id.rv_event_horizontal);
        rvEventVertical = view.findViewById(R.id.rv_event_vertical);
    }
}
