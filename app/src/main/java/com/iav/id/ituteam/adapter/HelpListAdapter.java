package com.iav.id.ituteam.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.help.HelpSortActivity;
import com.iav.id.ituteam.helper.Config;
import com.iav.id.ituteam.model.HelpListModel;

import java.util.ArrayList;

public class HelpListAdapter extends RecyclerView.Adapter<HelpListAdapter.ViewHolder> {

    private ArrayList<HelpListModel> helpListModels;
    private Context context;

    public HelpListAdapter(ArrayList<HelpListModel> helpListModels, Context context) {
        this.helpListModels = helpListModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_help_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(helpListModels.get(position).getGamabrHelpList()).into(holder.ivHelpList);
        holder.tvHelpListJenisHelp.setText(helpListModels.get(position).getNamaHelpList());
        holder.cvKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HelpSortActivity.class);
                intent.putExtra(Config.BUNDLE_JENIS_HELP, helpListModels.get(position).getNamaHelpList());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return helpListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivHelpList;
        private TextView tvHelpListJenisHelp;
        private CardView cvKlik;
        public ViewHolder(View itemView) {
            super(itemView);
            ivHelpList = itemView.findViewById(R.id.iv_help_list);
            tvHelpListJenisHelp = itemView.findViewById(R.id.tv_help_list_jenis_help);
            cvKlik = itemView.findViewById(R.id.cv_klik);
        }
    }
}
