package com.happyfall.android.demo.cells;

import com.happyfall.android.demo.R;
import com.happyfall.android.demo.BR;
import com.happyfall.android.swiftui.recyclerview.viewholder.ListingViewModel;

/**
 * Created by Pradip on 5/13/2015.
 */
public class TitleRModelListing extends ListingViewModel {
    public String mTitle;

    public TitleRModelListing(String title){
        mLayoutId = R.layout.recycler_cell_title;
        mBindingVariable = BR.titleRModel;
        mTitle = title;
    }

}

