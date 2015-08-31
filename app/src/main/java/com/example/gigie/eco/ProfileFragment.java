package com.example.gigie.eco;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by gigie on 8/31/15 AD.
 */
public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_profile, null, false);

        Button shop = (Button) v.findViewById(R.id.shop);
        Button favorite = (Button) v.findViewById(R.id.favorite);
        Button request = (Button) v.findViewById(R.id.request);

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add("Test");
                arrayList.add("Test2");
                arrayList.add("Test3");
                arrayList.add("Test4");
                arrayList.add("Test5");
                arrayList.add("Test6");
                arrayList.add("Test7");
                arrayList.add("Test8");
                arrayList.add("Test9");
                arrayList.add("Test10");


                ListView listView = (ListView) v.findViewById(R.id.list_profile);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (getActivity(),android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(adapter);
                /*
                int[] array_res = getImageArray(R.array.my_image_array, R.mipmap.ic_launcher);

                String[] array_string = getStringArray(R.array.my_string_array);

                listView.setAdapter(new CustomListviewAdapter(v.getContext()
                        , android.R.layout.simple_expandable_list_item_1, array_string, array_res));
*/
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

    public int[] getImageArray(int resId, int defResId) {
        TypedArray my_image_array = getResources().obtainTypedArray(resId);
        int[] array_res = new int[my_image_array.length()];
        for (int i = 0; i < array_res.length; i++)
            array_res[i] = my_image_array.getResourceId(i, defResId);
        my_image_array.recycle();
        return array_res;
    }

    public String[] getStringArray(int resId) {
        TypedArray my_string_array = getResources().obtainTypedArray(resId);
        String[] array_string = new String[my_string_array.length()];
        for (int i = 0; i < array_string.length; i++)
            array_string[i] = my_string_array.getString(i);
        my_string_array.recycle();
        return array_string;
    }
}
