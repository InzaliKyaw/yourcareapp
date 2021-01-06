package com.example.yourcare.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.MedicineVO
import kotlinx.android.synthetic.main.item_prescription_text.view.*

class PrescriptionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindPrescription(medicineVO: MedicineVO){
        itemView.txtMedicineList.text = medicineVO.pillName
    }
}