package com.speedui.android.util;

import android.view.View;
import android.view.ViewGroup;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 5/27/2015.
 */
public class ViewUtil {

    public static void setHeightForView (View view,
                                         int heightInPixels){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = heightInPixels;
        view.setLayoutParams(layoutParams);
    }

    public static void setMarginForView (View view,
                                         int leftMarginInPixels,
                                         int topMarginInPixels,
                                         int rightMarginInPixels,
                                         int bottomMarginInPixels) {

        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.setMargins(leftMarginInPixels, topMarginInPixels, rightMarginInPixels, bottomMarginInPixels);
        }
    }

}
