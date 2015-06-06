package com.speedui.android.uiautomationdemo.cellfragments;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
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
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPCheckListViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderCustomisor;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomationdemo.R;

import java.util.Arrays;

/**
 * Created by pradipvaghasiya on 27/05/15.
 */
public class GridFragment extends Fragment implements SPBindingViewHolderListener,SPBindingViewHolderCustomisor{

    RecyclerView recyclerView;
    ObservableList checklistItems;
    SPBindingRecyclerAdapter adapter;

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

        ObservableList titleItems = new ObservableArrayList();
        for (String itemTitle : Arrays.asList("Title Cell 1",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell Last")){



            titleItems.add(new SPTitleViewHolder.ViewModel(itemTitle));
        }

        SPListingData.ItemGroup cellGroup1 = SPTitleViewHolder.getItemGroupFromItems(titleItems);

        checklistItems = new ObservableArrayList<>();

        for(String titleText : Arrays.asList("Check List Cell 1", "Check List Cell 2", "Check List Cell 3")){
            checklistItems.add(new SPCheckListViewHolder.ViewModel(titleText, true));
        }


        SPListingData.ItemGroup cellGroup2 = SPCheckListViewHolder.getItemGroupFromItems(checklistItems);

         SPListingData listingData = new SPListingData(Arrays.asList(cellGroup1,cellGroup2));
        //endregion

        return new SPBindingRecyclerAdapter(listingData, this);

    }

    @Override
    public void didSelectItem(View view, int adapterPosition, int itemGroupPosition) {
        try {
            SPListingData.ItemGroup cellGroup = SPCheckListViewHolder.getItemGroupFromItems(checklistItems);
            adapter.getSpListingData().itemGroupList.remove(1);
        }catch (Exception e){
            //Ignore
        }
    }

    @Override
    public void customiseViewHolder(SPBindingViewHolder bindingViewHolder, int position) {
        ((SPTitleViewHolder) bindingViewHolder).dividerLine.setVisibility(View.INVISIBLE);
    }
}
