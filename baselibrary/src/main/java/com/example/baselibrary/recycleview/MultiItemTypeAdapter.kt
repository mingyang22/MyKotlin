package com.example.baselibrary.recycleview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author yang on 2020/5/10
 */
open class MultiItemTypeAdapter<T>(private val context: Context, private val list: ArrayList<T>) :
    RecyclerView.Adapter<ViewHolder>() {

    private val mItemViewDelegateManager = ItemViewDelegateManager<T>()

    //    private var mOnItemClickListener: OnItemClickListener? = null
//    private var mOnItemLongClickListener: OnItemLongClickListener? = null


    override fun getItemViewType(position: Int): Int =
        if (!useItemViewDelegateManager()) super.getItemViewType(position) else
            mItemViewDelegateManager.getItemViewType(list[position], position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType)
        val layoutId = itemViewDelegate.getItemViewLayoutId()
        val holder = ViewHolder.createViewHolder(context, parent, layoutId)
        onViewHolderCreated(holder, holder.getConvertView())
        setListener(parent, holder, viewType)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        convert(holder, list[position])
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        convert(holder, list[position], payloads)
    }

    private fun convert(holder: ViewHolder, t: T) {
        mItemViewDelegateManager.convert(holder, t, holder.adapterPosition)
    }

    private fun convert(holder: ViewHolder, t: T, payloads: List<Any>) {
        mItemViewDelegateManager.convert(holder, t, holder.adapterPosition)
    }

    private fun setListener(parent: ViewGroup, holder: ViewHolder, viewType: Int) {
        if (!isListenerEnabled(viewType)) return
        holder.getConvertView().setOnClickListener {
            val position = holder.adapterPosition
            if (position == -1) return@setOnClickListener
            mOnItemClickListener.invoke(holder, position)
        }
        holder.getConvertView().setOnLongClickListener {
            val position = holder.adapterPosition
            if (position == -1) return@setOnLongClickListener false else mOnItemLongClickListener.invoke(
                holder,
                position
            )
            false
        }
    }

    fun isListenerEnabled(viewType: Int): Boolean {
        return true
    }

    fun onViewHolderCreated(holder: ViewHolder, convertView: View) {}


    override fun getItemCount() = list.size

    fun addData(position: Int, t: T) {
        list.add(position, t)
        notifyItemRangeChanged(position, list.size - position)
    }

    /**
     * 在有数据的情况下添加数据集合
     */
    fun addDataList(data: List<T>) {
        val position = list.size - 1
        list.addAll(data)
        notifyItemRangeChanged(position, data.size)
    }

    fun updateData(data: List<T>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun removeData(position: Int) {
        list.removeAt(position)
        notifyItemRangeChanged(position, list.size - position)
    }

    fun updateItemData(position: Int, data: T) {
        notifyItemChanged(position, data)
    }

    fun addItemViewDelegate(itemViewDelegate: ItemViewDelegate<T>) = apply {
        mItemViewDelegateManager.addDelegate(itemViewDelegate)
    }

    fun addItemViewDelegate(viewType: Int, itemViewDelegate: ItemViewDelegate<T>) = apply {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate)
    }

    private fun useItemViewDelegateManager() =
        mItemViewDelegateManager.getItemViewDelegateCount() > 0


    private var mOnItemClickListener: (holder: RecyclerView.ViewHolder, position: Int) -> Unit? =
        { _, _ -> }

    private var mOnItemLongClickListener: (holder: RecyclerView.ViewHolder, position: Int) -> Boolean? =
        { _, _ -> true }

    fun setOnItemClickListener(onItemClickListener: (holder: RecyclerView.ViewHolder, position: Int) -> Unit) {
        this.mOnItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: (holder: RecyclerView.ViewHolder, position: Int) -> Boolean) {
        this.mOnItemLongClickListener = onItemLongClickListener
    }
}