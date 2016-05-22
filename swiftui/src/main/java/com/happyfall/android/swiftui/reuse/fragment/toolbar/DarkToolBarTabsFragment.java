package com.happyfall.android.swiftui.reuse.fragment.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.happyfall.android.swiftui.R;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 6/2/2015.
 */
abstract public class DarkToolBarTabsFragment extends LightToolBarTabsFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dark_toolbar_tabs,container,false);
    }
}
