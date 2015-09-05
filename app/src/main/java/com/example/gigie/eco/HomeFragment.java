package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by gigie on 8/31/15 AD.
 */
public class HomeFragment extends Fragment {
    private static View view;
    private static GoogleMap mMap;
    private SupportMapFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, null, false);

        mMap = ((SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.setMyLocationEnabled(true);


        ImageButton btn_recycle = (ImageButton) v.findViewById(R.id.btn_recycle);
        ImageButton btn_foodscrape = (ImageButton) v.findViewById(R.id.btn_foodscrape);
        ImageButton btn_landfill = (ImageButton) v.findViewById(R.id.btn_landfill);
        ImageButton btn_freecycle = (ImageButton) v.findViewById(R.id.btn_freecycle);


        btn_recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(14, 100.38))
                                .title("Recycle Waste")
                                .snippet("Recycle Waste Locator")
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle))
                );

            }
        });


        btn_foodscrape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(14, 100.38))
                                        .title("FoodScrape")
                                        .snippet("FoodScrape Locator")
                                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_foodscrape))
                );
            }
        });

        btn_landfill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(13.786, 100.44))
                                .title("Landfill")
                                .snippet("Landfill Locator")
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_constructor))
                );

            }
        });

        btn_freecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(13.6524, 100.4944))
                                .title("Freecycle Stuffs")
                                .snippet("Freecycle Stuffs Locator")
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle))
                );

            }
        });


        return v;


    }

}




