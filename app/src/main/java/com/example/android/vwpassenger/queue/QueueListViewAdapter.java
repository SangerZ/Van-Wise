package com.example.android.vwpassenger.queue;

import android.content.Context;
import android.content.SharedPreferences;
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
    private ArrayList<Queue> mFavorites;
    private ArrayList<Queue> mQueues;
    private SharedPreferences.Editor editor;


    public QueueListViewAdapter(Context context, ArrayList<Queue> favorite, ArrayList<Queue> queues) {
        mContext = context;
        mFavorites = favorite;
        mQueues = queues;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SharedPreferences sharedpref = mContext.getSharedPreferences("favorite_queues", Context.MODE_PRIVATE);
        editor = sharedpref.edit();
    }

    @Override
    public int getCount() {
        return mFavorites.size() + mQueues.size();
    }

    @Override
    public Object getItem(int i) {
        if (i < mFavorites.size()) {
            return mFavorites.get(i);
        } else {
            i -= mFavorites.size();
            return mQueues.get(i);
        }
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

        final Queue t = (Queue) getItem(i);

        ImageView icon = (ImageView) view.findViewById(R.id.destination_icon);
        icon.setImageResource(t.getIcon());

        TextView name = (TextView) view.findViewById(R.id.destination_name);
        name.setText(t.getDestinationName());

        TextView numberInQueue = (TextView) view.findViewById(R.id.number_in_queue);
        numberInQueue.setText("99");

//        ImageButton enterQueue = (ImageButton) view.findViewById(R.id.bt_enter_queue);
//
//        if (t.isSelected()) {
//            enterQueue.setImageResource(R.drawable.ic_menu_send);
//            enterQueue.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    foundBeacon(t.getBeaconUUID(), t.getBeaconMajor(), t.getBeaconMinor());
//                    // teset
//
//                }
//            });
//        } else {
//            enterQueue.setImageResource(R.drawable.ic_enter_queue);
//            enterQueue.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    foundBeacon(t.getBeaconUUID(), t.getBeaconMajor(), t.getBeaconMinor());
//
//                }
//            });
//        }

        ImageButton favButton = (ImageButton) view.findViewById(R.id.bt_favorite);

        if (i < mFavorites.size()) {
            favButton.setImageResource(R.drawable.ic_favorite);
            final int favIndex = i;
            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Queue fav = mFavorites.remove(favIndex);
                    editor.remove(Integer.toString(fav.getTripID()));
                    editor.commit();
                    mQueues.add(fav);
                    notifyDataSetChanged();
                }
            });

        } else {
            favButton.setImageResource(R.drawable.ic_not_favorite);
            final int queueIndex = i - mFavorites.size();
            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Queue fav = mQueues.remove(queueIndex);
                    editor.putBoolean(Integer.toString(fav.getTripID()), true);
                    editor.commit();
                    mFavorites.add(fav);
                    notifyDataSetChanged();
                }
            });
        }

        return view;
    }

    public ArrayList<Queue> getmQueues() {
        return mQueues;
    }

    public void setmQueues(ArrayList<Queue> items) {
        this.mQueues = mQueues;
    }
}
