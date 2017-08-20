package com.santos.android.evenlychallenge.RecyclerView;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.santos.android.evenlychallenge.API.ClientSingleton;
import com.santos.android.evenlychallenge.API.Venue;
import com.santos.android.evenlychallenge.Activity.ChallengeLauncherActivity;
import com.santos.android.evenlychallenge.Fragments.VenueDetailsFragment;
import com.santos.android.evenlychallenge.R;
import java.util.List;

/**
 * Created by Abel Cruz dos Santos on 17.08.2017.
 */

public class VenuesAdapter extends RecyclerView.Adapter<VenuesAdapter.VenueViewHolder> {
    private Context mContext;
    private ClientSingleton sClientSingleton;
    private List<Venue> mVenueList;
    private TextView mVenueName;
    private TextView mVenueAddress;
    private TextView mVenueCategory;
    private TextView mVenueDistance;


    public VenuesAdapter(List<Venue> list, Context context){
        mContext = context;
        mVenueList = list;
        sClientSingleton = ClientSingleton.getInstance(mContext);

    }
    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_venue, parent, false);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        final Venue venue = mVenueList.get(position);
        holder.bindItem(venue);
    }

    @Override
    public int getItemCount() {
        Log.d("Item count = ", String.valueOf(mVenueList.size()));
        return mVenueList.size();
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }


    public class VenueViewHolder extends RecyclerView.ViewHolder{

        public VenueViewHolder(View itemView) {
            super(itemView);
            mVenueName = (TextView)itemView.findViewById(R.id.txt_venue_name);
            mVenueCategory = (TextView)itemView.findViewById(R.id.txt_venue_category);
            mVenueAddress = (TextView)itemView.findViewById(R.id.txt_venue_address);
            mVenueDistance = (TextView)itemView.findViewById(R.id.txt_distance);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ChallengeLauncherActivity)mContext).startDetailsFragment();

                }
            });
        }

        private void bindItem(Venue venue){
            mVenueName.setText(venue.getName());
            mVenueAddress.setText(venue.getLocation().getAddress());
            //Only the first category
            if(venue.getCategories().length > 0){
                mVenueCategory.setText(venue.getCategories()[0].getNameShort());
            }
            mVenueDistance.append(String.valueOf(venue.getLocation().getDistance()) + " meter(s)");
        }
    }
}
