package com.speedui.android.uiautomation.listingautomation.gridview.cells;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellInterface;


/**
 * Created by Pradip on 5/12/2015.
 */
public class SPTitleCell extends LinearLayout implements SPListingCellInterface {
    TextView textView;
    public SPTitleCell(Context context) {
        super(context);
    }

    public SPTitleCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public SPTitleCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SPTitleCell(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void configureCellUsing(Object cellModel) {

    }
}
