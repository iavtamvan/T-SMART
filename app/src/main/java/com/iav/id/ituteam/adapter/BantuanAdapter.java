package com.iav.id.ituteam.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.bantuan.BantuaPertanyaannActivity;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.BantuanListModel;

import java.util.ArrayList;

public class BantuanAdapter extends RecyclerView.Adapter<BantuanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BantuanListModel> bantuanListModels;
    private LinearLayout divContainerKlik;


    public BantuanAdapter(Context context, ArrayList<BantuanListModel> bantuanListModels) {
        this.context = context;
        this.bantuanListModels = bantuanListModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_bantuan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvListBantuan.setText(bantuanListModels.get(position).getJenisBantuan());
        Glide.with(context).load(bantuanListModels.get(position).getGambarBantuan()).into(holder.ivListBantuan);
        holder.divContainerKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BantuaPertanyaannActivity.class);
                intent.putExtra(Config.BUNDLE_JENIS_BANTUAN, bantuanListModels.get(position).getJenisBantuan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bantuanListModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout divContainerKlik;
        private ImageView ivListBantuan;
        private TextView tvListBantuan;


        public ViewHolder(View itemView) {
            super(itemView);
            divContainerKlik = itemView.findViewById(R.id.div_container_klik);
            divContainerKlik = itemView.findViewById(R.id.div_container_klik);
            ivListBantuan = itemView.findViewById(R.id.iv_list_bantuan);
            tvListBantuan = itemView.findViewById(R.id.tv_list_bantuan);
        }
    }
}
