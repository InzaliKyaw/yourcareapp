package com.example.yourcare.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.MedicineVO
import kotlinx.android.synthetic.main.item_prescription.view.*
import kotlinx.android.synthetic.main.item_prescription_list.view.*

class PrescripeCheckOutViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    var day:Long = 0
    var amount:Long = 0
    var totalPrice:Long = 0
    var price:Long =0
    fun bindPrescripeData(medicineVO: MedicineVO){

        itemView.medicineNameCT.text = medicineVO.pillName
        day = medicineVO.days!!
        amount = medicineVO.amount!!
        itemView.amountCT.text = medicineVO.amount.toString()+" Tablet"
        //need To Calculate with times
        price = medicineVO.price!!
        totalPrice = (day*amount)* price
        itemView.priceCT.text = totalPrice.toString()
    }
}