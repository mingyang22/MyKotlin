package com.example.baselibrary.recycleview

import android.util.Log
import android.util.SparseArray

/**
 * @author yang on 2020/5/10
 */
class ItemViewDelegateManager<T> {
    private val TAG = "ItemViewDelegateManager"
    private val delegates = SparseArray<ItemViewDelegate<T>>()

    fun getItemViewDelegateCount() = delegates.size()

    fun addDelegate(delegate: ItemViewDelegate<T>) {
        val viewType = delegates.size()
        delegates.put(viewType, delegate)
    }

    fun addDelegate(viewType: Int, delegate: ItemViewDelegate<T>) {
        if (delegates[viewType] != null) {
            throw IllegalArgumentException("An ItemViewDelegate is already registered for the viewType = $viewType. Already registered ItemViewDelegate is ${delegates[viewType]}")
        }
        delegates.put(viewType, delegate)
    }

    fun removeDelegate(delegate: ItemViewDelegate<T>) {
        val indexToRemove = delegates.indexOfValue(delegate)
        if (indexToRemove >= 0) delegates.removeAt(indexToRemove)
    }

    fun removeDelegate(viewType: Int) {
        val indexToRemove = delegates.indexOfKey(viewType)
        if (indexToRemove >= 0) delegates.removeAt(indexToRemove)
    }

    fun getItemViewType(item: T, position: Int): Int {
        val delegatesCount = delegates.size()
        for (i in delegatesCount - 1 downTo 0) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(item, position)) return delegates.keyAt(i)
        }
        Log.e(
            TAG,
            "getItemViewType: ",
            Throwable("No ItemViewDelegate added that matches position= $position in data source")
        )
        return 0
    }

    fun convert(holder: ViewHolder, item: T, position: Int) {
        val delegatesCount = delegates.size()
        for (i in 0 until delegatesCount) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position)
                return
            }
        }
        delegates.valueAt(0).convert(holder, item, position)
        Log.e(
            TAG,
            "convert: ",
            Throwable("No ItemViewDelegateManager added that matches position=$position in data source")
        )
    }

    fun convert(holder: ViewHolder, item: T, position: Int, payloads: List<Any>) {
        val delegatesCount = delegates.size()
        for (i in 0 until delegatesCount) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position, payloads)
                return
            }
        }
        delegates.valueAt(0).convert(holder, item, position, payloads)
        Log.e(
            TAG,
            "convert: ",
            Throwable("No ItemViewDelegateManager added that matches position=$position in data source")
        )
    }

    fun getItemViewDelegate(viewType: Int): ItemViewDelegate<T> = delegates.get(viewType)

    fun getItemViewLayoutId(viewType: Int) = getItemViewDelegate(viewType).getItemViewLayoutId()

    fun getItemViewType(itemViewDelegate: ItemViewDelegate<T>) =
        delegates.indexOfValue(itemViewDelegate)
}