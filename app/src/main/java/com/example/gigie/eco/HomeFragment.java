package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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


        Button btn_recycle = (Button) v.findViewById(R.id.btn_recycle);
        Button btn_foodscrape = (Button) v.findViewById(R.id.btn_foodscrape);
        Button btn_landfill = (Button) v.findViewById(R.id.btn_landfill);
        Button btn_freecycle = (Button) v.findViewById(R.id.btn_freecycle);


        btn_recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(14, 100.38))
                                .title("Recycle Waste")
                                .snippet("Recycle Waste Locator")
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle))
                );

            }
        });


        btn_foodscrape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(13.6524, 100.4944))
                                .title("Food Scrap")
                                .snippet("Food Scrap Locator")
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle))
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
                mMap.addMarker(new MarkerOptions().position(new LatLng(13.89, 100.55))
                                .title("Freecycle Stuff")
                                .snippet("Freecycle Stuff Locator")
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_foodscrape))
                );

            }
        });


        return v;


    }

}




