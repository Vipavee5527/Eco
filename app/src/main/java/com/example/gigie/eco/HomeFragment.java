package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
 * Created by gigie on 8/31/15 AD.
 */
public class HomeFragment extends Fragment {
    private static View view;
    private static GoogleMap mMap;
    private SupportMapFragment fragment;
    MarkerOptions markerOptions;
    LatLng latLng;

    Marker marker;

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
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
                query.whereContains("type", "recycle");
                //query.whereMatches("shopName", "o+");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
                            for (ParseObject dealsObject : scoreList) {
                                // use dealsObject.get('columnName') to access the properties of the Deals object.

                                marker = mMap.addMarker(new MarkerOptions().position(new LatLng((double) dealsObject.get("lat"),
                                        (double) dealsObject.get("lng")))
                                        .title(dealsObject.get("shopName").toString())
                                        .snippet("Recycle Locator")
                                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));
                                marker.showInfoWindow();

                                // recycle
                                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                    @Override
                                    public void onInfoWindowClick(Marker marker) {
                                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
                                        query.whereContains("shopName", marker.getTitle());
                                        //query.whereMatches("shopName", "o+");
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (e == null) {
                                                    Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
                                                    for (ParseObject dealsObject : scoreList) {

                                                        // use dealsObject.get('columnName') to access the properties of the Deals object.


                                                        ShowRecycleShop showRecycleShop = new ShowRecycleShop();
                                                        FragmentManager fm = getFragmentManager();
                                                        FragmentTransaction transaction = fm.beginTransaction();
                                                        Bundle args = new Bundle();
                                                        args.putInt("sID", (int) dealsObject.get("shopID"));
                                                        showRecycleShop.setArguments(args);
                                                        transaction.replace(R.id.fragment_container, showRecycleShop);
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

                            }

                        } else {
                            Log.i("score", "Error: " + e.getMessage());
                        }
                    }
                });

            }
        });


        btn_foodscrape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
                query.whereContains("type", "foodscrape");
                //query.whereMatches("shopName", "o+");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
                            for (ParseObject dealsObject : scoreList) {
                                // use dealsObject.get('columnName') to access the properties of the Deals object.

                                marker = mMap.addMarker(new MarkerOptions().position(new LatLng((double) dealsObject.get("lat"),
                                        (double) dealsObject.get("lng")))
                                        .title(dealsObject.get("shopName").toString())
                                        .snippet("Foodsrape Locator")
                                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_foodscrape)));
                                marker.showInfoWindow();

                                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                    @Override
                                    public void onInfoWindowClick(Marker marker) {
                                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
                                        query.whereContains("shopName", marker.getTitle());
                                        //query.whereMatches("shopName", "o+");
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (e == null) {
                                                    Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
                                                    for (ParseObject dealsObject : scoreList) {

                                                        // use dealsObject.get('columnName') to access the properties of the Deals object.


                                                        ShowFoodscrape showFoodscrape = new ShowFoodscrape();
                                                        FragmentManager fm = getFragmentManager();
                                                        FragmentTransaction transaction = fm.beginTransaction();
                                                        Bundle args = new Bundle();
                                                        args.putInt("sID", (int) dealsObject.get("shopID"));
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

                            }

                        } else {
                            Log.i("score", "Error: " + e.getMessage());
                        }
                    }
                });

            }
        });


        btn_landfill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
                query.whereContains("type", "landfill");
                //query.whereMatches("shopName", "o+");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
                            for (ParseObject dealsObject : scoreList) {
                                // use dealsObject.get('columnName') to access the properties of the Deals object.

                                marker = mMap.addMarker(new MarkerOptions().position(new LatLng((double) dealsObject.get("lat"),
                                        (double) dealsObject.get("lng")))
                                        .title(dealsObject.get("shopName").toString())
                                        .snippet("Landfill Locator")
                                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_constructor)));
                                marker.showInfoWindow();

                                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                    @Override
                                    public void onInfoWindowClick(Marker marker) {
                                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
                                        query.whereContains("shopName", marker.getTitle());
                                        //query.whereMatches("shopName", "o+");
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (e == null) {
                                                    Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
                                                    for (ParseObject dealsObject : scoreList) {

                                                        // use dealsObject.get('columnName') to access the properties of the Deals object.


                                                        ShowLandfill showLandfill = new ShowLandfill();
                                                        FragmentManager fm = getFragmentManager();
                                                        FragmentTransaction transaction = fm.beginTransaction();
                                                        Bundle args = new Bundle();
                                                        args.putInt("sID", (int) dealsObject.get("shopID"));
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

                            }

                        } else {
                            Log.i("score", "Error: " + e.getMessage());
                        }
                    }
                });

            }
        });


