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
public class FoodScrapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_foodscrap, null, false);


        Button selectcatFood = (Button) v.findViewById(R.id.btn_cat_food);
        selectcatFood.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                FragmentSelectFood fragmentSelectFood = new FragmentSelectFood();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentSelectFood);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return v;

    }
}
