package com.example.android.vwpassenger.queue;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Pulitz on 2/21/2017.
 */

public class QueuePagerAdapter extends FragmentPagerAdapter {

    public QueuePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            default:
                break;

        }

        return QueueListFragment.newInstance("Test", ""+position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
        }
        return null;
    }


}
