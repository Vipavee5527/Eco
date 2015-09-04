package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by gigie on 9/4/15 AD.
 */
public class AddRequestFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_request, null, false);
        Spinner spinner = (Spinner) v.findViewById(R.id.catReq);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.catreq, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) v.findViewById(R.id.catReq);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:

                        break;
                    case 1:
                        Toast.makeText(getActivity().getApplicationContext(), "Recycle Waste",
                                Toast.LENGTH_LONG).show();
                        Log.i("sdfdsfdsf","sdfsdfsdfsd");
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        return v;
    }
}