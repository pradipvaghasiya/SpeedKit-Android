package com.happyfall.android.demo.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by pradipvaghasiya on 10/06/15.
 */
public class ActivityUtil {
    public static void hideKeyboard(Activity activity, View viewResponsibleForKeyboard){
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(viewResponsibleForKeyboard.getApplicationWindowToken(), 0);
    }
}
