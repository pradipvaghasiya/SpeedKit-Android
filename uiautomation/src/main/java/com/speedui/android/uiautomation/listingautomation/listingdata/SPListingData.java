package com.speedui.android.uiautomation.listingautomation.listingdata;

import android.databinding.ObservableList;

import com.speedui.android.uiautomation.listingautomation.recyclerview.adapter.SPBindingRecyclerAdapter;
import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPBindingViewHolder;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created by Pradip on 5/12/2015.
 */
final public class SPListingData {
    public List<ItemGroup> itemGroupList;

    private ItemsOnListChangedCallback itemsOnListChangedCallback;
    public ItemsOnListChangedCallback getItemsOnListChangedCallback() {
        if (itemsOnListChangedCallback == null){
            itemsOnListChangedCallback = new ItemsOnListChangedCallback();
        }
        return itemsOnListChangedCallback;
    }

    private WeakReference<SPBindingRecyclerAdapter> weakReferenceBindingRecyclerAdapter;
    public void setSpBindingRecyclerAdapter(SPBindingRecyclerAdapter spBindingRecyclerAdapter) {
        this.weakReferenceBindingRecyclerAdapter = new WeakReference<SPBindingRecyclerAdapter>(spBindingRecyclerAdapter);

        for (ItemGroup itemGroup : itemGroupList){
            itemGroup.setWeakReferenceItemsOnListChangedCallback(getItemsOnListChangedCallback());
        }
    }

    public void removeObserverCallbacks(){
        for (ItemGroup itemGroup : itemGroupList){
            if (itemGroup.itemModelList != null){
                itemGroup.itemModelList.removeOnListChangedCallback(itemsOnListChangedCallback);
            }
        }
    }

    // Create Listing Data with given section array.
    public SPListingData(List<ItemGroup> itemGroupList){
        this.itemGroupList = itemGroupList;
    }

    final public static class ItemGroup{
        // Item Layout ID
        public int itemLayoutId;

        // Item Binding Variable
        public int itemBindingVariable;

        // Cell Class Name
        public Constructor<?> bindingViewHolderConstructor;



        private WeakReference<ItemsOnListChangedCallback> weakReferenceItemsOnListChangedCallback;
        public void setWeakReferenceItemsOnListChangedCallback(ItemsOnListChangedCallback itemsOnListChangedCallback) {
            this.weakReferenceItemsOnListChangedCallback = new WeakReference<ItemsOnListChangedCallback>(itemsOnListChangedCallback);
            if (this.itemModelList != null){
                this.itemModelList.addOnListChangedCallback(this.weakReferenceItemsOnListChangedCallback.get());
            }
        }

        // Cell Model
        private ObservableList<?> itemModelList;
        public ObservableList<?> getItemModelList() {
            return itemModelList;
        }

        public void setItemModelList(ObservableList<Object> itemModelList) {

            if (this.itemModelList == itemModelList) return;

            if (this.itemModelList != null){
                this.itemModelList.removeOnListChangedCallback(this.weakReferenceItemsOnListChangedCallback.get());
                this.weakReferenceItemsOnListChangedCallback.get().onItemRangeRemoved(this.itemModelList, 0, this.itemModelList.size());
            }

            if (itemModelList == null){
                this.itemModelList = itemModelList;
                return;
            }

            this.itemModelList = itemModelList;
            this.itemModelList.addOnListChangedCallback(this.weakReferenceItemsOnListChangedCallback.get());
            this.weakReferenceItemsOnListChangedCallback.get().onItemRangeInserted(this.itemModelList,0,this.itemModelList.size());

        }

        // Cell Count
        public int getItemCount() {
            return itemModelList.size();
        }


        public ItemGroup(int itemLayoutId,
                                  int itemBindingVariable,
                                  Constructor<?> bindingViewHolderConstructor,
                                  ObservableList<?> itemModelList){
            this.itemLayoutId = itemLayoutId;
            this.itemBindingVariable = itemBindingVariable;
            this.bindingViewHolderConstructor = bindingViewHolderConstructor;
            this.itemModelList = itemModelList;
        }

    }



    private class ItemsOnListChangedCallback extends ObservableList.OnListChangedCallback{

        @Override
        public void onChanged(ObservableList sender) {
            SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
            if (adapter != null){
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
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
            int startPositionOffset = 0;
            for (ItemGroup itemGroup : itemGroupList){
                if (itemGroup.itemModelList == sender){
                    SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
                    if (adapter != null){
                        adapter.notifyItemRangeInserted(startPositionOffset + positionStart, itemCount);
                    }
                }else{
                    startPositionOffset = startPositionOffset + itemGroup.getItemCount();
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
            int startPositionOffset = 0;
            for (ItemGroup itemGroup : itemGroupList){
                if (itemGroup.itemModelList == sender){
                    SPBindingRecyclerAdapter adapter = weakReferenceBindingRecyclerAdapter.get();
                    if (adapter != null){
                        adapter.notifyItemRangeRemoved(startPositionOffset + positionStart,itemCount);
                    }
                }else{
                    startPositionOffset = startPositionOffset + itemGroup.getItemCount();
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

            startIndexOfCellFound += startIndexOfCellFound + itemGroup.getItemCount();
        }
        return returnType;
    }

    public int getIndexOfItemGroupFrom(int indexOfListView){
        int startIndexOfItemGroup  = 0;
        int totalIndexCovered = 0;
        for(ItemGroup itemGroup : this.itemGroupList){
            totalIndexCovered += itemGroup.getItemCount();
            if (indexOfListView < totalIndexCovered){
                return startIndexOfItemGroup;
            }

            startIndexOfItemGroup ++;
        }
        return 0;
    }

}
