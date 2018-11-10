package com.oohaweb.podcastapplication.view;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oohaweb.podcastapplication.R;
import com.oohaweb.podcastapplication.model.Podcast;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PodcastListAdapter extends RecyclerView.Adapter<PodcastListAdapter.ViewHolder> {
    private static final String TAG = "PodcastListAdapter";

    private LayoutInflater layoutInflater;
    private List<Podcast> podcasts;
    private static ClickListener clickListener;

    public PodcastListAdapter(Context context, List<Podcast> podcasts) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.podcasts = podcasts;

        clickListener = new ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Podcast podcast = podcasts.get(position);
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                File file = new File(podcast.getUrl());
                intent.setDataAndType(Uri.fromFile(file), "audio/*");
                context.startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v) {
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return podcasts.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Podcast podcast = podcasts.get(position);
        holder.nameTextView.setText(podcast.getName());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_podcast, null);
        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView nameTextView;

        ViewHolder(@NonNull View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name);

            LinearLayout podcastItem = view.findViewById(R.id.podcastItem);
            if (null != podcastItem) {
                podcastItem.setOnClickListener(this);
                podcastItem.setOnLongClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
}
