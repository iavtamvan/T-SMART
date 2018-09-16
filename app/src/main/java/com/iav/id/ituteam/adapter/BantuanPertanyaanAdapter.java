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

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.BantuanDetailActivity;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.BantuanPertanyaanModel;

import java.util.ArrayList;

public class BantuanPertanyaanAdapter extends RecyclerView.Adapter<BantuanPertanyaanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BantuanPertanyaanModel> bantuanPertanyaanModels;


    public BantuanPertanyaanAdapter(Context context, ArrayList<BantuanPertanyaanModel> BantuanPertanyaanModels) {
        this.context = context;
        this.bantuanPertanyaanModels = BantuanPertanyaanModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_bantuan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ivListBantuan.setVisibility(View.GONE);
        holder.tvListBantuan.setText(bantuanPertanyaanModels.get(position).getPertanyaanBantuan());
        holder.divContainerKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BantuanDetailActivity.class);
                intent.putExtra(Config.BUNDLE_JENIS_BANTUAN, bantuanPertanyaanModels.get(position).getJenisBantuan());
                intent.putExtra(Config.BUNDLE_PERTANYAAN_BANTUAN, bantuanPertanyaanModels.get(position).getPertanyaanBantuan());
                intent.putExtra(Config.BUNDLE_JAWABAN_BANTUAN, bantuanPertanyaanModels.get(position).getJawabanBantuan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bantuanPertanyaanModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout divContainerKlik;

        private ImageView ivListBantuan;
        private TextView tvListBantuan;
        public ViewHolder(View itemView) {
            super(itemView);
            divContainerKlik = itemView.findViewById(R.id.div_container_klik);
            ivListBantuan = itemView.findViewById(R.id.iv_list_bantuan);
            tvListBantuan = itemView.findViewById(R.id.tv_list_bantuan);
        }
    }
}
