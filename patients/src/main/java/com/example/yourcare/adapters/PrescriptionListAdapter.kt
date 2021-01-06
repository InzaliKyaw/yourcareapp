package com.example.yourcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.MedicineVO
import com.example.yourcare.R
import com.example.yourcare.view.viewholders.PrescriptionViewHolder

class PrescriptionListAdapter: RecyclerView.Adapter<PrescriptionViewHolder>() {

    private var mData:MutableList<MedicineVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prescription_text,parent,false)
        return PrescriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrescriptionViewHolder, position: Int) {
        holder.bindPrescription(mData[position])
    }

    fun setNewData(data:MutableList<MedicineVO>){
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}