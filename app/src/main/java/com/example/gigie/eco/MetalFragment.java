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
 * Created by gigie on 9/7/15 AD.
 */
public class MetalFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.catmetal, null, false);
        Button metal = (Button) v.findViewById(R.id.btn_savemetal);
        metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecycleShopFragment recycleShopFragment = new RecycleShopFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, recycleShopFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }
}
