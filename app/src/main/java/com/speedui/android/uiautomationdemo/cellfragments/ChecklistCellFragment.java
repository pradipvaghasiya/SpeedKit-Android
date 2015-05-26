package com.speedui.android.uiautomationdemo.cellfragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPCheckListViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPEmptyViewHolder;
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
public class ChecklistCellFragment extends android.support.v4.app.Fragment implements SPViewHolderListener,SPViewHolderCustomisor {

    public RecyclerView recyclerView;
    ArrayList<SPCheckListViewHolder.ViewModel> viewModelArrayList;
    SPRecyclerAdapter spRecyclerAdapter;
    SPSlidingTabsFragment parentFragment;

    public ChecklistCellFragment() {
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

    void setupRecyclerView(){
        //region Listing Data Creation
        viewModelArrayList = new ArrayList<>();

        for(String titleText : Arrays.asList("Check List Cell 1", "Check List Cell 2", "Check List Cell 3")){

            SPCheckListViewHolder.ViewModel cellViewModel = new SPCheckListViewHolder.ViewModel();
            cellViewModel.titleText = titleText;
            cellViewModel.isSelected = true;

            viewModelArrayList.add(cellViewModel);
        }

        SPListingCellGroup emptyRowCellGroup = SPEmptyViewHolder.getCellGroupFromCellModels(Arrays.asList("HEADER"));
        SPListingCellGroup cellGroup = SPCheckListViewHolder.getCellGroupFromCellModels(viewModelArrayList);
        SPListingData listingData = new SPListingData(Arrays.asList(emptyRowCellGroup,cellGroup));
        //endregion

        spRecyclerAdapter = new SPRecyclerAdapter(listingData,this);

        //region RecyclerView Setup
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(spRecyclerAdapter);
        //endregion

    }


    //region Cell Callbacks or Customization
    @Override
    public void didSelectItem(View view, int position) {

        if (viewModelArrayList.size()>position){
            viewModelArrayList.get(position).isSelected = !viewModelArrayList.get(position).isSelected;
            spRecyclerAdapter.notifyItemChanged(position);
        }

        System.out.println("Item DidSelect At : " + position);
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
