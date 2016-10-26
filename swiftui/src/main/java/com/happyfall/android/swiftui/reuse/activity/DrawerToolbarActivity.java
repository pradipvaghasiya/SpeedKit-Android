package com.happyfall.android.swiftui.reuse.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.happyfall.android.swiftui.R;

import com.happyfall.android.swiftui.reuse.fragment.toolbar.ToolBarFragment;
import com.happyfall.android.swiftui.listing.ListingData;
import com.happyfall.android.swiftui.listing.adapter.ListingAdapter;
import com.happyfall.android.swiftui.listing.viewholder.ListingViewHolderListener;
import com.happyfall.android.swiftui.util.ViewUtil;

public abstract class DrawerToolbarActivity extends AppCompatActivity implements
        ListingViewHolderListener,
        ToolBarFragment.ToolBarFragmentListner {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    protected RecyclerView mRecyclerView;
    protected int mSelectedMenuPosition = 0;
    protected boolean mIsDrawerOverToolBar = true;
    private ListingData mListingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        setupMenu();
        setupAppearance();

        this.didSelectItem(null, mSelectedMenuPosition);
    }

    private void setupAppearance() {
        if (mIsDrawerOverToolBar){
            ViewUtil.setMarginForView(mRecyclerView, 0, 0, 0, 0);
        }else{
            mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        }
    }

    private void setupMenu() {
        // SPRecycler Adapter Setup
        mListingData = getListingDataForDrawer();
        ListingAdapter spRecyclerAdapter = new ListingAdapter(mListingData,this);

        //Drawer RecyclerView Setup
        mRecyclerView = (RecyclerView)findViewById(R.id.menu_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(spRecyclerAdapter);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void setActionBarDrawerToggle(){
        if (mActionBarDrawerToggle == null) {
            mActionBarDrawerToggle = new ActionBarDrawerToggle(
                    this,
                    mDrawerLayout,
                    R.string.drawer_open,
                    R.string.drawer_close) {

                /**
                 * Called when a drawer has settled in a completely open state.
                 */
                public void onDrawerOpened(View drawerView) {
                    invalidateOptionsMenu();
                }

                /**
                 * Called when a drawer has settled in a completely closed state.
                 */
                public void onDrawerClosed(View view) {
                    invalidateOptionsMenu();
                }
            };
        }
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
    }

    protected ActionBarDrawerToggle configureHomeButtonOnToolBar(Toolbar toolbar){
        if (toolbar != null){
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            setActionBarDrawerToggle();
            mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
            mActionBarDrawerToggle.syncState();

            return mActionBarDrawerToggle;
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void didSelectItem(View view, final int position){
        mDrawerLayout.closeDrawer(mRecyclerView);
        replaceFragmentAfterSomeDelay(position);
    }

    @Override
    public boolean didLongPressed(View view, int position) {
        return false;
    }

    private void replaceFragmentAfterSomeDelay(final int adapterPosition) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                replaceFragments(adapterPosition);
            }
        }, 200);
    }

    private void replaceFragments(int position){
        ToolBarFragment fragment = this.getFragmentAtPosition(position);
        fragment.mToolBarFragmentListner = this;

        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.drawer_content,fragment)
                    .commit();
        }
    }

    @Override
    public void onViewCreated(ToolBarFragment fragment) {
        this.configureHomeButtonOnToolBar(fragment.toolbar);
    }

    protected abstract ListingData getListingDataForDrawer();

    protected abstract ToolBarFragment getFragmentAtPosition(int position);


}
