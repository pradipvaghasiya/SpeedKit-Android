package com.happyfall.android.demo.cellfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.happyfall.android.swiftui.reuse.fragment.toolbar.DarkToolBarFragment;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class TitleCellToolBarFragment extends DarkToolBarFragment {

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
