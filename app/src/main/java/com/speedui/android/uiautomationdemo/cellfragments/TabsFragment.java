package com.speedui.android.uiautomationdemo.cellfragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.android.common.view.SlidingTabLayout;
import com.speedui.android.uiautomation.slidingtabs.SPSlidingTabsFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pradipvaghasiya on 26/05/15.
 */
public class TabsFragment extends SPSlidingTabsFragment{

    public TabsFragment(){

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Customise the colors of indicator.
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.RED;
            }

            @Override
            public int getDividerColor(int position) {
                return Color.GRAY;
            }
        });
    }

    @Override
    public List<String> getTabTitles() {
        return Arrays.asList("Title Cell","Check List Cell");
    }

    @Override
    public Fragment getV4FragmentAt(int position) {
        switch (position){
            case 0:
                return new TitleCellFragment();
            case 1:
                return new ChecklistCellFragment();
            default:
                return null;
        }
    }
}
