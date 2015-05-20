package com.happyfall.speedkitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import happyfall.speedkit.cells.SPCheckListViewHolder;
import happyfall.speedkit.cells.SPTitleViewHolder;
import happyfall.speedkit.listing.SPListingCellGroup;
import happyfall.speedkit.listing.SPListingData;
import happyfall.speedkit.listing.recyclerview.SPRecyclerAdapter;
import happyfall.speedkit.listing.recyclerview.SPViewHolderListener;

import java.util.ArrayList;
import java.util.Arrays;

public class RecyclerActivity extends AppCompatActivity
        implements SPViewHolderListener, SPCheckListViewHolder.Customizor, SPTitleViewHolder.Customizor{

    RecyclerView recyclerView;
    ArrayList<SPCheckListViewHolder.Model> modelArrayList;
    SPRecyclerAdapter spRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        //region Listing Data Creation
        modelArrayList = new ArrayList<>();

        for(String titleText : Arrays.asList("Check List Cell 1","Check List Cell 2","Check List Cell 3",
                "Check List Cell 4","Check List Cell 5","Check List Cell 6",
                "Check List Cell 7","Check List Cell 8","Check List Cell 9","Check List Cell 10",
                "Check List Cell 11","Check List Cell 12","Check List Cell 13",
                "Check List Cell 14","Check List Cell 15","Check List Cell 16",
                "Check List Cell 17","Check List Cell 18","Check List Cell 19","Check List Cell 20" )){

            SPCheckListViewHolder.Model cellModel = new SPCheckListViewHolder.Model();
            cellModel.titleText = titleText;
            cellModel.isSelected = true;

            modelArrayList.add(cellModel);
        }

        SPListingCellGroup cellGroup1 = SPCheckListViewHolder.getCellGroupFromCellModels(modelArrayList);
        SPListingCellGroup cellGroup2 = SPTitleViewHolder.getCellGroupFromCellModels(Arrays.asList("Title 1", "Title 2"));
        SPListingData listingData = new SPListingData(Arrays.asList(cellGroup1,cellGroup2));
        //endregion

        spRecyclerAdapter = new SPRecyclerAdapter(listingData,this);

        //region RecyclerView Setup
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(spRecyclerAdapter);
        //endregion

    }

    //region Cell Callbacks or Customization
    @Override
    public void didSelectItem(View view) {
        int position = recyclerView.getChildLayoutPosition(view);

        if (modelArrayList.size()>position){
            modelArrayList.get(position).isSelected = !modelArrayList.get(position).isSelected;
            spRecyclerAdapter.notifyItemChanged(position);
        }

        System.out.println("Item DidSelect At : " + position);
    }

    @Override
    public void customizeTextView(TextView textView, SPTitleViewHolder viewHolder) {
        System.out.println("Customise Textview for SPTitleViewHolder");
    }

    @Override
    public void customizeTextView(TextView textView, SPCheckListViewHolder spCheckListViewHolder) {
        System.out.println("Customise Textview for SPCheckListViewHolder");
    }
    //endregion
}
