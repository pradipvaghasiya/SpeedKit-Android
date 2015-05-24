package com.speedui.android.uiautomationdemo.cellfragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.common.view.SlidingTabLayout;
import com.speedui.android.uiautomationdemo.R;

import java.util.Arrays;
import java.util.List;

public class SlidingTabsFragment extends android.support.v4.app.Fragment {
    private SlidingTabLayout slidingTabLayout;

    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sliding_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SamplePagerAdapter(getActivity().getSupportFragmentManager(), Arrays.asList("Title Cell", "Checklist Cell")));

        slidingTabLayout = (SlidingTabLayout)view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setViewPager(viewPager);
    }


    class SamplePagerAdapter extends FragmentPagerAdapter{

        List<String> pageTitles;

        public SamplePagerAdapter(FragmentManager fm, List<String> pageTitles) {
            super(fm);

            this.pageTitles = pageTitles;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new TitleCellFragment();
                case 1:
                    return new ChecklistCellFragment();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return this.pageTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return this.pageTitles.get(position);
        }
    }
}
