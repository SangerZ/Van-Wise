package com.example.android.vwpassenger;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.vwpassenger.queue.QueuePagerAdapter;


public class MainFragment extends Fragment {

    private Button btScanQR;

    private QueuePagerAdapter tpa;
    private ViewPager viewPager;
    private TabLayout tabs;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        btScanQR = (Button) rootView.findViewById(R.id.bt_scan_qr);
        btScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tpa = new QueuePagerAdapter(getChildFragmentManager());

        viewPager = (ViewPager) rootView.findViewById(R.id.container);
        viewPager.setAdapter(tpa);

        tabs = (TabLayout) rootView.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return rootView;
    }
}
