package com.speedui.android.uiautomation.listingautomation.listingdata;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.speedui.android.uiautomation.listingautomation.recyclerview.viewholder.SPViewModel;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Pradip on 5/12/2015.
 */

final public class SPListingData implements ObservableList<SPViewModel>{
    private ObservableList<SPViewModel> mItems;

    public SPListingData(){
        mItems = new ObservableArrayList<>();
    }

    @Override
    public void addOnListChangedCallback(OnListChangedCallback<? extends ObservableList<SPViewModel>> callback) {
        mItems.addOnListChangedCallback(callback);
    }

    @Override
    public void removeOnListChangedCallback(OnListChangedCallback<? extends ObservableList<SPViewModel>> callback) {
        mItems.removeOnListChangedCallback(callback);
    }

    @Override
    public void add(int location, SPViewModel object) {
        mItems.add(location, object);
    }

    @Override
    public boolean add(SPViewModel object) {
        return mItems.add(object);
    }

    @Override
    public boolean addAll(int location, Collection<? extends SPViewModel> collection) {
        return mItems.addAll(location, collection);
    }

    @Override
    public boolean addAll(Collection<? extends SPViewModel> collection) {
        return addAll(collection);
    }

    @Override
    public void clear() {
        mItems.clear();
    }

    @Override
    public boolean contains(Object object) {
        return mItems.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return mItems.containsAll(collection);
    }

    @Override
    public boolean equals(Object object) {
        return mItems.equals(object);
    }

    @Override
    public SPViewModel get(int location) {
        return mItems.get(location);
    }

    @Override
    public int hashCode() {
        return mItems.hashCode();
    }

    @Override
    public int indexOf(Object object) {
        return mItems.indexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return mItems.isEmpty();
    }

    @NonNull
    @Override
    public Iterator<SPViewModel> iterator() {
        return mItems.iterator();
    }

    @Override
    public int lastIndexOf(Object object) {
        return mItems.lastIndexOf(object);
    }

    @Override
    public ListIterator<SPViewModel> listIterator() {
        return mItems.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<SPViewModel> listIterator(int location) {
        return mItems.listIterator(location);
    }

    @Override
    public SPViewModel remove(int location) {
        return mItems.remove(location);
    }

    @Override
    public boolean remove(Object object) {
        return mItems.remove(object);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return mItems.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return mItems.retainAll(collection);
    }

    @Override
    public SPViewModel set(int location, SPViewModel object) {
        return mItems.set(location, object);
    }

    @Override
    public int size() {
        return mItems.size();
    }

    @NonNull
    @Override
    public List<SPViewModel> subList(int start, int end) {
        return mItems.subList(start, end);
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return mItems.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] array) {
        return mItems.toArray(array);
    }
}
