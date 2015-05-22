package com.speedui.android.uiautomationdemo;


import android.app.Fragment;
import android.net.Uri;


import java.util.Arrays;

import com.speedui.android.uiautomation.navigationdrawer.SPDrawerActivity;
import com.speedui.android.uiautomation.recyclerviewcells.SPTitleViewHolder;
import com.speedui.android.uiautomation.listingdata.SPListingCellGroup;

/**
 * Created by pradipvaghasiya on 22/05/15.
 */
public class DrawerActivity extends SPDrawerActivity implements CellsListFragment.OnFragmentInteractionListener {

    @Override
    protected SPListingCellGroup getCellGroupForDrawer() {
        return SPTitleViewHolder.getCellGroupFromCellModels(Arrays.asList("Menu 1", "Menu 2"));
    }

    @Override
    protected Fragment getFragmentAtPosition(int position) {
        return CellsListFragment.newInstance(position);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
