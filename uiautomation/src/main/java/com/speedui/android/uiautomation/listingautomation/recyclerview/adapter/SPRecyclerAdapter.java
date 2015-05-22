package com.speedui.android.uiautomation.listingautomation.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SingleSelector;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;

import java.lang.reflect.Constructor;

/**
 * Created by Pradip on 5/13/2015.
 */

public class SPRecyclerAdapter extends RecyclerView.Adapter<SPViewHolder> {
    // SPListing Data
    public SPListingData spListingData;
    public MultiSelector multiSelector;
    SPViewHolderListener listener;

    public SPRecyclerAdapter(SPListingData spListingData, SPViewHolderListener listener){
        super();
        this.spListingData = spListingData;
        this.listener = listener;
        this.multiSelector = new SingleSelector();
    }

    public SPRecyclerAdapter(SPListingData spListingData, SPViewHolderListener listener, MultiSelector multiSelector){
        super();
        this.spListingData = spListingData;
        this.listener = listener;
        this.multiSelector = multiSelector ;
    }



    @Override
    public int getItemViewType(int position) {
        return spListingData.getIndexOfCellGroupFrom(position);
    }

    @Override
    public SPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listingCell = null;
        SPListingCellGroup cellGRoup = spListingData.spCellGroupList.get(viewType);

        switch (cellGRoup.cellType){
            case SubclassCell:
                listingCell = this.createCellFromSubclass(cellGRoup.cellClassName, parent.getContext());
            case XMLCell:
                listingCell = this.createCellFromXML(cellGRoup.cellLayoutId, parent);
        }

        // Check Whether Listing Cell created properly.
        if (listingCell == null){
            System.out.println("SpeedKit Error: Listing Cell not created for " + cellGRoup);
            return null;
        }

        try {
            Class<?> viewClass = Class.forName(cellGRoup.cellViewHolderClassName);
            Constructor<?> constructor = viewClass.getConstructor(View.class,SPViewHolderListener.class,MultiSelector.class);

            SPViewHolder viewHolder = (SPViewHolder)constructor.newInstance(listingCell,this.listener,this.multiSelector);

            return viewHolder;

        } catch (Exception e) {
            System.out.println("SpeedKit Error: View Holder Class <" + cellGRoup.cellViewHolderClassName + "> is not valid : " + e.toString());
        }

        return null;
    }

    View createCellFromSubclass(String cellId, Context context){
        try {
            Class<?> viewClass = Class.forName(cellId);
            Constructor<?> constructor = viewClass.getConstructor(Context.class);
            return (View)constructor.newInstance(context);

        } catch (Exception e) {
            System.out.println("SpeedKit Error: CellId <" + cellId + "> is not valid Subclass of ViewGroup");
        }
        return null;
    }

    View createCellFromXML(int cellLayoutId, ViewGroup parent){
        return LayoutInflater
                .from(parent.getContext())
                .inflate(cellLayoutId, parent, false);
    }

    @Override
    public void onBindViewHolder(SPViewHolder holder, int position) {
        SPListingData.CellGroupAndCellModelIndexReturnType cellGroupDetail = spListingData.getListingCellGroupWithIndexOfCellModelList(position);

        if (cellGroupDetail.spListingCellGroup.cellCommonModel != null){
            holder.configureCellUsing(cellGroupDetail.spListingCellGroup.cellCommonModel);
        }

        if (cellGroupDetail.spListingCellGroup.cellModelList.size() > cellGroupDetail.indexOfCellModelList &&
                cellGroupDetail.indexOfCellModelList >= 0){
            holder.configureCellUsing(cellGroupDetail.spListingCellGroup.cellModelList.get(cellGroupDetail.indexOfCellModelList));
        }

    }

    @Override
    public int getItemCount() {
        return spListingData.getTotalCellCount();
    }
}
