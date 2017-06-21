package com.happyfall.android.swiftui.listing.viewholder;

import android.content.res.Resources;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.happyfall.android.swiftui.util.ViewUtil;

/**
 * Created by pradipvaghasiya on 04/06/15.
 */
public class ListingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ListingViewHolderListener mListener;
    private ViewDataBinding mDataBinding;

    public ListingViewHolder(ViewDataBinding dataBinding,
                             ListingViewHolderListener listener) {
        super(dataBinding.getRoot());
        mListener = listener;
        mDataBinding = dataBinding;


        itemView.setOnClickListener(this);
    }

    public ViewDataBinding getDataBinding() {
        return mDataBinding;
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        if (position < 0){
            return;
        }

        if (mListener != null){
            mListener.didSelectItem(v, position);
        }
    }
}
