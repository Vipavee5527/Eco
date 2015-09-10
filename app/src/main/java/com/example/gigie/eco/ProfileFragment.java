package com.example.gigie.eco;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by gigie on 8/31/15 AD.
 */
public class ProfileFragment extends Fragment {

    String[] shopname = {"Dummy 1", "Dummy2", "Dummy 3", "Dummy 4", "Dummy 5", "Dummy 6"};

    String[] type = {"Dummy 1-2", "Dummy2-2", "Dummy 3-2", "Dummy 4-2", "Dummy 5-2", "Dummy 6-2"};

    Integer[] image = {
            R.drawable.cast_ic_notification_2,
            R.drawable.common_full_open_on_phone

    };

    String[] title = {"Dummy 1", "Dummy2", "Dummy 3", "Dummy 4", "Dummy 5", "Dummy 6"};

    String[] description = {"Dummy 1-2", "Dummy2-2", "Dummy 3-2", "Dummy 4-2", "Dummy 5-2", "Dummy 6-2"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, null, false);

        final ImageButton shop = (ImageButton) v.findViewById(R.id.shop);
        //ImageButton favorite = (ImageButton) v.findViewById(R.id.favorite);
        ImageButton request = (ImageButton) v.findViewById(R.id.request);
        final ListView listView = (ListView) v.findViewById(R.id.list_profile);

//        >>>SELECT by condition
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
        // query.whereEqualTo("User","User1"); // WHERE
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.i(">>>>>>>>>>>>>>>>>>>>>", "Retrieved " + scoreList.size() + " scores"); // Get List size
                    shopname = new String[scoreList.size()];
                    type = new String[scoreList.size()];
                    image = new Integer[scoreList.size()];

                    int i = 0;

                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.

                        shopname[i] = (String) dealsObject.get("shopName");
                        type[i] = (String) dealsObject.get("type");
                        ParseFile im = (ParseFile) dealsObject.getParseFile("image");

                        displayImage(im);


                        Log.i(">>>>>>>", shopname[i]);

                        i++;
                    }

                    CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), shopname, type, image);
                    listView.setAdapter(adapter);
                } else {
                    Log.i("score", "Error: " + e.getMessage());
                }
            }
        });





//        >>>SELECT by condition
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Request");
        // query.whereEqualTo("User","User1"); // WHERE
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.i(">>>>>>>>>>>>>>>>>>>>>", "Retrieved " + scoreList.size() + " scores"); // Get List size
                    title = new String[scoreList.size()];
                    description = new String[scoreList.size()];
                    image = new Integer[scoreList.size()];

                    int i = 0;

                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.

                        title[i] = (String) dealsObject.get("title");
                        description[i] = (String) dealsObject.get("description");
                        ParseFile im = (ParseFile) dealsObject.getParseFile("image");

                        displayImage(im);


                        Log.i(">>>>>>>", shopname[i]);

                        i++;
                    }

                    CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), shopname, type, image);
                    listView.setAdapter(adapter);
                } else {
                    Log.i("score", "Error: " + e.getMessage());
                }
            }
        });



        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), shopname, type, image);
                listView.setAdapter(adapter);
            }
        });


//        favorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), shopname, type, image);
//                listView.setAdapter(adapter);
//            }
//
//        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), title, description, image);
                listView.setAdapter(adapter);
            }
        });


        return v;
    }


    private void displayImage(ParseFile thumbnail) {

        if (thumbnail != null) {
            thumbnail.getDataInBackground(new GetDataCallback() {

                @Override
                public void done(byte[] data, ParseException e) {

                    if (e == null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);

                        if (bmp != null) {

                            Log.e("parse file ok", " null");
                            // img.setImageBitmap(Bitmap.createScaledBitmap(bmp,
                            // (display.getWidth() / 5),
                            // (display.getWidth() /50), false));
                            //img.setImageBitmap(new ImageHelper().getRoundedCornerBitmap(bmp, 10));
                            // img.setPadding(10, 10, 0, 0);

                        }
                    } else {
                        Log.e("paser after downloade", " null");
                    }

                }
            });
        } else {

            Log.e("parse file", " null");

            // img.setImageResource(R.drawable.ic_launcher);

            //img.setPadding(10, 10, 10, 10);
        }

    }

}
