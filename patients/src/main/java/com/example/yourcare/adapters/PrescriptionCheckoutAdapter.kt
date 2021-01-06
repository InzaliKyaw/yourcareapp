package com.example.yourcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.MedicineVO
import com.example.yourcare.R
import com.example.yourcare.view.viewholders.PrescripeCheckOutViewHolder

class PrescriptionCheckoutAdapter: RecyclerView.Adapter<PrescripeCheckOutViewHolder>() {
    private var mData:MutableList<MedicineVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescripeCheckOutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prescription_list,parent,false)
        return PrescripeCheckOutViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrescripeCheckOutViewHolder, position: Int) {
        holder.bindPrescripeData(mData[position])
    }

    fun setNewData(data:MutableList<MedicineVO>){
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.size
    }

}