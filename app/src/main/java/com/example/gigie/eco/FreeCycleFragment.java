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
import android.widget.EditText;
import android.widget.RadioButton;
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
public class FreeCycleFragment extends Fragment {

    private GoogleMap mMap;
    ScrollView mScrollView;

    EditText shopName;
    EditText description;
    EditText address;
    EditText telephone;
    EditText landmark;
    RadioButton chair;
    RadioButton table;
    RadioButton bed;
    RadioButton book;
    RadioButton clothes;
    RadioButton others;
    EditText startDate;
    EditText endDate;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_freecycle, null, false);

        mScrollView = (ScrollView) v.findViewById(R.id.scrollview_freecycle);
        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_pick)).getMap();
        mMap.setMyLocationEnabled(true);


        shopName = (EditText) v.findViewById(R.id.itemName);
        description = (EditText) v.findViewById(R.id.freecycle_description);
        address = (EditText) v.findViewById(R.id.freecycle_address);
        telephone = (EditText) v.findViewById(R.id.telephone);
        landmark = (EditText) v.findViewById(R.id.nearbylandmark);

        chair = (RadioButton) v.findViewById(R.id.radio_chair);
        table = (RadioButton) v.findViewById(R.id.radio_table);
        bed = (RadioButton) v.findViewById(R.id.radio_bed);
        book = (RadioButton) v.findViewById(R.id.radio_book);
        clothes = (RadioButton) v.findViewById(R.id.radio_clothes);
        others = (RadioButton) v.findViewById(R.id.radio_other);

        startDate = (EditText) v.findViewById(R.id.startDate);
        endDate = (EditText)v.findViewById(R.id.finishDate);

        final Marker[] marker = new Marker[1];

        double lat = 0.1;
        double lng = 0.1;
        mMap.clear();
        marker[0] = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle)));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.clear();
                marker[0] = mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle)));
            }

        });

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                double lat = mMap.getMyLocation().getLatitude();
                double lng = mMap.getMyLocation().getLongitude();
                mMap.clear();
                marker[0] = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle)));
                return false;
            }
        });

        Button btn_donefreecycle = (Button) v.findViewById(R.id.btn_donefreecycle);
        btn_donefreecycle.setOnClickListener(new View.OnClickListener() {
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
                                ParseObject freecycle = new ParseObject("Freecycle");
                                ParseObject shop = new ParseObject("Shop");

                                int[] tmp = new int[1];
                                tmp[0] = (int) dealsObject.get("shopID");
                                Log.i(">>>>>>>", "" + tmp[0]);
                                tmp[0]++;

                                shop.put("shopID", tmp[0]);
                                freecycle.put("shopID", tmp[0]);

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

                                shop.put("type", "freecycle");

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
                                    freecycle.put("startDate", startDate.getText().toString());
                                } else {
                                    freecycle.put("minCoffee", "-");
                                }

                                str = endDate.getText().toString();
                                if (!str.equals("")) {
                                    freecycle.put("endDate", endDate.getText().toString());
                                } else {
                                    freecycle.put("endDate", "-");
                                }

//                                str = minFoodScrape.getText().toString();
//                                if (!str.equals("")) {
//                                    foodscrape.put("minFoodScrape", minFoodScrape.getText().toString());
//                                } else {
//                                    foodscrape.put("minFoodScrape", "-");
//                                }
//
//                                str = priceFoodScrape.getText().toString();
//                                if (!str.equals("")) {
//                                    foodscrape.put("priceFoodScrape", priceFoodScrape.getText().toString());
//                                } else {
//                                    foodscrape.put("priceFoodScrape", "-");
//                                }
//
//                                str = minCookingOil.getText().toString();
//                                if (!str.equals("")) {
//                                    foodscrape.put("minCookingOil", minCookingOil.getText().toString());
//                                } else {
//                                    foodscrape.put("minCookingOil", "-");
//                                }
//
//                                str = priceCookingOil.getText().toString();
//                                if (!str.equals("")) {
//                                    foodscrape.put("priceCookingOil", priceCookingOil.getText().toString());
//                                } else {
//                                    foodscrape.put("priceCookingOil", "-");
//                                }

                                str = others.getText().toString();
                                if (!str.equals("")) {
                                    freecycle.put("others", others.getText().toString());
                                } else {
                                    freecycle.put("others", "-");
                                }


                                freecycle.saveInBackground();

                                ShowFreecycle showFreecycle = new ShowFreecycle();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", tmp[0]);
                                showFreecycle.setArguments(args);
                                transaction.replace(R.id.fragment_container, showFreecycle);
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
