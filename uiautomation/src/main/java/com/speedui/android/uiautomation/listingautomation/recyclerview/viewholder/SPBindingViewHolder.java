package com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder;

import android.content.res.Resources;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.speedui.android.util.ViewUtil;

/**
 * Created by pradipvaghasiya on 04/06/15.
 */
public class SPBindingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private SPBindingViewHolderListener listener;
    private ViewDataBinding dataBinding;

    public SPBindingViewHolder(ViewDataBinding dataBinding,
                               SPBindingViewHolderListener listener) {
        super(dataBinding.getRoot());
        this.listener = listener;
        this.dataBinding = dataBinding;


        itemView.setOnClickListener(this);
        this.setDefaultDrawable();

    }

    public ViewDataBinding getDataBinding() {
        return dataBinding;
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
        if (this.listener != null){
            this.listener.didSelectItem(v,getAdapterPosition(),getAdapterPosition());
        }
    }
}
