package com.happyfall.android.demo.withtoolbar;

import com.happyfall.android.swiftui.listingdata.SPListingData;
import com.happyfall.android.demo.cells.TitleRModel;
import com.happyfall.android.demo.toolbar.SPDrawerToolbarActivity;
import com.happyfall.android.demo.toolbar.SPToolBarFragment;
import com.happyfall.android.demo.cellfragments.TitleCellToolBarFragment;
import com.happyfall.android.demo.cellfragments.ToolbarTabsFragment;

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
