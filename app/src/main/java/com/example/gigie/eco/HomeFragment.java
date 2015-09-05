package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Field;

/**
 * Created by gigie on 8/31/15 AD.
 */
public  class HomeFragment extends Fragment {
    private static View view;
    private static GoogleMap mMap;
    private SupportMapFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_home, null, false);

        // map.setMyLocationEnabled(true);


        Button btn_recycle = (Button) v.findViewById(R.id.btn_recycle);
        Button btn_foodscrape = (Button) v.findViewById(R.id.btn_foodscrape);
        Button btn_landfill = (Button) v.findViewById(R.id.btn_landfill);
        Button btn_freecycle = (Button) v.findViewById(R.id.btn_freecycle);


        btn_recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                mMap.addMarker(new MarkerOptions().position(new LatLng(13.89, 100.55))
                                .title("Freecycle Stuff")
                                .snippet("Freecycle Stuff Locator")
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_foodscrape))
                );

            }
        });


        return v;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();

        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.fl_map, fragment).commit();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mMap == null)
            mMap = fragment.getMap();
        try {
            if (mMap != null) {
                //builder = new LatLngBounds.Builder();
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.getUiSettings().setZoomControlsEnabled(false);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setMyLocationEnabled(true);

                // do whatever you want to do with map
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}




