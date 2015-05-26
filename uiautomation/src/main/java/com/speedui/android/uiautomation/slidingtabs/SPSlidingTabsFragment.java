package com.speedui.android.uiautomation.slidingtabs;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.android.common.view.SlidingTabLayout;
import com.speedui.android.uiautomation.R;

import java.lang.reflect.Array;
import java.util.List;


abstract public class SPSlidingTabsFragment extends android.support.v4.app.Fragment {

    public SlidingTabLayout slidingTabLayout;
    private List<String> pageTitles;
    private ViewPager viewPager;
    protected boolean isActionBarOverLay;
    private View rootView;
    public boolean isActionBarHidden = false;
    public int actionBarPlusSlidingTabHeight = 0;

    public SPSlidingTabsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_sliding_tabs, container, false);
    }

    public static void setMarginForView (View v, int left, int top, int right, int bottom) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            v.requestLayout();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rootView = view.findViewById(R.id.sliding_tabs_fragment_layout);

        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SPSlidingTabsAdapter(getChildFragmentManager(),this.getTabTitles()));

        slidingTabLayout = (SlidingTabLayout)view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setViewPager(viewPager);

        try {
            if (isActionBarOverLay){
                TypedValue typedValue = new TypedValue();
                if (getActivity().getTheme().resolveAttribute(R.attr.actionBarSize,typedValue, true))
                {
                    int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data,getResources().getDisplayMetrics());

                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                        SPSlidingTabsFragment.setMarginForView(slidingTabLayout,0,actionBarHeight,0,0);
                        //view.setPadding(0,slidingTabLayout.getHeight(),0,0);
                    }else{
                        view.setPadding(0,actionBarHeight,0,0);
                    }
                }
            }
        }catch (Exception e){
            System.out.println("SPSlidingTabsFragment onViewCreated isActionBarOverLay: Check whether fragment is inside the activity which contains Action Bar.");
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

    public void hideActionBar(){

        if (isActionBarHidden){
            return;
        }


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){

            if (!isActionBarOverLay){
                System.out.println("Please set ActionBar as OverLay and set isActionBarOverLay to true");
                return;
            }

            try {
                ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
                TypedValue typedValue = new TypedValue();
                if (getActivity().getTheme().resolveAttribute(R.attr.actionBarSize,typedValue, true))
                {
                    int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data,getResources().getDisplayMetrics());
                    System.out.println(rootView.getY());
//                    slidingTabLayout.animate().translationY(-actionBarHeight);
//                    actionBar.hide();


                    ObjectAnimator slidingTabAnimator = ObjectAnimator.ofFloat(slidingTabLayout, "y", 0);
                    ObjectAnimator actionBarAnimator = ObjectAnimator.ofFloat(this.getActionBarView(), "y", -actionBarHeight);

                    ObjectAnimator[] objectAnimators = new ObjectAnimator[2];
                    objectAnimators[0] = slidingTabAnimator;
                    objectAnimators[1] = actionBarAnimator;

                    AnimatorSet animSetXY = new AnimatorSet();
                    animSetXY.playTogether(objectAnimators);
                    animSetXY.setDuration(300);//1sec
                    animSetXY.start();
                    isActionBarHidden = true;

                }

            }catch (Exception e){
                System.out.println("SPSlidingTabsFragment hideActionBar: Check whether fragment is inside the activity which contains Action Bar.");
            }
        }else{
            System.out.println("ViewPropertyAnimator only available in API 12 or above. Ignoring your call.");
        }

    }

    public View getActionBarView() {
        Window window = getActivity().getWindow();
        View v = window.getDecorView();
        return v.findViewById(R.id.action_bar_container);
    }

    public void showActionBar(){
        if (!isActionBarHidden){
            return;
        }


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){
            if (!isActionBarOverLay){
                System.out.println("Please set ActionBar as OverLay and set isActionBarOverLay to true");
                return;
            }

            try {
                ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//                slidingTabLayout.animate().translationY(0);
//                actionBar.show();

                ObjectAnimator slidingTabAnimator = ObjectAnimator.ofFloat(slidingTabLayout, "y", 112);
                ObjectAnimator actionBarAnimator = ObjectAnimator.ofFloat(this.getActionBarView(), "y", 0);

                ObjectAnimator[] objectAnimators = new ObjectAnimator[2];
                objectAnimators[0] = slidingTabAnimator;
                objectAnimators[1] = actionBarAnimator;

                AnimatorSet animSetXY = new AnimatorSet();
                animSetXY.playTogether(objectAnimators);
                animSetXY.setDuration(300);//1sec
                animSetXY.start();

                isActionBarHidden = false;

            }catch (Exception e){
                System.out.println("SPSlidingTabsFragment hideActionBar: Check whether fragment is inside the activity which contains Action Bar.");
            }
        }else{
            System.out.println("ViewPropertyAnimator only available in API 12 or above. Ignoring your call.");
        }

    }

}
