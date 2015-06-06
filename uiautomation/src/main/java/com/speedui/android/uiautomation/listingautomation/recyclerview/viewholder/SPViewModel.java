package com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder;

import android.databinding.BaseObservable;

import java.lang.ref.WeakReference;

/**
 * Created by pradipvaghasiya on 06/06/15.
 */
public class SPViewModel extends BaseObservable {
    public SPBindingViewHolder getBindingViewHolder() {
        return weakViewHolder.get();
    }

    public void setWeakViewHolder(SPBindingViewHolder spBindingViewHolder) {
        this.weakViewHolder = new WeakReference<SPBindingViewHolder>(spBindingViewHolder);
    }

    private WeakReference<SPBindingViewHolder> weakViewHolder;
}
