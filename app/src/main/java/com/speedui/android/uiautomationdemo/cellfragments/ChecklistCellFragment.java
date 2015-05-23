package com.speedui.android.uiautomationdemo.cellfragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
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
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;
import com.speedui.android.uiautomationdemo.R;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class ChecklistCellFragment extends Fragment implements SPViewHolderListener {

    RecyclerView recyclerView;
    ArrayList<SPCheckListViewHolder.Model> modelArrayList;
    SPRecyclerAdapter spRecyclerAdapter;

    public ChecklistCellFragment() {
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

    void setupRecyclerView(){
        //region Listing Data Creation
        modelArrayList = new ArrayList<>();

        for(String titleText : Arrays.asList("Check List Cell 1", "Check List Cell 2", "Check List Cell 3")){

            SPCheckListViewHolder.Model cellModel = new SPCheckListViewHolder.Model();
            cellModel.titleText = titleText;
            cellModel.isSelected = true;

            modelArrayList.add(cellModel);
        }

        SPListingCellGroup cellGroup = SPCheckListViewHolder.getCellGroupFromCellModels(modelArrayList);
        SPListingData listingData = new SPListingData(Arrays.asList(cellGroup));
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

        if (modelArrayList.size()>position){
            modelArrayList.get(position).isSelected = !modelArrayList.get(position).isSelected;
            spRecyclerAdapter.notifyItemChanged(position);
        }

        System.out.println("Item DidSelect At : " + position);
    }
}
