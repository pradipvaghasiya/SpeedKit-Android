package com.speedui.android.uiautomation.navigationdrawer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SingleSelector;

import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;

import java.util.List;


abstract public class SPDrawerActivity extends AppCompatActivity implements SPViewHolderListener{

    DrawerLayout drawerLayout;
    RecyclerView drawerRecyclerView;
    ActionBarDrawerToggle actionBarDrawerToggle;

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

    protected abstract List<SPListingCellGroup> getCellGroupListForDrawer();


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
    public void didSelectItem(View view, int position) {

        Fragment fragment = this.getFragmentAtPosition(position);

        if (fragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.spdrawer_content_frame,this.getFragmentAtPosition(position))
                    .commit();
        }

        drawerLayout.closeDrawer(drawerRecyclerView);
    }

    protected abstract Fragment getFragmentAtPosition(int position);

}
