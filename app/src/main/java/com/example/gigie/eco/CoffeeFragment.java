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
public class CoffeeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.catcoffee, null, false);
        Button coffee = (Button) v.findViewById(R.id.btn_savecoffee);
        coffee.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FoodScrapFragment foodScrapFragment = new FoodScrapFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, foodScrapFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }
}
