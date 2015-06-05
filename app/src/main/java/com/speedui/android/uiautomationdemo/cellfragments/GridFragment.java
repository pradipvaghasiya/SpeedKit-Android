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
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderCustomisor;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomation.actionbar.slidingtabs.SPSlidingTabsFragment;
import com.speedui.android.uiautomationdemo.R;

import java.util.Arrays;

/**
 * Created by pradipvaghasiya on 27/05/15.
 */
public class GridFragment extends Fragment implements SPBindingViewHolderListener,SPBindingViewHolderCustomisor{

    RecyclerView recyclerView;
    SPSlidingTabsFragment spSlidingTabsFragmentParent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cells_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setAdapter(getRecyclerAdapter());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) return 2;

                return 1;
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);


        try {
            spSlidingTabsFragmentParent = (SPSlidingTabsFragment)getParentFragment();
            spSlidingTabsFragmentParent.configureRecyclerViewOnScrollListenerToHideUnHideActionBar(recyclerView);

        } catch (Exception e) {
            System.out.println("Ignore id parent is not sliding layout.");
        }

    }


    private SPBindingRecyclerAdapter getRecyclerAdapter(){

        ObservableList titleItems = new ObservableArrayList();
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
                "Title Cell", "Title Cell", "Title Cell Last")){



            titleItems.add(new SPTitleViewHolder.ViewModel(itemTitle));

        }

        SPListingData.ItemGroup cellGroup = SPTitleViewHolder.getItemGroupFromItems(titleItems);

        SPListingData listingData = new SPListingData(Arrays.asList(cellGroup));
        //endregion

        return new SPBindingRecyclerAdapter(listingData, this);

    }

    @Override
    public void didSelectItem(View view, int position) {

    }

    @Override
    public void customiseViewHolder(SPBindingViewHolder bindingViewHolder, int position) {
        ((SPTitleViewHolder) bindingViewHolder).dividerLine.setVisibility(View.INVISIBLE);
    }
}
