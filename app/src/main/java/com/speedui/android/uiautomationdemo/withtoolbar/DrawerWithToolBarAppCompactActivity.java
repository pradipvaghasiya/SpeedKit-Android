package com.speedui.android.uiautomationdemo.withtoolbar;

import android.support.v4.app.Fragment;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomationdemo.cellfragments.ChecklistCellFragment;
import com.speedui.android.uiautomationdemo.cellfragments.TabsFragment;
import com.speedui.android.uiautomationdemo.cellfragments.TitleCellFragment;
import com.speedui.android.uiautomationdemo.cellfragments.ToolbarTabsFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 6/2/2015.
 */
public class DrawerWithToolBarAppCompactActivity extends ToolbarActivity {
    private static List<String> MENU_LIST = Arrays.asList("Title Cell", "Check List Cell", "ToolBar Tabs");

    TitleCellFragment titleCellFragment;
    ChecklistCellFragment checklistCellFragment;
    ToolbarTabsFragment toolbarTabsFragment;

   public DrawerWithToolBarAppCompactActivity(){
        this.titleCellFragment = new TitleCellFragment();
        this.checklistCellFragment = new ChecklistCellFragment();
       this.toolbarTabsFragment = new ToolbarTabsFragment();
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
                return this.toolbarTabsFragment;
            default:
                return null;
        }
    }
}
