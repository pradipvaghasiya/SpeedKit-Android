package com.happyfall.android.swiftui.recyclerview.viewholder;

import android.view.View;

/**
 * Created by pradipvaghasiya on 20/05/15.
 */
public interface ListingViewHolderListener {
    void didSelectItem(View view, int adapterPosition, int itemGroupPosition);

}
