package com.speedui.android.uiautomationdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

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

    TitleCellFragment titleCellFragment;
    ChecklistCellFragment checklistCellFragment;
    TabsFragment tabsFragment;

    public DrawerActivity(){
        //this.isActionBarOverlay = true;

        this.titleCellFragment = new TitleCellFragment();
        this.checklistCellFragment = new ChecklistCellFragment();
        this.tabsFragment = new TabsFragment();
    }


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
                return this.titleCellFragment;
            case 1:
                return this.checklistCellFragment;
            case 2:
                return this.tabsFragment;
            default:
                return null;
        }
    }

}
