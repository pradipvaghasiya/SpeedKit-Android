package com.happyfall.android.demo.cellfragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happyfall.android.swiftui.listingdata.ListingData;
import com.happyfall.android.swiftui.recyclerview.adapter.ListingAdapter;
import com.happyfall.android.demo.cells.TitleRModelListing;
import com.happyfall.android.swiftui.recyclerview.viewholder.ListingViewHolderListener;
import com.happyfall.android.demo.R;

import java.util.Arrays;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class TitleCellFragment extends android.support.v4.app.Fragment implements ListingViewHolderListener {
    RecyclerView recyclerView;
    ListingAdapter spRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    ListingData listingData = new ListingData();

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

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.setupRecyclerView();
    }

    void setupRecyclerView() {
        //Create SPRecycler Adapter
        spRecyclerAdapter = getRecyclerAdapter();

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(spRecyclerAdapter);

    }

    private ListingAdapter getRecyclerAdapter() {
        for (String itemTitle : Arrays.asList("Title Cell 1", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell",
                "Title Cell", "Title Cell", "Title Cell Last")) {

            listingData.add(new TitleRModelListing(itemTitle));

        }

        //endregion

        return new ListingAdapter(listingData,this);

    }

    @Override
    public void didSelectItem(View view, int adapterPosition, int itemGroupPosition) {
        try {
            listingData.remove(adapterPosition);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Ignore
        }
    }

}
