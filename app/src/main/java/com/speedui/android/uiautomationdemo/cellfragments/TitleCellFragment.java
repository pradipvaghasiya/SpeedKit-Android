package com.speedui.android.uiautomationdemo.cellfragments;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedui.android.uiautomation.activity.SPActivity;
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
import com.speedui.android.util.ActionBarUtil;
import com.speedui.android.util.DeviceUtil;
import com.speedui.android.util.ViewUtil;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class TitleCellFragment extends android.support.v4.app.Fragment implements SPViewHolderListener, SPViewHolderCustomisor {

    RecyclerView recyclerView;
    ArrayList<SPCheckListViewHolder.ViewModel> viewModelArrayList;
    SPRecyclerAdapter spRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    SPSlidingTabsFragment spSlidingTabsFragmentParent;

    public TitleCellFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cells_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        this.setupRecyclerView();
    }

    void setupRecyclerView() {
        //Create SPRecycler Adapter
        spRecyclerAdapter = getRecyclerAdapter();

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(spRecyclerAdapter);

        try {
            spSlidingTabsFragmentParent = (SPSlidingTabsFragment)getParentFragment();
        } catch (Exception e) {
            System.out.println("Ignore id parent is not sliding layout.");
        }

        if (spSlidingTabsFragmentParent != null){
            spSlidingTabsFragmentParent.configureRecyclerViewOnScrollListenerToHideUnHideActionBar(recyclerView);
        }else if (getActivity() instanceof SPActivity) {
            ((SPActivity) getActivity()).configureRecyclerViewOnScrollListenerToHideUnHideActionBar(recyclerView);
        }
    }

    private SPRecyclerAdapter getRecyclerAdapter(){
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

        return new SPRecyclerAdapter(listingData, this);

    }

    @Override
    public void didSelectItem(View view, int position) {

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
        }
    }

}
