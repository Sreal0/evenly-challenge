package com.santos.android.evenlychallenge.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.santos.android.evenlychallenge.API.Venue;
import com.santos.android.evenlychallenge.Activity.ChallengeLauncherActivity;
import com.santos.android.evenlychallenge.R;

/**
 * Created by Abel Cruz dos Santos on 20.08.2017.
 */

public class VenueDetailsFragment extends Fragment implements OnMapReadyCallback {
    public static final String TAG = "VenueDetailsFragment";
    static final LatLng EVENLY_HQ = new LatLng(52.500342, 13.425170);
    private GoogleMap mGoogleMap;
    private Venue mVenue;
    private TextView mVenueName;
    private TextView mVenuaAddress;
    private TextView mVenueCategory;
    private TextView mVenueContact;

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

        mVenuaAddress = (TextView)view.findViewById(R.id.txtAddress);
        mVenueCategory = (TextView)view.findViewById(R.id.txt_category);
        mVenueContact = (TextView)view.findViewById(R.id.txtContact);
        mVenueName = (TextView)view.findViewById(R.id.txt_name);

        mVenue = ((ChallengeLauncherActivity)getActivity()).getVenue();
        setupTextViews();
        return view;

    }

    private void setupTextViews(){
        if(mVenue.getLocation().getFormattedAddress() != null){
            mVenuaAddress.setText(mVenue.getLocation().getAddress());
        }else{

            mVenuaAddress.setText("no address avaliable");
        }

        mVenueName.setText(mVenue.getName());
        mVenueContact.setText(mVenue.getContact().getFormattedPhone());
        if (mVenue.getCategories().length > 0){
            mVenueCategory.setText(mVenue.getCategories()[0].getNameShort());
        }else {
            mVenueCategory.setText("n/a");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        LatLng position = new LatLng(mVenue.getLocation().getLat(), mVenue.getLocation().getLon());
        mGoogleMap.addMarker(new MarkerOptions().position(position).title(mVenue.getName()));
        //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EVENLY_HQ, 15));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position,14));

    }
}
