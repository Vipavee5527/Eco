package com.example.gigie.eco;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by gigie on 8/31/15 AD.
 */
public class ProfileShopFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profileshop,null,false);
        int[] array_res = getImageArray(R.array.my_image_array, R.mipmap.ic_launcher);
        String[] array_string = getStringArray(R.array.my_string_array);

        ListView listView = (ListView)v.findViewById(R.id.shop_profile);
        listView.setAdapter(new CustomListviewAdapter(v.getContext()
                , android.R.id.text1, array_string, array_res));
        return v;
    }

    public int[] getImageArray(int resId, int defResId) {
        TypedArray my_image_array = getResources().obtainTypedArray(resId);
        int[] array_res = new int[my_image_array.length()];
        for(int i = 0 ; i < array_res.length ; i++)
            array_res[i] = my_image_array.getResourceId(i, defResId);
        my_image_array.recycle();
        return array_res;
    }

    public String[] getStringArray(int resId) {
        TypedArray my_string_array = getResources().obtainTypedArray(resId);
        String[] array_string = new String[my_string_array.length()];
        for(int i = 0 ; i < array_string.length ; i++)
            array_string[i] = my_string_array.getString(i);
        my_string_array.recycle();
        return array_string;
    }
}


