package happyfall.speedkit.listing;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
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
    public void setCellModelList(List<?> cellModelList) {
        this.cellModelList = cellModelList;
        this.cellCount = cellModelList.size();
    }

    // Cell Common Model
    public Object cellCommonModel;

    // Cell Count
    int cellCount;
    public int getCellCount() {
        return cellCount;
    }
    public void setCellCount(int cellCount) {
        this.cellCount = cellCount;
        int cellModelCount = this.cellModelList.size();
        if (cellModelCount > 0 && this.cellCount != cellModelCount) {
            System.out.println("SpeedKit Warning: Cell Model Array contains some values so Cell Count will always be CellModelArray.count. Ignoring your update.");
            this.cellCount = cellModelCount;
        }
    }

    //Constructors
    public SPListingCellGroup(int cellLayoutId, List<Object> cellModelList){
        this.cellClassName = null;
        this.cellLayoutId = cellLayoutId;
        this.cellViewHolderClassName = null;
        this.cellModelList = cellModelList;
        this.cellCount = cellModelList.size();
        this.cellType = SPCellType.XMLCell;
        this.cellCommonModel = null;

    }

    public SPListingCellGroup(int cellLayoutId, int cellCount, Object cellCommonModel){
        this.cellClassName = null;
        this.cellLayoutId = cellLayoutId;
        this.cellViewHolderClassName = null;
        this.cellModelList = new ArrayList<Object>();
        this.cellCount = cellCount;
        this.cellType = SPCellType.XMLCell;
        this.cellCommonModel = cellCommonModel;
    }


    public SPListingCellGroup(int cellLayoutId, String cellViewHolderClassName, List<?> cellModelList){
        this.cellClassName = null;
        this.cellLayoutId = cellLayoutId;
        this.cellViewHolderClassName = cellViewHolderClassName;
        this.cellModelList = cellModelList;
        this.cellCount = cellModelList.size();
        this.cellType = SPCellType.XMLCell;
        this.cellCommonModel = null;

    }

    public SPListingCellGroup(int cellLayoutId, String cellViewHolderClassName, int cellCount, Object cellCommonModel){
        this.cellClassName = null;
        this.cellLayoutId = cellLayoutId;
        this.cellViewHolderClassName = cellViewHolderClassName;
        this.cellModelList = new ArrayList<Object>();
        this.cellCount = cellCount;
        this.cellType = SPCellType.XMLCell;
        this.cellCommonModel = cellCommonModel;
    }


    public SPListingCellGroup(String cellClassName, String cellViewHolderClassName, List<Object> cellModelList){
        this.cellClassName = cellClassName;
        this.cellLayoutId = 0;
        this.cellViewHolderClassName = cellViewHolderClassName;
        this.cellModelList = cellModelList;
        this.cellCount = cellModelList.size();
        this.cellType = SPCellType.SubclassCell;
        this.cellCommonModel = null;
    }

    public SPListingCellGroup(String cellClassName, String cellViewHolderClassName, int cellCount, Object cellCommonModel) {
        this.cellClassName = cellClassName;
        this.cellLayoutId = 0;
        this.cellViewHolderClassName = cellViewHolderClassName;
        this.cellModelList = new ArrayList<Object>();
        this.cellCount = cellCount;
        this.cellType = SPCellType.SubclassCell;
        this.cellCommonModel = cellCommonModel;
    }
}


