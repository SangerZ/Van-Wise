package com.example.android.vwpassenger.queue;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.vwpassenger.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QueuePageFragment extends Fragment {

    private QueuePagerAdapter qpa;
    private ViewPager viewPager;
    private TabLayout tabs;

    public QueuePageFragment() {
        // Required empty public constructor
    }

    public static QueuePageFragment newInstance() {
        QueuePageFragment fragment = new QueuePageFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_queue, container, false);
        qpa = new QueuePagerAdapter(getChildFragmentManager());

        viewPager = (ViewPager) rootView.findViewById(R.id.container);
        viewPager.setAdapter(qpa);

        tabs = (TabLayout) rootView.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        return rootView;
    }

}
