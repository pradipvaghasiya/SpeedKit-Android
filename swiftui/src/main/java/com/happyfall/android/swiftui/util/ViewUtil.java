package com.happyfall.android.swiftui.util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 5/27/2015.
 */
public class ViewUtil {

    public static void setWidthForView (View view,
                                         int widthInPixels){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = widthInPixels;
        view.setLayoutParams(layoutParams);
    }

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

    public static void setBackground(View view, Drawable drawable){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        }else{
            view.setBackgroundDrawable(drawable);
        }
    }

}
