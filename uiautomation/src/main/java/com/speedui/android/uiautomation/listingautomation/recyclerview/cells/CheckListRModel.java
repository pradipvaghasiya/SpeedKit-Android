package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import android.databinding.Bindable;
import android.view.View;

import com.speedui.android.uiautomation.BR;
import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

/**
 * Created by pradip on 12/13/15.
 */
public class ChecklistRModel extends SPViewModel{
    public ChecklistRModel(String title, boolean selected){
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