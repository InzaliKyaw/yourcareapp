package com.example.shared.data.model

import androidx.lifecycle.LiveData
import com.example.shared.data.vos.*
import com.example.shared.network.FirebaseApi


interface YourcareModel {
    var mFirebaseApi: FirebaseApi

    fun addDoctors(
        deviceId: String,
        id: Int,
        name: String,
        phone: String,
        speciality: String,
        gender: String,
        experience: Long,
        degree: String,
        bio: String,
        address: String
    )
    fun addPatient(
        patientsVO: PatientsVO,
        onSuccess: (PatientsVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun addPatientCaseSummary(
        patientId: Long,
        id: String,
        question: String,
        answer: String
    )

    fun getPatientCaseSummary(
        patientId: Long,
        onSuccess: (List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    /*
    fun getPatientWithId(
        patientEmail:String,
        onSuccess:(List<PatientsVO>)->Unit,
        onFailure: (String) -> Unit
    )
     */
    fun registerNewPatient(
        patientVO: PatientsVO
    )

    fun addPrescriptionConsultation(
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

    fun getDoctors(onFailure: (String) -> Unit): LiveData<List<DoctorsVO>>

    fun getDoctorByEmail(email: String):LiveData<List<DoctorsVO>>

    //fun getPatients(onFailure: (String) -> Unit):LiveData<List<PatientsVO>>
    fun getPatients(onSuccess: (List<PatientsVO>) -> Unit, onFailure: (String) -> Unit)

    fun getPatientsFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<PatientsVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getPatientByEmail(email: String): LiveData<List<PatientsVO>>

    fun getPatientById(id: Long): LiveData<List<PatientsVO>>


    fun getConsultationRequest(onFailure: (String) -> Unit): LiveData<List<ConsultationRequestVO>>

    fun getConsultationRequestFromFirebase(
        onSuccess: (List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationRequestWithId(consultationId: Long): LiveData<List<ConsultationRequestVO>>

    fun getMedicineDataWithId(medicineId: String): LiveData<List<MedicineVO>>
    //fun getMedicineDataWithId(speciality: String,medicineId:String,onSuccess: (List<MedicineVO>) -> Unit,onFailure: (String) -> Unit)

    fun getConsultationRequestCaseSummaryQAndA(
        consultationId: Long,
        onSuccess: (List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun finishConsultation(consultationId: Long)

    fun getPrescriptionFromConsultation(
        consultationId: Long,
        onSuccess: (List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getAllSpeciality(onFailure: (String) -> Unit): LiveData<List<SpecialitiesVO>>

    fun getSpecialityById(id: String): LiveData<List<SpecialitiesVO>>

    fun getDoctorWithSpecialities(
        speciality: String,
        onSuccess: (List<DoctorsVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGeneralQuestionTemplate(
        onFailure: (String) -> Unit
    ): LiveData<List<GeneralQuestionTemplateVO>>


//    fun getMostUsedMedicineWithSpecialities(
//        speciality: String,
//        onSuccess: (List<MedicineVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )

    fun getMostUsedMedicineWithSpecialities(speciality: String):LiveData<List<MedicineVO>>

    fun getRelatedQuestionWithSpecialities(
        speciality: String,
        onSuccess: (List<RelatedQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    )

//    fun getMostUsedMedicineWithSpecialities(
//        speciality: String,
//        onFailure: (String) -> Unit
//    ):LiveData<List<MedicineVO>>

    fun consultationRequestStart(
        id: Long,
        date: String,
        time: String,
        responseStatus:String,
        patientsVO: PatientsVO,
        specialitiesVO: SpecialitiesVO,
    )
    fun addCaseSummaryToConsultationRequest(
        consultationId:Long,
        id: String,
        question: String,
        answer: String
    )
    //Same Id with consultation request and consultation
    fun consultationWithSameId(
        consultationId: Long,
        onSuccess:(List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkout(
        caseSummaryVO: List<CaseSummaryVO>,
        medicineVO: MedicineVO,
        patientsVO: PatientsVO,
        id: Long,
        deliveryRoutine: String,
        address: String
    )

    fun getConsultationRequestWithSpeciality(
        speciality: String,
        onSuccess: (List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun sendMessage(
        chatId: Long,
        id: String,
        messageImage: String,
        messageText: String,
        sendAt: String,
        sendBy: String
    )

    fun getConsultations(
        onSuccess: (List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getChatConsultation(
        chatId: Long,
        onSuccess: (List<ChatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

//    fun addCaseSummaryForCheckOut(
//        checkOutId: Long,caseSummaryVO: CaseSummaryVO
//    )
}