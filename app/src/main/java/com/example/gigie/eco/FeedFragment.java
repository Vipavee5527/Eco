package com.example.gigie.eco;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Chayut Upatham on 9/5/2015.
 */
public class FeedFragment extends Fragment {


    String[] username = {"User 1", "User 2", "User 3", "User 4", "User 5", "User 6", "User 7", "User 8"};
    String[] comment = {"Comment 1 Comment 1 Comment 1 Comment 1 Comment 1 Comment 1 Comment 1 Comment 1 Comment 1 Comment 1",
            "Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2",
            "Comment 3 Comment 3 Comment 3 Comment 3 Comment 3 Comment 3",
            "Comment 4 Comment 4 Comment 4 Comment 4 Comment 4 Comment 4",
            "Comment 1 Comment 1 Comment 1 Comment 1 Comment 1 Comment 1 Comment 1 Comment 1 Comment 1 Comment 1",
            "Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2 Comment 2",
            "Comment 3 Comment 3 Comment 3 Comment 3 Comment 3 Comment 3",
            "Comment 4 Comment 4 Comment 4 Comment 4 Comment 4 Comment 4"};
    String[] imageId = {
            "R.drawable.com_facebook_profile_picture_blank_square",
            "R.drawable.com_facebook_profile_picture_blank_square",
            "R.drawable.com_facebook_profile_picture_blank_square",
            "R.drawable.com_facebook_profile_picture_blank_square",
            "R.drawable.com_facebook_profile_picture_blank_square",
            "R.drawable.com_facebook_profile_picture_blank_square",
            "R.drawable.com_facebook_profile_picture_blank_square",
            "R.drawable.com_facebook_profile_picture_blank_square"
    };

    Integer[] pic = {0,R.drawable.cast_ic_notification_0, R.drawable.bigsofaforu};



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_feed, null, false);
        

        //        >>>SELECT by condition
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Feed");
       // query.whereEqualTo("User","User1"); // WHERE
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.i(">>>>>>>>>>>>>>>>>>>>>", "Retrieved " + scoreList.size() + " scores"); // Get List size
                    username = new String[scoreList.size()];
                    comment  = new String[scoreList.size()];
                    imageId  = new String[scoreList.size()];

                    int i = 0;

                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.

                        username[i] = (String) dealsObject.get("User");
                        comment[i]  = (String) dealsObject.get("Message");

                        Log.i(">>>>>>>", comment[i]);

                        i++;
                    }

                    if(getActivity() == null){
                        Log.d("love","get activity null");
                    } else{
                        Log.d("love","get activity not null");
                    }

                    NewsfeedAdapter newsfeedAdapter = new NewsfeedAdapter(getActivity(), username, comment, pic);
                    ListView listView = (ListView) v.findViewById(R.id.list_feed);
                    listView.setAdapter(newsfeedAdapter);
                } else {
                    Log.i("score", "Error: " + e.getMessage());
                }
            }
        });

        return v;
    }
}
