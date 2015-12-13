package com.speedui.android.uiautomation.listingautomation.recyclerview.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

/**
 * Created by pradipvaghasiya on 04/06/15.
 */
public class SPBindingRecyclerAdapter extends RecyclerView.Adapter<SPBindingViewHolder> {
    //Unlike iOS, this need not be Weak reference because,
    //GC can remove entire retain cycle if none of the Objects pointing to the cycle.
    private SPBindingViewHolderListener mListener;
    private SPBindingRecyclerAdapterChangeListener mChangeListener = new SPBindingRecyclerAdapterChangeListener(this);
    private SPListingData mListingData;

    public void setListingData(SPListingData listingData) {
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

    private LayoutInflater mLayoutInflater;

    public SPBindingRecyclerAdapter(@NonNull SPListingData listingData, @NonNull SPBindingViewHolderListener listener){
        super();
        mListener = listener;
        setListingData(listingData);
    }

    @Override
    public int getItemViewType(int position) {
        return mListingData.get(position).mLayoutId;
    }

    @Override
    public SPBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mLayoutInflater == null)
        {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }

        try {

            ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater,
                    viewType,       // Here viewType is mLayoutId itself
                    parent,
                    false);

            return new SPBindingViewHolder(binding, mListener);

        } catch (Exception e) {
            System.out.println("SpeedKit Error: onCreateViewHolder :" + e.toString());
        }

        return null;
    }

    @Override
    public void onBindViewHolder(SPBindingViewHolder bindingViewHolder, int position) {
        SPViewModel model = mListingData.get(position);

        model.mViewHolder = bindingViewHolder;

        if (model.mBindingVariable == 0){ // Return if binding not used.
            return;
        }

        bindingViewHolder.getDataBinding().setVariable(model.mBindingVariable, model);
        bindingViewHolder.getDataBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mListingData.size();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mListingData = null;
    }

    @Override
    public void onViewRecycled(SPBindingViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onViewDetachedFromWindow(SPBindingViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public boolean onFailedToRecycleView(SPBindingViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }
}
