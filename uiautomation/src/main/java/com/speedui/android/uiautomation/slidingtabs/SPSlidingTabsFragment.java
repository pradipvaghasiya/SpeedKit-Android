package com.speedui.android.uiautomation.slidingtabs;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.common.view.SlidingTabLayout;
import com.speedui.android.uiautomation.R;
import com.speedui.android.util.ActionBarUtil;
import com.speedui.android.util.AnimationUtil;
import com.speedui.android.util.DeviceUtil;
import com.speedui.android.util.ViewUtil;

import java.util.Arrays;
import java.util.List;


abstract public class SPSlidingTabsFragment extends android.support.v4.app.Fragment {
    public static final int DEFAULT_TABS_HEIGHT_IN_DP = 40;
    public static final int DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS = 300;

    public SlidingTabLayout slidingTabLayout;
    private List<String> pageTitles;
    private ViewPager viewPager;
    protected boolean isActionBarOverLay;
    public boolean isActionBarHidden = false;

    public SPSlidingTabsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_sliding_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configure View Pager
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SPSlidingTabsAdapter(getChildFragmentManager(),this.getTabTitles()));

        // Configure Sliding Tabs
        slidingTabLayout = (SlidingTabLayout)view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setViewPager(viewPager);

        this.adjustLayoutInCaseOfOverlayActionBar(view);

    }

    private void adjustLayoutInCaseOfOverlayActionBar(View view){
        try {
            if (isActionBarOverLay){

                int actionBarHeight = ActionBarUtil.getActionBarHeightInPixels(getActivity().getTheme(), getResources());

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    ViewUtil.setMarginForView(slidingTabLayout, 0, actionBarHeight, 0, 0);
                }else{
                    view.setPadding(0,actionBarHeight,0,0);
                }
            }
        }catch (Exception e){
            System.out.println("SPSlidingTabsFragment onViewCreated isActionBarOverLay: Check whether fragment is inside the activity which contains Action Bar.");
        }
    }

    // Subclass must implement below method to configure the Sliding Tabs Fragment.
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

    public int getDefaultHeightInPixelsOfActionBarPlusTabs(){
        int actionBarHeight = ActionBarUtil.getActionBarHeightInPixels(getActivity().getTheme(), getResources());
        return actionBarHeight +
                (int)(DeviceUtil.getDeviceDensity(getResources()) * SPSlidingTabsFragment.DEFAULT_TABS_HEIGHT_IN_DP);
    }

    public void configureRecyclerViewOnScrollListenerToHideUnHideActionBar(RecyclerView recyclerView){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int previousFirstVisibleItem = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                try {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
                    final int currentFirstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                    if (currentFirstVisibleItem > this.previousFirstVisibleItem) {
                        hideActionBar();

                    } else if (currentFirstVisibleItem < this.previousFirstVisibleItem) {
                        showActionBar();
                    }

                    this.previousFirstVisibleItem = currentFirstVisibleItem;
                }catch (ClassCastException e){
                    System.out.println("RecyclerView not using Linear Layout manager. ignoring scroll event.");
                }
            }
        });
    }


    public void hideActionBar(){
        if (isActionBarHidden){
            return;
        }

        this.hideUnHideActionBar(true);
    }

    public void showActionBar(){
        if (!isActionBarHidden){
            return;
        }

        this.hideUnHideActionBar(false);
    }

    private void hideUnHideActionBar(boolean hide){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){

            if (!isActionBarOverLay){
                System.out.println("Please set ActionBar as OverLay and set isActionBarOverLay to true");
                return;
            }

            try {
                int actionBarHeight = ActionBarUtil.getActionBarHeightInPixels(getActivity().getTheme(), getResources());

                int newSlidingTabY, newActionBarY;
                if (hide){
                    newSlidingTabY = 0;
                    newActionBarY = -actionBarHeight;
                }else{
                    newSlidingTabY = actionBarHeight;
                    newActionBarY = 0;
                }


                ObjectAnimator slidingTabAnimator = ObjectAnimator.ofFloat(
                        slidingTabLayout,
                        "y",
                        newSlidingTabY);

                ObjectAnimator actionBarAnimator = ObjectAnimator.ofFloat(
                        ActionBarUtil.getActionBarView(getActivity()),
                        "y",
                        newActionBarY);

                AnimationUtil.animateObjectAnimatorsWithDuration(
                        Arrays.asList(slidingTabAnimator,actionBarAnimator),
                        SPSlidingTabsFragment.DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS);

                isActionBarHidden = hide;

            }catch (Exception e){
                System.out.println("SPSlidingTabsFragment hideActionBar: Check whether fragment is inside the AppCompatActivity which contains Action Bar.");
            }
        }else{
            System.out.println("ObjectAnimator only available in API 11 or above. Ignoring your call.");
        }
    }

}
