package com.santos.android.evenlychallenge.API;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by Abel Cruz dos Santos on 18.08.2017.
 */

public class MyResponse {

    private String[] mVenues;

    public MyResponse(){

    }

    public String[] getVenues() {
        return mVenues;
    }
    @JsonProperty("venues")
    public void setVenues(String[] venues) {
        mVenues = venues;
    }
}
