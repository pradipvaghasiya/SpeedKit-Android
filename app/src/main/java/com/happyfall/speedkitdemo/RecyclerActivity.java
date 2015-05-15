package com.happyfall.speedkitdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.happyfall.speedkit.cells.SPCheckListViewHolder;
import com.happyfall.speedkit.listing.SPListingCellGroup;
import com.happyfall.speedkit.listing.SPListingData;
import com.happyfall.speedkit.listing.recyclerview.SPRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RecyclerActivity extends ActionBarActivity implements SPRecyclerAdapter.ViewHolderNotifier {

    RecyclerView recyclerView;
    ArrayList<SPCheckListViewHolder.Model> modelArrayList;
    SPRecyclerAdapter spRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        modelArrayList = new ArrayList<>();

        for(String titleText : Arrays.asList("Title Cell 1","Title Cell 2","Title Cell 3",
                "Title Cell 4","Title Cell 5","Title Cell 6",
                "Title Cell 7","Title Cell 8","Title Cell 9","Title Cell 10 with very very long name to check how it behaves.",
                "Title Cell 11","Title Cell 12","Title Cell 13",
                "Title Cell 14","Title Cell 15","Title Cell 16",
                "Title Cell 17","Title Cell 18","Title Cell 19","Title Cell 20" )){

            SPCheckListViewHolder.Model cellModel = new SPCheckListViewHolder.Model();
            cellModel.titleText = titleText;
            cellModel.isSelected = true;

            modelArrayList.add(cellModel);
        }

        SPListingCellGroup cellGroup = new SPListingCellGroup(
                R.layout.recycler_cell_checklist,
                SPCheckListViewHolder.class.getName(),
                modelArrayList);

        List<SPListingCellGroup> listingCellGroupList = new ArrayList<>();
        listingCellGroupList.add(cellGroup);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void didSelectItem(View view) {
        int position = recyclerView.getChildPosition(view);

        modelArrayList.get(position).isSelected = modelArrayList.get(position).isSelected ? false : true;
        spRecyclerAdapter.notifyItemChanged(position);

        System.out.println("Item DidSelect At : " + position);
    }
}
