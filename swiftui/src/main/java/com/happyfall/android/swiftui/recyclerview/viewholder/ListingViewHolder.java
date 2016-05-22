package com.happyfall.android.swiftui.recyclerview.viewholder;

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
public class ListingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ListingViewHolderListener mListener;
    private ViewDataBinding mDataBinding;

    public ListingViewHolder(ViewDataBinding dataBinding,
                             ListingViewHolderListener listener) {
        super(dataBinding.getRoot());
        mListener = listener;
        mDataBinding = dataBinding;


        itemView.setOnClickListener(this);
        this.setDefaultDrawable();

    }

    public ViewDataBinding getDataBinding() {
        return mDataBinding;
    }

    public void setDefaultDrawable(){
        TypedValue outValue = new TypedValue();
        Resources.Theme theme = itemView.getContext().getTheme();
        theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);

        Drawable selectionDrawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            selectionDrawable = itemView.getResources().getDrawable(outValue.resourceId, theme);
        }else{
            selectionDrawable = itemView.getResources().getDrawable(outValue.resourceId);
        }

        ViewUtil.setBackground(itemView, selectionDrawable);
    }

    @Override
    public void onClick(View v) {
        if (mListener != null){
            mListener.didSelectItem(v, getAdapterPosition(), getAdapterPosition());
        }
    }
}
