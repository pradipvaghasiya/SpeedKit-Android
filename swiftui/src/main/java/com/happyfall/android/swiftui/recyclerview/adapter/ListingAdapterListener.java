package com.happyfall.android.swiftui.recyclerview.adapter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.happyfall.android.swiftui.recyclerview.viewholder.ListingViewModel;

/**
 * Created by pradip on 12/13/15.
 */
public class ListingAdapterListener extends ObservableList.OnListChangedCallback<ObservableArrayList<ListingViewModel>> {

    private ListingAdapter mAdapter;
    public ListingAdapterListener(ListingAdapter adapter){
        mAdapter = adapter;
    }

    @Override
    public void onChanged(ObservableArrayList<ListingViewModel> sender) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableArrayList<ListingViewModel> sender, int positionStart, int itemCount) {
        mAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeInserted(ObservableArrayList<ListingViewModel> sender, int positionStart, int itemCount) {
        mAdapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(ObservableArrayList<ListingViewModel> sender, int fromPosition, int toPosition, int itemCount) {
        if (itemCount == 1){
            mAdapter.notifyItemMoved(fromPosition, toPosition);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemRangeRemoved(ObservableArrayList<ListingViewModel> sender, int positionStart, int itemCount) {
        mAdapter.notifyItemRangeRemoved(positionStart, itemCount);
    }
}
