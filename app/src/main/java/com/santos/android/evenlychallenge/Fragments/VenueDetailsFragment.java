package com.santos.android.evenlychallenge.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.santos.android.evenlychallenge.R;

/**
 * Created by Abel Cruz dos Santos on 20.08.2017.
 */

public class VenueDetailsFragment extends Fragment implements OnMapReadyCallback {
    public static final String TAG = "VenueDetailsFragment";
    static final LatLng EVENLY_HQ = new LatLng(52.500342, 13.425170);
    private GoogleMap mGoogleMap;

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
        View view = inflater.inflate(R.layout.fragment_venue_details, null, false);

        //map
        SupportMapFragment supportMapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        //Suppport map fragment because it is what is declared in the xml
        supportMapFragment.getMapAsync(this);

        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.addMarker(new MarkerOptions().position(EVENLY_HQ).title("Evenly"));
        //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EVENLY_HQ, 15));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(EVENLY_HQ,14));

    }
}
