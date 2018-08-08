package com.happyfall.android.swiftui.listing

import android.content.Context
import android.databinding.BaseObservable

import com.happyfall.android.swiftui.listing.viewholder.ListingViewHolder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by pradipvaghasiya on 06/06/15.
 */
abstract class ListingViewModel : BaseObservable() {
    var mViewHolder: ListingViewHolder? = null
    var serverId: Any? = null
    var mLayoutId: Int = 0
    var mBindingVariable = 0  // By default 0 if in case no Binding used.

    protected val context: Context?
        get() = mViewHolder?.dataBinding?.root?.context

    abstract fun bindingExecuted()
    abstract fun viewHolderAttached()

    fun viewHolderDettached() {
        compositeDisposable?.dispose()
    }

    private var compositeDisposable: CompositeDisposable? = null
    fun addDisposable(d: Disposable){
        if (compositeDisposable == null){
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable!!.add(d)
    }
}
