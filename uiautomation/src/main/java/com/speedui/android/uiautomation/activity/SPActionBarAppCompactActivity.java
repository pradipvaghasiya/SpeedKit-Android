package com.speedui.android.uiautomation.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Project: UIAutomation-Android
 * Created by Pradip on 5/27/2015.
 */
public class SPActionBarAppCompactActivity extends AppCompatActivity{
    protected boolean isActionBarOverlay;

    public void configureRecyclerViewOnScrollListenerToHideUnHideActionBar(RecyclerView recyclerView){
        if (!isActionBarOverlay){
            System.out.println("Please set ActionBar as OverLay and set isActionBarOverlay to true");
            return;
        }


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int previousFirstVisibleItem = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                try {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
                    final int currentFirstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                    if (currentFirstVisibleItem > this.previousFirstVisibleItem) {
                        getSupportActionBar().hide();

                    } else if (currentFirstVisibleItem < this.previousFirstVisibleItem) {
                        getSupportActionBar().show();
                    }

                    this.previousFirstVisibleItem = currentFirstVisibleItem;
                }catch (ClassCastException e){
                    System.out.println("RecyclerView not using Linear Layout manager. ignoring scroll event.");
                }
            }
        });
    }

}
