package com.example.android.vwpassenger.queue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.vwpassenger.R;

import java.util.ArrayList;

/**
 * Created by Pulitz on 3/4/2017.
 */

public class QueueListViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Trip> mDataSource;

    public QueueListViewAdapter(Context context, ArrayList<Trip> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null) {
            view = mInflater.inflate(R.layout.queue_list_item, parent, false);
        }

        final Trip t = (Trip) getItem(i);

        ImageView icon = (ImageView) view.findViewById(R.id.destination_icon);
        icon.setImageResource(t.getIcon());

        TextView name = (TextView) view.findViewById(R.id.destination_name);
        name.setText(t.getDestinationName());

        TextView numberInQueue = (TextView) view.findViewById(R.id.number_in_queue);
        numberInQueue.setText("99");

        ImageButton enterQueue = (ImageButton) view.findViewById(R.id.bt_enter_queue);

        if (t.isSelected()) {
            enterQueue.setImageResource(R.drawable.ic_menu_send);
            enterQueue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    foundBeacon(t.getBeaconUUID(), t.getBeaconMajor(), t.getBeaconMinor());
                    // teset

                }
            });
        } else {
            enterQueue.setImageResource(R.drawable.ic_enter_queue);
            enterQueue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    foundBeacon(t.getBeaconUUID(), t.getBeaconMajor(), t.getBeaconMinor());

                }
            });
        }


        return view;
    }


}
