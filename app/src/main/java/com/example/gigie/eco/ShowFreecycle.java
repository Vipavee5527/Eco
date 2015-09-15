package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by gigie on 9/14/15 AD.
 */
public class ShowFreecycle extends Fragment {
    private GoogleMap mMap;
    int sID;

    TextView shopName;
    TextView description;
    TextView address;
    TextView telephone;
    TextView landmark;

    TextView startDate;
    TextView endDate;


    TextView category;
    ImageView img_category;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.showfreecycle, null, true);

        sID = getArguments().getInt("sID");

        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_show)).getMap();
        mMap.setMyLocationEnabled(true);

        shopName = (TextView) v.findViewById(R.id.show_itemname);
        description = (TextView) v.findViewById(R.id.description);
        address = (TextView) v.findViewById(R.id.location);
        telephone = (TextView) v.findViewById(R.id.telephone);
        landmark = (TextView) v.findViewById(R.id.itemlocation);

        startDate = (TextView) v.findViewById(R.id.startDate);
        endDate = (TextView)v.findViewById(R.id.finishDate);

        img_category = (ImageView) v.findViewById(R.id.itemcat);
        category = (TextView) v.findViewById(R.id.text_cat);

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Freecycle");
        query2.whereEqualTo("shopID",sID);
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.i(">>>>>>>>>>>>>>>>>>>>>", "Retrieved " + scoreList.size() + " scores"); // Get List size
                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        startDate.setText(dealsObject.get("startDate").toString());
                        endDate.setText(dealsObject.get("endDate").toString());

                        String cat = dealsObject.get("category").toString();

                        if (cat.equals("Chair")) {
                            img_category.setImageResource(R.mipmap.chair);
                            category.setText(cat);
                        }

                        if (cat.equals("Table")) {
                            img_category.setImageResource(R.mipmap.table);
                            category.setText(cat);
                        }

                        if (cat.equals("Bed")) {
                            img_category.setImageResource(R.mipmap.bed);
                            category.setText(cat);
                        }

                        if (cat.equals("Book")) {
                            img_category.setImageResource(R.mipmap.book);
                            category.setText(cat);
                        }

                        if (cat.equals("Cloth")) {
                            img_category.setImageResource(R.mipmap.cloth);
                            category.setText(cat);
                        }

                        if (cat.equals("Others")) {
                            img_category.setImageResource(R.mipmap.freeother);
                            category.setText(cat);
                        }
                    }

                } else {
                    Log.i("score", "Error: " + e.getMessage());
                }
            }
        });





        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
        query.whereEqualTo("shopID", sID);
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
                                (double) dealsObject.get("lng"))).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle)));
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng((double) dealsObject.get("lat"), (double) dealsObject.get("lng")))      // Sets the center of the map to location user
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
