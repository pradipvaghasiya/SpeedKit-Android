package com.happyfall.android.swiftui.listing.adapter;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.happyfall.android.swiftui.listing.ListingViewModel;

import java.lang.ref.WeakReference;

/**
 * Created by pradip on 12/13/15.
 */
public class ListingAdapterListener extends ObservableList.OnListChangedCallback<ObservableArrayList<ListingViewModel>> {

    private WeakReference<ListingAdapter> mAdapterWeakReference;
    public ListingAdapterListener(ListingAdapter adapter){
        mAdapterWeakReference = new WeakReference<ListingAdapter>(adapter);
    }

    @Override
    public void onChanged(ObservableArrayList<ListingViewModel> sender) {
        mAdapterWeakReference.get().notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableArrayList<ListingViewModel> sender, int positionStart, int itemCount) {
        mAdapterWeakReference.get().notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeInserted(ObservableArrayList<ListingViewModel> sender, int positionStart, int itemCount) {
        mAdapterWeakReference.get().notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(ObservableArrayList<ListingViewModel> sender, int fromPosition, int toPosition, int itemCount) {
        if (itemCount == 1){
            mAdapterWeakReference.get().notifyItemMoved(fromPosition, toPosition);
        }else{
            mAdapterWeakReference.get().notifyDataSetChanged();
        }
    }

    @Override
    public void onItemRangeRemoved(ObservableArrayList<ListingViewModel> sender, int positionStart, int itemCount) {
        mAdapterWeakReference.get().notifyItemRangeRemoved(positionStart, itemCount);
    }
}
