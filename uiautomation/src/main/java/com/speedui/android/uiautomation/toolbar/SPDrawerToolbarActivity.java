package com.speedui.android.uiautomation.toolbar;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;
import com.speedui.android.uiautomation.toolbar.SPToolBarFragment;
import com.speedui.android.uiautomation.R;
import com.speedui.android.util.ViewUtil;

import java.util.List;

public abstract class SPDrawerToolbarActivity extends AppCompatActivity implements
        SPViewHolderListener,
        SPToolBarFragment.SPFragmentLifeCycleListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    protected RecyclerView drawerRecyclerView;
    protected int selectedMenuPosition = 0;
    protected boolean isDrawerOverToolBar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spdrawer_toolbar);

        // SPRecycler Adapter Setup
        SPListingData listingData = new SPListingData(this.getCellGroupListForDrawer());
        SPRecyclerAdapter spRecyclerAdapter = new SPRecyclerAdapter(listingData,this);

        //Drawer RecyclerView Setup
        drawerRecyclerView = (RecyclerView)findViewById(R.id.drawer_recyclerview_drawer);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerRecyclerView.setAdapter(spRecyclerAdapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (isDrawerOverToolBar){
            ViewUtil.setMarginForView(drawerRecyclerView,0,0,0,0);
        }else{
            drawerLayout.setScrimColor(Color.TRANSPARENT);
        }

        this.didSelectItem(null,selectedMenuPosition);
    }

    protected ActionBarDrawerToggle configureHomeButtonOnToolBar(Toolbar toolbar){
        if (toolbar != null){
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            actionBarDrawerToggle = new ActionBarDrawerToggle(
                    this,
                    drawerLayout,
                    com.speedui.android.uiautomation.R.string.spdrawer_open,
                    com.speedui.android.uiautomation.R.string.spdrawer_close) {

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
            actionBarDrawerToggle.syncState();

            return actionBarDrawerToggle;
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        SPToolBarFragment fragment = this.getFragmentAtPosition(position);
        fragment.fragmentLifeCycleListener = this;

        if (fragment != null){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.spdrawer_content_frame,fragment)
                    .commit();
        }
    }

    @Override
    public void onViewCreated(SPToolBarFragment fragment) {
        this.configureHomeButtonOnToolBar(fragment.toolbar);
    }

    protected abstract List<SPListingCellGroup> getCellGroupListForDrawer();

    protected abstract SPToolBarFragment getFragmentAtPosition(int position);

}
