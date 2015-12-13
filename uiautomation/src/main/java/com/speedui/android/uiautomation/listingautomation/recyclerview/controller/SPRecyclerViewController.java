package com.speedui.android.uiautomation.listingautomation.recyclerview.controller;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;

/**
 * Created by pradip on 12/13/15.
 */
public interface SPRecyclerViewController extends SPBindingViewHolderListener {
    public SPListingData getListingData();
}
