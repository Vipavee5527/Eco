package com.example.gigie.eco;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by gigie on 9/14/15 AD.
 */
public class ShowLandfill extends Fragment {
    private GoogleMap mMap;
    int sID;

    TextView shopName;
    TextView description;
    TextView address;
    TextView telephone;
    TextView landmark;

    TextView startDate;
    TextView endDate;
    CheckBox concrete;
    CheckBox sand;
    CheckBox stone;
    CheckBox other;
    TextView otherSpecify;
    TextView show_category;
    TextView areaSize;

    ImageView imageTop;
    ImageView imageLeft;
    ImageView imageCenter;
    ImageView imageRight;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.showlandfill, null, true);

        sID = getArguments().getInt("sID");

        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_show)).getMap();
        mMap.setMyLocationEnabled(true);

        shopName = (TextView) v.findViewById(R.id.show_shopName);
        description = (TextView) v.findViewById(R.id.description);
        address = (TextView) v.findViewById(R.id.location);
        telephone = (TextView) v.findViewById(R.id.show_telephone);
        landmark = (TextView) v.findViewById(R.id.nearby);
        startDate = (TextView) v.findViewById(R.id.startDate);
        endDate = (TextView) v.findViewById(R.id.finishDate);
        show_category = (TextView) v.findViewById(R.id.show_category);
        areaSize = (TextView)v.findViewById(R.id.AreaDropSizeShown);
//        CheckBox concrete;
//        CheckBox sand;
//        CheckBox stone;
//        CheckBox other;
       // otherSpecify = (TextView) v.findViewById(R.id.other_specify);

        imageTop = (ImageView) v.findViewById(R.id.imageButton4);
        imageLeft = (ImageView) v.findViewById(R.id.imageButton3);
        imageCenter = (ImageView) v.findViewById(R.id.imageButton);
        imageRight = (ImageView) v.findViewById(R.id.imageButton2);



        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Landfill");
        query2.whereEqualTo("shopID",sID);
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.i(">>>>>>>>>>>>>>>>>>>>>", "Retrieved " + scoreList.size() + " scores"); // Get List size
                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        startDate.setText(dealsObject.get("startDate").toString());
                        endDate.setText(dealsObject.get("endDate").toString());
                        areaSize.setText(dealsObject.get("areaSize").toString() + " square metres");

                        String show = "";

                        if ((boolean)dealsObject.get("concrete")) {
                            show += "Concrete, ";
                        }

                        if ((boolean)dealsObject.get("sand")) {
                            show += "Sand, ";
                        }

                        if ((boolean)dealsObject.get("stone")) {
                            show += "Stone";
                        }

                        if ((boolean)dealsObject.get("other")) {
                            show += ", " + dealsObject.get("otherSpecify").toString();
                        }
                        show_category.setText(show);


                        ParseObject parseObject = new ParseObject("Landfill");
                        ParseFile fileObject1 = (ParseFile) dealsObject.getParseFile("ImageFileTop");
                        fileObject1.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (e == null) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    imageTop.setImageBitmap(bmp);
                                } else {

                                }
                            }
                        });


                        ParseFile fileObject2 = (ParseFile) dealsObject.getParseFile("ImageFileLeft");
                        fileObject2.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(e == null){
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
                                    imageLeft.setImageBitmap(bmp);
                                }
                                else{

                                }
                            }
                        });

                        ParseFile fileObject3 = (ParseFile) dealsObject.getParseFile("ImageFileCenter");
                        fileObject3.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(e == null){
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
                                    imageCenter.setImageBitmap(bmp);
                                }
                                else{

                                }
                            }
                        });


                        ParseFile fileObject4 = (ParseFile) dealsObject.getParseFile("ImageFileRight");
                        fileObject4.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (e == null) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    imageRight.setImageBitmap(bmp);
                                } else {

                                }
                            }
                        });
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
                                (double) dealsObject.get("lng"))).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_constructor)));
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

