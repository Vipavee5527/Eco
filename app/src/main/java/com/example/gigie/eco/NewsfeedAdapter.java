package com.example.gigie.eco;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gigie on 9/7/15 AD.
 */
public class NewsfeedAdapter extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] username;
    private final String[] comment;
    private final Integer[] imageId;

    public NewsfeedAdapter(Activity context, String[] username, String[] comment, Integer[] imageId) {
        super(context, R.layout.listview, username);
        this.context = context;
        this.username = username;
        this.comment = comment;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.newsfeed_customlist, null, true);

        TextView txt1 = (TextView) rowView.findViewById(R.id.text_user);
        TextView txt2 = (TextView) rowView.findViewById(R.id.text_comment);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.userPic);
        txt1.setText(username[position]);
        txt2.setText(comment[position]);

        // imageView.setImageResource(imageId[position]);

        return rowView;
    }
}
