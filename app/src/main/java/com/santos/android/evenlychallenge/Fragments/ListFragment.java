package com.santos.android.evenlychallenge.Fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.santos.android.evenlychallenge.API.ClientSingleton;
import com.santos.android.evenlychallenge.R;
import com.santos.android.evenlychallenge.RecyclerView.VenuesAdapter;

/**
 * Created by Abel Cruz dos Santos on 20.08.2017.
 */

public class ListFragment extends Fragment {
    public static final String TAG = "ListFragment";

    private VenuesAdapter mVenuesAdapter;
    private CoordinatorLayout mCoordinatorLayout;
    private RecyclerView mRecyclerView;
    private ClientSingleton sClientSingleton;


    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        sClientSingleton = ClientSingleton.getInstance(getActivity());
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewVenues);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCoordinatorLayout = (CoordinatorLayout)view.findViewById(R.id.coordinator_layout);
        initialiseAdapter();
        return view;

    }

    private void initialiseAdapter(){

        mVenuesAdapter = new VenuesAdapter(sClientSingleton.getVenueList(), getActivity());
        mRecyclerView.setAdapter(mVenuesAdapter);
        mVenuesAdapter.notifyDataSetChanged();

    }

    public void notifyAdapter(){
        mVenuesAdapter.notifyDataSetChanged();
    }
}
