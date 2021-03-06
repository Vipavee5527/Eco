package com.example.gigie.eco;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
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
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    ImageButton imageTop;
    ImageButton imageLeft;
    ImageButton imageCenter;
    ImageButton imageRight;

    private Uri tmpUri;
    private Uri tmpUri1;
    private Uri tmpUri2;
    private Uri tmpUri3;

    EditText minCoffee;
    EditText priceCoffee;
    EditText minFoodScrape;
    EditText priceFoodScrape;
    EditText minCookingOil;
    EditText priceCookingOil;
    EditText minFoodScrapeOther;
    EditText priceFoodScrapeOther;

    private int PICK_IMAGE_REQUEST = 1;
    public static final int REQUEST_GALLERY = 1;
    public static final int REQUEST_GALLERY1 = 2;
    public static final int REQUEST_GALLERY2 = 3;
    public static final int REQUEST_GALLERY3 = 4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_foodscrap, null, false);

        mScrollView = (ScrollView) v.findViewById(R.id.scrollview_foodscrape);
        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_pick)).getMap();
       // mMap.setMyLocationEnabled(true);
        setUpMap();

        imageTop = (ImageButton) v.findViewById(R.id.imageButton4);
        imageLeft = (ImageButton) v.findViewById(R.id.imageButton3);
        imageCenter = (ImageButton) v.findViewById(R.id.imageButton);
        imageRight = (ImageButton) v.findViewById(R.id.imageButton2);


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


        imageTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                // Locate the image in res > drawable-hdpi
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_pause_light);
                // Convert it to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Compress image to lower quality scale 1 - 100
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] image = stream.toByteArray();

                // Create the ParseFile
                ParseFile file = new ParseFile("androidbegin.png", image);
                // Upload the image into Parse Cloud
                file.saveInBackground();

                // Create a New Class called "ImageUpload" in Parse
                ParseObject imgupload = new ParseObject("ImageUpload");

                // Create a column named "ImageName" and set the string
                imgupload.put("ImageName", "AndroidBegin Logo");

                // Create a column named "ImageFile" and insert the image
                imgupload.put("ImageFile", file);

                // Create the class and the columns
                imgupload.saveInBackground();
                */
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent
                        , "Select photo from"), REQUEST_GALLERY);
            }
        });

        imageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent
                        , "Select photo from"), REQUEST_GALLERY1);
            }
        });


        imageCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent
                        , "Select photo from"), REQUEST_GALLERY2);
            }
        });

        imageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent
                        , "Select photo from"), REQUEST_GALLERY3);
            }
        });

        final Marker[] marker = new Marker[1];
        double lat = 13.652493;
        double lng = 100.493719;
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

                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                // Compress image to lower quality scale 1 - 100
                                try {
                                    decodeUri(tmpUri).compress(Bitmap.CompressFormat.PNG, 100, stream);
                                } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                                byte[] image = stream.toByteArray();

                                ParseFile file = new ParseFile("TEST.png", image);
                                // Upload the image into Parse Cloud
                                file.saveInBackground();

                                foodscrape.put("ImageFileTop", file);


                                ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                                // Compress image to lower quality scale 1 - 100
                                try {
                                    decodeUri(tmpUri1).compress(Bitmap.CompressFormat.PNG, 100, stream1);
                                } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                                byte[] image1 = stream1.toByteArray();

                                ParseFile file1 = new ParseFile("TEST1.png", image1);
                                // Upload the image into Parse Cloud
                                file1.saveInBackground();

                                // Create a column named "ImageFile" and insert the image
                                foodscrape.put("ImageFileLeft", file1);


                                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                                // Compress image to lower quality scale 1 - 100
                                try {
                                    decodeUri(tmpUri2).compress(Bitmap.CompressFormat.PNG, 100, stream2);
                                } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                                byte[] image2 = stream2.toByteArray();

                                ParseFile file2 = new ParseFile("TEST2.png", image2);
                                // Upload the image into Parse Cloud
                                file2.saveInBackground();

                                // Create a column named "ImageFile" and insert the image
                                foodscrape.put("ImageFileCenter", file2);



                                ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                                // Compress image to lower quality scale 1 - 100
                                try {
                                    decodeUri(tmpUri3).compress(Bitmap.CompressFormat.PNG, 100, stream3);
                                } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                                byte[] image3 = stream3.toByteArray();

                                ParseFile file3 = new ParseFile("TEST3.png", image3);
                                // Upload the image into Parse Cloud
                                file3.saveInBackground();

                                // Create a column named "ImageFile" and insert the image
                                foodscrape.put("ImageFileRight", file3);


                                foodscrape.saveInBackground();
                                ShowFoodscrape showFoodscrape = new ShowFoodscrape();
                                FragmentManager fm = getFragmentManager();
                                try{ Thread.sleep(10000); }catch(InterruptedException el){ }
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("image", "requestCode : " + requestCode + " resultCode : " + resultCode);

        if (requestCode == REQUEST_GALLERY) {
            if (data == null)
                Log.e("dataNull", "null 1");
            else
                Log.e("dataNull", "not null 1");
            Uri uri = data.getData();
            tmpUri = uri;
            try {
//                bitmap1 = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), uri);
//
//                imageTop.setImageBitmap(bitmap1);
                imageTop.setImageBitmap(decodeUri2(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_GALLERY1) {
            if (data == null)
                Log.e("dataNull", "null 2");
            else
                Log.e("dataNull", "not null 2");
            Uri uri = data.getData();
            tmpUri1 = uri;
            try {
                //bitmap2 = Media.getBitmap(this.getActivity().getContentResolver(), uri);
                //imageLeft.setImageBitmap(bitmap2);
                imageLeft.setImageBitmap(decodeUri(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_GALLERY2) {
            if (data == null)
                Log.e("dataNull", "null 3");
            else
                Log.e("dataNull", "not null 3");
            Uri uri = data.getData();
            tmpUri2 = uri;

            try {
                //bitmap3 = Media.getBitmap(this.getActivity().getContentResolver(), uri);
                //imageCenter.setImageBitmap(bitmap3);
                imageCenter.setImageBitmap(decodeUri(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_GALLERY3) {
            if (data == null)
                Log.e("dataNull", "null 4");
            else
                Log.e("dataNull", "not null 4");
            Uri uri = data.getData();
            tmpUri3 = uri;
            try {
                //bitmap4 = Media.getBitmap(this.getActivity().getContentResolver(), uri);


                //imageRight.setImageBitmap(bitmap4);
                imageRight.setImageBitmap(decodeUri(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private Bitmap decodeUri(Uri uri) throws FileNotFoundException {

        //decode image
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(
                getActivity().getContentResolver().openInputStream(uri), null, o);

        // the size we want to scale to
        final int REQUIRED_SIZE = 100;

        //find the correct scale value. It should be the power of 2

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        //decode with inSampleSize

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(
                getActivity().getContentResolver().openInputStream(uri), null, o2);
    }

    private Bitmap decodeUri2(Uri uri) throws FileNotFoundException {

        //decode image
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(
                getActivity().getContentResolver().openInputStream(uri), null, o);

        // the size we want to scale to
        final int REQUIRED_SIZE = 500;

        //find the correct scale value. It should be the power of 2

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        //decode with inSampleSize

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(
                getActivity().getContentResolver().openInputStream(uri), null, o2);
    }

    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            //mMap.addMarker(new MarkerOptions().position(loc).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_recycle)));
            if (mMap != null) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
            }
        }
    };

}
