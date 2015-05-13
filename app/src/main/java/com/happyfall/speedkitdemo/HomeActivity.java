package com.happyfall.speedkitdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.happyfall.speedkit.listing.SPListingCellGroup;
import com.happyfall.speedkit.listing.SPListingData;
import com.happyfall.speedkit.listing.gridview.SPGridAdapter;
import com.happyfall.speedkit.listing.gridview.SPGridView;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SPListingCellGroup cellGroup = new SPListingCellGroup(Integer.toString(R.layout.cell_title),
                10,
                "Sample");
        List<SPListingCellGroup> listingCellGroupList = new ArrayList<>();
        listingCellGroupList.add(cellGroup);

        SPListingData listingData = new SPListingData(listingCellGroupList);

        SPGridView gridView = (SPGridView)findViewById(R.id.Home_SPGridView);
        gridView.setListingData(listingData);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
