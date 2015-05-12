package com.happyfall.speedkit.listing;

import java.util.List;

/**
 * Created by Pradip on 5/12/2015.
 */
final public class SPListingData {
    List<SPListingCellGroup> cellGroupList;

    // Create Listing Data with given section array.
    public SPListingData(List<SPListingCellGroup> cellGroupList){
        this.cellGroupList = cellGroupList;
    }

    public int getTotalCellCount(){
        int cellCount = 0;
        for (SPListingCellGroup cellGroup : cellGroupList){
            cellCount += cellGroup.cellCount;
        }
        return cellCount;
    }

    public class CellGroupAndCellModelIndexReturnType{
        public SPListingCellGroup spListingCellGroup;
        public int indexOfCellModelList;
    }

    public CellGroupAndCellModelIndexReturnType getListingCellGroupWithIndexOfCellModelList(int index){
        CellGroupAndCellModelIndexReturnType returnType = new CellGroupAndCellModelIndexReturnType();

        int startIndexOfCellFound  = 0;
        int totalCellCountParsedFromCellGroupArray  = 0;
        for(SPListingCellGroup cellGroup : this.cellGroupList){
            totalCellCountParsedFromCellGroupArray += cellGroup.cellCount;
            if (index < totalCellCountParsedFromCellGroupArray){
                returnType.spListingCellGroup = cellGroup;
                returnType.indexOfCellModelList = index - startIndexOfCellFound;
                break;
            }

            startIndexOfCellFound += startIndexOfCellFound + cellGroup.cellCount;
        }
        return returnType;
    }
}
