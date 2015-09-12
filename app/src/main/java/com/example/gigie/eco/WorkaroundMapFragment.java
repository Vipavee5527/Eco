package com.example.gigie.eco;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;

public class WorkaroundMapFragment extends SupportMapFragment {
    private ScrollView mListener;
    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        View layout = super.onCreateView(layoutInflater, viewGroup, savedInstance);

        mListener = (ScrollView)layout.findViewById(R.id.scrollview_recycle);
        mapView = (MapView)layout.findViewById(R.id.map_pick);

        TouchableWrapper frameLayout = new TouchableWrapper(getActivity());

        frameLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        ((ViewGroup) layout).addView(frameLayout,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return layout;
    }

//    public void setListener(OnTouchListener listener) {
//        mListener = listener;
//    }

    public interface OnTouchListener {
        public abstract void onTouch();
    }

    public class TouchableWrapper extends FrameLayout {

        public TouchableWrapper(Context context) {
            super(context);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                case MotionEvent.ACTION_DOWN:
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                case MotionEvent.ACTION_UP:
                    this.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                default:
                    return true;
            }
        }
    }
}