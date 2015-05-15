package com.happyfall.speedkit.cells;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.happyfall.speedkit.listing.recyclerview.SPRecyclerAdapter;
import com.happyfall.speedkitdemo.R;

/**
 * Created by Pradip on 5/15/2015.
 */
public class SPCheckListViewHolder extends SPRecyclerAdapter.ViewHolder {
    TextView textView;
    ImageView imageView;

    public static class Model{
        public String titleText;
        public boolean isSelected;
    }

    public SPCheckListViewHolder(View v) {
        super(v);
        System.out.println("SPCheckListViewHolder View Holder Created");
        this.textView = (TextView)v.findViewById(R.id.SPCheckList_TextView);
        this.imageView = (ImageView)v.findViewById(R.id.SPCheckList_ImageView);
    }

    @Override
    public void configureCellUsing(Object cellModel) {

        // Check if the model is my model.
        if (cellModel instanceof Model){
            Model myModel = ((Model) cellModel);

            // Set the title Text
            if (this.textView != null){
                this.textView.setText(myModel.titleText);
            }

            // Hide selection image if not selected and vice versa
            if (this.imageView != null){
                if (myModel.isSelected) {
                    this.imageView.setVisibility(View.VISIBLE);
                }else{
                    this.imageView.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
}
