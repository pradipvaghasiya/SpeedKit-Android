package com.happyfall.speedkitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import happyfall.speedkit.cells.SPCheckListViewHolder;
import happyfall.speedkit.cells.SPTitleViewHolder;
import happyfall.speedkit.listing.SPListingCellGroup;
import happyfall.speedkit.listing.SPListingData;
import happyfall.speedkit.listing.recyclerview.SPRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements SPRecyclerAdapter.ViewHolderNotifier {

    RecyclerView recyclerView;
    ArrayList<SPCheckListViewHolder.Model> modelArrayList;
    SPRecyclerAdapter spRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        modelArrayList = new ArrayList<>();

        for(String titleText : Arrays.asList("Check List Cell 1","Check List Cell 2","Check List Cell 3",
                "Check List Cell 4","Check List Cell 5","Check List Cell 6",
                "Check List Cell 7","Check List Cell 8","Check List Cell 9","Check List Cell 10 with very very long name to check how it behaves.",
                "Check List Cell 11","Check List Cell 12","Check List Cell 13",
                "Check List Cell 14","Check List Cell 15","Check List Cell 16",
                "Check List Cell 17","Check List Cell 18","Check List Cell 19","Check List Cell 20" )){

            SPCheckListViewHolder.Model cellModel = new SPCheckListViewHolder.Model();
            cellModel.titleText = titleText;
            cellModel.isSelected = true;

            modelArrayList.add(cellModel);
        }

        SPListingCellGroup cellGroup = new SPListingCellGroup(
                R.layout.recycler_cell_checklist,
                SPCheckListViewHolder.class.getName(),
                modelArrayList);

        SPListingCellGroup cellGroup1 = new SPListingCellGroup(
                R.layout.recycler_cell_title,
                SPTitleViewHolder.class.getName(),
                Arrays.asList("Title 1", "Title 2"));


        List<SPListingCellGroup> listingCellGroupList = new ArrayList<>();
        listingCellGroupList.add(cellGroup);
        listingCellGroupList.add(cellGroup1);

        SPListingData listingData = new SPListingData(listingCellGroupList);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        spRecyclerAdapter = new SPRecyclerAdapter(listingData,this);
        recyclerView.setAdapter(spRecyclerAdapter);

    }

    @Override
    public void didSelectItem(View view) {
        int position = recyclerView.getChildLayoutPosition(view);

        if (modelArrayList.size()>position){
            modelArrayList.get(position).isSelected = !modelArrayList.get(position).isSelected;
            spRecyclerAdapter.notifyItemChanged(position);
        }

        System.out.println("Item DidSelect At : " + position);
    }
}
