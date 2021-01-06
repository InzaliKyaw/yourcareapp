package com.example.yourcare.view.view

import com.example.shared.data.vos.MedicineVO
import com.example.yourcare.mvp.view.BaseView

interface ConfirmCheckoutDialogView:BaseView {
    fun showPrescriptionList(medicineList: List<MedicineVO>)
}