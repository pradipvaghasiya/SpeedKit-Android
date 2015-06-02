package com.speedui.android.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;

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
        try {
            drawable = activity.getResources().getDrawable(iconId, activity.getTheme());
        }catch (NoSuchMethodError e){
            drawable= activity.getResources().getDrawable(iconId);
        } catch (Resources.NotFoundException e) {
            System.out.println("getDrawableFromIcon: Icon not found");
        }

        return drawable;
    }

}
