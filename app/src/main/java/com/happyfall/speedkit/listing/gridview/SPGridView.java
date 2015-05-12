package com.happyfall.speedkit.listing.gridview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.GridView;

import com.happyfall.speedkit.listing.SPListingData;
import com.happyfall.speedkit.listing.SPListingViewInterface;

/**
 * Created by Pradip on 5/12/2015.
 */
public class SPGridView extends GridView implements SPListingViewInterface {
    public SPListingData listingData;
    public SPGridView(Context context) {
        super(context);
    }

    public SPGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SPGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SPGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.setAdapter(new SPGridAdapter(this));
    }

    @Override
    public SPListingData getListingData() {
        return this.listingData;
    }
}
