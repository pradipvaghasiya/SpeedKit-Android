package com.happyfall.android.demo.cells;

import com.happyfall.android.demo.R;
import com.happyfall.android.demo.BR;
import com.happyfall.android.swiftui.recyclerview.viewholder.SPViewModel;

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

