package com.example.shared.data.model


import android.util.Log
import androidx.lifecycle.LiveData
import com.example.shared.data.vos.*
import com.example.shared.network.CloudFirestoreImpl
import com.example.shared.network.FirebaseApi

object YourcareModelImpl : YourcareModel, BaseModel() {

    const val TAG: String = "YOUR CARE MODEL"
    override var mFirebaseApi: FirebaseApi = CloudFirestoreImpl

    override fun addDoctors(
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
    ) {
        mFirebaseApi.addDoctors(
            deviceId,
            id,
            name,
            phone,
            speciality,
            gender,
            experience,
            degree,
            bio,
            address
        )
    }

    override fun addPatient(
        patientsVO: PatientsVO,
        onSuccess: (PatientsVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addPatient(patientsVO, onSuccess = {
            onSuccess(patientsVO)
        }, onFailure = {
            Log.d(TAG, "Failed Adding Patient")
        })
    }

    override fun addPatientCaseSummary(
        patientId: Long,
        id: String,
        question: String,
        answer: String
    ) {
        mFirebaseApi.addPatientCaseSummary(patientId, id, question, answer)
    }

    override fun getPatientCaseSummary(
        patientId: Long,
        onSuccess: (List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPatientCaseSummary(patientId, onSuccess, onFailure)
    }

    /*
        override fun getPatientWithId(
            patientEmail: String,
            onSuccess: (List<PatientsVO>) -> Unit,
            onFailure: (String) -> Unit
        ) {
            mFirebaseApi.getPatientWithId( patientEmail, onSuccess, onFailure)
        }

     */
    override fun addPrescriptionConsultation(
        chatId: Long,
        id: String,
        pillName: String,
        speciality: String,
        price: Long,
        routine: Routine,
        days: Long,
        amount: Long,
        remark: String
    ) {
        mFirebaseApi.addPrescriptionConsultation(
            chatId,
            id,
            pillName,
            speciality,
            price,
            routine,
            days,
            amount,
            remark
        )
    }


//    override fun addDoctors(deviceId: String, id: Int, name: String, speciality: String) {
//        mFirebaseApi.addDoctors(deviceId, id, name, speciality)
//    }

    override fun getDoctors(onFailure: (String) -> Unit): LiveData<List<DoctorsVO>> {
        mFirebaseApi.getDoctor(onSuccess = {
            mTheDB.doctorDao().deleteAll()
            mTheDB.doctorDao().insertAllDoctors(it)
        }, onFailure = {
            Log.d(TAG, "GET DOCTOR FAILED" + it)
        })
        return mTheDB.doctorDao().getAllDoctorsList()
    }

    override fun getDoctorByEmail(email: String): LiveData<List<DoctorsVO>> {
        return mTheDB.doctorDao().getDoctorByEmail(email)
    }

    override fun getPatients(onSuccess: (List<PatientsVO>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getPatients(onSuccess, onFailure)
    }

    override fun registerNewPatient(
        patientVO: PatientsVO
    ) {
        mFirebaseApi.addPatient(patientVO, onSuccess = {
            Log.d(TAG, "Success adding Patient Request")
        }, onFailure = {
            Log.d(TAG, "Failed adding Patient Request")

        })
    }

    override fun getPatientsFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<PatientsVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getPatients(onSuccess = {
            mTheDB.patientDao().deleteAll()
            mTheDB.patientDao().insertAllPatients(it)
        }, onFailure = {
            Log.d(TAG, "Failed getting patient" + it)
        })
    }

    override fun getPatientByEmail(email: String): LiveData<List<PatientsVO>> {
        return mTheDB.patientDao().getPatientWithEmail(email)

    }

    override fun getPatientById(id: Long): LiveData<List<PatientsVO>> {
        return mTheDB.patientDao().getPatientById(id)
    }


    override fun getConsultationRequest(onFailure: (String) -> Unit): LiveData<List<ConsultationRequestVO>> {
        mFirebaseApi.getConsultationRequest(onSuccess = {
            mTheDB.consultationRequestDao().insertAllConsultationRequest(it)
            Log.d(TAG, "Success getting Consultation Request" + it)
        }, onFailure = {
            Log.d(TAG, "Failed getting Consultation Request" + it)

        })
        return mTheDB.consultationRequestDao().getAllConsultationRequest()
    }

    override fun getConsultationRequestFromFirebase(
        onSuccess: (List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultationRequest(onSuccess, onFailure)
    }

    override fun getConsultationRequestWithId(consultationId: Long): LiveData<List<ConsultationRequestVO>> {
        return mTheDB.consultationRequestDao().getConsultationRequestWithId(consultationId)
    }

//    override fun getMedicineDataWithId(
//        speciality: String,
//        medicineId: String,
//        onSuccess: (List<MedicineVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mFirebaseApi.getMostUsedMedicineById(medicineId, speciality, onSuccess, onFailure)
//    }

    override fun getMedicineDataWithId(medicineId: String): LiveData<List<MedicineVO>> {
        return mTheDB.medicineDao().getMedicineWithId(medicineId)
    }

    override fun getConsultationRequestCaseSummaryQAndA(
        consultationId: Long,
        onSuccess: (List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultationRequestCaseSummaryQAndA(consultationId, onSuccess, onFailure)
    }

    override fun finishConsultation(consultationId: Long) {
        mFirebaseApi.finishConsultation(consultationId)
    }

    override fun getPrescriptionFromConsultation(
        consultationId: Long,
        onSuccess: (List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPrescriptionFromConsultation(consultationId, onSuccess, onFailure)
    }

    override fun getAllSpeciality(onFailure: (String) -> Unit): LiveData<List<SpecialitiesVO>> {
        mFirebaseApi.getAllSpeciality(onSuccess = {
            mTheDB.specialityDao().insertAllSpeciality(it)
            Log.d(TAG, "Get All Speciality" + it)
        }, onFailure = {
            Log.d(TAG, "Failed getting All Speciality" + it)
        })
        return mTheDB.specialityDao().getAllSpeciality()
    }

    override fun getSpecialityById(id: String): LiveData<List<SpecialitiesVO>> {
        return mTheDB.specialityDao().getSpecialityWithId(id)
    }

    override fun getDoctorWithSpecialities(
        speciality: String,
        onSuccess: (List<DoctorsVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getDoctorWithSpecialities(speciality, onSuccess, onFailure)
    }

    override fun getGeneralQuestionTemplate(
        onFailure: (String) -> Unit
    ): LiveData<List<GeneralQuestionTemplateVO>> {
        mFirebaseApi.getGeneralQuestionTemplate(onSuccess = {
            mTheDB.generalQuestionTemplateDao().insertAllGeneralQuestion(it)
            Log.d(TAG, "Get All General Question" + it)
        }, onFailure = {
            Log.d(TAG, "Failed getting All General Question" + it)
        })
        //mFirebaseApi.getGeneralQuestionTemplate(type, onSuccess, onFailure)
        return mTheDB.generalQuestionTemplateDao().getAllGeneralQuestion()
    }

    override fun getMostUsedMedicineWithSpecialities(speciality: String): LiveData<List<MedicineVO>> {
        mFirebaseApi.getMostUsedMedicineBySpeciality(speciality,onSuccess = {
            mTheDB.medicineDao().deleteAll()
            mTheDB.medicineDao().insertAllMedicine(it)
            Log.d(TAG,"Success Medicine"+it)
        },onFailure = {
            Log.d(TAG,"Failed Medicine"+it)
        })
        return mTheDB.medicineDao().getAllMedicine()
    }

//    override fun getMostUsedMedicineWithSpecialities(
//        speciality: String,
//        onSuccess: (List<MedicineVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mFirebaseApi.getMostUsedMedicineBySpeciality(speciality, onSuccess, onFailure)
//    }

//    override fun getMostUsedMedicineFromFirebaseAndAddToDB(
//        speciality: String
//    ): LiveData<List<MedicineVO>> {
//        mFirebaseApi.getMostUsedMedicineBySpeciality(speciality,onSuccess = {
//            mTheDB.medicineDao().deleteAll()
//            mTheDB.medicineDao().insertAllMedicine(it)
//            Log.d(TAG, "Success getting All Medicne $it")
//        },onFailure = {
//            Log.d(TAG, "Failed getting All Medicne $it")
//        })
//        return mTheDB.medicineDao().getAllMedicine()
//    }

    override fun getRelatedQuestionWithSpecialities(
        speciality: String,
        onSuccess: (List<RelatedQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getRelatedQuestionBySpeciality(speciality, onSuccess, onFailure)
    }

    //check
    override fun consultationRequestStart(
        id: Long,
        date: String,
        time: String,
        responseStatus: String,
        patientsVO: PatientsVO,
        specialitiesVO: SpecialitiesVO,
    ) {
        mFirebaseApi.startConsultationRequest(
            id,
            date,
            time,
            responseStatus,
            patientsVO,
            specialitiesVO
        )
    }

    override fun addCaseSummaryToConsultationRequest(
        consultationId: Long,
        id: String,
        question: String,
        answer: String
    ) {
        mFirebaseApi.addPatientCaseSummary(consultationId, id, question, answer)
    }

    override fun consultationWithSameId(
        consultationId: Long,
        onSuccess: (List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.consultationWithSameId(consultationId, onSuccess, onFailure)
    }

    override fun checkout(
        caseSummaryVO: List<CaseSummaryVO>,
        medicineVO: MedicineVO,
        patientsVO: PatientsVO,
        id: Long,
        deliveryRoutine: String,
        address: String
    ) {
        mFirebaseApi.checkOut(caseSummaryVO, medicineVO, patientsVO, id, deliveryRoutine, address)
    }

    override fun getConsultationRequestWithSpeciality(
        speciality: String,
        onSuccess: (List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultationRequestWithSpecialities(speciality, onSuccess, onFailure)
    }

    override fun sendMessage(
        chatId: Long,
        id: String,
        messageImage: String,
        messageText: String,
        sendAt: String,
        sendBy: String
    ) {
        mFirebaseApi.sendMessage(chatId, id, messageImage, messageText, sendAt, sendBy)
    }

    override fun getConsultations(
        onSuccess: (List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultations(onSuccess, onFailure)
    }

    override fun getChatConsultation(
        chatId: Long,
        onSuccess: (List<ChatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getChatConsultation(chatId, onSuccess, onFailure)
    }


//    override fun addCaseSummaryForCheckOut(
//        checkOutId: Long,
//        caseSummaryVO: CaseSummaryVO
//    ) {
//        mFirebaseApi.addCaseSummaryForCheckOut(checkOutId,caseSummaryVO)
//    }
}