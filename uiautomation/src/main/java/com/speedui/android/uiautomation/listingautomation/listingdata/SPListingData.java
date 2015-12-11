package com.speedui.android.uiautomation.listingautomation.listingdata;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;

import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPBindingRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Pradip on 5/12/2015.
 */
final public class SPListingData {
    public ObservableList<ItemGroup> itemGroupList;

    private boolean userWantsToAutomateAdapterNotificationForGroupList;

    private int maxItemTypeAssigned;
    // Create Listing Data with given section array.
    public SPListingData(List<ItemGroup> itemGroupList) {
        if (itemGroupList instanceof ObservableList){
            this.itemGroupList = (ObservableList<ItemGroup>) itemGroupList;
            userWantsToAutomateAdapterNotificationForGroupList = true;
        }else{
            this.itemGroupList = new ObservableArrayList<ItemGroup>();
            this.itemGroupList.addAll(itemGroupList);
            userWantsToAutomateAdapterNotificationForGroupList = false;
        }

        configureItemType();
    }

    private void configureItemType() {
        int itemType;
        for (itemType = 0; itemType < this.itemGroupList.size(); itemType++){
            this.itemGroupList.get(itemType).itemType = itemType;
        }
        maxItemTypeAssigned = itemType - 1;
    }


    private ItemsOnListChangedCallback itemsOnListChangedCallback;
    private ItemsOnListChangedCallback getItemsOnListChangedCallback() {
        if (itemsOnListChangedCallback == null){
            itemsOnListChangedCallback = new ItemsOnListChangedCallback();
        }
        return itemsOnListChangedCallback;
    }

    private WeakReference<SPBindingRecyclerAdapter> weakReferenceBindingRecyclerAdapter;
    public void setSpBindingRecyclerAdapter(SPBindingRecyclerAdapter spBindingRecyclerAdapter) {
        this.weakReferenceBindingRecyclerAdapter = new WeakReference<SPBindingRecyclerAdapter>(spBindingRecyclerAdapter);

        this.itemGroupList.addOnListChangedCallback(getItemsOnListChangedCallback());
        for (ItemGroup itemGroup : itemGroupList){
            itemGroup.setWeakReferenceItemsOnListChangedCallback(getItemsOnListChangedCallback());
        }
    }

    public void removeObserverCallbacks(){
        if (this.itemGroupList != null){
            this.itemGroupList.removeOnListChangedCallback(itemsOnListChangedCallback);
        }
        for (ItemGroup itemGroup : itemGroupList){
            if (itemGroup.items != null && itemGroup.items instanceof ObservableList){
                ((ObservableList)itemGroup.items).removeOnListChangedCallback(itemsOnListChangedCallback);
            }
        }
    }


    final public static class ItemGroup{
        // Item Layout ID
        public int itemLayoutId;

        private int itemType;

        // Item Binding Variable
        public int itemBindingVariable;

        public SPBindingViewHolder getBindingViewHolder(ViewDataBinding viewDataBinding, SPBindingViewHolderListener listener, int itemType){
            return ((SPViewModel)items.get(0)).createBindingViewHolder(viewDataBinding, listener, itemType);
        }

        private WeakReference<ItemsOnListChangedCallback> weakReferenceItemsOnListChangedCallback;
        public void setWeakReferenceItemsOnListChangedCallback(ItemsOnListChangedCallback itemsOnListChangedCallback) {
            this.weakReferenceItemsOnListChangedCallback = new WeakReference<ItemsOnListChangedCallback>(itemsOnListChangedCallback);

            if (this.items != null && this.items instanceof ObservableList){
                ((ObservableList)this.items).addOnListChangedCallback(this.weakReferenceItemsOnListChangedCallback.get());
            }
        }

        // Cell Model
        public List<? extends  SPViewModel> items;

        public void setItems(ObservableList<? extends  SPViewModel> items) {

            if (this.items == items) return;

            if (!(this.items instanceof ObservableList)){
                this.items = items;
                return;
            }

            if (this.items != null){
                ((ObservableList)this.items).removeOnListChangedCallback(this.weakReferenceItemsOnListChangedCallback.get());
                this.weakReferenceItemsOnListChangedCallback.get().onItemRangeRemoved(((ObservableList) this.items), 0, this.items.size());
            }

            if (items == null){
                this.items = items;
                return;
            }

            this.items = items;
            ((ObservableList)this.items).addOnListChangedCallback(this.weakReferenceItemsOnListChangedCallback.get());
            this.weakReferenceItemsOnListChangedCallback.get().onItemRangeInserted(((ObservableList) this.items), 0, this.items.size());

        }

        // Cell Count
        public int getItemCount() {
            return items.size();
        }


        public ItemGroup(int itemLayoutId,
                                  int itemBindingVariable,
                                  ObservableList<? extends  SPViewModel> items){
            this.itemLayoutId = itemLayoutId;
            this.itemBindingVariable = itemBindingVariable;
            this.items = items;
        }

    }



    private class ItemsOnListChangedCallback extends ObservableList.OnListChangedCallback{

