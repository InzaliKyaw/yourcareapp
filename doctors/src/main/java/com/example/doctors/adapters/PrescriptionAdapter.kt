package com.example.doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.R
import com.example.doctors.delegates.PrescriptionDelegates
import com.example.doctors.view.viewholders.PrescriptionViewHolder
import com.example.shared.data.vos.MedicineVO

class PrescriptionAdapter(prescriptionDelegates: PrescriptionDelegates): RecyclerView.Adapter<PrescriptionViewHolder>() {
    private var mData:MutableList<MedicineVO> = arrayListOf()
    private var mDelegates:PrescriptionDelegates = prescriptionDelegates
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prescription,parent,false)
        return PrescriptionViewHolder(view,mDelegates)
    }

    override fun onBindViewHolder(holder: PrescriptionViewHolder, position: Int) {
        holder.bindPresciption(mData[position])
    }

    fun setNewData(data:MutableList<MedicineVO>){
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}