//FreeCycle
        btn_freecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
                query.whereContains("type", "landfill");
                //query.whereMatches("shopName", "o+");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
                            for (ParseObject dealsObject : scoreList) {
                                // use dealsObject.get('columnName') to access the properties of the Deals object.

                                marker = mMap.addMarker(new MarkerOptions().position(new LatLng((double) dealsObject.get("lat"),
                                        (double) dealsObject.get("lng")))
                                        .title(dealsObject.get("shopName").toString())
                                        .snippet("Freecycle Stuffs Locator")
                                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle)));
                                marker.showInfoWindow();

                                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                    @Override
                                    public void onInfoWindowClick(Marker marker) {
                                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
                                        query.whereContains("shopName", marker.getTitle());
                                        //query.whereMatches("shopName", "o+");
                                        query.findInBackground(new FindCallback<ParseObject>() {
                                            public void done(List<ParseObject> scoreList, ParseException e) {
                                                if (e == null) {
                                                    Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
                                                    for (ParseObject dealsObject : scoreList) {

                                                        // use dealsObject.get('columnName') to access the properties of the Deals object.


                                                        ShowLandfill showLandfill = new ShowLandfill();
                                                        FragmentManager fm = getFragmentManager();
                                                        FragmentTransaction transaction = fm.beginTransaction();
                                                        Bundle args = new Bundle();
                                                        args.putInt("sID", (int) dealsObject.get("shopID"));
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

                            }

                        } else {
                            Log.i("score", "Error: " + e.getMessage());
                        }
                    }
                });

            }
        });
//        //food
//
//        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Shop");
//                query2.whereContains("shopName", marker.getTitle());
//                //query.whereMatches("shopName", "o+");
//                query2.findInBackground(new FindCallback<ParseObject>() {
//                    public void done(List<ParseObject> scoreList, ParseException e) {
//                        if (e == null) {
//                            Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
//                            for (ParseObject dealsObject : scoreList) {
//                                // use dealsObject.get('columnName') to access the properties of the Deals object.
//
//                                ShowFoodscrape showFoodscrape = new ShowFoodscrape();
//                                FragmentManager fm = getFragmentManager();
//                                FragmentTransaction transaction = fm.beginTransaction();
//                                Bundle args = new Bundle();
//                                args.putInt("sID", (int) dealsObject.get("shopID"));
//                                showFoodscrape.setArguments(args);
//                                transaction.replace(R.id.fragment_container, showFoodscrape);
//                                transaction.addToBackStack(null);
//                                transaction.commit();
//                            }
//
//                        } else {
//                            Log.i("score", "Error: " + e.getMessage());
//                        }
//                    }
//                });
//
//
//            }
//        });

//
//        btn_foodscrape.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mMap.clear();
//                ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Shop");
//                query2.whereContains("type", "foodscrape");
//                //query.whereMatches("shopName", "o+");
//                query2.findInBackground(new FindCallback<ParseObject>() {
//                    public void done(List<ParseObject> scoreList, ParseException e) {
//                        if (e == null) {
//                            Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
//                            for (ParseObject dealsObject : scoreList) {
//                                // use dealsObject.get('columnName') to access the properties of the Deals object.
//
//                                marker = mMap.addMarker(new MarkerOptions().position(new LatLng((double) dealsObject.get("lat"),
//                                        (double) dealsObject.get("lng")))
//                                        .title(dealsObject.get("shopName").toString())
//                                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_foodscrape)));
//                                marker.showInfoWindow();
//                            }
//
//                        } else {
//                            Log.i("score", "Error: " + e.getMessage());
//                        }
//                    }
//                });
//
//            }
//        });



//        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Shop");
//                query3.whereContains("shopName", marker.getTitle());
//                //query.whereMatches("shopName", "o+");
//                query3.findInBackground(new FindCallback<ParseObject>() {
//                    public void done(List<ParseObject> scoreList, ParseException e) {
//                        if (e == null) {
//                            Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
//                            for (ParseObject dealsObject : scoreList) {
//                                // use dealsObject.get('columnName') to access the properties of the Deals object.
//
//                                ShowLandfill showLandfill = new ShowLandfill();
//                                FragmentManager fm = getFragmentManager();
//                                FragmentTransaction transaction = fm.beginTransaction();
//                                Bundle args = new Bundle();
//                                args.putInt("sID", (int) dealsObject.get("shopID"));
//                                showLandfill.setArguments(args);
//                                transaction.replace(R.id.fragment_container, showLandfill);
//                                transaction.addToBackStack(null);
//                                transaction.commit();
//                            }
//
//                        } else {
//                            Log.i("score", "Error: " + e.getMessage());
//                        }
//                    }
//                });
//
//
//            }
//        });


//        btn_landfill.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mMap.clear();
//                mMap.addMarker(new MarkerOptions().position(new LatLng(13.6524, 100.4944))
//                                .title("Landfill")
//                                .snippet("Landfill Locator")
//                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_constructor))
//                );
//
//
//            }
//        });






//        btn_freecycle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mMap.clear();
//                mMap.addMarker(new MarkerOptions().position(new LatLng(13.6524, 100.4944))
//                                .title("Freecycle Stuffs")
//                                .snippet("Freecycle Stuffs Locator")
//                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle))
//                );
//
//            }
//        });


        return v;

    }

}




