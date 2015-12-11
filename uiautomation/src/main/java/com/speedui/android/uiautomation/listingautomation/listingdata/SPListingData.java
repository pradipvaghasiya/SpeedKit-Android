package com.speedui.android.uiautomation.listingautomation.listingdata;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.InsetDrawable;

import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPBindingRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolderListener;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
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
            if (itemGroup.itemModelList != null && itemGroup.itemModelList instanceof ObservableList){
                ((ObservableList)itemGroup.itemModelList).removeOnListChangedCallback(itemsOnListChangedCallback);
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
            return itemModelList.get(0).createBindingViewHolder(viewDataBinding, listener, itemType);
        }

        private WeakReference<ItemsOnListChangedCallback> weakReferenceItemsOnListChangedCallback;
        public void setWeakReferenceItemsOnListChangedCallback(ItemsOnListChangedCallback itemsOnListChangedCallback) {
            this.weakReferenceItemsOnListChangedCallback = new WeakReference<ItemsOnListChangedCallback>(itemsOnListChangedCallback);

            if (this.itemModelList != null && this.itemModelList instanceof ObservableList){
                ((ObservableList)this.itemModelList).addOnListChangedCallback(this.weakReferenceItemsOnListChangedCallback.get());
            }
        }

        // Cell Model
        private List<? extends SPViewModel> itemModelList;
        public List<? extends SPViewModel> getItemModelList() {
            return itemModelList;
        }

        public void setItemModelList(ObservableList<SPViewModel> itemModelList) {

            if (this.itemModelList == itemModelList) return;

            if (!(this.itemModelList instanceof ObservableList)){
                this.itemModelList = itemModelList;
                return;
            }

            if (this.itemModelList != null){
                ((ObservableList)this.itemModelList).removeOnListChangedCallback(this.weakReferenceItemsOnListChangedCallback.get());
                this.weakReferenceItemsOnListChangedCallback.get().onItemRangeRemoved(((ObservableList)this.itemModelList), 0, this.itemModelList.size());
            }

            if (itemModelList == null){
                this.itemModelList = itemModelList;
                return;
            }

            this.itemModelList = itemModelList;
            ((ObservableList)this.itemModelList).addOnListChangedCallback(this.weakReferenceItemsOnListChangedCallback.get());
            this.weakReferenceItemsOnListChangedCallback.get().onItemRangeInserted(((ObservableList)this.itemModelList),0,this.itemModelList.size());

        }

        // Cell Count
        public int getItemCount() {
            return itemModelList.size();
        }


        public ItemGroup(int itemLayoutId,
                                  int itemBindingVariable,
                                  List<? extends SPViewModel> itemModelList){
            this.itemLayoutId = itemLayoutId;
            this.itemBindingVariable = itemBindingVariable;
            this.itemModelList = itemModelList;
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
                if (itemGroup.itemModelList == sender){
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
                while (insertedPositionIndex < itemCount){
                    // Set the listener as well
                    itemGroupList.get(insertedPositionIndex).setWeakReferenceItemsOnListChangedCallback(this);

                    itemGroupList.get(insertedPositionIndex).itemType = maxItemTypeAssigned + 1;
                    maxItemTypeAssigned++;

                    newItemCount += itemGroupList.get(insertedPositionIndex).getItemCount();
                    insertedPositionIndex++;
                }

                SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
                if (adapter != null  && userWantsToAutomateAdapterNotificationForGroupList == true){
                    adapter.notifyItemRangeInserted(newPosition, newItemCount);
                }
            }else {
                int startPositionOffset = 0;
                for (ItemGroup itemGroup : itemGroupList) {
                    if (itemGroup.itemModelList == sender) {
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
                if (itemGroup.itemModelList == sender){
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
                    if (itemGroup.itemModelList == sender) {
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
