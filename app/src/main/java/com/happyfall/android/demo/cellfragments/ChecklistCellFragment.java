package com.happyfall.android.demo.cellfragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happyfall.android.swiftui.listingdata.ListingData;
import com.happyfall.android.swiftui.recyclerview.adapter.ListingAdapter;
import com.happyfall.android.demo.cells.ChecklistRModelListing;
import com.happyfall.android.swiftui.recyclerview.viewholder.ListingViewHolderListener;
import com.happyfall.android.demo.R;

import java.util.Arrays;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class ChecklistCellFragment extends android.support.v4.app.Fragment
        implements ListingViewHolderListener
{

    public RecyclerView recyclerView;
    ListingAdapter spRecyclerAdapter;
    ListingData listingData = new ListingData();

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
        for(String titleText : Arrays.asList("Check List Cell 1", "Check List Cell 2", "Check List Cell 3")){
            listingData.add(new ChecklistRModelListing(titleText, true));
        }

        spRecyclerAdapter = new ListingAdapter(listingData,this);

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
//                models.size()> checklistPosition){
//            models.get(checklistPosition).ismSelected = !models.get(checklistPosition).ismSelected;
//            spRecyclerAdapter.notifyItemChanged(position);
//        }
//
//        System.out.println("Item DidSelect At : " + position );
    }

}
