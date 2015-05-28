package com.speedui.android.uiautomation.navigationdrawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SingleSelector;

import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.activity.SPAppCompactActivity;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;
import com.speedui.android.util.ActionBarUtil;

import java.util.List;


abstract public class SPDrawerAppCompactActivity extends SPAppCompactActivity implements SPViewHolderListener{
    private static final int DEFAULT_DRAWER_CLOSURE_TIME_IN_MILLISECONDS = 200;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    protected RecyclerView drawerRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spdrawer);

        // SPRecycler Adapter Setup
        SPListingData listingData = new SPListingData(this.getCellGroupListForDrawer());
        MultiSelector selector = new SingleSelector();
        selector.setSelectable(true);
        SPRecyclerAdapter spRecyclerAdapter = new SPRecyclerAdapter(listingData,this,selector);

        //Drawer RecyclerView Setup
        drawerRecyclerView = (RecyclerView)findViewById(R.id.spdrawer_recyclerview_drawer);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerRecyclerView.setAdapter(spRecyclerAdapter);


        //Set the Recyclerview padding id Overlay actionbar
        if (isActionBarOverlay){
            int actionBarHeight = ActionBarUtil.getActionBarHeightInPixels(getTheme(), getResources());
            this.drawerRecyclerView.setPadding(0,actionBarHeight,0,0);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.spdrawer_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.spdrawer_open, R.string.spdrawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }
        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void didSelectItem(View view, final int position) {

        drawerLayout.closeDrawer(drawerRecyclerView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                replaceFragments(position);
            }
        }, SPDrawerAppCompactActivity.DEFAULT_DRAWER_CLOSURE_TIME_IN_MILLISECONDS);
    }

    private void replaceFragments(int position){
        Fragment fragment = this.getFragmentAtPosition(position);

        if (fragment != null){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.spdrawer_content_frame,this.getFragmentAtPosition(position))
                    .commit();
        }

    }

    protected abstract List<SPListingCellGroup> getCellGroupListForDrawer();

    protected abstract android.support.v4.app.Fragment getFragmentAtPosition(int position);

}
