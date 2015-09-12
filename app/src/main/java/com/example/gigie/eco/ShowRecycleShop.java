package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by gigie on 9/12/15 AD.
 */
public class ShowRecycleShop extends Fragment {

    private GoogleMap mMap;
    ScrollView mScrollView;
    Marker marker;

    int sID;

    TextView shopName;
    TextView description;
    TextView address;
    TextView telephone;
    TextView landmark;

    TextView priceOfficepaper;
    TextView priceNewspaper;
    TextView priceCardBoard;
    TextView priceHardboard;
    TextView paperSpecify;
    TextView pricePaperOther;
    TextView pricePet;
    TextView pricePP;
    TextView pricePvc;
    TextView priceCable;
    TextView plasticSpecify;
    TextView pricePlasticOther;
    TextView priceCan;
    TextView priceCopper;
    TextView priceLead;
    TextView priceZinc;
    TextView priceIron;
    TextView MetalSpecify;
    TextView priceMetalOther;
    TextView priceBeerBottle;
    TextView priceBottleScrape;
    TextView glassSpecify;
    TextView priceotherGlass;



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.showrecycleshop, null, true);


        sID = getArguments().getInt("sID");

        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_show)).getMap();
        mMap.setMyLocationEnabled(true);

        shopName = (TextView) v.findViewById(R.id.show_shopName);
        description = (TextView) v.findViewById(R.id.show_recycle_description);
        address = (TextView) v.findViewById(R.id.show_recycle_location);
        telephone = (TextView) v.findViewById(R.id.show_telephone);
        landmark = (TextView) v.findViewById(R.id.show_nearby);

        priceOfficepaper = (TextView) v.findViewById(R.id.show_priceOfficePaper);
        priceNewspaper=(TextView) v.findViewById(R.id.show_priceNewspaper);
        priceCardBoard = (TextView) v.findViewById(R.id.show_priceCardBoard);
        priceHardboard = (TextView) v.findViewById(R.id.show_priceHardboard);
        paperSpecify = (TextView) v.findViewById(R.id.show_paperSpecify);
        pricePaperOther=(TextView) v.findViewById(R.id.show_priceOtherPaper);
        pricePet = (TextView) v.findViewById(R.id.show_pricePet);
        pricePP = (TextView) v.findViewById(R.id.show_pricePP);
        pricePvc = (TextView) v.findViewById(R.id.show_pricePvc);
        priceCable = (TextView) v.findViewById(R.id.show_priceCable);
        plasticSpecify = (TextView) v.findViewById(R.id.show_PlasticSpecify);
        pricePlasticOther=(TextView) v.findViewById(R.id.show_priceOtherPlastic);
        priceCan = (TextView) v.findViewById(R.id.show_priceCan);
        priceCopper = (TextView) v.findViewById(R.id.show_priceCopper);
        priceLead = (TextView) v.findViewById(R.id.show_priceLead);
        priceZinc = (TextView) v.findViewById(R.id.show_priceZinc);
        priceIron = (TextView) v. findViewById(R.id.show_priceIron);
        MetalSpecify = (TextView) v.findViewById(R.id.show_metalSpecify);
        priceMetalOther = (TextView) v.findViewById(R.id.show_priceMetalOther);
        priceBeerBottle = (TextView) v.findViewById(R.id.show_priceBeerBottle);
        priceBottleScrape = (TextView) v.findViewById(R.id.show_priceBottleScrape);
        glassSpecify = (TextView) v.findViewById(R.id.show_glassSpecify);
        priceotherGlass = (TextView) v.findViewById(R.id.show_priceGlassOther);


//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                mMap.clear();
//                mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));
//            }
//
//        });
//
//        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
//            @Override
//            public boolean onMyLocationButtonClick() {
//                double lat = mMap.getMyLocation().getLatitude();
//                double lng = mMap.getMyLocation().getLongitude();
//                mMap.clear();
//                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));
//                return false;
//            }
//        });





        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Recycle");
        query2.whereEqualTo("shopID",sID);
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.i(">>>>>>>>>>>>>>>>>>>>>", "Retrieved " + scoreList.size() + " scores"); // Get List size
                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        priceOfficepaper.setText(dealsObject.get("PriceofficePaper").toString());
                        priceNewspaper.setText(dealsObject.get("Pricenewspaper").toString());
                        priceCardBoard.setText(dealsObject.get("Pricecardboard").toString());
                        priceHardboard.setText(dealsObject.get("PriceHardboard").toString());
                        paperSpecify.setText(dealsObject.get("paperSpecify").toString());
                        pricePaperOther.setText(dealsObject.get("PriceofficePaper").toString());
                        pricePet.setText(dealsObject.get("pricePet").toString());
                        pricePP.setText(dealsObject.get("pricePP").toString());
                        pricePvc.setText(dealsObject.get("pricePvc").toString());
                        priceCable.setText(dealsObject.get("priceCable").toString());
                        plasticSpecify.setText(dealsObject.get("plasticSpecify").toString());
                        pricePlasticOther.setText(dealsObject.get("pricePlasticOther").toString());
                        priceCan.setText(dealsObject.get("priceCable").toString());
                        priceCopper.setText(dealsObject.get("priceCopper").toString());
                        priceLead.setText(dealsObject.get("priceLead").toString());
                        priceZinc.setText(dealsObject.get("priceZinc").toString());
                        priceIron.setText(dealsObject.get("priceIron").toString());
                        MetalSpecify.setText(dealsObject.get("MetalSpecify").toString());
                        priceMetalOther.setText(dealsObject.get("priceMetalOther").toString());
                        priceBeerBottle.setText(dealsObject.get("priceBeerBottle").toString());
                        priceBottleScrape.setText(dealsObject.get("priceBottleScrape").toString());
                        glassSpecify.setText(dealsObject.get("glassSpecify").toString());
                        priceotherGlass.setText(dealsObject.get("priceotherGlass").toString());
                    }

                } else {
                    Log.i("score", "Error: " + e.getMessage());
                }
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
        query.whereEqualTo("shopID",sID);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        shopName.setText(dealsObject.get("shopName").toString());
                        description.setText(dealsObject.get("description").toString());
                        address.setText(dealsObject.get("address").toString());
                        telephone.setText(dealsObject.get("telephone").toString());
                        landmark.setText(dealsObject.get("landmark").toString());
                        mMap.addMarker(new MarkerOptions().position(new LatLng((double) dealsObject.get("lat"),
                                (double) dealsObject.get("lng"))).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng((double) dealsObject.get("lat"),(double) dealsObject.get("lng")))      // Sets the center of the map to location user
                                .zoom(17)                   // Sets the zoom
                                .bearing(90)                // Sets the orientation of the camera to east
                                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                                .build();                   // Creates a CameraPosition from the builder
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    }

                } else {
                    Log.i("score", "Error: " + e.getMessage());
                }
            }
        });
        return v;
    }
}
