package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.speedui.android.uiautomation.R;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;

/**
 * Created by Pradip on 5/15/2015.
 */
public class SPCheckListViewHolder extends SPBindingViewHolder {
    public TextView textView;
    public ImageView imageView;

    public SPCheckListViewHolder(ViewDataBinding viewDataBinding, SPBindingViewHolderListener listener) {
        super(viewDataBinding, listener);
    }

    public static class ViewModel {
        public String titleText;
        public boolean isSelected;
    }

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

    public static SPListingData.ItemGroup getItemGroupFromItems(ObservableList<ViewModel> viewModelList){
        return new SPListingData.ItemGroup(
                R.layout.recycler_cell_checklist,
                0,
                SPCheckListViewHolder.class.getConstructors()[0],
                viewModelList);

    }

}
