package com.iav.id.ituteam.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.iav.id.ituteam.R;
import com.iav.id.ituteam.fragment.riwayatFragment.DonorASIFragment;
import com.iav.id.ituteam.fragment.riwayatFragment.DonorDarahFragment;
import com.iav.id.ituteam.fragment.riwayatFragment.PenukaranSampahkFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class RiwayatActivity extends AppCompatActivity {

    private SmartTabLayout viewpagertab;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        initView();

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Donor Darah", DonorDarahFragment.class)
                .add("Donor ASI", DonorASIFragment.class)
                .add("Penukaran Samaph", PenukaranSampahkFragment.class)
                .create());

        viewpager.setAdapter(adapter);
        viewpagertab.setViewPager(viewpager);

    }

    private void initView() {
        viewpagertab = findViewById(R.id.viewpagertab);
        viewpager = findViewById(R.id.viewpager);
    }
}
