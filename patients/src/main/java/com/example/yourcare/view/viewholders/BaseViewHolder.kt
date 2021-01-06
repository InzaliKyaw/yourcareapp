package com.example.yourcare.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<W>(itemView: View):RecyclerView.ViewHolder(itemView) {
    var mData:W?= null
    abstract fun bindData(data: W)

}