package com.santos.android.evenlychallenge.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.santos.android.evenlychallenge.R;

/**
 * Created by Abel Cruz dos Santos on 20.08.2017.
 */

public class VenueDetailsFragment extends Fragment implements OnMapReadyCallback {
    public static final String TAG = "VenueDetailsFragment";

    public static VenueDetailsFragment newInstance() {
        VenueDetailsFragment fragment = new VenueDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_venue_details, container, false);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
