package com.happyfall.speedkitdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;

import com.happyfall.speedkit.listing.SPListingCellGroup;
import com.happyfall.speedkit.listing.SPListingData;
import com.happyfall.speedkit.listing.gridview.SPGridView;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    SPListingData listingData;
    SPGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SPListingCellGroup cellGroup = new SPListingCellGroup(R.layout.cell_title,
                10,
                "Sample");
        List<SPListingCellGroup> listingCellGroupList = new ArrayList<>();
        listingCellGroupList.add(cellGroup);

        listingData = new SPListingData(listingCellGroupList);

        gridView = (SPGridView)findViewById(R.id.Home_SPGridView);
        gridView.setListingData(listingData);

    }
}
