package com.speedui.android.uiautomationdemo.cellfragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import com.speedui.android.uiautomation.toolbar.SPDarkToolBarOnlyFragment;
import com.speedui.android.uiautomation.toolbar.SPToolBarOnlyFragment;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class TitleCellToolBarFragment extends SPDarkToolBarOnlyFragment {

    @Override
    protected Fragment getContentFragment() {
        return new TitleCellFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.toolbar.setTitle("UIAutomation Demo");
    }
}
