package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by gigie on 9/15/15 AD.
 */
public class ShowRequest extends Fragment {

    TextView title;
    TextView description;
    TextView telephone;
    TextView contactname;
    TextView category;
    TextView specify;
    TextView startDate;
    TextView endDate;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.showreq, null, true);

        title = (TextView) v.findViewById(R.id.title);
        description = (TextView) v.findViewById(R.id.tdescrip);
        telephone = (TextView) v.findViewById(R.id.number);
        contactname = (TextView) v.findViewById(R.id.connumber);
        category = (TextView) v.findViewById(R.id.cat_description);
        specify = (TextView) v.findViewById(R.id.charity_description);
        startDate = (TextView) v.findViewById(R.id.startDate);
        endDate = (TextView) v.findViewById(R.id.finishDate);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Request");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.i("Shop", "Retrieved " + scoreList.size() + " scores"); // Get List size
                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        title.setText(dealsObject.get("title").toString());
                        description.setText(dealsObject.get("description").toString());
                        telephone.setText(dealsObject.get("telephone").toString());
                        contactname.setText(dealsObject.get("contactname").toString());
                        category.setText(dealsObject.get("category").toString());
                        specify.setText(dealsObject.get("specify").toString());
                        startDate.setText(dealsObject.get("startDate").toString());
                        endDate.setText(dealsObject.get("endDate").toString());
                    }

                } else {
                    Log.i("score", "Error: " + e.getMessage());
                }
            }
        });

        return v;

    }
}
