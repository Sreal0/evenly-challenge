package com.santos.android.evenlychallenge.Activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.santos.android.evenlychallenge.Fragments.ListFragment;
import com.santos.android.evenlychallenge.Fragments.VenueDetailsFragment;
import com.santos.android.evenlychallenge.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChallengeLauncherActivity extends AppCompatActivity {
    private static final String TAG = "ChallengeLauncher";
    private ClientSingleton sClientSingleton;
    private Toolbar mToolbar;
    private List<Venue> mVenueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_launcher);

        sClientSingleton = ClientSingleton.getInstance(getApplicationContext());
        mVenueList = new ArrayList<>();

        //Toolbar
        mToolbar = (Toolbar)findViewById(R.id.toolbar_launcher);
        mToolbar.setTitle(getString(R.string.app_name));

        //Start the ListFragment
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, ListFragment.newInstance(), ListFragment.TAG)
                .addToBackStack(null)
                .commit();

        //Get data from API
        requestVenuesFromAPI();
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
                                sClientSingleton.getVenueList().add(venue);
                                Log.d(TAG, String.valueOf(mVenueList.size()));
                            }

                            Log.d("Result", mVenueList.toString());
                            FragmentManager manager = getSupportFragmentManager();
                            ListFragment listFragment = (ListFragment)manager.findFragmentByTag(ListFragment.TAG);
                            listFragment.notifyAdapter();

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

    public void startDetailsFragment(){
        FragmentManager manager = getSupportFragmentManager();
        Fragment detailsFragment = manager.findFragmentByTag(VenueDetailsFragment.TAG);
        manager.beginTransaction()
                .replace(R.id.frame_layout, VenueDetailsFragment.newInstance(), VenueDetailsFragment.TAG)
                .addToBackStack(VenueDetailsFragment.TAG)
                .commit();
    }
}
