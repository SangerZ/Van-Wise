package com.example.android.vwpassenger.queue;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.vwpassenger.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QueuePageFragment extends Fragment {

    private QueuePagerAdapter qpa;
//    private ViewPager viewPager;
//    private TabLayout tabs;

    private ArrayList<Queue> queues;
    private ArrayList<Queue> favorites;

    private SharedPreferences sharedPref;


    public QueuePageFragment() {
        // Required empty public constructor
    }

    public static QueuePageFragment newInstance() {
        QueuePageFragment fragment = new QueuePageFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = getActivity().getSharedPreferences("favorite_queues", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_queue, container, false);
//        qpa = new QueuePagerAdapter(getChildFragmentManager());
//
//        viewPager = (ViewPager) rootView.findViewById(R.id.container);
//        viewPager.setAdapter(qpa);
//
//        tabs = (TabLayout) rootView.findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);

        ListView listView = (ListView) rootView.findViewById(R.id.lv_queue);

        queues = new ArrayList<>();

        queues.add(new Queue(0, R.drawable.ic_menu_camera, "somewhere", 13, false, "", 0, 0));
        queues.add(new Queue(0, R.drawable.ic_menu_camera, "somewhere1", 13, false, "", 0, 0));
        queues.add(new Queue(0, R.drawable.ic_menu_camera, "somewhere2", 13, false, "", 0, 0));


        favorites = separateFavorites(queues);

        QueueListViewAdapter adapter = new QueueListViewAdapter(getContext(), favorites, queues);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return rootView;
    }

    private ArrayList<Queue> separateFavorites(ArrayList<Queue> queues) {
        ArrayList<Queue> favorites = new ArrayList<>();
        for (int i = 0; i < queues.size(); ++i) {
            if (sharedPref.getBoolean(Integer.toString(queues.get(i).getTripID()), false)) {

                favorites.add(queues.remove(i));
            }
        }
        return favorites;
    }


}
