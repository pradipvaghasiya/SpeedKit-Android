package com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;

/**
 * Created by pradipvaghasiya on 20/05/15.
 */

abstract public class SPViewHolder extends SwappingHolder implements View.OnClickListener {
    protected SPViewHolderListener listener;
    protected MultiSelector multiSelector;

    public SPViewHolder(View itemView, SPViewHolderListener listener, MultiSelector multiSelector) {
        super(itemView,multiSelector);
        this.listener = listener;
        this.multiSelector = multiSelector;
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
        this.setDefaultModeBackgroundDrawable(selectionDrawable);

    }

    public abstract void configureCellUsing(Object cellModel);

    @Override
    public void onClick(View v) {
        if (this.multiSelector.tapSelection(this)) {
            // Selection is on, so tapSelection() toggled item selection.
        } else {
            // Selection is off; handle normal item click here.
        }
        listener.didSelectItem(v,getAdapterPosition());
    }

    protected void customiseViewHolderIfRequired(){
        if (this.listener instanceof SPViewHolderCustomisor){
            ((SPViewHolderCustomisor) this.listener).customiseViewHolder(this);
        }
    }
}
