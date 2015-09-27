package com.example.gigie.eco;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.ProfilePictureView;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

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

    boolean doubleBackToExitPressedOnce = false;
    private ProfilePictureView userProfilePictureView;
    private TextView userNameView;
    private Dialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, null, false);

        final ImageButton shop = (ImageButton) v.findViewById(R.id.shop);
        //ImageButton favorite = (ImageButton) v.findViewById(R.id.favorite);
        ImageButton request = (ImageButton) v.findViewById(R.id.request);
        final ListView listView = (ListView) v.findViewById(R.id.list_profile);

        userProfilePictureView = (ProfilePictureView) v.findViewById(R.id.userProfilePicture);
        userNameView = (TextView) v.findViewById(R.id.displayname);


        //Fetch Facebook user info if it is logged
        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null) && currentUser.isAuthenticated()) {
            makeMeRequest();
        }


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
                    final int[] sID = new int[scoreList.size()];

                    int i = 0;

                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        sID[i] = (int) dealsObject.get("shopID");
                        shopname[i] = (String) dealsObject.get("shopName");
                        type[i] = (String) dealsObject.get("type");
                        ParseFile im = (ParseFile) dealsObject.getParseFile("image");

                        displayImage(im);


                        Log.i(">>>>>>>", shopname[i]);

                        i++;
                    }

                    CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), shopname, type);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                            ShowRecycleShop showRecycleShop = new ShowRecycleShop();
//                            FragmentManager fm = getFragmentManager();
//                            FragmentTransaction transaction = fm.beginTransaction();
//                            Bundle args = new Bundle();
//                            args.putInt("sID", sID[position]);
//                            showRecycleShop.setArguments(args);
//                            transaction.replace(R.id.fragment_container, showRecycleShop);
//                            transaction.addToBackStack(null);
//                            transaction.commit();

                            if(type[position].equals("recycle")) {
                                ShowRecycleShop showRecycleShop = new ShowRecycleShop();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", sID[position]);
                                showRecycleShop.setArguments(args);
                                transaction.replace(R.id.fragment_container, showRecycleShop);
                                transaction.addToBackStack(null);
                                transaction.commit();

                            }

                            if(type[position].equals("foodscrape")){
                                ShowFoodscrape showFoodscrape = new ShowFoodscrape();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", sID[position]);
                                showFoodscrape.setArguments(args);
                                transaction.replace(R.id.fragment_container, showFoodscrape);
                                transaction.addToBackStack(null);
                                transaction.commit();

                            }

                            if(type[position].equals("landfill")){
                                ShowLandfill showLandfill = new ShowLandfill();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", sID[position]);
                                showLandfill.setArguments(args);
                                transaction.replace(R.id.fragment_container, showLandfill);
                                transaction.addToBackStack(null);
                                transaction.commit();

                            }
                            if(type[position].equals("freecycle")){
                                ShowFreecycle showFreecycle = new ShowFreecycle();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", sID[position]);
                                showFreecycle.setArguments(args);
                                transaction.replace(R.id.fragment_container, showFreecycle);
                                transaction.addToBackStack(null);
                                transaction.commit();

                            }

                        }
                    });
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

                    CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), title, description);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            ShowRequest showRequest = new ShowRequest();
                            FragmentManager fm = getFragmentManager();
                            FragmentTransaction transaction = fm.beginTransaction();
                            Bundle args = new Bundle();
                            //args.putInt("rID", rID[position]);
                            showRequest.setArguments(args);
                            transaction.replace(R.id.fragment_container, showRequest);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    });
                } else {
                    Log.i("score", "Error: " + e.getMessage());
                }
            }
        });


        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), shopname, type);
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
                CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), title, description);
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

    @Override
    public void onResume() {
        super.onResume();

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // Check if the user is currently logged
            // and show any cached content
            updateViewsWithProfileInfo();
        } else {
            // If the user is not logged in, go to the
            // activity showing the login view.
            startLoginActivity();
        }
    }

    private void makeMeRequest() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        if (jsonObject != null) {
                            JSONObject userProfile = new JSONObject();

                            try {
                                userProfile.put("facebookId", jsonObject.getLong("id"));
                                userProfile.put("name", jsonObject.getString("name"));


                                // Save the user profile info in a user property
                                ParseUser currentUser = ParseUser.getCurrentUser();
                                currentUser.put("profile", userProfile);
                                currentUser.saveInBackground();
                                currentUser.put("name", userProfile.get("name"));
                                currentUser.saveInBackground();

                                // Show the user info
                                updateViewsWithProfileInfo();
                            } catch (JSONException e) {
                                Log.d(EcoSpot.TAG,
                                        "Error parsing returned user data. " + e);
                            }
                        } else if (graphResponse.getError() != null) {
                            switch (graphResponse.getError().getCategory()) {
                                case LOGIN_RECOVERABLE:
                                    Log.d(EcoSpot.TAG,
                                            "Authentication error: " + graphResponse.getError());
                                    break;

                                case TRANSIENT:
                                    Log.d(EcoSpot.TAG,
                                            "Transient error. Try again. " + graphResponse.getError());
                                    break;

                                case OTHER:
                                    Log.d(EcoSpot.TAG,
                                            "Some other error: " + graphResponse.getError());
                                    break;
                            }
                        }
                    }
                });

        request.executeAsync();
    }

    private void updateViewsWithProfileInfo() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser.has("profile")) {
            JSONObject userProfile = currentUser.getJSONObject("profile");
            try {

                if (userProfile.has("facebookId")) {
                    userProfilePictureView.setProfileId(userProfile.getString("facebookId"));
                } else {
                    // Show the default, blank user profile picture
                    userProfilePictureView.setProfileId(null);
                }

                if (userProfile.has("name")) {
                    userNameView.setText(userProfile.getString("name"));
                } else {
                    userNameView.setText("");
                }

            } catch (JSONException e) {
                Log.d(EcoSpot.TAG, "Error parsing saved user data.");
            }
        }
    }


    public void onLogoutClick(View v) {
        logout();
    }

    private void logout() {
        // Log the user out
        ParseUser.logOut();

        // Go to the login view
        startLoginActivity();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}

