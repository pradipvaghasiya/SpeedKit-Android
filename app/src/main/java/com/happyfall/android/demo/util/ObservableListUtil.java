package com.happyfall.android.demo.util;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

/**
 * Created by ibm on 08/06/15.
 */
public class ObservableListUtil {
    public static <T> ObservableList<T> asObservableList(T... array) {
        ObservableList<T> observableList = new ObservableArrayList<>();
        for (T item : array){
            observableList.add(item);
        }
        return observableList;
    }

}
