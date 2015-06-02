package com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.speedui.android.util.ViewUtil;


/**
 * Created by pradipvaghasiya on 20/05/15.
 */

abstract public class SPViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected SPViewHolderListener listener;

    public SPViewHolder(View itemView, SPViewHolderListener listener) {
        super(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);

        this.setDefaultDrawable();
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

        ViewUtil.setBackground(itemView,selectionDrawable);
    }

    public abstract void configureCellUsing(Object cellModel);

    @Override
    public void onClick(View v) {
        listener.didSelectItem(v,getAdapterPosition());
    }

    protected void customiseViewHolderIfRequired(){
        if (this.listener instanceof SPViewHolderCustomisor){
            ((SPViewHolderCustomisor) this.listener).customiseViewHolder(this);
        }
    }
}
