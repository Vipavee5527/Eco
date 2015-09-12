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
public class RecycleShopFragment extends Fragment {


    private GoogleMap mMap;
    ScrollView mScrollView;
    Marker marker;


    EditText shopName;
    EditText description;
    EditText address;
    EditText telephone;
    EditText landmark;
    EditText priceOfficepaper;
    EditText priceNewspaper;
    EditText priceCardBoard;
    EditText priceHardboard;
    EditText paperSpecify;
    EditText pricePaperOther;
    EditText pricePet;
    EditText pricePP;
    EditText pricePvc;
    EditText priceCable;
    EditText plasticSpecify;
    EditText pricePlasticOther;
    EditText priceCan;
    EditText priceCopper;
    EditText priceLead;
    EditText priceZinc;
    EditText priceIron;
    EditText MetalSpecify;
    EditText priceMetalOther;
    EditText priceBeerBottle;
    EditText priceBottleScrape;
    EditText glassSpecify;
    EditText priceotherGlass;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.create_recycleshop, null, true);

        mScrollView = (ScrollView) v.findViewById(R.id.scrollview_recycle);
        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_pick)).getMap();
        mMap.setMyLocationEnabled(true);

        shopName = (EditText) v.findViewById(R.id.shopName);
        description = (EditText) v.findViewById(R.id.recycle_description);
        address = (EditText) v.findViewById(R.id.recycle_address);
        telephone = (EditText) v.findViewById(R.id.telephone);
        landmark = (EditText) v.findViewById(R.id.nearbylandmark);
        priceOfficepaper = (EditText) v.findViewById(R.id.priceOfficePaper);
        priceNewspaper=(EditText) v.findViewById(R.id.priceNewspaper);
        priceCardBoard = (EditText) v.findViewById(R.id.priceCardBoard);
        priceHardboard = (EditText) v.findViewById(R.id.priceHardboard);
        paperSpecify = (EditText) v.findViewById(R.id.paperSpecify);
        pricePaperOther=(EditText) v.findViewById(R.id.priceOtherPaper);
        pricePet = (EditText) v.findViewById(R.id.pricePet);
        pricePP = (EditText) v.findViewById(R.id.pricePP);
        pricePvc = (EditText) v.findViewById(R.id.pricePvc);
        priceCable = (EditText) v.findViewById(R.id.priceCable);
        plasticSpecify = (EditText) v.findViewById(R.id.PlasticSpecify);
        pricePlasticOther=(EditText) v.findViewById(R.id.priceOtherPlastic);
        priceCan = (EditText) v.findViewById(R.id.priceCan);
        priceCopper = (EditText) v.findViewById(R.id.priceCopper);
        priceLead = (EditText) v.findViewById(R.id.priceLead);
        priceZinc = (EditText) v.findViewById(R.id.priceZinc);
        priceIron = (EditText) v. findViewById(R.id.priceIron);
        MetalSpecify = (EditText) v.findViewById(R.id.metalSpecify);
        priceMetalOther = (EditText) v.findViewById(R.id.priceMetalOther);
        priceBeerBottle = (EditText) v.findViewById(R.id.priceBeerBottle);
        priceBottleScrape = (EditText) v.findViewById(R.id.priceBottleScrape);
        glassSpecify = (EditText) v.findViewById(R.id.glassSpecify);
        priceotherGlass = (EditText) v.findViewById(R.id.priceGlassOther);

        final Marker[] marker = new Marker[1];




//        if (mMap == null) {
//            mMap = ((WorkaroundMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map_pick)).getMap();
//            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//            mMap.getUiSettings().setZoomControlsEnabled(true);
//            mScrollView = (ScrollView) v.findViewById(R.id.scrollview_recycle); //parent scrollview in xml, give your scrollview id value
//
//            ((WorkaroundMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map_pick))
//                    .setListener(new WorkaroundMapFragment.OnTouchListener() {
//                        @Override
//                        public void onTouch() {
//                            mScrollView.requestDisallowInterceptTouchEvent(true);
//                        }
//                    });
//
//        }

        double lat = 0.1;
        double lng = 0.1;
        mMap.clear();
        marker[0] = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.clear();
                marker[0] = mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));
            }

        });

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                double lat = mMap.getMyLocation().getLatitude();
                double lng = mMap.getMyLocation().getLongitude();
                mMap.clear();
                marker[0] = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));
                return false;
            }
        });


        //dataGotFromServer = ((EditText) v.findViewById(R.id.shopName)).getText().toString();



