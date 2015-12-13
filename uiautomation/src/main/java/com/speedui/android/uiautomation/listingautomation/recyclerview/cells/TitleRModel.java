package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import com.speedui.android.uiautomation.BR;
import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

/**
 * Created by Pradip on 5/13/2015.
 */
public class TitleRModel extends SPViewModel {
    public String mTitle;

    public TitleRModel(String title){
        mLayoutId = R.layout.recycler_cell_title;
        mBindingVariable = BR.titleRModel;
        mTitle = title;
    }

}

