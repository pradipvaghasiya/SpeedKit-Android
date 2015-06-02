package com.speedui.android.uiautomationdemo.withtoolbar;

import android.content.res.Configuration;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;
import com.speedui.android.uiautomationdemo.R;

import java.util.Arrays;
import java.util.List;

public abstract class ToolbarActivity extends AppCompatActivity implements SPViewHolderListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    protected RecyclerView drawerRecyclerView;
    protected int selectedMenuPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // SPRecycler Adapter Setup
        SPListingData listingData = new SPListingData(this.getCellGroupListForDrawer());
        SPRecyclerAdapter spRecyclerAdapter = new SPRecyclerAdapter(listingData,this);

        //Drawer RecyclerView Setup
        drawerRecyclerView = (RecyclerView)findViewById(R.id.drawer_recyclerview_drawer);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerRecyclerView.setAdapter(spRecyclerAdapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                com.speedui.android.uiautomation.R.string.spdrawer_open, R.string.spdrawer_close) {

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


        this.didSelectItem(null,selectedMenuPosition);
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
        }, 200);

    }

    private void replaceFragments(int position){
        Fragment fragment = this.getFragmentAtPosition(position);

        if (fragment != null){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.spdrawer_content_frame,fragment)
                    .commit();
        }

    }

    protected abstract List<SPListingCellGroup> getCellGroupListForDrawer();

    protected abstract android.support.v4.app.Fragment getFragmentAtPosition(int position);

}
