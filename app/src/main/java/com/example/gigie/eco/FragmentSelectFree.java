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
public class FragmentSelectFree extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.freecyclecat, null, false);


        ImageButton chair = (ImageButton) v.findViewById(R.id.btn_chair);
        chair.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FreeCycleFragment freeCycleFragment = new FreeCycleFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, freeCycleFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton table = (ImageButton)v.findViewById(R.id.btn_table);
        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeCycleFragment freeCycleFragment = new FreeCycleFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, freeCycleFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        ImageButton bed = (ImageButton)v.findViewById(R.id.btn_bed);
        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeCycleFragment freeCycleFragment = new FreeCycleFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, freeCycleFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton book = (ImageButton)v.findViewById(R.id.btn_book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeCycleFragment freeCycleFragment = new FreeCycleFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, freeCycleFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton clothes = (ImageButton)v.findViewById(R.id.btn_clothes);
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeCycleFragment freeCycleFragment = new FreeCycleFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, freeCycleFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton other = (ImageButton)v.findViewById(R.id.btn_other);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeCycleFragment freeCycleFragment = new FreeCycleFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, freeCycleFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return v;
    }

}
