package com.example.android.vwpassenger.queue;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.vwpassenger.R;
import com.example.android.vwpassenger.session.SessionManager;
import com.example.android.vwpassenger.web_service.VanWiseAPIClient;
import com.example.android.vwpassenger.web_service.VanWiseInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pulitz on 3/4/2017.
 */

public class QueueListViewAdapter extends BaseAdapter {
    private final VanWiseInterface api = VanWiseAPIClient.getClient().create(VanWiseInterface.class);
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
        return dataSource.get(i).getTripID();
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

        view.findViewById(R.id.lv_body).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userTripID = dataSource.getUserTripID();
                final int tripID = t.getTripID();
                if (userTripID == 0 || userTripID != tripID) {
                    Call<EnqueueResponse> call = api.Enqueue(SessionManager.getInstance().getSessionDetails().getUserID(), tripID);
                    call.enqueue(new Callback<EnqueueResponse>() {
                        @Override
                        public void onResponse(Call<EnqueueResponse> call, Response<EnqueueResponse> response) {
                            Log.d("Testing Enqueue", response.toString());
                            EnqueueResponse res = response.body();
                            if (res.isSuccess()) {
                                Toast.makeText(mContext, res.getMessage(), Toast.LENGTH_SHORT).show();
                                dataSource.update((ArrayList<Queue>) res.getQueueUpdates());
                                dataSource.setUserTripID(tripID);
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(mContext, res.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<EnqueueResponse> call, Throwable t) {
                            Toast.makeText(mContext, "Failure to enqueue: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Call<EnqueueResponse> call = api.Dequeue(SessionManager.getInstance().getSessionDetails().getUserID());
                    call.enqueue(new Callback<EnqueueResponse>() {
                        @Override
                        public void onResponse(Call<EnqueueResponse> call, Response<EnqueueResponse> response) {
                            Log.d("Testing Dequeue", response.toString());
                            EnqueueResponse res = response.body();
                            if (res.isSuccess()) {
                                Toast.makeText(mContext, res.getMessage(), Toast.LENGTH_SHORT).show();
                                dataSource.update((ArrayList<Queue>) res.getQueueUpdates());
                                dataSource.setUserTripID(0);
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(mContext, res.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<EnqueueResponse> call, Throwable t) {
                            Toast.makeText(mContext, "Failure to dequeue: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        Log.d("Trip ID Comparison", t.getTripID() + " == " + dataSource.getUserTripID());

        //TODO: Find a better way to implement background colors and Enqueue/Dequeue button
        if (t.getTripID() == dataSource.getUserTripID()) {
            view.findViewById(R.id.lv_bg).setBackgroundColor(mContext.getResources().getColor(R.color.blue));
        } else {
            view.findViewById(R.id.lv_bg).setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }

        return view;
    }
}
