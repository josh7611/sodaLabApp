package com.oohaweb.podcastapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.oohaweb.podcastapplication.model.Podcast;

import java.util.List;


public class PodcastViewModel extends ViewModel {
    private static PodcastViewModel INSTANCE;

    private PodcastViewModel() {
    }


    public static PodcastViewModel getInstance() {
        if (null == INSTANCE) {
            synchronized (PodcastViewModel.class) {
                INSTANCE = new PodcastViewModel();
            }
        }
        return INSTANCE;
    }

    public LiveData<List<Podcast>> getPodcasts() {
        return null;
    }

}
