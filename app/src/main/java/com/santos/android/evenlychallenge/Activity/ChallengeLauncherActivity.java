package com.santos.android.evenlychallenge.Activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santos.android.evenlychallenge.API.ClientSingleton;
import com.santos.android.evenlychallenge.API.Location;
import com.santos.android.evenlychallenge.API.Meta;
import com.santos.android.evenlychallenge.API.MyResponse;
import com.santos.android.evenlychallenge.API.Venue;
import com.santos.android.evenlychallenge.R;
import com.santos.android.evenlychallenge.RecyclerView.VenuesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChallengeLauncherActivity extends AppCompatActivity {
    private static final String TAG = "ChallengeLauncher";
    private ClientSingleton sClientSingleton;
    private VenuesAdapter mVenuesAdapter;
    private CoordinatorLayout mCoordinatorLayout;
    private RecyclerView mRecyclerView;
    private List<Venue> mVenueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_launcher);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewVenues);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        sClientSingleton = ClientSingleton.getInstance(getApplicationContext());
        mVenueList = new ArrayList<>();
        //mCoordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator_layout);

        //Initialise adapter
        initialiseAdapter();
        //Get data from API
        requestVenuesFromAPI();
    }

    private void initialiseAdapter(){

        mVenuesAdapter = new VenuesAdapter(mVenueList, getApplicationContext());
        mRecyclerView.setAdapter(mVenuesAdapter);
        mVenuesAdapter.notifyDataSetChanged();

    }

    private void requestVenuesFromAPI(){
        String url = sClientSingleton.getRequestUrl();
        Log.d(TAG, url);

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            //Response Object
                            JSONObject responseObject = response.getJSONObject("response");
                            JSONArray jsonArray = responseObject.getJSONArray("venues");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String data = object.toString();
                                //Location data
                                JSONObject locationObject = object.getJSONObject("location");
                                String locationData = locationObject.toString();
                                Location location = mapper.readValue(locationData, Location.class);
                                Venue venue = mapper.readValue(data, Venue.class);
                                venue.setLocation(location);
                                mVenueList.add(venue);
                                Log.d(TAG, String.valueOf(mVenueList.size()));
                            }

                            Log.d("Result", mVenueList.toString());
                            mVenuesAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //here

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error loading data", Toast.LENGTH_SHORT).show();
                    }
                });

        jsObjRequest.setShouldCache(true);
        sClientSingleton.addToRequestQueue(jsObjRequest);
    }
}
