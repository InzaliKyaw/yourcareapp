package com.example.yourcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.CaseSummaryVO
import com.example.yourcare.R
import com.example.yourcare.view.viewholders.CaseSummaryViewHolder
import com.example.yourcare.view.viewholders.ChatCaseSummaryViewHolder

class ChatCaseSummaryAdapter:RecyclerView.Adapter<ChatCaseSummaryViewHolder>() {
    private var mData:MutableList<CaseSummaryVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatCaseSummaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_show_case_summary,parent,false)
        return ChatCaseSummaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatCaseSummaryViewHolder, position: Int) {
        holder.bindList(mData[position])
    }
    fun setNewData(data:MutableList<CaseSummaryVO>){
        mData = data
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return mData.size
    }
}