package com.speedui.android.uiautomation.listingautomation.gridview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellInterface;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingData;

import java.lang.reflect.Constructor;

/**
 * Project: SpeedKit-Android
 * Created by Pradip on 5/12/2015.
 */
public class SPGridAdapter extends BaseAdapter {

    SPListingData listingData;
    public SPGridAdapter(SPListingData listingData){
        this.listingData = listingData;
    }

    @Override
    public int getCount() {
        return this.listingData.getTotalCellCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SPListingCellInterface listingCell  = (SPListingCellInterface)convertView;
        SPListingData.CellGroupAndCellModelIndexReturnType cellGroupDetail = listingData.getListingCellGroupWithIndexOfCellModelList(position);

        if (listingCell == null){
            switch (cellGroupDetail.spListingCellGroup.cellType){
                case SubclassCell:
                    listingCell = this.createCellFromSubclass(cellGroupDetail.spListingCellGroup.cellClassName, parent.getContext());
                case XMLCell:
                    listingCell = this.createCellFromXML(cellGroupDetail.spListingCellGroup.cellLayoutId, parent);
            }
        }

        if (cellGroupDetail.spListingCellGroup.cellCommonModel != null){
            listingCell.configureCellUsing(cellGroupDetail.spListingCellGroup.cellCommonModel);
        }

        if (cellGroupDetail.spListingCellGroup.cellModelList.size() > cellGroupDetail.indexOfCellModelList &&
                cellGroupDetail.indexOfCellModelList >= 0){
            listingCell.configureCellUsing(cellGroupDetail.spListingCellGroup.cellModelList.get(cellGroupDetail.indexOfCellModelList));
        }

        return (ViewGroup)listingCell;
    }

    SPListingCellInterface createCellFromSubclass(String cellId, Context context){
        try {
            Class<?> viewClass = Class.forName(cellId);
            Constructor<?> constructor = viewClass.getConstructor(Context.class);
            return (SPListingCellInterface)constructor.newInstance(context);

        } catch (Exception e) {
            System.out.println("SpeedKit Error: CellId <" + cellId + "> is not valid Subclass of ViewGroup");
        }
        return null;
    }

    SPListingCellInterface createCellFromXML(int cellLayoutId, ViewGroup parent){
        return  (SPListingCellInterface) LayoutInflater
                .from(parent.getContext())
                .inflate(cellLayoutId, parent, false);
    }
}
