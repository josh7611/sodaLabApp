package com.oohaweb.podcastapplication.view;

import android.view.View;

public interface ClickListener {
    void onItemClick(int position, View v);

    void onItemLongClick(int position, View v);
}
