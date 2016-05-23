package com.happyfall.android.demo.withtoolbar;

import com.happyfall.android.demo.cells.TitleRModelListing;
import com.happyfall.android.swiftui.reuse.fragment.toolbar.ToolBarFragment;
import com.happyfall.android.swiftui.listing.ListingData;
import com.happyfall.android.swiftui.reuse.activity.DrawerToolbarActivity;
import com.happyfall.android.demo.cellfragments.TitleCellToolBarFragment;
import com.happyfall.android.demo.cellfragments.ToolbarTabsFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 6/2/2015.
 */
public class MainActivity extends DrawerToolbarActivity {
    private static List<String> MENU_LIST = Arrays.asList("Title Cell",  "ToolBar Tabs");

    ToolbarTabsFragment toolbarTabsFragment;

    public MainActivity(){
        this.mSelectedMenuPosition = 0;
        this.toolbarTabsFragment = new ToolbarTabsFragment();
    }

    @Override
    protected ListingData getListingDataForDrawer() {
        ListingData listingData  = new ListingData();
        for (String itemTitle : MENU_LIST){

            listingData.add(new TitleRModelListing(itemTitle));

        }
        return listingData;
    }

    @Override
    protected ToolBarFragment getFragmentAtPosition(int position) {

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
