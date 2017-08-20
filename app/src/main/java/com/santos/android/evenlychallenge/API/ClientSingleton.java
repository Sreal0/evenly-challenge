package com.santos.android.evenlychallenge.API;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santos.android.evenlychallenge.Activity.ChallengeLauncherActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Abel Cruz dos Santos on 16.08.2017.
 */

public class ClientSingleton extends Application {
    private static final String TAG = "ClientSingleton";
    private static final String REQUEST_HEADER = "https://api.foursquare.com/v2/venues/search?ll=";
    private static final String CLIENT_ID = "05XL1PRM0WJWFWKMZ2ZF44DGK5UHQB3JAPRP4SNWVATDOUL5";
    private static final String CLIENT_SECRET = "OR5RBTJKHM0LGCIPOFWJ4GSABL3KWT3Y212R5O0KQTER2XTR";
    private static final String VENUE_DETAILS_REQUEST = "https://api.foursquare.com/v2/venues/";
    private static final double LATITUDE =  52.500342;
    private static final double LONGITUDE =  13.425170;
    private static final int LIMIT = 10;
    private static final int RADIUS = 800;  //in meters -> max 10000

    private static ClientSingleton sClientSingleton;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context mContext;


    private ClientSingleton(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    private List<Venue> mVenueList = new ArrayList<>();

    @Override
    public void onCreate(){
        super.onCreate();
        sClientSingleton = this;
    }

    public static synchronized ClientSingleton getInstance(Context context){
        if(sClientSingleton == null){
            sClientSingleton = new ClientSingleton(context);
        }
        return sClientSingleton;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    /*public ImageLoader getImageLoader(){
        getRequestQueue();
        if(mImageLoader == null){
            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
        }
        return this.mImageLoader;
    }*/

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public List<Venue> getVenueList() {
        return mVenueList;
    }

    public void setVenueList(List<Venue> venueList) {
        mVenueList = venueList;
    }

    public String getRequestUrl(){
        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("yyyy/MM/dd").format(currentTime);
        String result = date.replaceAll("/", "");
        Log.d(TAG, date);
        return REQUEST_HEADER + LATITUDE + "," + LONGITUDE + "&client_id=" + CLIENT_ID
                + "&client_secret=" + CLIENT_SECRET + "&v=" + result + "&radius=500" +
                "&sortByDistance=1"  + "&fields=" + "id,name,location,like,dislike,categories";
        //
    }

    public String getVenueDetails(String venueId){
        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("yyyy/MM/dd").format(currentTime);
        String result = date.replaceAll("/", "");
        return  VENUE_DETAILS_REQUEST + venueId + "?client_id=" + CLIENT_ID
                + "&client_secret=" + CLIENT_SECRET + "&v=" + result;
    }
}
