package com.happyfall.android.demo.cellfragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.happyfall.android.swiftui.reuse.fragment.toolbar.DarkToolBarTabsFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 6/2/2015.
 */
public class ToolbarTabsFragment extends DarkToolBarTabsFragment {
    private List<String> tabTitles = Arrays.asList("Title Cell", "Check List Cell", "Grid Titles");
    private TitleCellFragment titleCellFragment;
    private ChecklistCellFragment checklistCellFragment;
    private GridFragment gridFragment;

    public ToolbarTabsFragment(){
        this.titleCellFragment = new TitleCellFragment();
        this.checklistCellFragment = new ChecklistCellFragment();
        this.gridFragment = new GridFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.toolbar.setTitle("UIAutomation Demo");
        this.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public List<String> getTabTitles() {
        return tabTitles;
    }

    @Override
    public Fragment getV4FragmentAt(int position) {
        switch (position){
            case 0:
                return this.titleCellFragment;
            case 1:
                return this.checklistCellFragment;
            case 2:
                return this.gridFragment;
            default:
                return null;
        }

    }
}
