package com.speedui.android.uiautomationdemo.cellfragments;

import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPBindingRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPCheckListViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderCustomisor;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomationdemo.R;

import java.util.Arrays;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class ChecklistCellFragment extends android.support.v4.app.Fragment implements SPBindingViewHolderListener,SPBindingViewHolderCustomisor{

    public RecyclerView recyclerView;
    ObservableArrayList<SPCheckListViewHolder.ViewModel> viewModelArrayList;
    SPBindingRecyclerAdapter spRecyclerAdapter;

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
        viewModelArrayList = new ObservableArrayList<>();

        for(String titleText : Arrays.asList("Check List Cell 1", "Check List Cell 2", "Check List Cell 3")){
            viewModelArrayList.add(new SPCheckListViewHolder.ViewModel(titleText,true));
        }


        SPListingData.ItemGroup cellGroup = SPCheckListViewHolder.getItemGroupFromItems(viewModelArrayList);
        SPListingData listingData = new SPListingData(Arrays.asList(cellGroup));
        //endregion

        spRecyclerAdapter = new SPBindingRecyclerAdapter(listingData,this);

        //region RecyclerView Setup
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(spRecyclerAdapter);
        //endregion

    }


    //region Cell Callbacks or Customization
    @Override
    public void didSelectItem(View view, int adapterPosition, int itemGroupPosition) {
//        int checklistPosition = position - 1;  //Offset to cover header view row.
//
//        if (checklistPosition >= 0 &&
//                viewModelArrayList.size()> checklistPosition){
//            viewModelArrayList.get(checklistPosition).isSelected = !viewModelArrayList.get(checklistPosition).isSelected;
//            spRecyclerAdapter.notifyItemChanged(position);
//        }
//
//        System.out.println("Item DidSelect At : " + position );
    }


    @Override
    public void customiseViewHolder(SPBindingViewHolder bindingViewHolder, int position) {

    }
}
