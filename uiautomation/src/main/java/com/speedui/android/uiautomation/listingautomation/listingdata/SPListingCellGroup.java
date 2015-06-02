package com.speedui.android.uiautomation.listingautomation.listingdata;

import java.util.List;

/**
 * Project: SpeedKit-Android
 * Created by Pradip on 5/12/2015.
 */
public class SPListingCellGroup {
    // Cell Type
    public enum SPCellType{
        SubclassCell,
        XMLCell
    }
    public SPCellType cellType;

    // Cell Layout ID
    public int cellLayoutId;

    // Cell Class Name
    public String cellClassName;

    // Cell Class Name
    public String cellViewHolderClassName;

    // Cell Model
    public List<?> cellModelList;

    // Cell Count
    public int getCellCount() {
        return cellModelList.size();
    }

    //region Constructors
    public SPListingCellGroup(int cellLayoutId, List<Object> cellModelList){
        this.cellClassName = null;
        this.cellLayoutId = cellLayoutId;
        this.cellViewHolderClassName = null;
        this.cellModelList = cellModelList;
        this.cellType = SPCellType.XMLCell;

    }

//    public SPListingCellGroup(int cellLayoutId, int cellCount, Object cellCommonModel){
//        this.cellClassName = null;
//        this.cellLayoutId = cellLayoutId;
//        this.cellViewHolderClassName = null;
//        this.cellModelList = new ArrayList<Object>();
//        this.cellType = SPCellType.XMLCell;
//    }


    public SPListingCellGroup(int cellLayoutId, String cellViewHolderClassName, List<?> cellModelList){
        this.cellClassName = null;
        this.cellLayoutId = cellLayoutId;
        this.cellViewHolderClassName = cellViewHolderClassName;
        this.cellModelList = cellModelList;
        this.cellType = SPCellType.XMLCell;

    }

//    public SPListingCellGroup(int cellLayoutId, String cellViewHolderClassName, int cellCount, Object cellCommonModel){
//        this.cellClassName = null;
//        this.cellLayoutId = cellLayoutId;
//        this.cellViewHolderClassName = cellViewHolderClassName;
//        this.cellModelList = new ArrayList<Object>();
//        this.cellType = SPCellType.XMLCell;
//    }
//

    public SPListingCellGroup(String cellClassName, String cellViewHolderClassName, List<Object> cellModelList){
        this.cellClassName = cellClassName;
        this.cellLayoutId = 0;
        this.cellViewHolderClassName = cellViewHolderClassName;
        this.cellModelList = cellModelList;
        this.cellType = SPCellType.SubclassCell;
    }

//    public SPListingCellGroup(String cellClassName, String cellViewHolderClassName, int cellCount, Object cellCommonModel) {
//        this.cellClassName = cellClassName;
//        this.cellLayoutId = 0;
//        this.cellViewHolderClassName = cellViewHolderClassName;
//        this.cellModelList = new ArrayList<Object>();
//        this.cellType = SPCellType.SubclassCell;
//    }
    //endregion
}


