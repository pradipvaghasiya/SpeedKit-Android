package com.speedui.android.uiautomation.listingautomation.recyclerview.cells;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import com.speedui.android.uiautomation.R;
import com.speedui.android.uiautomation.listingautomation.listingdata.SPListingCellGroup;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewHolderListener;

/**
 * Created by Pradip on 5/13/2015.
 */
public class SPTitleViewHolder extends SPViewHolder {
    public static final String CLASS_NAME = "SPTitleViewHolder";

    public TextView textView;
    public View dividerLine;

    public SPTitleViewHolder(View itemView,SPViewHolderListener delegate) {
        super(itemView, delegate);
        //System.out.println("SPTitleViewHolder View Holder Created");

        this.textView = (TextView)itemView.findViewById(R.id.SPTitleViewHolder_TextView);
        this.dividerLine = itemView.findViewById(R.id.cell_divider);

        this.customiseViewHolderIfRequired();
    }

    @Override
    public void configureCellUsing(Object cellModel) {
        if (cellModel instanceof String) {
            if (this.textView != null) {
                this.textView.setText((String) cellModel);
            }
        }
    }

    public static SPListingCellGroup getCellGroupFromCellModels(List<String> modelList){
        return new SPListingCellGroup(
                R.layout.recycler_cell_title,
                SPTitleViewHolder.class.getName(),
                modelList);

    }

}

