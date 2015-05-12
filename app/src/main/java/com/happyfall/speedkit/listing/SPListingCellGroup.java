package com.happyfall.speedkit.listing;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pradip on 5/12/2015.
 */
public class SPListingCellGroup {
    public enum SPCellType{
        SubclassCell,
        XMLCell
    }
    public SPCellType cellType;

    public String cellId;
    public void setCellId(String cellId) {
        if (this.cellId != null && !this.cellId.equals(cellId)){
            this.cellId = cellId;
            this.removeCellIdValueIfInValid();
        }
    }

    public List<Object> cellModelList;
    public void setCellModelList(List<Object> cellModelList) {
        this.cellModelList = cellModelList;
        this.cellCount = cellModelList.size();
    }

    public Object cellCommonModel;

    int cellCount;
    public void setCellCount(int cellCount) {
        this.cellCount = cellCount;
        int cellModelCount = this.cellModelList.size();
        if (cellModelCount > 0 && this.cellCount != cellModelCount) {
            System.out.println("SpeedKit Warning: Cell Model Array contains some values so Cell Count will always be CellModelArray.count. Ignoring your update.");
            this.cellCount = cellModelCount;
        }
    }

    //Constructors
    SPListingCellGroup(String cellId, List<Object> cellModelList){
        this.cellId = cellId;
        this.cellModelList = cellModelList;
        this.cellCount = cellModelList.size();
        this.cellType = SPCellType.XMLCell;
        this.cellCommonModel = null;

        // Remove cell id if invalid
        this.removeCellIdValueIfInValid();
    }

    SPListingCellGroup(String cellId, List<Object> cellModelList, SPCellType cellType){
        this.cellId = cellId;
        this.cellModelList = cellModelList;
        this.cellCount = cellModelList.size();
        this.cellType = cellType;
        this.cellCommonModel = null;

        // Remove cell id if invalid
        this.removeCellIdValueIfInValid();
    }

    SPListingCellGroup(String cellId, Object cellCommonModel , List<Object> cellModelList){
        this.cellId = cellId;
        this.cellModelList = cellModelList;
        this.cellCount = cellModelList.size();
        this.cellType = SPCellType.XMLCell;
        this.cellCommonModel = cellCommonModel;

        // Remove cell id if invalid
        this.removeCellIdValueIfInValid();
    }

    SPListingCellGroup(String cellId, Object cellCommonModel , List<Object> cellModelList, SPCellType cellType){
        this.cellId = cellId;
        this.cellModelList = cellModelList;
        this.cellCount = cellModelList.size();
        this.cellType = cellType;
        this.cellCommonModel = cellCommonModel;

        // Remove cell id if invalid
        this.removeCellIdValueIfInValid();
    }

    SPListingCellGroup(String cellId, int cellCount, Object cellCommonModel){
        this.cellId = cellId;
        this.cellModelList = new ArrayList<Object>();
        this.cellCount = cellCount;
        this.cellType = SPCellType.XMLCell;
        this.cellCommonModel = cellCommonModel;

        // Remove cell id if invalid
        this.removeCellIdValueIfInValid();
    }

    SPListingCellGroup(String cellId, int cellCount, Object cellCommonModel, SPCellType cellType){
        this.cellId = cellId;
        this.cellModelList = new ArrayList<Object>();
        this.cellCount = cellCount;
        this.cellType = cellType;
        this.cellCommonModel = cellCommonModel;

        // Remove cell id if invalid
        this.removeCellIdValueIfInValid();
    }

    void removeCellIdValueIfInValid(){
        if(this.checkWhetherCellIdIsValid() == false){
            this.cellId = null;
        }
    }

    boolean checkWhetherCellIdIsValid(){
        if (this.cellId != null){
            switch (this.cellType){
                case SubclassCell:
                    try {
                        return View.class.isAssignableFrom(Class.forName(this.cellId));
                    } catch (ClassNotFoundException e) {
                        return false;
                    }
                default:
                    return true;
            }
        }
        return false;
    }
}


