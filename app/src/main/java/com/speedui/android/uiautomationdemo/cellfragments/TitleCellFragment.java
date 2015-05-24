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
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderCustomisor;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;
import com.speedui.android.uiautomationdemo.R;

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

    public TitleCellFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_cells_list, container, false);
        recyclerView = (RecyclerView)fragmentView.findViewById(R.id.recyclerView);

        this.setupRecyclerView();

        // Inflate the layout for this fragment
        return fragmentView;
    }

    void setupRecyclerView() {
        //region Listing Data Creation
        viewModelArrayList = new ArrayList<>();

        SPListingCellGroup cellGroup = SPTitleViewHolder.getCellGroupFromCellModels(Arrays.asList("Title 1", "Title 2", "Title 2"));
        SPListingData listingData = new SPListingData(Arrays.asList(cellGroup));
        //endregion

        spRecyclerAdapter = new SPRecyclerAdapter(listingData, this);

        //region RecyclerView Setup
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(spRecyclerAdapter);
        //endregion

    }

    @Override
    public void didSelectItem(View view, int position) {

    }


    @Override
    public void customiseViewHolder(SPViewHolder viewHolder) {
        if (viewHolder instanceof SPTitleViewHolder){
            SPTitleViewHolder titleViewHolder = (SPTitleViewHolder)viewHolder;
            titleViewHolder.dividerLine.setVisibility(View.INVISIBLE);
        }

    }
}
