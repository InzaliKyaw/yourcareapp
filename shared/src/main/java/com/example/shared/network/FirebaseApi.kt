package com.example.shared.network

import androidx.lifecycle.LiveData
import com.example.shared.data.vos.*
import java.net.CacheResponse

interface FirebaseApi {
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

    fun addPatient(patientVO: PatientsVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addPatientCaseSummary(
        patientId:Long,
        id: String,
        question: String,
        answer: String
    )

    fun getPatientCaseSummary(
        patientId: Long,
        onSuccess:(List<CaseSummaryVO>)->Unit,
        onFailure: (String) -> Unit
    )

    /*
    fun getPatientWithId(
        patientEmail:String,
        onSuccess:(List<PatientsVO>)->Unit,
        onFailure: (String) -> Unit
    )
     */



    fun getDoctor(onSuccess: (List<DoctorsVO>) -> Unit, onFailure: (String) -> Unit)

    fun getPatients(onSuccess: (List<PatientsVO>) -> Unit, onFailure: (String) -> Unit)

    fun getDoctorWithSpecialities(
        speciality: String,
        onSuccess: (List<DoctorsVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGeneralQuestionTemplate(
        onSuccess: (List<GeneralQuestionTemplateVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMostUsedMedicineBySpeciality(
        speciality: String,
        onSuccess: (List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    //fun getMostUsedMedicineBySpeciality(speciality: String):LiveData<List<MedicineVO>>

    fun getMostUsedMedicineById(
        medicineId:String,
        speciality: String,
        onSuccess: (List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getRelatedQuestionBySpeciality(
        speciality: String,
        onSuccess: (List<RelatedQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun startConsultationRequest(
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

    fun getConsultationRequestWithSpecialities(
        speciality: String,
        onSuccess: (List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationRequest(
        onSuccess: (List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationRequestCaseSummaryQAndA(
        consultationId: Long,
        onSuccess: (List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    //Same Id with consultation request and consultation
    fun consultationWithSameId(
        consultationId: Long,
        onSuccess:(List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun finishConsultation(
        consultationId: Long
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

    fun checkOut(
        caseSummaryVO: List<CaseSummaryVO>,
        medicineVO: MedicineVO,
        patientsVO: PatientsVO,
        id: Long,
        deliveryRoutine: String,
        address: String
    )

    fun getAllSpeciality(onSuccess: (List<SpecialitiesVO>) -> Unit, onFailure: (String) -> Unit)


    fun sendMessage(
        chatId: Long,
        id: String,
        messageImage: String,
        messageText: String,
        sendAt: String,
        SendBy: String
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

    fun getPrescriptionFromConsultation(
        consultationId: Long,
        onSuccess: (List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    /*
        fun acceptRequest(
            responseStatus: String,
            doctorsVO: DoctorsVO,
            id: Long,
            patientsVO: PatientsVO
        )
     */
    fun getRecentlyConsultedDoctors(
        patientName: String,
        onSuccess: (List<DoctorsVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    //fun sendDirectRequest()

}