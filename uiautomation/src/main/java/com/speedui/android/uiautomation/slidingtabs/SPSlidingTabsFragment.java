package com.speedui.android.uiautomation.slidingtabs;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.android.common.view.SlidingTabLayout;
import com.speedui.android.uiautomation.R;
import com.speedui.android.util.ActionBarUtil;
import com.speedui.android.util.AnimationUtil;
import com.speedui.android.util.DeviceUtil;
import com.speedui.android.util.ViewUtil;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Abstract Fragment to show Sliding Tabs with child fragment as page content for each tab.
 * To create fragment user must subclass this class and implement the abstract methods
 * {@link #getTabTitles()} and {@link #getV4FragmentAt(int position)}.</p>
 *
 * <p>If you require animation support for hiding actionbar and moving sliding tabs on
 * top,
 * <p>1. Use Overlay action bar for Activity in which this fragment will be added.</p>
 * <p>2. In your subclass's default constructor you need to set {@link #isActionBarOverlay} to true.</p>
 * <p>3. Listen to scroll event of your scroll view and call {@link #hideActionBarAndMoveSlidingTabsOnTop()} and
 * {@link #showActionBarAndMoveSlidingTabsBelowIt()} based on requirement.</p>
 * <p>**Note** If you are using recycler view which has {@link LinearLayoutManager} or {@link android.support.v7.widget.GridLayoutManager}
 * then you can get this same functionality by just calling
 * {@link #configureRecyclerViewOnScrollListenerToHideUnHideActionBar(RecyclerView recyclerview)} and
 * it will take care of animation!!</p>
 *
 * <p>This class uses {@link com.example.android.common.view.SlidingTabLayout}
 * for sliding tabs. So customisation to Divider Color, Indicator Color and
 * Tab Background View is available.
 * Use can implement {@link android.support.v4.view.ViewPager.OnPageChangeListener}
 * for listening to tab change events.</p>
 */
abstract public class SPSlidingTabsFragment extends android.support.v4.app.Fragment {

    private static final int DEFAULT_ANIMATION_DURATION_IN_MILLISECONDS = 300;
    private static final int DEFAULT_TABS_HEIGHT_IN_DP = 40;
    private List<String> pageTitles;
    private ViewPager viewPager;
    private boolean isActionBarHidden = false;


    /**
     * Used by subclass to indicate whether Action Bar is overlaid on Activity.
     */
    protected boolean isActionBarOverlay;


    /**
     * Used by subclass to customise Tabs or listening to {@link android.support.v4.view.ViewPager.OnPageChangeListener}.
     */
    protected SlidingTabLayout slidingTabLayout;


    /**
     * Empty Constructor.
     */
    public SPSlidingTabsFragment(){

    }

    /**
     * <p>Inflates layout and returns View.</p>
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_sliding_tabs, container, false);
    }

    /**
     * Configures ViewPager, Sliding Tabs and adjsuts for padding or margin required incase of Overlay Action Bar.
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configure View Pager
        this.setupViewPager(view);

        // Configure Sliding Tabs
        this.setupSlidingTabs(view);

        // Adjust Layout padding/margin if actionbar is overlaid.
        this.adjustLayoutInCaseOfOverlayActionBar(view);

    }

    /**
     * <p>Gets ViewPager from Layout and sets its adapter
     * {@link com.speedui.android.uiautomation.slidingtabs.SPSlidingTabsFragment.SPSlidingTabsAdapter}</p>
     *
     * @param view Layoutview for this fragment.
     */
    private void setupViewPager(View view){
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SPSlidingTabsAdapter(getChildFragmentManager(), this.getTabTitles()));
    }

    /**
     * <p>Gets SlidingTabLayout from Layout and sets its adapter.</p>
     *
     * @param view Layoutview for this fragment.
     */
    private void setupSlidingTabs(View view){
        slidingTabLayout = (SlidingTabLayout)view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setViewPager(viewPager);

    }

    /**
     * <P>This method sets top margin of sliding tabs in order to add animation
     * functionality if ActionBar is overlaid.</P>
     *
     *
     * @param view Layoutview for this fragment.
     */
    private void adjustLayoutInCaseOfOverlayActionBar(View view){
        try {
            if (isActionBarOverlay){

                int actionBarHeight = ActionBarUtil.getActionBarHeightInPixels(getActivity().getTheme(), getResources());
                 ViewUtil.setMarginForView(slidingTabLayout, 0, actionBarHeight, 0, 0);

            }else{
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewPager.getLayoutParams();
                layoutParams.addRule(RelativeLayout.BELOW,R.id.sliding_tabs);

            }
        }catch (Exception e){
            System.out.println("SPSlidingTabsFragment onViewCreated isActionBarOverlay: Check whether fragment is inside the activity which contains Action Bar.");
        }
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
     * <p>SPSlidingTabsAdapter is FragmentPagerAdapter which will be used to generate
     * {@link SPSlidingTabsFragment} fragment which contains
     * Sliding Tabs.</p>
     *
     * <p>This Class uses abstract methods {@link #getTabTitles()} and
     * {@link #getV4FragmentAt(int)} to get the data.</p>
     */
    private class SPSlidingTabsAdapter extends FragmentPagerAdapter {
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

    /**
     * <p>Helper method to get default height of Action Bar + Sliding Tabs.
     * If you are using your own custom tabs then this method is not helpful.</p>
     * <p>This can be used by class who wants to add Extra row or Header on RecyclerView to adjust for overlaid
     * ActionBar + Sliding Tabs.</p>
     *
     * @return height of Action Bar + Sliding Tabs
     */
    public int getDefaultHeightInPixelsOfActionBarPlusTabs(){
        int actionBarHeight = ActionBarUtil.getActionBarHeightInPixels(getActivity().getTheme(), getResources());
        return actionBarHeight +
                (int)(DeviceUtil.getDeviceDensity(getResources()) * SPSlidingTabsFragment.DEFAULT_TABS_HEIGHT_IN_DP);
    }

    /**
     * <p>Anyone who uses {@link SPSlidingTabsFragment} and {@link RecyclerView} can use this method
     * to configure {@link android.support.v7.widget.RecyclerView.OnScrollListener}
     * for hiding ActionBar and moving Sliding tabs on top while scrolling up
     * and moving them back while scrolling down.</p>
     *
     * <p>This method currently supports {@link LinearLayoutManager} and {@link android.support.v7.widget.GridLayoutManager}.</p>
     * @param recyclerView For which OnScrollListener needs to be added.
     */
    public void configureRecyclerViewOnScrollListenerToHideUnHideActionBar(RecyclerView recyclerView){
        if (!isActionBarOverlay){
            System.out.println("Please set ActionBar as OverLay and set isActionBarOverlay to true");
            return;
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int previousFirstVisibleItem = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                try {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
                    final int currentFirstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                    if (currentFirstVisibleItem > this.previousFirstVisibleItem) {
                        hideActionBarAndMoveSlidingTabsOnTop();

                    } else if (currentFirstVisibleItem < this.previousFirstVisibleItem) {
                        showActionBarAndMoveSlidingTabsBelowIt();
                    }

                    this.previousFirstVisibleItem = currentFirstVisibleItem;
                }catch (ClassCastException e){
                    System.out.println("RecyclerView not using Linear/Grid Layout manager. ignoring scroll event.");
                }
            }
        });
    }

    /**
     * <p>Hides the Action Bar and moves Sliding tabs on top with smooth animation.</p>
     */
    public void hideActionBarAndMoveSlidingTabsOnTop(){
        if (isActionBarHidden){
            return;
        }

        this.animateActionBarAndSlidingTabs(true);
    }

    /**
     * <p>Shows Action Bar back and moves Sliding tabs below it with smooth animation.</p>
     */
    public void showActionBarAndMoveSlidingTabsBelowIt(){
        if (!isActionBarHidden){
            return;
        }

        this.animateActionBarAndSlidingTabs(false);
    }

    /**
     * <p>Helper method to animate ActionBar and Slidin tabs based on request from
     * {@link #hideActionBarAndMoveSlidingTabsOnTop()} and {@link #showActionBarAndMoveSlidingTabsBelowIt()} methods.</p>
     *
     *
     * @param hide Boolean to check whether hide animation or show animation required.
     */
    private void animateActionBarAndSlidingTabs(boolean hide){

        if (!isActionBarOverlay){
            System.out.println("Please set ActionBar as OverLay and set isActionBarOverlay to true");
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
            System.out.println("SPSlidingTabsFragment hideActionBarAndMoveSlidingTabsOnTop: Check whether fragment is inside the AppCompatActivity which contains Action Bar.");
        }

    }

}
