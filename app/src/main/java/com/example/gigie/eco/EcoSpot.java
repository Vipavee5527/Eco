package com.example.gigie.eco;

import android.app.Application;
import android.content.Intent;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

/**
 * Created by gigie on 9/5/15 AD.
 */
public class EcoSpot extends Application {


    static final String TAG = "MyApp";
    @Override

    public void onCreate() {
        super.onCreate();


        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "3JVUVxK7YwlophTfcpYOCZcxuh4VG9zmTl1wi2r2", "sFBwfAWFhvYgoMPaM5sYCzGlLQAB1Jbh8SfEu0HY");

        ParseFacebookUtils.initialize(getApplicationContext());


        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            startActivity(new Intent(EcoSpot.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        } else {
            // show the signup or login screen

            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClass(getApplicationContext(), LoginActivity.class);

        }
    }
}
