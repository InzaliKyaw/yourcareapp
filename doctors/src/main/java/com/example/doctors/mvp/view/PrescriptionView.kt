package com.example.doctors.mvp.view

import com.example.shared.data.vos.MedicineVO

interface PrescriptionView:BaseView {
    fun displayMedicineList(medicineList:List<MedicineVO>)
    fun showDialogPrescription(medicineId:String,pillName:String)

}