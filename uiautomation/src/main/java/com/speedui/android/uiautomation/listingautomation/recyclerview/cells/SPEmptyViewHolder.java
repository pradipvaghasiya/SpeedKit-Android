package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;

import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

import java.util.List;

public class SPEmptyViewHolder extends SPBindingViewHolder {

    public class ViewModel extends SPViewModel{
        public ViewModel(){};

        @Override
        public SPBindingViewHolder createBindingViewHolder(ViewDataBinding viewDataBinding, SPBindingViewHolderListener listener, int itemType) {
            return new SPEmptyViewHolder(viewDataBinding,listener,itemType);
        }
    }

    public SPEmptyViewHolder(ViewDataBinding viewDataBinding, SPBindingViewHolderListener listener, int itemType) {
        super(viewDataBinding, listener, itemType);
    }

    public void configureCellUsing(Object cellModel) {

        // Check if the model is my model.
        if (cellModel instanceof String) {
            // Configure Cell Here...
        }
    }

    public static SPListingData.ItemGroup getItemGroupFromItems(List<ViewModel> viewModelList) {
        return new SPListingData.ItemGroup(
                R.layout.recycler_cell_empty,
                0,
                viewModelList);

    }
}