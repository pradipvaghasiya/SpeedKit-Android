package com.happyfall.android.demo.cells;

import android.databinding.Bindable;
import android.view.View;

import com.happyfall.android.demo.BR;
import com.happyfall.android.demo.R;
import com.happyfall.android.swiftui.recyclerview.viewholder.ListingViewModel;

/**
 * Created by pradip on 12/13/15.
 */
public class ChecklistRModelListing extends ListingViewModel {
    public ChecklistRModelListing(String title, boolean selected){
        mLayoutId = R.layout.recycler_cell_checklist;
        mBindingVariable = BR.checklistRModel;
        this.mTitle = title;
        this.mSelected = selected;
    }

    public String mTitle;

    @Bindable
    public boolean mSelected;

    public void setSelected(boolean mSelected) {
        this.mSelected = mSelected;
        notifyPropertyChanged(BR.selected);
    }

    public View.OnClickListener getOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(!mSelected);
                if (mViewHolder != null){
                    mViewHolder.onClick(mViewHolder.getDataBinding().getRoot());
                }
            }
        };
    }
}