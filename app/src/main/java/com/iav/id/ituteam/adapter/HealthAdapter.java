package com.iav.id.ituteam.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iav.id.ituteam.R;
import com.iav.id.ituteam.model.EventModel;

import java.util.ArrayList;

public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.ViewHolder> {
    private Context context;
    private ArrayList<EventModel> EventModels;


    public HealthAdapter(Context context, ArrayList<EventModel> EventModels) {
        this.context = context;
        this.EventModels = EventModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Glide.with(context).load(EventModels.get(position).getGambarEvent()).error(R.drawable.logo)
                .into(holder.ivListEventBackdroph);
        holder.tvListeventJudul.setText(EventModels.get(position).getJudulEvent());
        holder.tvListeventDate.setText(EventModels.get(position).getTglWaktuEvent());
        holder.tvListeventDeskripsi.setVisibility(View.GONE);
        holder.ivListEventShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Link Playstore : > " + holder.tvListeventJudul.getText().toString().trim();
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.SUBJEK));
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.SHARE_VIA)));
            }
        });
        holder.cvKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra(Config.BUNDLE_JUDUL, judul);
            }
        });
    }

    @Override
    public int getItemCount() {
        return EventModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivListEventShare;
        private ImageView ivListEventBackdroph;
        private TextView tvListeventJudul;
        private TextView tvListeventDate;
        private TextView tvListeventDeskripsi;
        private CardView cvKlik;

        public ViewHolder(View itemView) {
            super(itemView);
            ivListEventShare = itemView.findViewById(R.id.iv_list_event_share);
            ivListEventBackdroph = itemView.findViewById(R.id.iv_list_event_backdroph);
            tvListeventJudul = itemView.findViewById(R.id.tv_listevent__judul);
            tvListeventDate = itemView.findViewById(R.id.tv_listevent_date);
            tvListeventDeskripsi = itemView.findViewById(R.id.tv_listevent_deskripsi);
            cvKlik = itemView.findViewById(R.id.cv_klick);
        }
    }
}
