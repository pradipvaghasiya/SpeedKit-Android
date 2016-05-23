package com.happyfall.android.demo.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;

/**
 * Created by pradipvaghasiya on 28/05/15.
 */
public class DrawableUtil {

    public static void setIntrinsicBounds(@Nullable Drawable drawable){
        if (drawable == null){
            return;
        }

        int height = drawable.getIntrinsicHeight();
        int width = drawable.getIntrinsicWidth();
        drawable.setBounds( 0, 0, width, height );

    }

    public static Drawable getDrawableFromIcon(Activity activity, int iconId){
        Drawable drawable = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = activity.getResources().getDrawable(iconId, activity.getTheme());
        }else{
            drawable= activity.getResources().getDrawable(iconId);
        }
        return drawable;
    }

}
