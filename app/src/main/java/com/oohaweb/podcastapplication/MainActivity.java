package com.oohaweb.podcastapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.security.ProviderInstaller;
import com.oohaweb.podcastapplication.view.PodcastListAdapter;
import com.oohaweb.podcastapplication.view_model.PodcastViewModel;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PodcastListAdapter podcastListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list_view_user);

//       Patch Android's security Provider to solve SSLHandshakeException when android version <= 4.4
        updateAndroidSecurityProvider(this);

        PodcastViewModel.getInstance().getPodcasts(this).observe(this, users -> {
            podcastListAdapter = new PodcastListAdapter(this, users);
            recyclerView.setAdapter(podcastListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        });
    }

    private void updateAndroidSecurityProvider(Activity callingActivity) {
        try {
            ProviderInstaller.installIfNeeded(this);
        } catch (GooglePlayServicesRepairableException e) {
            // Thrown when Google Play Services is not installed, up-to-date, or enabled
            // Show dialog to allow users to install, update, or otherwise enable Google Play services.
            GooglePlayServicesUtil.getErrorDialog(e.getConnectionStatusCode(), callingActivity, 0);
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.e("SecurityException", "Google Play Services not available.");
        }
    }
}
