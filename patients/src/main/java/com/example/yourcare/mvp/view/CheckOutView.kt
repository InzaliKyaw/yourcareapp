package com.example.yourcare.mvp.view

import com.example.shared.data.vos.MedicineVO

interface CheckOutView:BaseView {
    fun showPrescriptionList(medicineList:List<MedicineVO>)
    fun showTotalPrice(price: Long)
}