//        Button selectcatRecycle = (Button) v.findViewById(R.id.btn_cat_recycle);
//        selectcatRecycle.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//
//            public void onClick(View v) {
//                FragmentSelectRecycle fragmentSelectRecycle = new FragmentSelectRecycle();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
////                fragmentManager.saveFragmentInstanceState(fragmentSelectRecycle);
//                transaction.replace(R.id.fragment_container, fragmentSelectRecycle);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });



        Button doneRecycle = (Button)v.findViewById(R.id.btn_donerecycle);
        doneRecycle.setOnClickListener(new View.OnClickListener() {
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
                                ParseObject recycle = new ParseObject("Recycle");
                                ParseObject shop = new ParseObject("Shop");

                                int[] tmp = new int[1];
                                tmp[0] = (int) dealsObject.get("shopID");
                                Log.i(">>>>>>>", "" + tmp[0]);
                                tmp[0]++;

                                shop.put("shopID", tmp[0]);
                                recycle.put("shopID", tmp[0]);

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

                                shop.put("type", "recycle");

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

                                shop.put("lat",marker[0].getPosition().latitude);
                                shop.put("lng", marker[0].getPosition().longitude);
                                shop.saveInBackground();



                                /////
                                str = priceOfficepaper.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("PriceofficePaper", priceOfficepaper.getText().toString());
                                } else {
                                    recycle.put("PriceofficePaper", "-");
                                }

                                str = priceNewspaper.getText().toString();
                                if(!str.equals("")) {
                                    recycle.put("Pricenewspaper", priceNewspaper.getText().toString());
                                } else {
                                    recycle.put("Pricenewspaper", "-");
                                }

                                str = priceCardBoard.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("Pricecardboard", priceCardBoard.getText().toString());
                                } else {
                                    recycle.put("Pricecardboard", "-");
                                }

                                str = priceHardboard.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("PriceHardboard", priceHardboard.getText().toString());
                                } else {
                                    recycle.put("PriceHardboard", "-");
                                }

                                str = paperSpecify.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("paperSpecify", paperSpecify.getText().toString());
                                } else {
                                    recycle.put("paperSpecify", "-");
                                }

                                str = pricePaperOther.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("pricePaperOther", pricePaperOther.getText().toString());
                                } else {
                                    recycle.put("pricePaperOther", "-");
                                }

                                str = pricePet.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("pricePet", pricePet.getText().toString());
                                } else {
                                    recycle.put("pricePet", "-");
                                }

                                str = pricePP.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("pricePP", pricePP.getText().toString());
                                } else {
                                    recycle.put("pricePP", "-");
                                }

                                str = pricePvc.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("pricePvc", pricePvc.getText().toString());
                                } else {
                                    recycle.put("pricePvc", "-");
                                }

                                str = priceCable.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("priceCable", priceCable.getText().toString());
                                } else {
                                    recycle.put("priceCable", "-");
                                }

                                str = plasticSpecify.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("plasticSpecify", plasticSpecify.getText().toString());
                                } else {
                                    recycle.put("plasticSpecify", "-");
                                }

                                str = pricePlasticOther.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("pricePlasticOther", pricePlasticOther.getText().toString());
                                } else {
                                    recycle.put("pricePlasticOther", "-");
                                }

                                str = priceCan.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("priceCan", priceCan.getText().toString());
                                } else {
                                    recycle.put("priceCan", "-");
                                }

                                str = priceCopper.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("priceCopper", priceCopper.getText().toString());
                                } else {
                                    recycle.put("priceCopper", "-");
                                }

                                str = priceLead.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("priceLead", priceLead.getText().toString());
                                } else {
                                    recycle.put("priceLead", "-");
                                }
                                str = priceZinc.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("priceZinc", priceZinc.getText().toString());
                                } else {
                                    recycle.put("priceZinc", "-");
                                }
                                str = priceIron.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("priceIron", priceIron.getText().toString());
                                } else {
                                    recycle.put("priceIron", "-");
                                }
                                str = MetalSpecify.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("MetalSpecify", MetalSpecify.getText().toString());
                                } else {
                                    recycle.put("MetalSpecify", "-");
                                }
                                str = priceMetalOther.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("priceMetalOther", priceMetalOther.getText().toString());
                                } else {
                                    recycle.put("priceMetalOther", "-");
                                }
                                str = priceBeerBottle.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("priceBeerBottle", priceBeerBottle.getText().toString());
                                } else {
                                    recycle.put("priceBeerBottle", "-");
                                }
                                str = priceBottleScrape.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("priceBottleScrape", priceBottleScrape.getText().toString());
                                } else {
                                    recycle.put("priceBottleScrape", "-");
                                }

                                str = glassSpecify.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("glassSpecify", glassSpecify.getText().toString());
                                } else {
                                    recycle.put("glassSpecify", "-");
                                }
                                str = priceotherGlass.getText().toString();
                                if (!str.equals("")) {
                                    recycle.put("priceotherGlass", priceotherGlass.getText().toString());
                                } else {
                                    recycle.put("priceotherGlass", "-");
                                }


                                recycle.saveInBackground();




                                ShowRecycleShop showRecycleShop = new ShowRecycleShop();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", tmp[0]);
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






        return v;

    }

}
