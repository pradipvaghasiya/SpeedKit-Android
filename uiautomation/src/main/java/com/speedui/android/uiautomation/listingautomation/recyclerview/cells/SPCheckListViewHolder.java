package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.MultiSelector;

import java.util.List;

import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;

import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderCustomizor;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;

/**
 * Created by Pradip on 5/15/2015.
 */
public class SPCheckListViewHolder extends SPViewHolder {
    public TextView textView;
    public ImageView imageView;

    public static class Model{
        public String titleText;
        public boolean isSelected;
    }

    public SPCheckListViewHolder(View v, SPViewHolderListener listener,MultiSelector multiSelector) {
        super(v,listener,multiSelector);
        System.out.println("SPCheckListViewHolder View Holder Created");

        this.textView = (TextView)v.findViewById(R.id.SPCheckList_TextView);
        this.imageView = (ImageView)v.findViewById(R.id.SPCheckList_ImageView);

        this.customiseViewHolderIfRequired();
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

    public static SPListingCellGroup getCellGroupFromCellModels(List<Model> modelList){
        return new SPListingCellGroup(
                R.layout.recycler_cell_checklist,
                SPCheckListViewHolder.class.getName(),
                modelList);

    }

}
