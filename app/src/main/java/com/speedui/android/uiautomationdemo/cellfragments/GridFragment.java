package com.speedui.android.uiautomationdemo.cellfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPEmptyViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderCustomisor;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;
import com.speedui.android.uiautomation.slidingtabs.SPSlidingTabsFragment;
import com.speedui.android.uiautomationdemo.R;
import com.speedui.android.util.ActionBarUtil;
import com.speedui.android.util.ViewUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pradipvaghasiya on 27/05/15.
 */
public class GridFragment extends Fragment implements SPViewHolderListener,SPViewHolderCustomisor{

    RecyclerView recyclerView;
    SPSlidingTabsFragment spSlidingTabsFragmentParent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cells_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setAdapter(getRecyclerAdapter());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);


        try {
            spSlidingTabsFragmentParent = (SPSlidingTabsFragment)getParentFragment();
            spSlidingTabsFragmentParent.configureRecyclerViewOnScrollListenerToHideUnHideActionBar(recyclerView);

        } catch (Exception e) {
            System.out.println("Ignore id parent is not sliding layout.");
        }

    }


    private SPRecyclerAdapter getRecyclerAdapter(){

        SPListingCellGroup emptyRowCellGroup = SPEmptyViewHolder.getCellGroupFromCellModels(Arrays.asList("HEADER"));

        SPListingCellGroup cellGroup = SPTitleViewHolder.getCellGroupFromCellModels(
                Arrays.asList("Title Cell 1", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell Last"));

        SPListingData listingData = new SPListingData(Arrays.asList(emptyRowCellGroup,cellGroup));
        //endregion

        return new SPRecyclerAdapter(listingData, this);

    }

    @Override
    public void customiseViewHolder(SPViewHolder viewHolder) {

        if (viewHolder instanceof SPEmptyViewHolder) {
            int height;
            if (spSlidingTabsFragmentParent != null) {
                height = spSlidingTabsFragmentParent.getDefaultHeightInPixelsOfActionBarPlusTabs();
            } else {
                height= ActionBarUtil.getActionBarHeightInPixels(getActivity().getTheme(), getResources());;
            }

            ViewUtil.setHeightForView(((SPEmptyViewHolder) viewHolder).emptyView, height);
        }else  if (viewHolder instanceof SPTitleViewHolder){
            ((SPTitleViewHolder) viewHolder).dividerLine.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void didSelectItem(View view, int position) {

    }
}
