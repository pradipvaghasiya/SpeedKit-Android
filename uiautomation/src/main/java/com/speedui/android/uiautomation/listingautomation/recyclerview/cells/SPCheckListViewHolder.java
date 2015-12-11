package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.speedui.android.uiautomation.BR;
import com.speedui.android.uiautomation.R;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

import java.util.List;

/**
 * Created by Pradip on 5/15/2015.
 */
public class SPCheckListViewHolder extends SPBindingViewHolder {

    public SPCheckListViewHolder(ViewDataBinding viewDataBinding, SPBindingViewHolderListener listener, int itemType) {
        super(viewDataBinding, listener, itemType);
    }

    public static class ViewModel extends SPViewModel {

        public ViewModel(String titleText, boolean selected){
            this.titleText = titleText;
            this.selected = selected;
        }

        public String titleText;

        @Bindable
        private boolean selected;

        public boolean isSelected() {
            return selected;
        }

        public void setIsSelected(boolean isSelected) {
            this.selected = isSelected;
            notifyPropertyChanged(BR.selected);
        }

        public View.OnClickListener getOnClickListener(){
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setIsSelected(!selected);
                    SPBindingViewHolder viewHolder = getBindingViewHolder();
                    if (viewHolder != null){
                        viewHolder.onClick(viewHolder.getViewDataBinding().getRoot());
                    }
                }
            };
        }

        @Override
        public SPBindingViewHolder createBindingViewHolder(ViewDataBinding viewDataBinding, SPBindingViewHolderListener listener, int itemType) {
            return new SPCheckListViewHolder(viewDataBinding,listener,itemType);
        }
    }

    public static SPListingData.ItemGroup getItemGroupFromItems(List<ViewModel> viewModelList){
        return new SPListingData.ItemGroup(
                R.layout.recycler_cell_checklist,
                BR.viewModel,
                viewModelList);

    }

}
