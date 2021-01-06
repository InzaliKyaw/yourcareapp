package com.example.doctors.dummy

import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.MedicineVO
import com.example.shared.data.vos.PatientsVO
import com.example.shared.data.vos.Routine

fun getPatient(): PatientsVO {
    var patientsVO: PatientsVO = PatientsVO(1, "Lupin", "")
    return patientsVO
}

fun getCaseSummary(): List<CaseSummaryVO> {
    var caseSummaryList:MutableList<CaseSummaryVO> = arrayListOf()
    var caseSummaryVO: CaseSummaryVO = CaseSummaryVO("", "Do yocau drink alcohol", "Yes")
     caseSummaryList.add(caseSummaryVO)
    return caseSummaryList
}

fun getMedicine(): MedicineVO {
    var medicineVO: MedicineVO =
        MedicineVO("1", "Pazotax", "Bones and Joints", 50,
            routine = Routine(true, false, false))
    return medicineVO
}