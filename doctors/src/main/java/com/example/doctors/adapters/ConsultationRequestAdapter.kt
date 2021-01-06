package com.example.doctors.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.R
import com.example.doctors.delegates.RequestDelegates
import com.example.doctors.view.viewholders.ConsultationRequestViewHolder
import com.example.shared.data.vos.ConsultationRequestVO

class ConsultationRequestAdapter(requestDelegates: RequestDelegates):RecyclerView.Adapter<ConsultationRequestViewHolder>() {
    var mData:MutableList<ConsultationRequestVO> = arrayListOf()
    val mDelegates:RequestDelegates = requestDelegates

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultationRequestViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient_request,parent,false)
        return ConsultationRequestViewHolder(view,mDelegates)
    }

    override fun onBindViewHolder(holder: ConsultationRequestViewHolder, position: Int) {
        holder.bindConsultation(mData[position])
    }
    fun setNewData(data:MutableList<ConsultationRequestVO>){
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.size
    }

}