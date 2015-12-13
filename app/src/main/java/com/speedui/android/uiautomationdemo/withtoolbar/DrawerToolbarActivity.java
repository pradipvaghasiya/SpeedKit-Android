package com.speedui.android.uiautomationdemo.withtoolbar;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.TitleRModel;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;
import com.speedui.android.uiautomation.toolbar.SPDrawerToolbarActivity;
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

    ToolbarTabsFragment toolbarTabsFragment;

    public DrawerToolbarActivity(){
        this.selectedMenuPosition = 0;
        this.toolbarTabsFragment = new ToolbarTabsFragment();
    }

    @Override
    protected SPListingData getListingDataForDrawer() {
        SPListingData listingData  = new SPListingData();
        for (String itemTitle : MENU_LIST){

            listingData.add(new TitleRModel(itemTitle));

        }
        return listingData;
    }

    @Override
    protected SPToolBarFragment getFragmentAtPosition(int position) {

        switch (position){
            case 0:
                return new TitleCellToolBarFragment();
            case 1:
                return this.toolbarTabsFragment;
            default:
                return null;
        }
    }

}
