package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by admin on 9/3/2015.
 */
public class RecycleShopFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_recycleshop, null, false);


        Button selectcatRecycle = (Button) v.findViewById(R.id.btn_cat_recycle);
        selectcatRecycle.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                FragmentSelectRecycle fragmentSelectRecycle = new FragmentSelectRecycle();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentSelectRecycle);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return v;

    }


}
