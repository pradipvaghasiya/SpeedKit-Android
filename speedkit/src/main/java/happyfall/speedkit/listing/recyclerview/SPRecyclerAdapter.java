package happyfall.speedkit.listing.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import happyfall.speedkit.listing.SPListingCellGroup;
import happyfall.speedkit.listing.SPListingData;

import java.lang.reflect.Constructor;

/**
 * Created by Pradip on 5/13/2015.
 */

public class SPRecyclerAdapter extends RecyclerView.Adapter<SPRecyclerAdapter.ViewHolder> {
    // SPListing Data
    public SPListingData spListingData;
    ViewHolderDelegate notifier;

    public SPRecyclerAdapter(SPListingData spListingData, ViewHolderDelegate notifier){
        super();
        this.spListingData = spListingData;
        this.notifier = notifier;
    }

    public interface ViewHolderDelegate {
        void didSelectItem(View view);
    }

    abstract public static class ViewHolder extends RecyclerView.ViewHolder {
        protected ViewHolderDelegate notifier;

        public ViewHolder(View itemView, ViewHolderDelegate pDelegate) {
            super(itemView);
            this.notifier = pDelegate;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(notifier != null){
                        notifier.didSelectItem(v);
                    }
                }
            });
        }

        public abstract void configureCellUsing(Object cellModel);

    }

    @Override
    public int getItemViewType(int position) {
        return spListingData.getIndexOfCellGroupFrom(position);
    }

    @Override
    public SPRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listingCell = null;
        SPListingCellGroup cellGRoup = spListingData.spCellGroupList.get(viewType);

        switch (cellGRoup.cellType){
            case SubclassCell:
                listingCell = this.createCellFromSubclass(cellGRoup.cellClassName, parent.getContext());
            case XMLCell:
                listingCell = this.createCellFromXML(cellGRoup.cellLayoutId, parent);
        }

        // Check Whether Listing Cell created properly.
        if (listingCell == null){
            System.out.println("SpeedKit Error: Listing Cell not created for " + cellGRoup);
            return null;
        }

        try {
            Class<?> viewClass = Class.forName(cellGRoup.cellViewHolderClassName);
            Constructor<?> constructor = viewClass.getConstructor(View.class,ViewHolderDelegate.class);

            ViewHolder viewHolder = (ViewHolder)constructor.newInstance(listingCell,this.notifier);

            return viewHolder;

        } catch (Exception e) {
            System.out.println("SpeedKit Error: View Holder Class <" + cellGRoup.cellViewHolderClassName + "> is not valid : " + e.toString());
        }

        return null;
    }

    View createCellFromSubclass(String cellId, Context context){
        try {
            Class<?> viewClass = Class.forName(cellId);
            Constructor<?> constructor = viewClass.getConstructor(Context.class);
            return (View)constructor.newInstance(context);

        } catch (Exception e) {
            System.out.println("SpeedKit Error: CellId <" + cellId + "> is not valid Subclass of ViewGroup");
        }
        return null;
    }

    View createCellFromXML(int cellLayoutId, ViewGroup parent){
        return LayoutInflater
                .from(parent.getContext())
                .inflate(cellLayoutId, parent, false);
    }

    @Override
    public void onBindViewHolder(SPRecyclerAdapter.ViewHolder holder, int position) {
        SPListingData.CellGroupAndCellModelIndexReturnType cellGroupDetail = spListingData.getListingCellGroupWithIndexOfCellModelList(position);

        if (cellGroupDetail.spListingCellGroup.cellCommonModel != null){
            holder.configureCellUsing(cellGroupDetail.spListingCellGroup.cellCommonModel);
        }

        if (cellGroupDetail.spListingCellGroup.cellModelList.size() > cellGroupDetail.indexOfCellModelList &&
                cellGroupDetail.indexOfCellModelList >= 0){
            holder.configureCellUsing(cellGroupDetail.spListingCellGroup.cellModelList.get(cellGroupDetail.indexOfCellModelList));
        }

    }

    @Override
    public int getItemCount() {
        return spListingData.getTotalCellCount();
    }
}
