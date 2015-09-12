package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.parse.ParseObject;

/**
 * Created by admin on 9/3/2015.
 */
public class RecycleShopFragment extends Fragment {


    private String dataGotFromServer;
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


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));
            }

        });

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                double lat = mMap.getMyLocation().getLatitude();
                double lng = mMap.getMyLocation().getLongitude();
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));
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

                ParseObject recycle = new ParseObject("Recycle");
                recycle.put("shopName", shopName.getText().toString());
                recycle.put("description", description.getText().toString());
                recycle.put("address", address.getText().toString());
                recycle.put("telephone", telephone.getText().toString());
                recycle.put("landmark", landmark.getText().toString());
                recycle.put("PriceofficePaper", priceOfficepaper.getText().toString());
                recycle.put("Pricenewspaper", priceNewspaper.getText().toString());
                recycle.put("Pricecardboard", priceCardBoard.getText().toString());
                recycle.put("PriceHardboard", priceHardboard.getText().toString());
                recycle.put("paperSpecify", paperSpecify.getText().toString());
                recycle.put("pricePaperOther", pricePaperOther.getText().toString());
                recycle.put("pricePet", pricePet.getText().toString());
                recycle.put("pricePP", pricePP.getText().toString());
                recycle.put("pricePvc", pricePvc.getText().toString());
                recycle.put("priceCable", priceCable.getText().toString());
                recycle.put("plasticSpecify", plasticSpecify.getText().toString());
                recycle.put("pricePlasticOther", pricePlasticOther.getText().toString());
                recycle.put("priceCan", priceCan.getText().toString());
                recycle.put("priceCopper", priceCopper.getText().toString());
                recycle.put("priceLead", priceLead.getText().toString());
                recycle.put("priceZinc", priceZinc.getText().toString());
                recycle.put("priceIron", priceIron.getText().toString());
                recycle.put("MetalSpecify", MetalSpecify.getText().toString());
                recycle.put("priceMetalOther", priceMetalOther.getText().toString());
                recycle.put("priceBeerBottle", priceBeerBottle.getText().toString());
                recycle.put("priceBottleScrape", priceBottleScrape.getText().toString());
                recycle.put("glassSpecify", glassSpecify.getText().toString());
                recycle.put("priceotherGlass", priceotherGlass.getText().toString());
                recycle.saveInBackground();

            }
        });






        return v;

    }

}
