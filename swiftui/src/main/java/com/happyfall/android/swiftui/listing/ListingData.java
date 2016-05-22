package com.happyfall.android.swiftui.listing;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Pradip on 5/12/2015.
 */

final public class ListingData implements ObservableList<ListingViewModel>{
    private ObservableList<ListingViewModel> mItems;

    public ListingData(){
        mItems = new ObservableArrayList<>();
    }

    @Override
    public void addOnListChangedCallback(OnListChangedCallback<? extends ObservableList<ListingViewModel>> callback) {
        mItems.addOnListChangedCallback(callback);
    }

    @Override
    public void removeOnListChangedCallback(OnListChangedCallback<? extends ObservableList<ListingViewModel>> callback) {
        mItems.removeOnListChangedCallback(callback);
    }

    @Override
    public void add(int location, ListingViewModel object) {
        mItems.add(location, object);
    }

    @Override
    public boolean add(ListingViewModel object) {
        return mItems.add(object);
    }

    @Override
    public boolean addAll(int location, Collection<? extends ListingViewModel> collection) {
        return mItems.addAll(location, collection);
    }

    @Override
    public boolean addAll(Collection<? extends ListingViewModel> collection) {
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
    public ListingViewModel get(int location) {
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
    public Iterator<ListingViewModel> iterator() {
        return mItems.iterator();
    }

    @Override
    public int lastIndexOf(Object object) {
        return mItems.lastIndexOf(object);
    }

    @Override
    public ListIterator<ListingViewModel> listIterator() {
        return mItems.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<ListingViewModel> listIterator(int location) {
        return mItems.listIterator(location);
    }

    @Override
    public ListingViewModel remove(int location) {
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
    public ListingViewModel set(int location, ListingViewModel object) {
        return mItems.set(location, object);
    }

    @Override
    public int size() {
        return mItems.size();
    }

    @NonNull
    @Override
    public List<ListingViewModel> subList(int start, int end) {
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
