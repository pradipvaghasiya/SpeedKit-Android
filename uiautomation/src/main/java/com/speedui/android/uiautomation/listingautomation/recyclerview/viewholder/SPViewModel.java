package com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder;

import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;

import java.lang.ref.WeakReference;

/**
 * Created by pradipvaghasiya on 06/06/15.
 */
abstract public class SPViewModel extends BaseObservable {
    public SPBindingViewHolder mViewHolder;
    public int mLayoutId;
    public int mBindingVariable = 0;  // By default 0 if in case no Binding used.
}
