package com.speedui.android.uiautomationdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.Arrays;
import java.util.List;

import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.navigationdrawer.SPDrawerActivity;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomationdemo.cellfragments.ChecklistCellFragment;
import com.speedui.android.uiautomation.slidingtabs.SPSlidingTabsFragment;
import com.speedui.android.uiautomationdemo.cellfragments.TabsFragment;
import com.speedui.android.uiautomationdemo.cellfragments.TitleCellFragment;

/**
 * Created by pradipvaghasiya on 22/05/15.
 */
public class DrawerActivity extends SPDrawerActivity{
    private static List<String> MENU_LIST = Arrays.asList("Title Cell","Check List Cell","Sliding Tabs");

    @Override
    protected List<SPListingCellGroup> getCellGroupListForDrawer() {
        return Arrays.asList(SPTitleViewHolder.getCellGroupFromCellModels(MENU_LIST));
    }

    @Override
    protected Fragment getFragmentAtPosition(int position) {
        try {
            getSupportActionBar().setTitle(MENU_LIST.get(position));
        }catch (Exception e){
            System.out.println(e.toString());
        }


        switch (position){
            case 0:
                return new TitleCellFragment();
            case 1:
                return new ChecklistCellFragment();
            case 2:
                return new TabsFragment();
            default:
                return null;
        }
    }

}
