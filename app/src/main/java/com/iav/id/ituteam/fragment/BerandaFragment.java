package com.iav.id.ituteam.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.activity.HealthActivity;
import com.iav.id.ituteam.helper.Config;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {


    private LinearLayout divHealth;
    private LinearLayout divGarbage;
    private LinearLayout divSecurity;
    private LinearLayout divEconomy;
    private LinearLayout divLodging;
    private LinearLayout divTravel;

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        initView(view);

        divHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), HealthActivity.class);
                intent.putExtra(Config.BUNDLE_JENIS_KATEGORI, "Kesehatan");
                intent.putExtra(Config.BUNDLE_PINDAH_TOKEN, "u43uy78esufh4-344ru9jskjfd-i34rwrb23o4ndfXsddD");
                getActivity().startActivity(intent);
            }
        });
        divGarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthActivity.class);
                intent.putExtra(Config.BUNDLE_JENIS_KATEGORI, "Sampah");
                intent.putExtra(Config.BUNDLE_PINDAH_TOKEN, "jdhfjsdh6637yGGJSHJ-sfuHbsdfb74NJBB-UIE+sdjfu74893");
                getActivity().startActivity(intent);
            }
        });



        return view;
    }

    private void initView(View view) {
        divHealth = view.findViewById(R.id.div_health);
        divGarbage = view.findViewById(R.id.div_garbage);
        divSecurity = view.findViewById(R.id.div_security);
        divEconomy = view.findViewById(R.id.div_economy);
        divLodging = view.findViewById(R.id.div_lodging);
        divTravel = view.findViewById(R.id.div_travel);
    }
}
