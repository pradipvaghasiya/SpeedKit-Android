package com.happyfall.android.swiftui.listing.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.happyfall.android.swiftui.listing.ListingData;
import com.happyfall.android.swiftui.listing.ListingViewModel;
import com.happyfall.android.swiftui.listing.viewholder.ListingViewHolder;
import com.happyfall.android.swiftui.listing.viewholder.ListingViewHolderListener;
import com.happyfall.android.swiftui.util.ViewUtil;

/**
 * Created by pradipvaghasiya on 04/06/15.
 */
public class ListingAdapter extends RecyclerView.Adapter<ListingViewHolder> {
    //Unlike iOS, this need not be Weak reference because,
    //GC can remove entire retain cycle if none of the Objects pointing to the cycle.
    private ListingViewHolderListener mListener;
    private ListingAdapterListener mChangeListener = new ListingAdapterListener(this);
    private ListingData mListingData;

    final public void setListingData(ListingData listingData) {
        if(mListingData == listingData){
            return;
        }

        if (mListingData != null){
            mListingData.removeOnListChangedCallback(mChangeListener);
            mChangeListener.onItemRangeRemoved(null,0,mListingData.size());
        }

        if (listingData == null){
            mListingData = listingData;
            return;
        }

        mListingData = listingData;
        mListingData.addOnListChangedCallback(mChangeListener);
        mChangeListener.onItemRangeInserted(null, 0 , mListingData.size());
    }

    protected LayoutInflater mLayoutInflater;

    public ListingAdapter(@NonNull ListingData listingData, @NonNull ListingViewHolderListener listener){
        super();
        mListener = listener;
        setListingData(listingData);
    }

    @Override
    final public int getItemViewType(int position) {
        return mListingData.get(position).getMLayoutId();
    }

    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mLayoutInflater == null)
        {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }

        try {

            ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater,
                    viewType,       // Here viewType is mLayoutId itself
                    parent,
                    false);

            return new ListingViewHolder(binding, mListener);

        } catch (Exception e) {
            throw new RuntimeException("SpeedKit Error: onCreateViewHolder :" + e.toString());
        }
    }

    @Override
    final public void onBindViewHolder(ListingViewHolder bindingViewHolder, int position) {
        ListingViewModel model = mListingData.get(position);

        model.setMViewHolder(bindingViewHolder);
        if (model.getMBindingVariable() == 0){ // Return if binding not used.
            return;
        }

        bindingViewHolder.getDataBinding().setVariable(model.getMBindingVariable(), model);
        bindingViewHolder.getDataBinding().executePendingBindings();

        model.bindingExecuted();
    }

    @Override
    final public int getItemCount() {
        return mListingData.size();
    }

    @Override
    final public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mListingData = null;
    }

    @Override
    final public void onViewRecycled(ListingViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(ListingViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getAdapterPosition();
        if (position < 0){
            return;
        }

        mListingData.get(position).viewHolderAttached();
    }

    @Override
    final public void onViewDetachedFromWindow(ListingViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        int position = holder.getAdapterPosition();
        if (position < 0){
            return;
        }

        mListingData.get(position).viewHolderDettached();
    }

    @Override
    final public boolean onFailedToRecycleView(ListingViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }
}
