package com.happyfall.android.demo.util;

import android.content.res.Resources;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 5/27/2015.
 */
public class DeviceUtil {
    public static float getDeviceDensity(Resources resources){
        return resources.getDisplayMetrics().density;
    }
}
