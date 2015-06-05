package com.speedui.android.uiautomationdemo.withactionbar;


import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.app.Fragment;

import java.util.Arrays;
import java.util.List;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.navigationdrawer.SPDrawerWithActionBarAppCompactActivity;
import com.speedui.android.uiautomationdemo.cellfragments.ChecklistCellFragment;
import com.speedui.android.uiautomationdemo.cellfragments.TabsFragment;
import com.speedui.android.uiautomationdemo.cellfragments.TitleCellFragment;

/**
 * Created by pradipvaghasiya on 22/05/15.
 */
public class DrawerWithActionBarAppCompactActivity extends SPDrawerWithActionBarAppCompactActivity {
    private static List<String> MENU_LIST = Arrays.asList("Title Cell","Check List Cell","Sliding Tabs");

    TitleCellFragment titleCellFragment;
    ChecklistCellFragment checklistCellFragment;
    TabsFragment tabsFragment;

    public DrawerWithActionBarAppCompactActivity(){
        this.isActionBarOverlay = true;

        this.titleCellFragment = new TitleCellFragment();
        this.checklistCellFragment = new ChecklistCellFragment();
        this.tabsFragment = new TabsFragment();
    }


    @Override
    protected List<SPListingData.ItemGroup> getItemGroupListForDrawer() {
        ObservableList titleItems = new ObservableArrayList();
        for (String itemTitle : MENU_LIST){

            titleItems.add(new SPTitleViewHolder.ViewModel(itemTitle));

        }
        return Arrays.asList(SPTitleViewHolder.getItemGroupFromItems(titleItems));
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
