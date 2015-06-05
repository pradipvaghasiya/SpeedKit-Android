package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;

import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;

public class SPEmptyViewHolder extends SPBindingViewHolder {

    public SPEmptyViewHolder(ViewDataBinding viewDataBinding, SPBindingViewHolderListener listener) {
        super(viewDataBinding, listener);
    }

    public void configureCellUsing(Object cellModel) {

        // Check if the model is my model.
        if (cellModel instanceof String) {
            // Configure Cell Here...
        }
    }

    public static SPListingData.ItemGroup getItemGroupFromItems(ObservableList<String> viewModelList) {
        return new SPListingData.ItemGroup(
                R.layout.recycler_cell_empty,
                0,
                SPEmptyViewHolder.class.getConstructors()[0],
                viewModelList);

    }
}