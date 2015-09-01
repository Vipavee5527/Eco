package com.example.gigie.eco;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by gigie on 8/31/15 AD.
 */
public class ProfileFragment extends Fragment {

    String[] text = {"Dummy 1", "Dummy2", "Dummy 3", "Dummy 4", "Dummy 5", "Dummy 6"};

    String[] text2 = {"Dummy 1-2", "Dummy2-2", "Dummy 3-2", "Dummy 4-2", "Dummy 5-2", "Dummy 6-2"};

    Integer[] image = {
            R.drawable.cast_ic_notification_2,
            R.drawable.common_full_open_on_phone,
            R.drawable.cast_ic_notification_0,
            R.drawable.cast_ic_notification_1,
            R.drawable.common_signin_btn_text_dark,
            R.drawable.ic_cast_on_2_light
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, null, false);

        Button shop = (Button) v.findViewById(R.id.shop);
        Button favorite = (Button) v.findViewById(R.id.favorite);
        Button request = (Button) v.findViewById(R.id.request);
        final ListView listView = (ListView) v.findViewById(R.id.list_profile);

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), text, text2, image);
                listView.setAdapter(adapter);
            }
        });


        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return v;
    }

}
