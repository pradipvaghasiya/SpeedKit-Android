package com.speedui.android.uiautomationdemo.withtoolbar;

import com.speedui.android.uiautomation.toolbar.SPDrawerToolbarActivity;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.toolbar.SPToolBarFragment;
import com.speedui.android.uiautomationdemo.cellfragments.TitleCellToolBarFragment;
import com.speedui.android.uiautomationdemo.cellfragments.ToolbarTabsFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 6/2/2015.
 */
public class DrawerToolbarActivity extends SPDrawerToolbarActivity {
    private static List<String> MENU_LIST = Arrays.asList("Title Cell",  "ToolBar Tabs");

    TitleCellToolBarFragment titleCellToolBarFragment;
    ToolbarTabsFragment toolbarTabsFragment;

    public DrawerToolbarActivity(){
        this.selectedMenuPosition = 0;
        this.titleCellToolBarFragment = new TitleCellToolBarFragment();
        this.toolbarTabsFragment = new ToolbarTabsFragment();
    }

    @Override
    protected List<SPListingCellGroup> getCellGroupListForDrawer() {
        return Arrays.asList(SPTitleViewHolder.getCellGroupFromCellModels(MENU_LIST));
    }

    @Override
    protected SPToolBarFragment getFragmentAtPosition(int position) {

        switch (position){
            case 0:
                return this.titleCellToolBarFragment;
            case 1:
                return this.toolbarTabsFragment;
            default:
                return null;
        }
    }

}
