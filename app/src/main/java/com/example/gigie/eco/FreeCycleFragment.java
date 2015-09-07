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
public class FreeCycleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_freecycle, null, false);


                Button selectcatFree = (Button) v.findViewById(R.id.btn_cat_free);
        selectcatFree.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                FragmentSelectFree fragmentSelectFree = new FragmentSelectFree();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentSelectFree);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return v;

    }
}
