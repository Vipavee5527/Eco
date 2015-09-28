package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by gigie on 9/4/15 AD.
 */
public class AddRequestFragment extends Fragment {

    EditText title;
    EditText description;
    EditText telephone;
    EditText contactname;
    EditText category;
    EditText specify;
    EditText startDate;
    EditText endDate;
    Spinner spinner;
    ScrollView mScrollView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_request, null, false);
        mScrollView = (ScrollView) v.findViewById(R.id.scrollview_request);

        title = (EditText) v.findViewById(R.id.titleName);
        description = (EditText) v.findViewById(R.id.add_req_description);
        telephone = (EditText) v.findViewById(R.id.telephone);
        contactname = (EditText) v.findViewById(R.id.contactName);
        spinner = (Spinner) v.findViewById(R.id.catReq);
        specify = (EditText) v.findViewById(R.id.specify);
        startDate = (EditText) v.findViewById(R.id.startDate);
        endDate = (EditText) v.findViewById(R.id.finishDate);


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.catreq, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:
                        Toast.makeText(getActivity().getApplicationContext(), "Recycle Waste",
                                Toast.LENGTH_LONG).show();
                        Log.i("sdfdsfdsf", "sdfsdfsdfsd");
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        Button btn_donereq = (Button) v.findViewById(R.id.btn_donereq);
        btn_donereq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Request");
                //query.orderByDescending("shopID");
                //query.setLimit(1);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            //Log.i("ShopID", "Retrieved " + scoreList.size() + " scores"); // Get List size

                                // use dealsObject.get('columnName') to access the properties of the Deals object.
                                //ParseObject freecycle = new ParseObject("Freecycle");
                                ParseObject request = new ParseObject("Request");
                                //////
                                String str = title.getText().toString();
                                if (!str.equals("")) {
                                    request.put("title", title.getText().toString());
                                } else {
                                    request.put("title", "-");
                                }


                                str = description.getText().toString();
                                if (!str.equals("")) {
                                    request.put("description", description.getText().toString());
                                } else {
                                    request.put("description", "-");
                                }


                                str = telephone.getText().toString();
                                if (!str.equals("")) {
                                    request.put("telephone", telephone.getText().toString());
                                } else {
                                    request.put("telephone", "-");
                                }

                                str = contactname.getText().toString();
                                if (!str.equals("")) {
                                    request.put("contactname", contactname.getText().toString());
                                } else {
                                    request.put("contactname", "-");
                                }


                                str = specify.getText().toString();
                                if (!str.equals("")) {
                                    request.put("specify", specify.getText().toString());
                                } else {
                                    request.put("specify", "-");
                                }

                                /////
                                str = startDate.getText().toString();
                                if (!str.equals("")) {
                                    request.put("startDate", startDate.getText().toString());
                                } else {
                                    request.put("startDate", "-");
                                }

                                str = endDate.getText().toString();
                                if (!str.equals("")) {
                                    request.put("endDate", endDate.getText().toString());
                                } else {
                                    request.put("endDate", "-");
                                }

                                str = spinner.getSelectedItem().toString();
                                if (!str.equals("")) {
                                    request.put("category", spinner.getSelectedItem().toString());
                                }
                                else if (str.equals("select Category")){
                                    request.put("category","-");
                                }
                                else {
                                    request.put("category", "-");
                                }


                                str = specify.getText().toString();
                                if (!str.equals("")) {
                                    request.put("specify", specify.getText().toString());
                                } else {
                                    request.put("specify", "-");
                                }



                                request.saveInBackground();

                                ShowRequest showRequest = new ShowRequest();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                Bundle args = new Bundle();
                                showRequest.setArguments(args);
                                transaction.replace(R.id.fragment_container, showRequest);
                                transaction.addToBackStack(null);
                                transaction.commit();


                        } else {
                            Log.i("score", "Error: " + e.getMessage());
                        }
                    }
                });


            }
        });



        return v;
    }
}