package com.speedui.android.uiautomation.listingautomation.recyclerview.adapter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

/**
 * Created by pradip on 12/13/15.
 */
public class SPBindingRecyclerAdapterChangeListener extends ObservableList.OnListChangedCallback<ObservableArrayList<SPViewModel>> {

    private SPBindingRecyclerAdapter mAdapter;
    public SPBindingRecyclerAdapterChangeListener(SPBindingRecyclerAdapter adapter){
        mAdapter = adapter;
    }

    @Override
    public void onChanged(ObservableArrayList<SPViewModel> sender) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableArrayList<SPViewModel> sender, int positionStart, int itemCount) {
        mAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeInserted(ObservableArrayList<SPViewModel> sender, int positionStart, int itemCount) {
        mAdapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(ObservableArrayList<SPViewModel> sender, int fromPosition, int toPosition, int itemCount) {
        if (itemCount == 1){
            mAdapter.notifyItemMoved(fromPosition, toPosition);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemRangeRemoved(ObservableArrayList<SPViewModel> sender, int positionStart, int itemCount) {
        mAdapter.notifyItemRangeRemoved(positionStart, itemCount);
    }
}
