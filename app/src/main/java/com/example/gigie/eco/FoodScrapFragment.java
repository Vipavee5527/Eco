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
public class FoodScrapFragment extends Fragment {

    private GoogleMap mMap;
    ScrollView mScrollView;

    EditText shopName;
    EditText description;
    EditText address;
    EditText telephone;
    EditText landmark;

    EditText minCoffee;
    EditText priceCoffee;
    EditText minFoodScrape;
    EditText priceFoodScrape;
    EditText minCookingOil;
    EditText priceCookingOil;
    EditText minFoodScrapeOther;
    EditText priceFoodScrapeOther;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_foodscrap, null, false);

        mScrollView = (ScrollView) v.findViewById(R.id.scrollview_foodscrape);
        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_pick)).getMap();
        mMap.setMyLocationEnabled(true);


        shopName = (EditText) v.findViewById(R.id.foodscrap_shopName);
        description = (EditText) v.findViewById(R.id.foodscrap_description);
        address = (EditText) v.findViewById(R.id.foodscrap_address);
        telephone = (EditText) v.findViewById(R.id.foodscrap_telephone);
        landmark = (EditText) v.findViewById(R.id.foodscrap_nearbylandmark);
        minCoffee = (EditText) v.findViewById(R.id.minCoffeeGround);
        priceCoffee = (EditText) v.findViewById(R.id.priceCoffeeGround);
        minFoodScrape = (EditText) v.findViewById(R.id.minFoodScrap);
        priceFoodScrape = (EditText) v.findViewById(R.id.priceFoodScrap);
        minCookingOil = (EditText) v.findViewById(R.id.minCookingOil);
        priceCookingOil = (EditText) v.findViewById(R.id.priceCookingOil);
        minFoodScrapeOther = (EditText) v.findViewById(R.id.minFoodScrapOther);
        priceFoodScrapeOther = (EditText) v.findViewById(R.id.priceFoodScrapOther);

        final Marker[] marker = new Marker[1];

        double lat = 0.1;
        double lng = 0.1;
        mMap.clear();
        marker[0] = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_foodscrape)));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.clear();
                marker[0] = mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_foodscrape)));
            }

        });

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                double lat = mMap.getMyLocation().getLatitude();
                double lng = mMap.getMyLocation().getLongitude();
                mMap.clear();
                marker[0] = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_foodscrape)));
                return false;
            }
        });


        Button btn_donefoodscrap = (Button) v.findViewById(R.id.btn_donefoodscrap);
        btn_donefoodscrap.setOnClickListener(new View.OnClickListener() {
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
                                ParseObject foodscrape = new ParseObject("FoodScrape");
                                ParseObject shop = new ParseObject("Shop");

                                int[] tmp = new int[1];
                                tmp[0] = (int) dealsObject.get("shopID");
                                Log.i(">>>>>>>", "" + tmp[0]);
                                tmp[0]++;

                                shop.put("shopID", tmp[0]);
                                foodscrape.put("shopID", tmp[0]);

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

                                shop.put("type", "foodscrape");

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
                                str = minCoffee.getText().toString();
                                if (!str.equals("")) {
                                    foodscrape.put("minCoffee", minCoffee.getText().toString());
                                } else {
                                    foodscrape.put("minCoffee", "-");
                                }

                                str = priceCoffee.getText().toString();
                                if (!str.equals("")) {
                                    foodscrape.put("priceCoffee", priceCoffee.getText().toString());
                                } else {
                                    foodscrape.put("priceCoffee", "-");
                                }

                                str = minFoodScrape.getText().toString();
                                if (!str.equals("")) {
                                    foodscrape.put("minFoodScrape", minFoodScrape.getText().toString());
                                } else {
                                    foodscrape.put("minFoodScrape", "-");
                                }

                                str = priceFoodScrape.getText().toString();
                                if (!str.equals("")) {
                                    foodscrape.put("priceFoodScrape", priceFoodScrape.getText().toString());
                                } else {
                                    foodscrape.put("priceFoodScrape", "-");
                                }

                                str = minCookingOil.getText().toString();
                                if (!str.equals("")) {
                                    foodscrape.put("minCookingOil", minCookingOil.getText().toString());
                                } else {
                                    foodscrape.put("minCookingOil", "-");
                                }

                                str = priceCookingOil.getText().toString();
                                if (!str.equals("")) {
                                    foodscrape.put("priceCookingOil", priceCookingOil.getText().toString());
                                } else {
                                    foodscrape.put("priceCookingOil", "-");
                                }

                                str = minFoodScrapeOther.getText().toString();
                                if (!str.equals("")) {
                                    foodscrape.put("minFoodScrapeOther", minFoodScrapeOther.getText().toString());
                                } else {
                                    foodscrape.put("minFoodScrapeOther", "-");
                                }

                                str = priceFoodScrapeOther.getText().toString();
                                if (!str.equals("")) {
                                    foodscrape.put("priceFoodScrapeOther", priceFoodScrapeOther.getText().toString());
                                } else {
                                    foodscrape.put("priceFoodScrapeOther", "-");
                                }

                                foodscrape.saveInBackground();
                                ShowFoodscrape showFoodscrape = new ShowFoodscrape();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", tmp[0]);
                                showFoodscrape.setArguments(args);
                                transaction.replace(R.id.fragment_container, showFoodscrape);
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
