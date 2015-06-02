package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;

import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;

/**
 * Created by Pradip on 5/15/2015.
 */
public class SPCheckListViewHolder extends SPViewHolder {
    public TextView textView;
    public ImageView imageView;

    public static class ViewModel {
        public String titleText;
        public boolean isSelected;
    }

    public SPCheckListViewHolder(View v, SPViewHolderListener listener) {
        super(v,listener);
        //System.out.println("SPCheckListViewHolder View Holder Created");

        this.textView = (TextView)v.findViewById(R.id.SPCheckList_TextView);
        this.imageView = (ImageView)v.findViewById(R.id.SPCheckList_ImageView);

        this.customiseViewHolderIfRequired();
    }

    @Override
    public void configureCellUsing(Object cellModel) {

        // Check if the model is my model.
        if (cellModel instanceof ViewModel){
            ViewModel myViewModel = ((ViewModel) cellModel);

            // Set the title Text
            if (this.textView != null){
                this.textView.setText(myViewModel.titleText);
            }

            // Hide selection image if not selected and vice versa
            if (this.imageView != null){
                if (myViewModel.isSelected) {
                    this.imageView.setVisibility(View.VISIBLE);
                }else{
                    this.imageView.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    public static SPListingCellGroup getCellGroupFromCellModels(List<ViewModel> viewModelList){
        return new SPListingCellGroup(
                R.layout.recycler_cell_checklist,
                SPCheckListViewHolder.class.getName(),
                viewModelList);

    }

}
