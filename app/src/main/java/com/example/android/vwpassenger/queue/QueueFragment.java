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
import com.example.android.vwpassenger.session.SessionDetails;
import com.example.android.vwpassenger.session.SessionManager;
import com.example.android.vwpassenger.web_service.QueueListResponse;
import com.example.android.vwpassenger.web_service.VanWiseAPIClient;
import com.example.android.vwpassenger.web_service.VanWiseInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class QueueFragment extends Fragment {

    public static final String FAVORITE_PREF_NAME = "favorite_queues";
    private final VanWiseInterface api = VanWiseAPIClient.getClient().create(VanWiseInterface.class);
//    private ViewPager viewPager;
//    private TabLayout tabs;
private QueuePagerAdapter qpa;
    private QueueDataSource dataSource;
    private SharedPreferences favoritePref;


    public QueueFragment() {
        // Required empty public constructor
    }

    public static QueueFragment newInstance() {
        QueueFragment fragment = new QueueFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionDetails sd = SessionManager.getInstance().getSessionDetails();
        favoritePref = getActivity().getSharedPreferences(FAVORITE_PREF_NAME, Context.MODE_PRIVATE);
        Call<QueueListResponse> call = api.GetAllQueues(sd.getUserID());
        call.enqueue(new Callback<QueueListResponse>() {
            @Override
            public void onResponse(Call<QueueListResponse> call, Response<QueueListResponse> response) {
                QueueListResponse res = response.body();
                ArrayList<Queue> queues = (ArrayList<Queue>) res.getQueueList();
                dataSource = new QueueDataSource(favoritePref, queues, res.getUserTripID());
            }

            @Override
            public void onFailure(Call<QueueListResponse> call, Throwable t) {
                dataSource = new QueueDataSource(favoritePref);
            }
        });
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

        QueueListViewAdapter adapter = new QueueListViewAdapter(getContext(), dataSource);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        return rootView;
    }




}
