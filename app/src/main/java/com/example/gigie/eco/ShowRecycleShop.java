package com.example.gigie.eco;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
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

    ImageView imageTop;
    ImageView imageLeft;
    ImageView imageCenter;
    ImageView imageRight;




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

        imageTop = (ImageView) v.findViewById(R.id.imageButton4);
        imageLeft = (ImageView) v.findViewById(R.id.imageButton3);
        imageCenter = (ImageView) v.findViewById(R.id.imageButton);
        imageRight = (ImageView) v.findViewById(R.id.imageButton2);


        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Recycle");
        query2.whereEqualTo("shopID",sID);
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.i(">>>>>>>>>>>>>>>>>>>>>", "Retrieved " + scoreList.size() + " scores"); // Get List size
                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        priceOfficepaper.setText(dealsObject.get("PriceofficePaper").toString()  + " THB./kg.");
                        priceNewspaper.setText(dealsObject.get("Pricenewspaper").toString()  + " THB./kg.");
                        priceCardBoard.setText(dealsObject.get("Pricecardboard").toString()  + " THB./kg.");
                        priceHardboard.setText(dealsObject.get("PriceHardboard").toString()  + " THB./kg.");
                        paperSpecify.setText(dealsObject.get("paperSpecify").toString());
                        pricePaperOther.setText(dealsObject.get("PriceofficePaper").toString()  + " THB./kg.");
                        pricePet.setText(dealsObject.get("pricePet").toString() + " THB./kg.");
                        pricePP.setText(dealsObject.get("pricePP").toString()  + " THB./kg.");
                        pricePvc.setText(dealsObject.get("pricePvc").toString()  + " THB./kg.");
                        priceCable.setText(dealsObject.get("priceCable").toString()  + " THB./kg.");
                        plasticSpecify.setText(dealsObject.get("plasticSpecify").toString());
                        pricePlasticOther.setText(dealsObject.get("pricePlasticOther").toString() + " THB./kg.");
                        priceCan.setText(dealsObject.get("priceCable").toString() + " THB./kg.");
                        priceCopper.setText(dealsObject.get("priceCopper").toString() + " THB./kg.");
                        priceLead.setText(dealsObject.get("priceLead").toString() + " THB./kg.");
                        priceZinc.setText(dealsObject.get("priceZinc").toString() + " THB./kg.");
                        priceIron.setText(dealsObject.get("priceIron").toString() + " THB./kg.");
                        MetalSpecify.setText(dealsObject.get("MetalSpecify").toString());
                        priceMetalOther.setText(dealsObject.get("priceMetalOther").toString() + " THB./kg.");
                        priceBeerBottle.setText(dealsObject.get("priceBeerBottle").toString() + " THB./kg.");
                        priceBottleScrape.setText(dealsObject.get("priceBottleScrape").toString() + " THB./kg.");
                        glassSpecify.setText(dealsObject.get("glassSpecify").toString());
                        priceotherGlass.setText(dealsObject.get("priceotherGlass").toString() + " THB./kg.");


                        ParseObject parseObject = new ParseObject("Freecycle");
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
                                if (e == null) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    imageLeft.setImageBitmap(bmp);
                                } else {

                                }
                            }
                        });

                        ParseFile fileObject3 = (ParseFile) dealsObject.getParseFile("ImageFileCenter");
                        fileObject3.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (e == null) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    imageCenter.setImageBitmap(bmp);
                                } else {

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
