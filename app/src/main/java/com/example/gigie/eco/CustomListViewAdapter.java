package com.example.gigie.eco;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by gigie on 8/31/15 AD.
 */
public class CustomListViewAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] txtLine1;
    private final String[] txtLine2;
    //private final Integer[] imageId;

    public CustomListViewAdapter(Activity context, String[] txtLine1, String[] txtLine2) {
        super(context, R.layout.listview, txtLine1);
        this.context = context;
        this.txtLine1 = txtLine1;
        this.txtLine2 = txtLine2;
        //this.imageId = imageId;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview, null, true);

        TextView txt1 = (TextView) rowView.findViewById(R.id.txt);
        TextView txt2 = (TextView) rowView.findViewById(R.id.txt2);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txt1.setText(txtLine1[position]);
        txt2.setText(txtLine2[position]);

//        imageView.setImageResource(imageId[position]);

        return rowView;
    }

}
