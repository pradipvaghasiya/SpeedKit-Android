package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import android.view.View;

import com.bignerdranch.android.multiselector.MultiSelector;

import java.util.List;

import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;

public class SPEmptyViewHolder extends SPViewHolder {

    public View emptyView;

    public SPEmptyViewHolder(View itemView, SPViewHolderListener listener, MultiSelector multiSelector) {
        super(itemView, listener, multiSelector);
        System.out.println("SPEmptyViewHolder View Holder Created");

        emptyView = itemView.findViewById(R.id.sp_empty_view);

        this.customiseViewHolderIfRequired();
    }

    @Override
    public void configureCellUsing(Object cellModel) {

        // Check if the model is my model.
        if (cellModel instanceof String) {
            // Configure Cell Here...
        }
    }

    public static SPListingCellGroup getCellGroupFromCellModels(List<String> viewModelList) {
        return new SPListingCellGroup(
                R.layout.recycler_cell_empty,
                SPEmptyViewHolder.class.getName(),
                viewModelList);

    }
}