package com.example.doctors.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView:View):RecyclerView.ViewHolder(itemView) {
        var mData:T? = null
}