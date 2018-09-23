package com.iav.id.ituteam.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.adapter.NewsHorizontalAdapter;
import com.iav.id.ituteam.adapter.NewsVerticalAdapter;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.newsModel.ArticlesItem;
import com.iav.id.ituteam.model.newsModel.NewsModel;
import com.iav.id.ituteam.rest.ApiService;
import com.iav.id.ituteam.rest.Client;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.paginate.NoPaginate;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    private RecyclerView rvEventHorizontal;
    private RecyclerView rvEventVertical;
    private ArrayList<ArticlesItem> articlesItems;
    private NewsVerticalAdapter newsVerticalAdapter;

    private NoPaginate noPaginate;

    private NewsHorizontalAdapter newsHorizontalAdapter;
//    private PulsatorLayout pulsator;
    private LottieAnimationView lottieAnimationView;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        articlesItems = new ArrayList<>();
//        pulsator.start();
//        pulsator.setColor(R.color.yellow);
//        pulsator.setDuration(1500);
        lottieAnimationView.playAnimation();

        getNewsHorizontal();
        getNewsVertical();

//        noPaginate = NoPaginate.with(rvEventHorizontal)
//                .setOnLoadMoreListener(new OnLoadMoreListener() {
//                    @Override
//                    public void onLoadMore() {
//                        getNewsHorizontal();
//                    }
//                })
//                .setLoadingTriggerThreshold(5)
//                .build();

        return view;
    }


    private void getNewsHorizontal() {
        ApiService apiService = Client.getInstanceRetrofitNews();
        apiService.getNewsHorizontal().enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful()) {
                    articlesItems = response.body().getArticles();
                    newsHorizontalAdapter = new NewsHorizontalAdapter(getActivity(), articlesItems);
                    rvEventHorizontal.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    rvEventHorizontal.setAdapter(newsHorizontalAdapter);
                    newsHorizontalAdapter.notifyDataSetChanged();
//                    pulsator.stop();
//                    pulsator.setVisibility(View.GONE);
                    lottieAnimationView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getNewsVertical() {
        ApiService apiService = Client.getInstanceRetrofitNews();
        apiService.getNewsVertical().enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful()) {
                    articlesItems = response.body().getArticles();
                    newsVerticalAdapter = new NewsVerticalAdapter(getActivity(), articlesItems);
                    rvEventVertical.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvEventVertical.setAdapter(newsVerticalAdapter);
                    newsVerticalAdapter.notifyDataSetChanged();
//                    pulsator.stop();
//                    pulsator.setVisibility(View.GONE);
                    lottieAnimationView.setVisibility(View.GONE);
                    noPaginate = NoPaginate.with(rvEventVertical)
                            .setOnLoadMoreListener(new OnLoadMoreListener() {
                                @Override
                                public void onLoadMore() {
                                    getNewsVertical();
                                }
                            })
                            .setLoadingTriggerThreshold(5)
                            .build();
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
        lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
    }
}
