package com.happyfall.android.demo.cellfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.happyfall.android.demo.cells.ChecklistRModelListing;
import com.happyfall.android.demo.cells.TitleRModelListing;
import com.happyfall.android.swiftui.listing.ListingData;
import com.happyfall.android.swiftui.listing.adapter.ListingAdapter;
import com.happyfall.android.swiftui.listing.viewholder.ListingViewHolderListener;
import com.happyfall.android.demo.R;

import java.util.Arrays;

/**
 * Created by pradipvaghasiya on 27/05/15.
 */
public class GridFragment extends Fragment implements ListingViewHolderListener {

    RecyclerView recyclerView;
    ListingAdapter adapter;
    ListingData listingData = new ListingData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listing_data,container,false);
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


    private ListingAdapter getRecyclerAdapter(){
        if (listingData.size() == 0) {
            for (String itemTitle : Arrays.asList("Title Cell 1",
                    "Title Cell", "Title Cell", "Title Cell",
                    "Title Cell", "Title Cell", "Title Cell",
                    "Title Cell", "Title Cell", "Title Cell Last")) {

                listingData.add(new TitleRModelListing(itemTitle));
            }


            for (String titleText : Arrays.asList("Check List Cell 1", "Check List Cell 2", "Check List Cell 3")) {
                listingData.add(new ChecklistRModelListing(titleText, true));
            }
        }
        return new ListingAdapter(listingData,this);
    }

    @Override
    public void didSelectItem(View view, int adapterPosition, int itemGroupPosition) {
        listingData.add(new TitleRModelListing("Hello"));
//        cellGroup1.items.add(new SPCheckListViewHolder.ListingViewModel("added after click", true));


//        ListingData.ItemGroup cellGroup = SPCheckListViewHolder.getItemGroupFromItems(checklistItems);

//        ((ListingAdapter)recyclerView.getAdapter()).getSpListingData().itemGroupList.add(cellGroup);

        //((ListingAdapter)recyclerView.getAdapter()).getSpListingData().itemGroupList.add(cellGroup);

    }

}
