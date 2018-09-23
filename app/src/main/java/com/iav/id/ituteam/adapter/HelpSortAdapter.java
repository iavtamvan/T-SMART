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
import com.iav.id.ituteam.model.HelpModel;

import java.util.ArrayList;

public class HelpSortAdapter extends RecyclerView.Adapter<HelpSortAdapter.ViewHolder> {

    private ArrayList<HelpModel> helpModels;
    private Context context;
    public HelpSortAdapter(ArrayList<HelpModel> HelpModels, Context context) {
        this.helpModels = HelpModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_help_sort, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(helpModels.get(position).getGambarHelp()).into(holder.ivListHelpSort);
        holder.tvListHelpSortNama.setText(helpModels.get(position).getNamaHelp());
        holder.tvListHelpSortVendor.setText(helpModels.get(position).getNamaVendorHelp());
        holder.cvKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HelpSortActivity.class);
//                intent.putExtra(Config.BUNDLE_JENIS_HELP, helpModels.get(position).getNamaHelpList());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return helpModels.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cvKlik;
        private ImageView ivListHelpSort;
        private TextView tvListHelpSortNama;
        private TextView tvListHelpSortVendor;

        public ViewHolder(View itemView) {
            super(itemView);

            cvKlik = itemView.findViewById(R.id.cv_klik);
            ivListHelpSort = itemView.findViewById(R.id.iv_list_help_sort);
            tvListHelpSortNama = itemView.findViewById(R.id.tv_list_help_sort_nama);
            tvListHelpSortVendor = itemView.findViewById(R.id.tv_list_help_sort_vendor);
        }
    }
}
