package com.speedui.android.util;

import android.app.Activity;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

import com.speedui.android.uiautomation.R;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 5/27/2015.
 */
public class ActionBarUtil {
    public static View getActionBarView(Activity activity){
        Window window = activity.getWindow();
        View v = window.getDecorView();
        return v.findViewById(R.id.action_bar_container);
    }

    public static int getActionBarHeightInPixels(Resources.Theme theme, Resources resources) {
        TypedValue typedValue = new TypedValue();
        if (theme.resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, resources.getDisplayMetrics());
        }
        return 0;
    }
}
