package com.example.gigie.eco;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 9/3/2015.
 */
public class FreeCycleFragment extends Fragment {

    private GoogleMap mMap;
    ScrollView mScrollView;

    ImageButton imageTop;
    ImageButton imageLeft;
    ImageButton imageCenter;
    ImageButton imageRight;


    EditText shopName;
    EditText description;
    EditText address;
    EditText telephone;
    EditText landmark;
    RadioButton chair;
    RadioButton table;
    RadioButton bed;
    RadioButton book;
    RadioButton clothes;
    RadioButton others;
    EditText startDate;
    EditText endDate;


    RadioGroup rg_category;

    private int PICK_IMAGE_REQUEST = 1;
    public static final int REQUEST_GALLERY = 1;
    public static final int REQUEST_GALLERY1 = 2;
    public static final int REQUEST_GALLERY2 = 3;
    public static final int REQUEST_GALLERY3 = 4;


    Bitmap bitmap1;
    Bitmap bitmap2;
    Bitmap bitmap3;
    Bitmap bitmap4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_freecycle, null, false);

        imageTop = (ImageButton) v.findViewById(R.id.imageButton4);
        imageLeft = (ImageButton) v.findViewById(R.id.imageButton3);
        imageCenter = (ImageButton) v.findViewById(R.id.imageButton);
        imageRight = (ImageButton) v.findViewById(R.id.imageButton2);

        mScrollView = (ScrollView) v.findViewById(R.id.scrollview_freecycle);
        mMap = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_pick)).getMap();
        mMap.setMyLocationEnabled(true);

        chair = (RadioButton) v.findViewById(R.id.radio_chair);

        shopName = (EditText) v.findViewById(R.id.itemName);
        description = (EditText) v.findViewById(R.id.freecycle_description);
        address = (EditText) v.findViewById(R.id.freecycle_address);
        telephone = (EditText) v.findViewById(R.id.telephone);
        landmark = (EditText) v.findViewById(R.id.nearbylandmark);


        startDate = (EditText) v.findViewById(R.id.startDate);
        endDate = (EditText) v.findViewById(R.id.finishDate);

        rg_category = (RadioGroup) v.findViewById(R.id.radioGroup_cat);

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

        double lat = 0.1;
        double lng = 0.1;
        mMap.clear();
        marker[0] = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle)));

        rg_category.check(chair.getId());

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.clear();
                marker[0] = mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle)));
            }

        });

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                double lat = mMap.getMyLocation().getLatitude();
                double lng = mMap.getMyLocation().getLongitude();
                mMap.clear();
                marker[0] = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_freecycle)));
                return false;
            }
        });

        Button btn_donefreecycle = (Button) v.findViewById(R.id.btn_donefreecycle);
        btn_donefreecycle.setOnClickListener(new View.OnClickListener() {
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
                                ParseObject freecycle = new ParseObject("Freecycle");
                                ParseObject shop = new ParseObject("Shop");

                                int[] tmp = new int[1];
                                tmp[0] = (int) dealsObject.get("shopID");
                                Log.i(">>>>>>>", "" + tmp[0]);
                                tmp[0]++;

                                shop.put("shopID", tmp[0]);
                                freecycle.put("shopID", tmp[0]);

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

                                shop.put("type", "freecycle");

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
                                str = startDate.getText().toString();
                                if (!str.equals("")) {
                                    freecycle.put("startDate", startDate.getText().toString());
                                } else {
                                    freecycle.put("startDate", "-");
                                }

                                str = endDate.getText().toString();
                                if (!str.equals("")) {
                                    freecycle.put("endDate", endDate.getText().toString());
                                } else {
                                    freecycle.put("endDate", "-");
                                }


//                                RadioGroup rg1 = (RadioGroup) this.findViewById(R.id.);
                                if (rg_category.getCheckedRadioButtonId() != -1) {
                                    int id = rg_category.getCheckedRadioButtonId();
                                    View radioButton = rg_category.findViewById(id);
                                    int radioId = rg_category.indexOfChild(radioButton);
                                    RadioButton btn = (RadioButton) rg_category.getChildAt(radioId);
                                    String selection = (String) btn.getText();
                                    //Log.i("Radio >>>>>>>>>>", selection);
                                    freecycle.put("category", selection);
                                }


                                freecycle.saveInBackground();

                                ShowFreecycle showFreecycle = new ShowFreecycle();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", tmp[0]);
                                showFreecycle.setArguments(args);
                                transaction.replace(R.id.fragment_container, showFreecycle);
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
            try {
                bitmap1 = Media.getBitmap(this.getActivity().getContentResolver(), uri);

                imageTop.setImageBitmap(bitmap1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (requestCode == REQUEST_GALLERY1) {
            if (data == null)
                Log.e("dataNull", "null 2");
            else
                Log.e("dataNull", "not null 2");
            Uri uri = data.getData();
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


}
