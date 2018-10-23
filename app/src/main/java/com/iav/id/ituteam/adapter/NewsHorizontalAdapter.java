package com.iav.id.ituteam.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.WebViewActivity;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.newsModel.ArticlesItem;

import java.util.ArrayList;

public class NewsHorizontalAdapter extends RecyclerView.Adapter<NewsHorizontalAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ArticlesItem> articlesItems;

    public NewsHorizontalAdapter(Context context, ArrayList<ArticlesItem> articlesItems) {
        this.context = context;
        this.articlesItems = articlesItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_news_horizontal, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ImagePopup imagePopup = new ImagePopup(context);
        imagePopup.setWindowHeight(800); // Optional
        imagePopup.setWindowWidth(800); // Optional
        imagePopup.setBackgroundColor(R.color.bgImagePopUp);  // Optional
        imagePopup.setFullScreen(true); // Optional
        imagePopup.setHideCloseIcon(true);  // Optional
        imagePopup.setImageOnClickClose(true);  // Optional
        imagePopup.initiatePopupWithGlide(articlesItems.get(position).getUrlToImage());
        Glide.with(context).load(articlesItems.get(position).getUrlToImage()).into(holder.ivNewsVertical);
        holder.ivNewsVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePopup.viewPopup();
            }
        });

        holder.tvNewsVerticalJudul.setSelected(true);
        holder.tvNewsVerticalJudul.setText(articlesItems.get(position).getTitle());
        String date = articlesItems.get(position).getPublishedAt();
//        String split = date.substring(0, 10);
//        holder.tvNewsVerticalTanggalTerbit.setText(split);
        holder.tvNewsHorizontalDeskripsi.setText(articlesItems.get(position).getDescription());


        holder.ivNewsHorizontalShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Shared link from D-NEWS by DORA Apps " + articlesItems.get(position).getTitle() + " " + articlesItems.get(position).getUrl();
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.SUBJEK));
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.SHARE_VIA)));
            }
        });
        holder.cvKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra(Config.BUNDLE_URL_NEWS, articlesItems.get(position).getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cvKlik;
        private ImageView ivNewsVertical;
        private ImageView ivNewsHorizontalShare;
        private LinearLayout divContainerNewsVertical;
        private TextView tvNewsVerticalJudul;
        private TextView tvNewsHorizontalDeskripsi;
        public ViewHolder(View itemView) {
            super(itemView);

            cvKlik = itemView.findViewById(R.id.cv_klik);
            ivNewsVertical = itemView.findViewById(R.id.iv_news_vertical);
            ivNewsHorizontalShare = itemView.findViewById(R.id.iv_news_horizontal_share);
            divContainerNewsVertical = itemView.findViewById(R.id.div_container_news_vertical);
            tvNewsVerticalJudul = itemView.findViewById(R.id.tv_news_vertical_judul);
            tvNewsHorizontalDeskripsi = itemView.findViewById(R.id.tv_news_horizontal_deskripsi);

        }
    }
}
