package com.santos.android.evenlychallenge.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by Abel Cruz dos Santos on 16.08.2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Location {
    private String mAddress;
    private String mCrossStreet;
    private double mLat;
    private double mLon;
    private int mDistance;
    private String[] mFormattedAddress;

    public String getAddress() {
        return mAddress;
    }
    @JsonSetter("address")
    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCrossStreet() {
        return mCrossStreet;
    }
    @JsonSetter("crossStreet")
    public void setCrossStreet(String crossStreet) {
        mCrossStreet = crossStreet;
    }

    public double getLat() {
        return mLat;
    }
    @JsonSetter("lat")
    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLon() {
        return mLon;
    }
    @JsonSetter("lng")
    public void setLon(double lon) {
        mLon = lon;
    }

    public int getDistance() {
        return mDistance;
    }
    @JsonSetter("distance")
    public void setDistance(int distance) {
        mDistance = distance;
    }

    public String[] getFormattedAddress() {
        return mFormattedAddress;
    }
    @JsonSetter("formattedAddress")
    public void setFormattedAddress(String[] formattedAddress) {
        mFormattedAddress = formattedAddress;
    }
}
