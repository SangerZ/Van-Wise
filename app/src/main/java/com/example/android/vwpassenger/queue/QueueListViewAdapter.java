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

/**
 * Created by Pulitz on 3/4/2017.
 */

public class QueueListViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private QueueDataSource dataSource;


    public QueueListViewAdapter(Context context, QueueDataSource dataSource) {
        mContext = context;
        this.dataSource = dataSource;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.get(i);
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
        //TODO: Find Icons for each destination
//        int resID = mContext.getResources().getIdentifier(t.getDestinationName(), "drawable", mContext.getPackageName());
        icon.setImageResource(R.drawable.ic_menu_send);

        TextView tvDestination = (TextView) view.findViewById(R.id.destination_name);
        tvDestination.setText(t.getDestinationName());

        TextView tvOrigin = (TextView) view.findViewById(R.id.origin_name);
        tvOrigin.setText(t.getOriginName());

        TextView numberInQueue = (TextView) view.findViewById(R.id.number_in_queue);
        numberInQueue.setText(Integer.toString(t.getNumberInQueue()));

        ImageButton favButton = (ImageButton) view.findViewById(R.id.bt_favorite);

        if (dataSource.isFavorite(i)) {
            favButton.setImageResource(R.drawable.ic_favorite);
            final int favIndex = i;
            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataSource.unfavorite(favIndex);
                    notifyDataSetChanged();
                }
            });

        } else {
            favButton.setImageResource(R.drawable.ic_not_favorite);
            final int queueIndex = i;
            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataSource.favorite(queueIndex);
                    notifyDataSetChanged();
                }
            });
        }

        return view;
    }
}
