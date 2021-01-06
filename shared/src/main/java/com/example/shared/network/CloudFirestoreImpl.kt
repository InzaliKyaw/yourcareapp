package com.example.shared.network

import android.util.Log
import com.example.shared.data.model.BaseModel
import com.example.shared.data.vos.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

object CloudFirestoreImpl : FirebaseApi, BaseModel() {
    val db = Firebase.firestore
    const val TAG = "Cloud Firestore"

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
        val doctorMap = hashMapOf(
            "device_id" to deviceId,
            "id" to id,
            "phone" to phone,
            "name" to name,
            "speciality" to speciality,
            "gender" to gender,
            "experience" to experience,
            "degree" to degree,
            "bio" to bio,
            "address" to address

        )
        db.collection("doctors")
            .document(id.toString())
            .set(doctorMap)
            .addOnSuccessListener {
                Log.d(TAG, "Added Doctor Successfully")
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed Adding Doctor")
            }
    }


    override fun addPatient(
        patientVO: PatientsVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val patientMap = hashMapOf(
            "deviceId" to patientVO.deviceId,
            "id" to patientVO.id,
            "name" to patientVO.name,
            "email" to patientVO.email,
            "birthdate" to patientVO.birthdate,
            "height" to patientVO.height,
            "bloodType" to patientVO.bloodType,
            "bloodPressure" to patientVO.bloodPressure,
            "bloodPressure" to patientVO.bloodPressure,
            "weight" to patientVO.weight,
            "address" to patientVO.address,
            "specialities" to patientVO.specialitiesVO
        )
        db.collection("patients")
            .document(patientVO.id.toString())
            .set(patientMap)
            .addOnSuccessListener {
                Log.d(TAG, "Added Patient Successfully")
                onSuccess
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed Adding Doctor")
                onFailure
            }

    }

    override fun addPatientCaseSummary(
        patientId: Long,
        id: String,
        question: String,
        answer: String
    ) {
        val caseSummaryMap = hashMapOf(
            "id" to id,
            "question" to question,
            "answer" to answer
        )
        db.collection("patients/${patientId}/case_summary")
            .document(UUID.randomUUID().toString())
            .set(caseSummaryMap)
            .addOnSuccessListener {
                Log.d(TAG, "Added case summary Successfully")
            }.addOnFailureListener {
                Log.d(TAG, "Failed case summary Successfully")

            }
    }

    override fun getPatientCaseSummary(
        patientId: Long,
        onSuccess: (List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("patients")
            .document("${patientId}")
            .collection("case_summary")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    var caseSummaryList: MutableList<CaseSummaryVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val caseSummaryVO = CaseSummaryVO()
                        val data = document.data
                        caseSummaryVO.id = data?.get("id") as String
                        caseSummaryVO.question = data?.get("question") as String
                        caseSummaryVO.answer = data?.get("answer") as String
                        caseSummaryList.add(caseSummaryVO)
                    }
                    onSuccess(caseSummaryList)
                }
            }
    }


    //Done
    override fun getDoctor(onSuccess: (List<DoctorsVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("doctors").get().addOnSuccessListener { result ->
            val doctorsList: MutableList<DoctorsVO> = arrayListOf()

            for (document in result) {
                val data = document.data
                val doctor = DoctorsVO()
                doctor.id = data["id"] as? Long
                doctor.name = data["name"] as? String
                doctor.email = data["email"] as? String
                doctor.deviceId = data["device_id"] as? String
                doctor.speciality = data["speciality"] as? String
                doctor.phone = data["phone"] as? String
                doctor.gender = data["gender"] as? String
                doctor.experience = data["experience"] as? Long
                doctor.degree = data["degree"] as? String
                doctor.bio = data["bio"] as? String
                doctor.address = data["address"] as? String
                doctorsList.add(doctor)
            }
            onSuccess(doctorsList)
//           mTheDB.DoctorDao().insertAllDoctors(doctorsList)

        }.addOnFailureListener {
            Log.d(TAG, "Failed Doctor List" + it.message)
        }
    }

    override fun getPatients(onSuccess: (List<PatientsVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("patients").get().addOnSuccessListener { result ->
            val patientList: MutableList<PatientsVO> = arrayListOf()
            for (document in result) {
//                val data = document.data?.convertToPatientVO()
                val data = document.data
                data?.let {
//                    it?.let {
//                        patientList.add(it)
//                    }
                    val patientsVO = PatientsVO()
                    patientsVO.id = data["id"] as Long
                    patientsVO.deviceId = data["deviceId"] as? String
                    patientsVO.name = data["name"] as? String
                    patientsVO.email = data["email"] as? String
                    patientsVO.height = data["height"] as? String
                    patientsVO.bloodType = data["bloodType"] as? String
                    patientsVO.allergyMedicine = data["allergyMedicine"] as? String
                    patientsVO.weight = data["weight"] as? String
                    patientsVO.bloodPressure = data["bloodPressure"] as? String
                    patientsVO.address = data["address"] as? String
//                    patientsVO.specialitiesVO = data["specialities"] as? SpecialitiesVO
                    patientList.add(patientsVO)
                }
            }
            onSuccess(patientList)
        }.addOnFailureListener {
            Log.d(TAG, "Failed Patients List" + it.message)
        }
    }

    override fun getDoctorWithSpecialities(
        speciality: String,
        onSuccess: (List<DoctorsVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val userRef = db.collection("doctors")
        userRef.whereEqualTo("speciality", speciality).get().addOnSuccessListener { result ->
            val doctorsList: MutableList<DoctorsVO> = arrayListOf()

            for (document in result) {
                val data = document.data
                data?.let {
                    val doctor = DoctorsVO()
                    doctor.id = data["id"] as Long
                    doctor.deviceId = data["device_id"] as? String
                    doctor.name = data["name"] as String
                    doctor.speciality = data["speciality"] as String
                    doctorsList.add(doctor)
                }

            }
            onSuccess(doctorsList)
        }.addOnFailureListener {
            Log.d(TAG, "Failed Doctors with speciality" + it.message)
        }
    }

    //FixedtoAll
    override fun getGeneralQuestionTemplate(
        onSuccess: (List<GeneralQuestionTemplateVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("general_question_template").get()
            .addOnSuccessListener { result ->
                val generalQuestionList: MutableList<GeneralQuestionTemplateVO> = arrayListOf()
                for (document in result) {
                    val data = document.data
                    data?.let {
                        val generalQuestionTemplateVO = GeneralQuestionTemplateVO()
                        generalQuestionTemplateVO.id = data["id"] as Long
                        generalQuestionTemplateVO.question = data["question"] as String
                        generalQuestionTemplateVO.type = data["type"] as String
                        generalQuestionList.add(generalQuestionTemplateVO)
                    }
                }
                onSuccess(generalQuestionList)

            }.addOnFailureListener {
                Log.d(TAG, "Falied General Question Template" + it.message)
            }
    }

    override fun getMostUsedMedicineBySpeciality(
        speciality: String,
        onSuccess: (List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specialities").document(speciality).collection("most_used_medicine")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val mostusedMedicineList: MutableList<MedicineVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val mostUsedMedicineVO = MedicineVO()
                        val data = document.data
                        mostUsedMedicineVO.id = data?.get("id") as String
                        mostUsedMedicineVO.pillName = data["pill_name"] as? String
                        mostUsedMedicineVO.speciality = data["speciality"] as? String
                        mostUsedMedicineVO.price = data["price"] as? Long
                        //mostUsedMedicineVO.routine = data["rountine"] as? Routine
                        mostusedMedicineList.add(mostUsedMedicineVO)
                    }

                    onSuccess(mostusedMedicineList)
                    //mTheDB.MedicineDao().insertAllMedicine(mostusedMedicineList)
                }
            }
    }

    override fun getMostUsedMedicineById(
        medicineId: String,
        speciality: String,
        onSuccess: (List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specialities").document(speciality)
            .collection("most_used_medicine")
            .whereEqualTo("id",medicineId)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val medicineList: MutableList<MedicineVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val mostUsedMedicineVO = MedicineVO()
                        val data = document.data
                        mostUsedMedicineVO.id = data?.get("id") as String
                        mostUsedMedicineVO.pillName = data["pill_name"] as? String
                        mostUsedMedicineVO.speciality = data["speciality"] as? String
                        mostUsedMedicineVO.price = data["price"] as? Long
                        //mostUsedMedicineVO.routine = data["rountine"] as? Routine
                        medicineList.add(mostUsedMedicineVO)
                    }

                    onSuccess(medicineList)
                    //mTheDB.MedicineDao().insertAllMedicine(mostusedMedicineList)
                }
            }

    }

    override fun getRelatedQuestionBySpeciality(
        speciality: String,
        onSuccess: (List<RelatedQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specialities").document(speciality).collection("related_question")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val relatedQuestionList: MutableList<RelatedQuestionVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val relatedQuestionVO = RelatedQuestionVO()
                        val data = document.data
                        relatedQuestionVO.id = data?.get("id") as String
                        relatedQuestionVO.question = data?.get("question") as String
                        relatedQuestionList.add(relatedQuestionVO)
                    }
                    onSuccess(relatedQuestionList)
                }
            }
    }

    //toLog
    //BroadCastConsultationRequest(consultation+pushNotification)
    override fun startConsultationRequest(
        id: Long,
        date: String,
        time: String,
        responseStatus: String,
        patientsVO: PatientsVO,
        specialitiesVO: SpecialitiesVO,
    ) {
        val consultationMap = hashMapOf(
            "id" to id,
            "time" to time,
            "date" to date,
            "responseStatus" to responseStatus,
            "patients" to patientsVO,
            "specialites" to specialitiesVO,
        )
        db.collection("consultation_request")
            .document(id.toString())
            .set(consultationMap)
            .addOnSuccessListener {
                Log.d(TAG, "Consultation  request successful" + it)
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed request")
            }

    }

    override fun addCaseSummaryToConsultationRequest(
        consultationId: Long,
        id: String,
        question: String,
        answer: String
    ) {
        val caseSummaryMap = hashMapOf(
            "id" to id,
            "question" to question,
            "answer" to answer
        )
        db.collection("consultation_request/${consultationId}/case_summary")
            .document(UUID.randomUUID().toString())
            .set(caseSummaryMap)
            .addOnSuccessListener {
                Log.d(TAG, "Added Consultation Request Case Summary")
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed Adding Consultation Request Case Summary")
            }

    }

    //NeedToGetDataWithSpecialities
    //ToAsk
    override fun getConsultationRequestWithSpecialities(
        speciality: String,
        onSuccess: (List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check network connection")
                } ?: run {
                    val consultationRequestList: MutableList<ConsultationRequestVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document.data?.convertToConsultationRequestVO()
                        data?.let {
                            consultationRequestList.add(it)
                        }

                    }
                    onSuccess(consultationRequestList)
                }
            }
    }

    override fun getConsultationRequest(
        onSuccess: (List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check network connection")
                } ?: run {
                    val consultationRequestList: MutableList<ConsultationRequestVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document.data?.convertToConsultationRequestVO()
                        data?.let {
                            it?.let {

                                consultationRequestList.add(it)
                            }
                        }

                    }
                    onSuccess(consultationRequestList)
                }
            }
    }

    override fun getConsultationRequestCaseSummaryQAndA(
        consultationId: Long,
        onSuccess: (List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request/$consultationId/case_summary")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check network connection")
                } ?: run {
                    val caseSummaryList: MutableList<CaseSummaryVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val caseSummaryVO = CaseSummaryVO()
                        val data = document.data
                        data?.let {
                            caseSummaryVO.id = data?.get("id") as String
                            caseSummaryVO.question = data?.get("question") as String
                            caseSummaryVO.answer = data?.get("answer") as String
                            caseSummaryList.add(caseSummaryVO)
                        }
                        onSuccess(caseSummaryList)
                    }
                }
            }
    }

    override fun consultationWithSameId(
        consultationId: Long,
        onSuccess: (List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation").whereEqualTo("id", consultationId)
            .get()
            .addOnSuccessListener { result ->
                val consultationList: MutableList<ConsultationVO> = arrayListOf()
                result.forEach { document ->
                    val data = document.data?.convertToConsultationVO()
                    data?.let {
                        consultationList.add(it)
                    }
                }
                onSuccess(consultationList)
            }.addOnFailureListener {
                Log.d(TAG, "Failed Adding Consultation Request Case Summary")

            }



    }

    override fun finishConsultation(consultationId: Long) {
        val finishMap= hashMapOf(
            "finish_status" to true
        )
        db.collection("consultation")
            .document(consultationId.toString())
            .set(finishMap)
            .addOnSuccessListener {
                Log.d(
                    TAG, "added Finish Consultation successfully" + it
                )
            }
            .addOnFailureListener {
                Log.d(
                    TAG, "Failed Finish Consultation successfully" + it
                )
            }
    }


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
        val prescriptionMap = hashMapOf(
            "id" to id,
            "pill_name" to pillName,
            "speciality" to speciality,
            "price" to price,
            "routine" to routine,
            "days" to days,
            "amount" to amount,
            "remark" to remark
        )
        db.collection("consultation/$chatId/prescription")
            .document(UUID.randomUUID().toString())
            .set(prescriptionMap)
            .addOnSuccessListener {
                Log.d(
                    TAG, "added Prescription successfully" + it
                )
            }.addOnFailureListener {
                Log.d(
                    TAG, "Failed addind Prescription" + it
                )
            }
    }

    override fun checkOut(
        caseSummaryVO: List<CaseSummaryVO>,
        medicineVO: MedicineVO,
        patientsVO: PatientsVO,
        id: Long,
        deliveryRoutine: String,
        address: String
    ) {
        val checkoutMap = hashMapOf(
            "id" to id,
            "address" to address,
            "delivery_routine" to deliveryRoutine,
            "patients" to patientsVO,
            "prescription" to medicineVO,
            "patients" to patientsVO
        )

        db.collection("check_out")
            .document(id.toString())
            .set(checkoutMap)
            .addOnSuccessListener {
                Log.d(TAG, "Added checkout successfully")
                val caseSummaryMap = hashMapOf(
                    "case_summary" to caseSummaryVO
                )
                db.collection("check_out")
                    .document(id.toString())
                    .collection("case_summary")
                    .document(UUID.randomUUID().toString())
                    .set(caseSummaryMap)
                    .addOnSuccessListener {
                        Log.d(TAG, "Added sub collection successfully")
                    }
                    .addOnFailureListener {
                        Log.d(TAG, "Failed sub collection")
                    }
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed checkout")
            }
    }




    //SubCollectionCaseSummaryCheckout
