package com.example.doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.R
import com.example.doctors.view.viewholders.ConsultationRequestCaseViewHolder
import com.example.doctors.view.viewholders.ConsultationRequestViewHolder
import com.example.shared.data.vos.CaseSummaryVO

class ConsultationRequestCaseSAdapter:RecyclerView.Adapter<ConsultationRequestCaseViewHolder>() {
    private var mData:MutableList<CaseSummaryVO> = arrayListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultationRequestCaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_casesummary_qa,parent,false)
        return ConsultationRequestCaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConsultationRequestCaseViewHolder, position: Int) {
        holder.bindCaseQA(mData[position])
    }

    fun setNewData(data:MutableList<CaseSummaryVO>){
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}