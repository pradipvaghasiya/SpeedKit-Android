package com.speedui.android.uiautomationdemo.cellfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPBindingRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.ChecklistRModel;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.TitleRModel;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomationdemo.R;

import java.util.Arrays;

/**
 * Created by pradipvaghasiya on 27/05/15.
 */
public class GridFragment extends Fragment implements SPBindingViewHolderListener{

    RecyclerView recyclerView;
    SPBindingRecyclerAdapter adapter;
    SPListingData listingData = new SPListingData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cells_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        adapter = getRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }


    private SPBindingRecyclerAdapter getRecyclerAdapter(){
        if (listingData.size() == 0) {
            for (String itemTitle : Arrays.asList("Title Cell 1",
                    "Title Cell", "Title Cell", "Title Cell",
                    "Title Cell", "Title Cell", "Title Cell",
                    "Title Cell", "Title Cell", "Title Cell Last")) {

                listingData.add(new TitleRModel(itemTitle));
            }


            for (String titleText : Arrays.asList("Check List Cell 1", "Check List Cell 2", "Check List Cell 3")) {
                listingData.add(new ChecklistRModel(titleText, true));
            }
        }
        return new SPBindingRecyclerAdapter(listingData,this);
    }

    @Override
    public void didSelectItem(View view, int adapterPosition, int itemGroupPosition) {
        listingData.add(new TitleRModel("Hello"));
//        cellGroup1.items.add(new SPCheckListViewHolder.ViewModel("added after click", true));


//        SPListingData.ItemGroup cellGroup = SPCheckListViewHolder.getItemGroupFromItems(checklistItems);

//        ((SPBindingRecyclerAdapter)recyclerView.getAdapter()).getSpListingData().itemGroupList.add(cellGroup);

        //((SPBindingRecyclerAdapter)recyclerView.getAdapter()).getSpListingData().itemGroupList.add(cellGroup);

    }

}
