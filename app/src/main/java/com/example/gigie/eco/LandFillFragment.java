package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by admin on 9/3/2015.
 */
public class LandFillFragment extends Fragment {
    private GoogleMap mMap;
    ScrollView mScrollView;


    EditText shopName;
    EditText description;
    EditText address;
    EditText telephone;
    EditText landmark;
    EditText startDate;
    EditText endDate;
    CheckBox concrete;
    CheckBox sand;
    CheckBox stone;
    CheckBox other;
    EditText otherSpecify;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.create_landfill, null, true);

        mScrollView = (ScrollView) v.findViewById(R.id.scrollview_landfill);
        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_pick)).getMap();
        mMap.setMyLocationEnabled(true);

        shopName = (EditText) v.findViewById(R.id.landfillName);
        description = (EditText) v.findViewById(R.id.landfill_description);
        address = (EditText) v.findViewById(R.id.landfill_address);
        telephone = (EditText) v.findViewById(R.id.telephone);
        landmark = (EditText) v.findViewById(R.id.nearbylandmark);
        startDate = (EditText) v.findViewById(R.id.startDate);
        endDate = (EditText) v.findViewById(R.id.finishDate);

        concrete = (CheckBox) v.findViewById(R.id.concrete);
        sand = (CheckBox) v.findViewById(R.id.sand);
        stone = (CheckBox) v.findViewById(R.id.stone);
        other = (CheckBox) v.findViewById(R.id.other);
        otherSpecify = (EditText) v.findViewById(R.id.other_specify);

        final Marker[] marker = new Marker[1];


        double lat = 0.1;
        double lng = 0.1;
        mMap.clear();
        marker[0] = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_constructor)));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.clear();
                marker[0] = mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_constructor)));
            }

        });

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                double lat = mMap.getMyLocation().getLatitude();
                double lng = mMap.getMyLocation().getLongitude();
                mMap.clear();
                marker[0] = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_constructor)));
                return false;
            }
        });


        Button btn_donelandfill = (Button) v.findViewById(R.id.btn_donelandfill);
        btn_donelandfill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
                query.orderByDescending("shopID");
                query.setLimit(1);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            Log.i("ShopID", "Retrieved " + scoreList.size() + " scores"); // Get List size
                            for (ParseObject dealsObject : scoreList) {
                                // use dealsObject.get('columnName') to access the properties of the Deals object.
                                ParseObject landfill = new ParseObject("Landfill");
                                ParseObject shop = new ParseObject("Shop");

                                int[] tmp = new int[1];
                                tmp[0] = (int) dealsObject.get("shopID");
                                Log.i(">>>>>>>", "" + tmp[0]);
                                tmp[0]++;

                                shop.put("shopID", tmp[0]);
                                landfill.put("shopID", tmp[0]);

                                //////
                                String str = shopName.getText().toString();

                                if (!str.equals("")) {
                                    shop.put("shopName", shopName.getText().toString());
                                } else {
                                    shop.put("shopName", "-");
                                }


                                str = description.getText().toString();
                                if (!str.equals("")) {
                                    shop.put("description", description.getText().toString());
                                } else {
                                    shop.put("description", "-");
                                }

                                shop.put("type", "landfill");

                                str = address.getText().toString();
                                if (!str.equals("")) {
                                    shop.put("address", address.getText().toString());
                                } else {
                                    shop.put("address", "-");
                                }
                                str = telephone.getText().toString();
                                if (!str.equals("")) {
                                    shop.put("telephone", telephone.getText().toString());
                                } else {
                                    shop.put("telephone", "-");
                                }

                                str = landmark.getText().toString();
                                if (!str.equals("")) {
                                    shop.put("landmark", landmark.getText().toString());
                                } else {
                                    shop.put("landmark", "-");
                                }

                                shop.put("lat", marker[0].getPosition().latitude);
                                shop.put("lng", marker[0].getPosition().longitude);
                                shop.saveInBackground();


                                /////
                                str = startDate.getText().toString();
                                if (!str.equals("")) {
                                    landfill.put("startDate", startDate.getText().toString());
                                } else {
                                    landfill.put("startDate", "-");
                                }

                                str = endDate.getText().toString();
                                if (!str.equals("")) {
                                    landfill.put("endDate", endDate.getText().toString());
                                } else {
                                    landfill.put("endDate", "-");
                                }

                                str = "";


                                if (!concrete.isChecked()) {
                                    landfill.put("concrete", concrete.getText().toString());
                                } else {
                                    landfill.put("concrete", "-");
                                }


                                if (!sand.isChecked()) {
                                    landfill.put("sand", sand.getText().toString());
                                } else {
                                    landfill.put("sand", "-");
                                }


                                if (!stone.isChecked()) {
                                    landfill.put("stone", stone.getText().toString());
                                } else {
                                    landfill.put("stone", "-");
                                }


                                if (!other.isChecked()) {
                                    landfill.put("other", other.getText().toString());
                                } else {
                                    landfill.put("other", "-");
                                }

                                str = otherSpecify.getText().toString();
                                if (!str.equals("")) {
                                    landfill.put("otherSpecify", otherSpecify.getText().toString());
                                } else {
                                    landfill.put("otherSpecify", "-");
                                }


                                landfill.saveInBackground();
                                ShowLandfill showLandfill = new ShowLandfill();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", tmp[0]);
                                showLandfill.setArguments(args);
                                transaction.replace(R.id.fragment_container, showLandfill);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }

                        } else {
                            Log.i("score", "Error: " + e.getMessage());
                        }
                    }
                });


            }
        });


        return v;
    }
}
