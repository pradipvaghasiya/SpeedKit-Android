package com.happyfall.speedkitdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.happyfall.speedkit.cells.SPTitleCell;
import com.happyfall.speedkit.cells.SPTitleViewHolder;
import com.happyfall.speedkit.listing.SPListingCellGroup;
import com.happyfall.speedkit.listing.SPListingData;
import com.happyfall.speedkit.listing.recyclerview.SPRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RecyclerActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        SPListingCellGroup cellGroup = new SPListingCellGroup(R.layout.recycler_cell_title, SPTitleViewHolder.class.getName(),
                Arrays.asList("Title Cell 1","Title Cell 2","Title Cell 3",
                        "Title Cell 4","Title Cell 5","Title Cell 6",
                        "Title Cell 7","Title Cell 8","Title Cell 9","Title Cell 10",
                        "Title Cell 1","Title Cell 2","Title Cell 3",
                        "Title Cell 4","Title Cell 5","Title Cell 6",
                        "Title Cell 7","Title Cell 8","Title Cell 9","Title Cell 10"));
        List<SPListingCellGroup> listingCellGroupList = new ArrayList<>();
        listingCellGroupList.add(cellGroup);

        SPListingData listingData = new SPListingData(listingCellGroupList);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SPRecyclerAdapter(listingData));

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
}
