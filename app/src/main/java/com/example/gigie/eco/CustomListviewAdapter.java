package com.example.gigie.eco;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by gigie on 8/31/15 AD.
 */
public class CustomListviewAdapter extends ArrayAdapter<String> {
    String[] STR;
    int[] RESOURCE_ID;
    LayoutInflater INFLATER;

    public CustomListviewAdapter(Context context, int textViewResourceId
            , String[] objects, int[] resourceId) {
        super(context, textViewResourceId, objects);
        STR = objects;
        RESOURCE_ID = resourceId;
        INFLATER = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;
        if(convertView == null) {
            convertView = INFLATER.inflate(R.layout.listview, parent, false);
            imageView = (ImageView)convertView.findViewById(R.id.imageView1);
        } else {
            imageView = (ImageView)convertView.findViewById(R.id.imageView1);
            DecodeTask dt1 = (DecodeTask)imageView.getTag(R.id.imageView1);
            if(dt1 != null)
                dt1.cancel(true);
        }

        imageView.setImageBitmap(null);
        DecodeTask dt2 = new DecodeTask(getContext(), imageView, RESOURCE_ID[position]);
        dt2.execute();
        imageView.setTag(R.id.imageView1, dt2);

        TextView textView = (TextView)convertView.findViewById(R.id.textView1);
        textView.setText(STR[position]);

        return convertView;
    }
}
