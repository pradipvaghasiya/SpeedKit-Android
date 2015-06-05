package com.speedui.android.uiautomationdemo.cellfragments;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedui.android.uiautomation.actionbar.SPActionBarAppCompactActivity;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPBindingRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.cells.SPTitleViewHolder;
import com.speedui.android.uiautomation.actionbar.slidingtabs.SPSlidingTabsFragment;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderCustomisor;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomationdemo.R;

import java.util.Arrays;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class TitleCellFragment extends android.support.v4.app.Fragment implements SPBindingViewHolderListener,SPBindingViewHolderCustomisor {
    ObservableList titleItems;
    RecyclerView recyclerView;
    SPBindingRecyclerAdapter spRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    SPSlidingTabsFragment spSlidingTabsFragmentParent;

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

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        this.setupRecyclerView();
    }

    void setupRecyclerView() {
        //Create SPRecycler Adapter
        spRecyclerAdapter = getRecyclerAdapter();

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(spRecyclerAdapter);

        try {
            spSlidingTabsFragmentParent = (SPSlidingTabsFragment)getParentFragment();
        } catch (Exception e) {
            System.out.println("Ignore id parent is not sliding layout.");
        }

        if (spSlidingTabsFragmentParent != null){
            spSlidingTabsFragmentParent.configureRecyclerViewOnScrollListenerToHideUnHideActionBar(recyclerView);
        }else if (getActivity() instanceof SPActionBarAppCompactActivity) {
            ((SPActionBarAppCompactActivity) getActivity()).configureRecyclerViewOnScrollListenerToHideUnHideActionBar(recyclerView);
        }
    }

    private SPBindingRecyclerAdapter getRecyclerAdapter(){
        titleItems = new ObservableArrayList();
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
        SPListingData.ItemGroup titleItemGroup = SPTitleViewHolder.getItemGroupFromItems(titleItems);

        SPListingData listingData = new SPListingData(Arrays.asList(titleItemGroup));
        //endregion

        return new SPBindingRecyclerAdapter(listingData, this);

    }

    @Override
    public void didSelectItem(View view, int position) {
        titleItems.remove(position);
    }


    @Override
    public void customiseViewHolder(SPBindingViewHolder bindingViewHolder, int position) {

    }
}
