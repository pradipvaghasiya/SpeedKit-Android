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
        System.out.println("SPTitleViewHolder View Holder Created");
        this.textView = (TextView)v.findViewById(R.id.SPTitleViewHolder_TextView);
    }

    @Override
    public void configureCellUsing(Object cellModel) {
        if (cellModel instanceof String) {
            if (this.textView != null) {
                this.textView.setText((String) cellModel);
            }
        }
    }

}
