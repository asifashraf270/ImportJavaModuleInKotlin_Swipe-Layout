package com.importjavaprojectintokotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.chauthai.swipereveallayout.ViewBinderHelper

class RecyclerViewAdapter(list: MutableList<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var mDataSet: List<String> = java.util.ArrayList()
    private val binderHelper = ViewBinderHelper()

    init {
        mDataSet = list
    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        fun bind(data: String) {
            textView?.setText(data.toString())
        }

        var swipeLayout: SwipeRevealLayout? = null
        var frontLayout: View? = null
        var deleteLayout: View? = null
        var textView: TextView? = null


        constructor(itemview: View) : super(itemview) {
            swipeLayout = itemView.findViewById<View>(R.id.swipe_layout) as SwipeRevealLayout
            frontLayout = itemView.findViewById<View>(R.id.front_layout)
            deleteLayout = itemView.findViewById<View>(R.id.delete_layout)
            textView = itemView.findViewById<View>(R.id.text) as TextView

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mDataSet != null && 0 <= position && position < mDataSet.size) {
            val data = mDataSet[position]

            // Use ViewBindHelper to restore and save the open/close state of the SwipeRevealView
            // put an unique string id as value, can be any string which uniquely define the data
            binderHelper.bind(holder.swipeLayout, data)

            // Bind your data here
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }
}