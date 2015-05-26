package com.speedui.android.uiautomationdemo.cellfragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.view.View;

import com.example.android.common.view.SlidingTabLayout;
import com.speedui.android.uiautomation.slidingtabs.SPSlidingTabsFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pradipvaghasiya on 26/05/15.
 */
public class TabsFragment extends SPSlidingTabsFragment implements ViewPager.OnPageChangeListener{

    private List<String> tabTitles = Arrays.asList("Title Cell","Check List Cell");

    public TabsFragment(){
        this.isActionBarOverLay = true;
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

        slidingTabLayout.setOnPageChangeListener(this);
    }

    @Override
    public List<String> getTabTitles() {
        return tabTitles;
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
       // ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
