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
public class FragmentSelectRecycle extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recyclewastecat, null, false);

        ImageButton paper = (ImageButton) v.findViewById(R.id.btn_paper);
        paper.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PaperFragment paperFragment = new PaperFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, paperFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton plastic = (ImageButton) v.findViewById(R.id.btn_plastic);
        plastic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PlasticFragment plasticFragment = new PlasticFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, plasticFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton metal = (ImageButton) v.findViewById(R.id.btn_metal);
        metal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MetalFragment metalFragment = new MetalFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, metalFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton glass = (ImageButton) v.findViewById(R.id.btn_glass);
        glass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GlassFragment glassFragment = new GlassFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, glassFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton others = (ImageButton) v.findViewById(R.id.btn_others);
        others.setOnClickListener(new View.OnClickListener() {

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
