package com.example.doctors.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.doctors.delegates.PrescriptionDelegates
import com.example.doctors.mvp.view.PrescriptionView
import com.example.shared.data.vos.MedicineVO
import com.example.shared.data.vos.Routine

interface PrescriptionPresenter : BasePresenter<PrescriptionView>, PrescriptionDelegates {
    fun loadMostUsedMedicineBySpeciality(lifecycleOwner: LifecycleOwner, speciality: String)
    fun onTapAddPrescription(
        lifecycleOwner: LifecycleOwner,
        chatId: Long,
        id: String,
        pillName: String,
        speciality: String,
        price: Long,
        routine: Routine,
        days: Long,
        amount: Long,
        remark: String
    )

    fun finishConsultation(
        consultationId:Long
    )

    fun loadAllPrescriptionAndAddedToChat(
        consultationId: Long
    )

//    fun loadMedicineDataWithId(
//        lifecycleOwner: LifecycleOwner,
//        medicineId: String,
//        speciality: String
//    ): MedicineVO


}