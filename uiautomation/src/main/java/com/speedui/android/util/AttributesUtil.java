package com.speedui.android.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by pradipvaghasiya on 12/06/15.
 */
public class AttributesUtil {
    public static int getValueFromResourceId(final Context context, int resourceId){
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute (resourceId, value, true);
        return value.data;
    }
}
