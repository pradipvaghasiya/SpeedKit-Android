package com.speedui.android.uiautomationdemo;


import android.app.Fragment;
import android.net.Uri;


import java.util.Arrays;
import java.util.List;

import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.navigationdrawer.SPDrawerActivity;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;

/**
 * Created by pradipvaghasiya on 22/05/15.
 */
public class DrawerActivity extends SPDrawerActivity implements CellsListFragment.OnFragmentInteractionListener {

    @Override
    protected List<SPListingCellGroup> getCellGroupListForDrawer() {
        return Arrays.asList(SPTitleViewHolder.getCellGroupFromCellModels(Arrays.asList("Menu 1", "Menu 2")));
    }

    @Override
    protected Fragment getFragmentAtPosition(int position) {
        return null;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
