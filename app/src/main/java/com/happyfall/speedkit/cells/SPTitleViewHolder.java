package com.happyfall.speedkit.cells;

import android.view.View;
import android.widget.TextView;

import com.happyfall.speedkit.listing.recyclerview.SPRecyclerAdapter;
import com.happyfall.speedkitdemo.R;

/**
 * Created by Pradip on 5/13/2015.
 */
public class SPTitleViewHolder extends SPRecyclerAdapter.ViewHolder{
    public static final String CLASS_NAME = "SPTitleViewHolder";

    TextView textView;
    public SPTitleViewHolder(View v) {
        super(v);
        System.out.println("View Holder Created");

        try {
            this.textView = (TextView)v.findViewById(R.id.SPTitleViewHolder_TextView);

        } catch (Exception e) {
            System.out.println("SpeedKit Error: Resource Class R not Found");        }

    }

    @Override
    public void configureCellUsing(Object cellModel) {
        if (this.textView != null) {
            this.textView.setText((String) cellModel);
        }
    }
}
