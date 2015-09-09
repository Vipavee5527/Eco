package com.example.gigie.eco;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by gigie on 8/31/15 AD.
 */
public class HomeFragment extends Fragment {
    private static View view;
    private static GoogleMap mMap;
    private SupportMapFragment fragment;
    MarkerOptions markerOptions;
    LatLng latLng;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, null, false);

        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map)).getMap();
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


        Button btn_search = (Button) v.findViewById(R.id.btn_search_map);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting reference to EditText to get the user input location
                EditText etLocation = (EditText) v.findViewById(R.id.searchMap);

                // Getting user input location
                String location = etLocation.getText().toString();

                if (location != null && !location.equals("")) {
                    new GeocoderTask().execute(location);
                }

            }
        });

        return v;

    }

    // An AsyncTask class for accessing the GeoCoding Web Service
    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if(addresses==null || addresses.size()==0){
                Toast.makeText(getContext(), "No Location found", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            mMap.clear();

            // Adding Markers on Google Map for each matching address
            for(int i=0;i<addresses.size();i++){

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                latLng = new LatLng(address.getLatitude(), address.getLongitude());

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(addressText);

                mMap.addMarker(markerOptions);

                // Locate the first location
                if(i==0)
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
    }
}




