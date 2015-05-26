package com.speedui.android.uiautomationdemo.cellfragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.common.view.SlidingTabLayout;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPCheckListViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPEmptyViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderCustomisor;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;
import com.speedui.android.uiautomation.slidingtabs.SPSlidingTabsFragment;
import com.speedui.android.uiautomationdemo.R;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class TitleCellFragment extends android.support.v4.app.Fragment implements SPViewHolderListener, SPViewHolderCustomisor {

    public RecyclerView recyclerView;
    ArrayList<SPCheckListViewHolder.ViewModel> viewModelArrayList;
    SPRecyclerAdapter spRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    SPSlidingTabsFragment parentFragment;

    public TitleCellFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_cells_list, container, false);
        recyclerView = (RecyclerView)fragmentView.findViewById(R.id.recyclerView);

        this.setupRecyclerView();

        try {
            parentFragment = (SPSlidingTabsFragment)getParentFragment();
        } catch (Exception e) {
            System.out.println("Ignore id parent is not sliding layout.");
        }

        // Inflate the layout for this fragment
        return fragmentView;
    }

    void setupRecyclerView() {
        //region Listing Data Creation
        viewModelArrayList = new ArrayList<>();

        SPListingCellGroup emptyRowCellGroup = SPEmptyViewHolder.getCellGroupFromCellModels(Arrays.asList("HEADER"));

        SPListingCellGroup cellGroup = SPTitleViewHolder.getCellGroupFromCellModels(
                Arrays.asList("Title Cell 1", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell",
                        "Title Cell", "Title Cell", "Title Cell"));

        SPListingData listingData = new SPListingData(Arrays.asList(emptyRowCellGroup,cellGroup));
        //endregion

        spRecyclerAdapter = new SPRecyclerAdapter(listingData, this);

        //region RecyclerView Setup
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(spRecyclerAdapter);
        //endregion

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                final int currentFirstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (currentFirstVisibleItem > this.mLastFirstVisibleItem) {

                    // If Parent fragment is not present then hide actionbar directly.
                    if (parentFragment != null ){
                        parentFragment.hideActionBar();
                    }else{
                        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
                    }


                } else if (currentFirstVisibleItem < this.mLastFirstVisibleItem) {
                    if (parentFragment != null){
                        parentFragment.showActionBar();
                    }else{
                        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                    }
                }

                this.mLastFirstVisibleItem = currentFirstVisibleItem;
            }
        });

    }

    @Override
    public void didSelectItem(View view, int position) {

    }


    @Override
    public void customiseViewHolder(SPViewHolder viewHolder) {

        if (viewHolder instanceof SPEmptyViewHolder) {
            ViewGroup.LayoutParams params =  ((SPEmptyViewHolder) viewHolder).emptyView.getLayoutParams();
            if (parentFragment != null){
                params.height = 112 + 80;
            }else{
                params.height = 112;
            }
            ((SPEmptyViewHolder) viewHolder).emptyView.setLayoutParams(params);
        }
    }
}
