package com.example.android.vwpassenger.queue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.vwpassenger.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link QueueListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueueListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QueueListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QueueListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QueueListFragment newInstance(String param1, String param2) {
        QueueListFragment fragment = new QueueListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.queue_listview);
        ArrayList<Trip> data = new ArrayList<>();

        data.add(new Trip(0,R.drawable.ic_menu_camera,"somewhere",13,false,"",0,0));

        QueueListViewAdapter adapter = new QueueListViewAdapter(getContext(),data);

        listView.setAdapter(adapter);

        return view;
    }
}
