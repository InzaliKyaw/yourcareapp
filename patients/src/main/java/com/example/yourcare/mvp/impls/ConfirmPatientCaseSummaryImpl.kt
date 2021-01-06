package com.example.yourcare.mvp.impls

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.PatientsVO
import com.example.shared.data.vos.SpecialitiesVO
import com.example.yourcare.mvp.AbstractBasePresenter
import com.example.yourcare.mvp.ConfirmCaseSummaryPresenter
import com.example.yourcare.mvp.view.ConfirmCaseSummaryView
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ConfirmPatientCaseSummaryImpl : ConfirmCaseSummaryPresenter,
    AbstractBasePresenter<ConfirmCaseSummaryView>() {
    private var mYourcareModel: YourcareModel = YourcareModelImpl
    private lateinit var currentDay: String
    private lateinit var patientsVO: PatientsVO
    private lateinit var specialitiesVO: SpecialitiesVO
    val TAG = "Confirm CASE SUMMARY"
    private var consultationReqId: Long = 0

    var caseSummaryVO: List<CaseSummaryVO> = arrayListOf()


    override fun loadPatientGeneralData(lifecycleOwner: LifecycleOwner, patientId: Long) {
        mYourcareModel.getPatientById(patientId).observe(lifecycleOwner, Observer {
            it.forEach {
                mView.showPatientData(it)
                patientsVO = it
            }
        })
    }

    override fun loadPatientCaseSummary(lifecycleOwner: LifecycleOwner, patientId: Long) {
        mYourcareModel.getPatientCaseSummary(patientId, onSuccess = {
            mView.showCaseSummary(it)
            caseSummaryVO = it
        }, onFailure = {
            mView.showError(it)
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun startConsultationRequest(
        lifecycleOwner: LifecycleOwner,
        patientId: Long,
        speciality: String
    ) {

        val currentDay = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formated = currentDay.format(formatter)


        var currentTime: Date = Calendar.getInstance().time
        var timeFormatter: SimpleDateFormat = SimpleDateFormat("hh:mm a")
        var time: String = timeFormatter.format(currentTime).toString()

        //CaseSummary Should Add With List

//        mYourcareModel.getSpecialityById(speciality).observe(lifecycleOwner, Observer {
//            it.forEach {
//                specialitiesVO = SpecialitiesVO(it.id,it.name,it.image)
//            }
//        })
        var count: String = ""
        mYourcareModel.getConsultationRequestWithSpeciality("bones_and_joints", onSuccess = {
           // count = it.size.toString()
            Log.d(TAG, "SUCCESS")
        }, onFailure = {
            Log.d(TAG, "FAILED")
        })


        consultationReqId = 7.toLong()
        specialitiesVO =
            SpecialitiesVO(id = "bones_and_joints", name = "Bones and Joints", image = "")
        mYourcareModel.consultationRequestStart(
            consultationReqId,
            formated,
            time,
            "",
            patientsVO,
            specialitiesVO
        )
        mView.navigateToStartConsultation(patientId, consultationReqId)

//            caseSummaryVO.forEach {
//                mYourcareModel.addCaseSummaryToConsultationRequest(consultationReqId, it.id.toString(),it.question,it.answer)
//            }
 //   }


}

override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {
}
}

