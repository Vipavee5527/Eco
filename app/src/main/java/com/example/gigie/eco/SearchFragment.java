package com.example.gigie.eco;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by gigie on 8/31/15 AD.
 */
public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, null, false);

        ImageButton btn_search = (ImageButton) v.findViewById(R.id.btn_searchFragment);
        final EditText edit_search = (EditText) v.findViewById(R.id.edit_search);
        final ListView list_search = (ListView) v.findViewById(R.id.list_search);


        //search(edit_search, list_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(edit_search, list_search);
            }
        });
        return v;
    }

    public void search(EditText edit_search, final ListView list_search) {
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Shop");
        if (!edit_search.getText().toString().equals("")) {
            query2.whereMatches("shopName", edit_search.getText().toString() + "+");
        }
        query2.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.i(">>>>>>>>>>>>>>>>>>>>>", "Retrieved " + scoreList.size() + " scores"); // Get List size

                    final String[] shopname = new String[scoreList.size()];
                    final String[] type = new String[scoreList.size()];
                    final int[] sID = new int[scoreList.size()];

                    int i = 0;
                    for (ParseObject dealsObject : scoreList) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        shopname[i] = (String) dealsObject.get("shopName");
                        type[i] = (String) dealsObject.get("type");
                        sID[i] = (int) dealsObject.get("shopID");
                        i++;
                    }

                    CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), shopname, type);
                    list_search.setAdapter(adapter);


                    list_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            if(type[position].equals("recycle")) {
                                ShowRecycleShop showRecycleShop = new ShowRecycleShop();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", sID[position]);
                                showRecycleShop.setArguments(args);
                                transaction.replace(R.id.fragment_container, showRecycleShop);
                                transaction.addToBackStack(null);
                                transaction.commit();

                            }

                            if(type[position].equals("foodscrape")){
                                ShowFoodscrape showFoodscrape = new ShowFoodscrape();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", sID[position]);
                                showFoodscrape.setArguments(args);
                                transaction.replace(R.id.fragment_container, showFoodscrape);
                                transaction.addToBackStack(null);
                                transaction.commit();

                            }

                            if(type[position].equals("landfill")){
                                ShowLandfill showLandfill = new ShowLandfill();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", sID[position]);
                                showLandfill.setArguments(args);
                                transaction.replace(R.id.fragment_container, showLandfill);
                                transaction.addToBackStack(null);
                                transaction.commit();

                            }
                            if(type[position].equals("freecycle")){
                                ShowFreecycle showFreecycle = new ShowFreecycle();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                args.putInt("sID", sID[position]);
                                showFreecycle.setArguments(args);
                                transaction.replace(R.id.fragment_container, showFreecycle);
                                transaction.addToBackStack(null);
                                transaction.commit();

                            }


                        }
                    });


                } else {
                    Log.i("SSS", "Error: " + e.getMessage());
                }
            }
        });
    }

}