//    override fun addCaseSummaryForCheckOut(
//        checkOutId:Long,
//        caseSummaryVO: CaseSummaryVO
//    ) {
//        val caseSummaryMap = hashMapOf(
//           "case_summary" to caseSummaryVO
//        )
//        db.collection("check_out")
//            .document(checkOutId.toString())
//            .collection("case_summary")
//            .document(UUID.randomUUID().toString())
//            .set(caseSummaryMap)
//            .addOnSuccessListener {
//                Log.d(TAG,"Added sub collection successfully")
//            }
//            .addOnFailureListener {
//                Log.d(TAG,"Failed sub collection")
//            }
//
//
//    }


//toLog
//    override fun addCaseSummaryToConsultationRequest(id:String,caseSummaryVO: CaseSummaryVO) {
//        val caseSummaryVO = hashMapOf(
//            "case_summary" to caseSummaryVO
//        )
//
//    }

    //Done
    override fun getAllSpeciality(
        onSuccess: (List<SpecialitiesVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specialities").get().addOnSuccessListener { result ->
            val specialitiesList: MutableList<SpecialitiesVO> = arrayListOf()
            for (document in result) {
                val specialitiesVO: SpecialitiesVO = SpecialitiesVO()
                specialitiesVO.id = document?.get("id") as String
                specialitiesVO.name = document?.get("name") as String
                specialitiesVO.image = document?.get("image") as String
                specialitiesList.add(specialitiesVO)
            }
            onSuccess(specialitiesList)
        }.addOnFailureListener {
            Log.d(TAG, "Failed adding specialities" + it)
        }
    }

    //toLog
    //consultation_random_id??
    override fun sendMessage(
        chatId: Long,
        id: String,
        messageImage: String,
        messageText: String,
        sendAt: String,
        sendBy: String
    ) {
//        var id = UUID.randomUUID().toString()
        val messageMap = hashMapOf(
            "id" to id,
            "message_image" to messageImage,
            "message_text" to messageText,
            "send_at" to sendAt,
            "send_by" to sendBy
        )
        db.collection("consultation/$chatId/chat")
            .document(UUID.randomUUID().toString())
            .set(messageMap)
            .addOnSuccessListener {
                Log.d(TAG, "Added Chat Successfully")
            }.addOnFailureListener {
                Log.d(TAG, "Failed Chat")
            }
    }

    override fun getConsultations(
        onSuccess: (List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val chatList: MutableList<ConsultationVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val consultationVO = ConsultationVO()
                        val data = document.data
                        consultationVO.id = data?.get("id") as Long
                        chatList.add(consultationVO)
                    }
                    onSuccess(chatList)
                }
            }
    }

    override fun getChatConsultation(
        chatId: Long,
        onSuccess: (List<ChatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation/$chatId/chat")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                }
                    ?: run {
                        val chatVOList: MutableList<ChatVO> = arrayListOf()
                        val result = value?.documents ?: arrayListOf()
                        for (document in result) {
                            val chatVO = ChatVO()
                            val data = document.data
                            chatVO.id = data?.get("id") as String
                            chatVO.messageImage = data?.get("message_image") as String
                            chatVO.messageText = data?.get("message_text") as String
                            chatVO.sendAt = data?.get("send_at") as String
                            chatVO.sendBy = data?.get("send_by") as String
                            chatVOList.add(chatVO)
                        }
                        onSuccess(chatVOList)
                    }
            }
    }

    override fun getPrescriptionFromConsultation(
        consultationId: Long,
        onSuccess: (List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation/$consultationId/prescription")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                }?:run{
                    val prescriptionList:MutableList<MedicineVO> = arrayListOf()
                    val result = value?.documents?: arrayListOf()
                    for (document in result){
                        val medicineVO = MedicineVO()
                        val data = document.data
                        medicineVO.id = data?.get("id") as String
                        medicineVO.pillName = data?.get("pill_name") as String
                        medicineVO.price = data.get("price")as Long
                        medicineVO.amount = data.get("amount") as Long
                        medicineVO.days = data.get("days")as Long
                        medicineVO.speciality= data?.get("speciality")as String
                        medicineVO.remark = data?.get("remark")as String
                        prescriptionList.add(medicineVO)
                    }
                    onSuccess(prescriptionList)
                }
            }

    }

    /*
    //toLog//towrite
    override fun acceptRequest(
        responseStatus: String,
        doctorsVO: DoctorsVO,
        patientNameId: Long,
        patientsVO: PatientsVO
    ) {
        val consultationMap = hashMapOf(
            "doctors" to doctorsVO,
            "id" to patientNameId,
            "patients" to patientsVO
        )
        db.collection("consultation")
            .document(patientsVO.name)
            .set(consultationMap)
            .addOnSuccessListener {
                Log.d(TAG, "Added consultation successfully" + it)
            }.addOnFailureListener {
                Log.d(TAG, "Failed consultation")
            }

        val status = hashMapOf(
            "response_status" to responseStatus
        )
        db.collection("consultation_request")
            .document()
    }

     */

    //
    //PatientHtalKa
    override fun getRecentlyConsultedDoctors(
        patientName: String,
        onSuccess: (List<DoctorsVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

}