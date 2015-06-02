package com.speedui.android.uiautomationdemo.cellfragments;

import android.support.v4.app.Fragment;

import com.speedui.android.uiautomation.slidingtabs.ToolBarWithTabsFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 6/2/2015.
 */
public class ToolbarTabsFragment extends ToolBarWithTabsFragment {
    private List<String> tabTitles = Arrays.asList("Title Cell", "Check List Cell", "Grid Titles");
    private TitleCellFragment titleCellFragment;
    private ChecklistCellFragment checklistCellFragment;
    private GridFragment gridFragment;

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
