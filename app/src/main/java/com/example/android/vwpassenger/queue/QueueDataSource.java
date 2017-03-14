package com.example.android.vwpassenger.queue;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Pulitz on 3/13/2017.
 */

public class QueueDataSource {
    private SharedPreferences favoritePref;
    private SharedPreferences.Editor favoriteEditor;
    private ArrayList<Queue> favorites;
    private ArrayList<Queue> queues;
    private int userTripID;


    public QueueDataSource(SharedPreferences favoritePref) {
        this.favoritePref = favoritePref;
        this.favoriteEditor = favoritePref.edit();
        favorites = new ArrayList<>();
        queues = new ArrayList<>();
        userTripID = 0;
    }

    public QueueDataSource(SharedPreferences favoritePref, ArrayList<Queue> queues, int userTripID) {
        this.favoritePref = favoritePref;
        this.favorites = new ArrayList<>();
        this.queues = queues;
        separateFavorites();
        this.userTripID = userTripID;
    }

    private void separateFavorites() {
        for (Queue q : queues) {
            if (favoritePref.getBoolean(Integer.toString(q.getTripID()), false)) {
                if (queues.remove(q))
                    favorites.add(q);
            }
        }
    }

    public int size() {
        return favorites.size() + queues.size();
    }

    public Queue get(int i) {
        if (i < favorites.size()) {
            return favorites.get(i);
        } else {
            i -= favorites.size();
            return queues.get(i);
        }
    }

    public boolean isFavorite(int i) {
        if (i < favorites.size()) return true;
        else return false;
    }

    public void favorite(int i) {
        Queue fav = get(i);
        if (queues.remove(fav)) {
            favorites.add(fav);
            favoriteEditor.putBoolean(Integer.toString(fav.getTripID()), true);
            favoriteEditor.commit();
        }
        sortQueues();
    }

    public void unfavorite(int i) {
        Queue unFav = get(i);
        if (favorites.remove(unFav)) {
            queues.add(unFav);
            favoriteEditor.remove(Integer.toString(unFav.getTripID()));
            favoriteEditor.commit();
        }
        sortQueues();
    }

    private void sortQueues() {
        Collections.sort(favorites);
        Collections.sort(queues);
    }

    public int getUserTripID() {
        return userTripID;
    }

    public void setUserTripID(int userTripID) {
        this.userTripID = userTripID;
    }

    public void update(ArrayList<Queue> updates) {
        this.favorites.clear();
        this.queues = updates;
        separateFavorites();
    }
}
