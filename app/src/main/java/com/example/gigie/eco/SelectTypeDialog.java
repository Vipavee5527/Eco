package com.example.gigie.eco;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by gigie on 9/1/15 AD.
 */
public class SelectTypeDialog extends android.support.v4.app.DialogFragment {
    String[] text = {"Recycled Waste", "Food Scrape", "Landfill", "Freecycle Stuffs", "New Request"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("TEST DIALOG")
                .setItems(text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which){
                            case 0: //Recycle waste
                                Toast.makeText(getActivity().getApplicationContext(),"Recycled Waste",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 1: //FoodScrape
                                Toast.makeText(getActivity().getApplicationContext(),"Food Scrape",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 2: //Landfill
                                Toast.makeText(getActivity().getApplicationContext(),"Landfill",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 3: //Freecycle
                                Toast.makeText(getActivity().getApplicationContext(),"Freecycle Stuffs",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 4: //Request pending
                                Toast.makeText(getActivity().getApplicationContext(),"New Request",
                                        Toast.LENGTH_LONG).show();
                                break;

                        }
                    }
                });
        return builder.create();
    }
}
