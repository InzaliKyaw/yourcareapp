package com.example.doctors.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.delegates.RequestDelegates
import com.example.shared.data.vos.ConsultationRequestVO
import kotlinx.android.synthetic.main.item_patient_request.view.*

class ConsultationRequestViewHolder(itemView:View,private val delegates: RequestDelegates):BaseConsultationViewHolder(itemView){
    init {
        itemView.acceptBtnReq.setOnClickListener{
            mData.let {
                it?.let { it1 -> delegates.onTapAcceptRequest(it1.id) }
            }
        }
    }
    fun bindConsultation(consultationRequestVO: ConsultationRequestVO){
            mData = consultationRequestVO
            itemView.patientNameReq.text = consultationRequestVO.patientsVO?.name
            itemView.patientBirthdayReq.text = consultationRequestVO.patientsVO?.birthdate
    }
}