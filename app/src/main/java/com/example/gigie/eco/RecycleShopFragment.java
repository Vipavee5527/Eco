package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by admin on 9/3/2015.
 */
public class RecycleShopFragment extends Fragment {


    private String dataGotFromServer;
    EditText shopName;
    private GoogleMap mMap;
    ScrollView mScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.create_recycleshop, null, true);

        mScrollView = (ScrollView) v.findViewById(R.id.scrollview_recycle);
        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_pick)).getMap();
        mMap.setMyLocationEnabled(true);



        if (mMap == null) {
            mMap = ((WorkaroundMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map_pick)).getMap();
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mScrollView = (ScrollView) v.findViewById(R.id.scrollview_recycle); //parent scrollview in xml, give your scrollview id value

            ((WorkaroundMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map_pick))
                    .setListener(new WorkaroundMapFragment.OnTouchListener() {
                        @Override
                        public void onTouch() {
                            mScrollView.requestDisallowInterceptTouchEvent(true);
                        }
                    });

        }



        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));
            }

        });





        //final EditText address = (EditText) findViewById(R.id.displayname);
        // final EditText telephone = (EditText) findViewById(R.id.mail);
        // final EditText nearby = (EditText) findViewById(R.id.username);
        // final EditText category = (EditText) findViewById(R.id.displayname);
        // final EditText  = (EditText) findViewById(R.id.mail);


        //dataGotFromServer = ((EditText) v.findViewById(R.id.shopName)).getText().toString();

        shopName = (EditText) v.findViewById(R.id.shopName);

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

        return v;

    }

}
