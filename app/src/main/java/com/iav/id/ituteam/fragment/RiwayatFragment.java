package com.iav.id.ituteam.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.fragment.riwayatFragment.DonorASIFragment;
import com.iav.id.ituteam.fragment.riwayatFragment.DonorDarahFragment;
import com.iav.id.ituteam.fragment.riwayatFragment.PenukaranSampahkFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatFragment extends Fragment {


    private SmartTabLayout viewpagertab;
    private ViewPager viewpager;

    public RiwayatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_riwayat, container, false);
        initView(view);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getActivity().getSupportFragmentManager(), FragmentPagerItems.with(getActivity())
                .add("Donor Darah", DonorDarahFragment.class)
                .add("Donor ASI", DonorASIFragment.class)
                .add("Penukaran Samaph", PenukaranSampahkFragment.class)
                .create());

        viewpager.setAdapter(adapter);
        viewpagertab.setViewPager(viewpager);

        return view;
    }


    private void initView(View view) {
        viewpagertab = view.findViewById(R.id.viewpagertab);
        viewpager = view.findViewById(R.id.viewpager);
    }
}
