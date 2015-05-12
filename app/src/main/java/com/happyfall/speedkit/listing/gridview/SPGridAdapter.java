package com.happyfall.speedkit.listing.gridview;

import android.content.Context;
import android.opengl.ETC1;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.happyfall.speedkit.listing.SPListingCellInterface;
import com.happyfall.speedkit.listing.SPListingData;
import com.happyfall.speedkit.listing.SPListingViewInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Pradip on 5/12/2015.
 */
public class SPGridAdapter extends BaseAdapter {

    SPListingViewInterface listingView;
    public SPGridAdapter(SPListingViewInterface listingView){
        this.listingView = listingView;
    }

    @Override
    public int getCount() {
        return this.listingView.getListingData().getTotalCellCount();
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
        SPListingData listingData = listingView.getListingData();
        SPListingData.CellGroupAndCellModelIndexReturnType cellGroupDetail = listingData.getListingCellGroupWithIndexOfCellModelList(position);

        if (listingCell == null){
            switch (cellGroupDetail.spListingCellGroup.cellType){
                case SubclassCell:
                    listingCell = this.createCellFromSubclass(cellGroupDetail.spListingCellGroup.cellId, parent.getContext());
                case XMLCell:
                    listingCell = this.createCellFromXML(cellGroupDetail.spListingCellGroup.cellId, parent);
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

    SPListingCellInterface createCellFromXML(String cellId, ViewGroup parent){
        return  (SPListingCellInterface) LayoutInflater
                .from(parent.getContext())
                .inflate(Integer.parseInt(cellId), parent, false);
    }
}