        @Override
        public void onChanged(ObservableList sender) {
            SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
            if (adapter != null){
                if (sender == itemGroupList && userWantsToAutomateAdapterNotificationForGroupList == false) return;;

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
            if (sender == itemGroupList)return;

            int startPositionOffset = 0;
            for (ItemGroup itemGroup : itemGroupList){
                if (itemGroup.items == sender){
                    SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
                    if (adapter != null){
                        adapter.notifyItemRangeChanged(startPositionOffset + positionStart,itemCount);
                    }
                }else{
                    startPositionOffset = startPositionOffset + itemGroup.getItemCount();
                }
            }
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
            if (sender == itemGroupList){

                int positionIndex = 0;
                int newPosition = 0;
                while (positionIndex < positionStart){
                    newPosition += itemGroupList.get(positionIndex).getItemCount();
                    positionIndex++;
                }

                int insertedPositionIndex = positionStart;
                int newItemCount = 0;
                //                while (insertedPositionIndex < itemCount){
                    // Set the listener as well
                    itemGroupList.get(insertedPositionIndex).setWeakReferenceItemsOnListChangedCallback(this);

                    itemGroupList.get(insertedPositionIndex).itemType = maxItemTypeAssigned + 1;
                    maxItemTypeAssigned++;

                    newItemCount += itemGroupList.get(insertedPositionIndex).getItemCount();
                    insertedPositionIndex++;
//                }

                SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
                if (adapter != null  && userWantsToAutomateAdapterNotificationForGroupList == true){
                    adapter.notifyItemRangeInserted(newPosition, newItemCount);
                }
            }else {
                int startPositionOffset = 0;
                for (ItemGroup itemGroup : itemGroupList) {
                    if (itemGroup.items == sender) {
                        SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
                        if (adapter != null) {
                            adapter.notifyItemRangeInserted(startPositionOffset + positionStart, itemCount);
                        }
                    } else {
                        startPositionOffset = startPositionOffset + itemGroup.getItemCount();
                    }
                }
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
            if (itemCount > 1) return;      // Currently only handles 1 item movement within same type of data.

            int startPositionOffset = 0;
            for (ItemGroup itemGroup : itemGroupList){
                if (itemGroup.items == sender){
                    SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
                    if (adapter != null){
                        adapter.notifyItemMoved(startPositionOffset + fromPosition, startPositionOffset + toPosition);
                    }
                }else{
                    startPositionOffset = startPositionOffset + itemGroup.getItemCount();
                }
            }

        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            if (sender == itemGroupList){
                SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
                if (adapter != null && userWantsToAutomateAdapterNotificationForGroupList == true){
                    adapter.notifyDataSetChanged();
                }
            }else {
                int startPositionOffset = 0;
                for (ItemGroup itemGroup : itemGroupList) {
                    if (itemGroup.items == sender) {
                        SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
                        if (adapter != null) {
                            adapter.notifyItemRangeRemoved(startPositionOffset + positionStart, itemCount);
                        }
                    } else {
                        startPositionOffset = startPositionOffset + itemGroup.getItemCount();
                    }
                }
            }
        }
    }


    public int getTotalItemCount(){
        int itemCount = 0;
        for (ItemGroup itemGroup : itemGroupList){
            itemCount += itemGroup.getItemCount();
        }
        return itemCount;
    }

    public class ItemGroupAndItemModelIndexReturnType {
        public ItemGroup itemGroup;
        public int indexOfItemModelList;
    }

    public SPViewModel getItemAtAdapterPosition(int position){
        int startIndexOfCellFound  = 0;
        int totalCellCountParsedFromCellGroupArray  = 0;
        for(ItemGroup itemGroup : this.itemGroupList){
            totalCellCountParsedFromCellGroupArray += itemGroup.getItemCount();
            if (position < totalCellCountParsedFromCellGroupArray){
                return (SPViewModel) itemGroup.items.get(position - startIndexOfCellFound);
            }

            startIndexOfCellFound += itemGroup.getItemCount();
        }
        return null;
    }

    public ItemGroupAndItemModelIndexReturnType getListingItemGroupWithIndexOfItemModelList(int index){
        ItemGroupAndItemModelIndexReturnType returnType = new ItemGroupAndItemModelIndexReturnType();

        int startIndexOfCellFound  = 0;
        int totalCellCountParsedFromCellGroupArray  = 0;
        for(ItemGroup itemGroup : this.itemGroupList){
            totalCellCountParsedFromCellGroupArray += itemGroup.getItemCount();
            if (index < totalCellCountParsedFromCellGroupArray){
                returnType.itemGroup = itemGroup;
                returnType.indexOfItemModelList = index - startIndexOfCellFound;
                break;
            }

            startIndexOfCellFound += itemGroup.getItemCount();
        }
        return returnType;
    }

    public int getItemType(int indexOfListView){
        int startIndexOfItemGroup  = 0;
        int totalIndexCovered = 0;
        for(ItemGroup itemGroup : this.itemGroupList){
            totalIndexCovered += itemGroup.getItemCount();
            if (indexOfListView < totalIndexCovered){
                return itemGroup.itemType;
            }

            startIndexOfItemGroup ++;
        }
        return 0;
    }

    public ItemGroup getItemGroupOfType(int viewType) {
        for(ItemGroup itemGroup : this.itemGroupList) {
            if (itemGroup.itemType == viewType){
                return itemGroup;
            }
        }
        return null;
    }

}
