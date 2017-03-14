package com.example.android.vwpassenger.queue;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

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
//    private QueuePagerAdapter qpa;
private QueueListViewAdapter adapter;
    private QueueDataSource dataSource;
    private SharedPreferences favoritePref;
    private SessionDetails sd;

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
        sd = SessionManager.getInstance().getSessionDetails();
        favoritePref = getActivity().getSharedPreferences(FAVORITE_PREF_NAME, Context.MODE_PRIVATE);

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

        dataSource = new QueueDataSource(favoritePref);

        adapter = new QueueListViewAdapter(getContext(), dataSource);

        listView.setAdapter(adapter);

        Call<QueueListResponse> call = api.GetAllQueues(sd.getUserID());
        call.enqueue(new Callback<QueueListResponse>() {
            @Override
            public void onResponse(Call<QueueListResponse> call, Response<QueueListResponse> response) {
                QueueListResponse res = response.body();
                ArrayList<Queue> queues = (ArrayList<Queue>) res.getQueueList();
                dataSource.update(queues);
                dataSource.setUserTripID(res.getUserTripID());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<QueueListResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Doesn't work!
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                int userTripID = dataSource.getUserTripID();
//                final int tripID = (int) l;
//                if(userTripID == 0 || userTripID != tripID) {
//                    Call<EnqueueResponse> call = api.Enqueue(sd.getUserID(), tripID);
//                    call.enqueue(new Callback<EnqueueResponse>() {
//                        @Override
//                        public void onResponse(Call<EnqueueResponse> call, Response<EnqueueResponse> response) {
//                            EnqueueResponse res = response.body();
//                            if(res.isSuccess()) {
//                                Toast.makeText(getContext(),res.getMessage(),Toast.LENGTH_SHORT).show();
//                                dataSource.update((ArrayList<Queue>) res.getQueueUpdates());
//                                dataSource.setUserTripID(tripID);
//                                adapter.notifyDataSetChanged();
//                            } else {
//                                Toast.makeText(getContext(),res.getMessage(),Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<EnqueueResponse> call, Throwable t) {
//                            Toast.makeText(getContext(),"Failure to enqueue: " + t.getMessage(),Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                } else {
//                    Call<EnqueueResponse> call = api.Dequeue(sd.getUserID());
//                    call.enqueue(new Callback<EnqueueResponse>() {
//                        @Override
//                        public void onResponse(Call<EnqueueResponse> call, Response<EnqueueResponse> response) {
//                            EnqueueResponse res = response.body();
//                            if(res.isSuccess()) {
//                                Toast.makeText(getContext(), res.getMessage(),Toast.LENGTH_SHORT).show();
//                                dataSource.update((ArrayList<Queue>) res.getQueueUpdates());
//                                dataSource.setUserTripID(0);
//                                adapter.notifyDataSetChanged();
//                            } else {
//                                Toast.makeText(getContext(), res.getMessage(),Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<EnqueueResponse> call, Throwable t) {
//                            Toast.makeText(getContext(),"Failure to dequeue: " + t.getMessage(),Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                }
//            }
//        });

        return rootView;
    }




}
