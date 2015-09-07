package com.example.gigie.eco;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
    Integer[] imageId = {
            R.drawable.com_facebook_profile_picture_blank_square,
            R.drawable.com_facebook_profile_picture_blank_square,
            R.drawable.com_facebook_profile_picture_blank_square,
            R.drawable.com_facebook_profile_picture_blank_square,
            R.drawable.com_facebook_profile_picture_blank_square,
            R.drawable.com_facebook_profile_picture_blank_square,
            R.drawable.com_facebook_profile_picture_blank_square,
            R.drawable.com_facebook_profile_picture_blank_square,
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feed, null, false);

        ListView listView = (ListView) v.findViewById(R.id.list_feed);
        NewsfeedAdapter newsfeedAdapter = new NewsfeedAdapter(getActivity(), username, comment, imageId);

        listView.setAdapter(newsfeedAdapter);
        return v;
    }
}
