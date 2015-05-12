package com.happyfall.speedkit.cells;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.happyfall.speedkit.listing.SPListingCellInterface;

/**
 * Created by Pradip on 5/12/2015.
 */
public class SPTitleCell extends LinearLayout implements SPListingCellInterface{
    TextView textView;
    public SPTitleCell(Context context) {
        super(context);
    }

    public SPTitleCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SPTitleCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SPTitleCell(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public void configureCellUsing(Object cellModel) {

    }
}
