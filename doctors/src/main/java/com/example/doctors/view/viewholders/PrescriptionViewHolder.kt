package com.example.doctors.view.viewholders

import android.view.View
import com.example.doctors.delegates.PrescriptionDelegates
import com.example.doctors.util.SessionManager
import com.example.shared.data.vos.MedicineVO
import kotlinx.android.synthetic.main.item_prescription.view.*

class PrescriptionViewHolder(itemView: View, private val delegates: PrescriptionDelegates) :
    BasePrescriptionViewHolder(itemView) {
    init {
        itemView.addMedicine.setOnClickListener {
            mData?.let {
                delegates.onTapMedicine(it.id,it.pillName.toString())
            }
        }
    }
    fun bindPresciption(medicineVO: MedicineVO){
        mData = medicineVO
        itemView.medicineName.text = medicineVO.pillName
        if (medicineVO.pillName == SessionManager.pill_name){
            itemView.addMedicine.visibility = View.GONE
            itemView.removeMedicine.visibility = View.VISIBLE
        }
    }
}