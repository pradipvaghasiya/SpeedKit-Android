package com.speedui.android.uiautomation.slidingtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.common.view.SlidingTabLayout;
import com.speedui.android.uiautomation.R;

import java.util.List;


abstract public class SPSlidingTabsFragment extends android.support.v4.app.Fragment {

    protected SlidingTabLayout slidingTabLayout;
    private List<String> pageTitles;
    private ViewPager viewPager;
    protected boolean isActionBarOverLay;

    public SPSlidingTabsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_sliding_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SPSlidingTabsAdapter(getChildFragmentManager(),this.getTabTitles()));

        slidingTabLayout = (SlidingTabLayout)view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setViewPager(viewPager);

        if (isActionBarOverLay){
            TypedValue typedValue = new TypedValue();
            if (getActivity().getTheme().resolveAttribute(R.attr.actionBarSize,typedValue, true))
            {
                int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data,getResources().getDisplayMetrics());
                view.setPadding(0,actionBarHeight,0,0);
            }
        }
    }

    public abstract List<String> getTabTitles();

    public abstract Fragment getV4FragmentAt(int position);


    public class SPSlidingTabsAdapter extends FragmentPagerAdapter {
        List<String> tabTitles;

        public SPSlidingTabsAdapter(FragmentManager fm, List<String> tabTitles) {
            super(fm);
            this.tabTitles = tabTitles;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

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
