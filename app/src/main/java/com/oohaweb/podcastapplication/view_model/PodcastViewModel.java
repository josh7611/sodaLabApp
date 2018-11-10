package com.oohaweb.podcastapplication.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.oohaweb.podcastapplication.model.Podcast;

import java.util.ArrayList;
import java.util.List;


public class PodcastViewModel extends ViewModel {
    private static final String TAG = "PodcastViewModel";
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

    public LiveData<List<Podcast>> getPodcasts(Context context) {
        final MutableLiveData<List<Podcast>> podcasts = new MutableLiveData<>();
        podcasts.setValue(getFromMediaStore(context));
        return podcasts;
    }

    private List<Podcast> getFromMediaStore(Context context) {
        List<Podcast> podcasts = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String songName = cursor.getString(cursor.
                            getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String path = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Audio.Media.DATA));

                    podcasts.add(new Podcast(songName, path));
                } while (cursor.moveToNext());
            }
        }
        return podcasts;
    }

}
