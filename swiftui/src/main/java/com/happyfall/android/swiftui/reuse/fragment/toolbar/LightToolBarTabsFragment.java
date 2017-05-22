package com.happyfall.android.swiftui.reuse.fragment.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import com.happyfall.android.swiftui.R;
/**
 * Project: UIAutomation-Android
 * Created by Pradip on 6/2/2015.
 */
public abstract class LightToolBarTabsFragment extends ToolBarFragment {
    protected ViewPager mViewPager;
    protected TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_toolbar_tabs,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configure View Pager
        this.setupToolbar(view);

        // Configure View Pager
        this.setupViewPager(view);

        // Configure Sliding Tabs
        this.setupTabLayout(view);

        if (this.mToolBarFragmentListner != null){
            this.mToolBarFragmentListner.onViewCreated(this);
        }
    }

    private void setupToolbar(View view){
        this.toolbar = (Toolbar) view.findViewById(R.id.toolbar_tabs_toolbar);
    }

    /**
     * <p>Gets ViewPager from Layout and sets its adapter
     * {@link com.happyfall.android.swiftui.actionbar.slidingtabs.SPSlidingTabsFragment.SPSlidingTabsAdapter}</p>
     *
     * @param view Layoutview for this fragment.
     */
    private void setupViewPager(View view){
        mViewPager = (ViewPager)view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new TabLayoutPagerAdapter(getChildFragmentManager(), this.getTabTitles()));
    }

    /**
     * <p>Gets SlidingTabLayout from Layout and sets its adapter.</p>
     *
     * @param view Layoutview for this fragment.
     */
    private void setupTabLayout(View view){
        mTabLayout = (TabLayout)view.findViewById(R.id.toolbar_tabs_tablayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    /**
     * Abstract method a Subclass must implement and return non-empty list.
     *
     * @return String List of Titles of Sliding Tabs
     */
    public abstract List<String> getTabTitles();

    /**
     * Abstract method a Subclass must implement and return non-null fragment.
     *
     * @param position Position for which fragment is required.
     *
     * @return Support Library V4 Fragment.
     */
    public abstract Fragment getV4FragmentAt(int position);


    /**
     * <p>TabLayoutPagerAdapter is FragmentPagerAdapter which will be used to generate
     * {@link SPSlidingTabsFragment} fragment which contains
     * Sliding Tabs.</p>
     *
     * <p>This Class uses abstract methods {@link #getTabTitles()} and
     * {@link #getV4FragmentAt(int)} to get the data.</p>
     */
    private class TabLayoutPagerAdapter extends FragmentPagerAdapter {
        List<String> tabTitles;

        public TabLayoutPagerAdapter(FragmentManager fm, List<String> tabTitles) {
            super(fm);
            this.tabTitles = tabTitles;
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = getV4FragmentAt(position);
            if (fragment == null){
                throw new RuntimeException("SPSlidingTabsFragment subclass method getV4FragmentAt must provide valid fragment at position: " + position);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return this.tabTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return this.tabTitles.get(position);
        }
    }


}
