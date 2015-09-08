package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by gigie on 9/7/15 AD.
 */
public class FragmentSelectFood  extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.foodscrapcat, null, false);

        ImageButton coffee = (ImageButton) v.findViewById(R.id.btn_coffee);
        coffee.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CoffeeFragment coffeeFragment = new CoffeeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, coffeeFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton foodscrape = (ImageButton) v.findViewById(R.id.btn_foodscrapes);
        foodscrape.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FoodSFragment foodSFragment = new FoodSFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, foodSFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton cookingoil = (ImageButton) v.findViewById(R.id.btn_cookingoil);
        cookingoil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CookingFragment cookingFragment = new CookingFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, cookingFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton foodother = (ImageButton) v.findViewById(R.id.btn_foodother);
        foodother.setOnClickListener(new View.OnClickListener() {

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
