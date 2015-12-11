package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import android.databinding.BaseObservable;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.TextView;

import com.speedui.android.uiautomation.BR;
import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

import java.util.List;

/**
 * Created by Pradip on 5/13/2015.
 */
public class SPTitleViewHolder extends SPBindingViewHolder {
    public static final String CLASS_NAME = "SPTitleViewHolder";

    public static class ViewModel extends SPViewModel{
        public String titleText;
        public ViewModel(String titleText){
            this.titleText = titleText;
        }

        @Override
        public SPBindingViewHolder createBindingViewHolder(ViewDataBinding viewDataBinding, SPBindingViewHolderListener listener, int itemType) {
            return new SPTitleViewHolder(viewDataBinding, listener, itemType);
        }
    }


    public SPTitleViewHolder(ViewDataBinding viewDataBinding, SPBindingViewHolderListener listener, int itemType) {
        super(viewDataBinding, listener, itemType);
    }


    public static SPListingData.ItemGroup getItemGroupFromItems(List<ViewModel> modelList){
        return new SPListingData.ItemGroup(
                R.layout.recycler_cell_title,
                BR.viewModel,
                modelList);

    }

}

