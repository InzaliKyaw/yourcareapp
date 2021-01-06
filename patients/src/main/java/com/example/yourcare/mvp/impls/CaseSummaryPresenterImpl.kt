package com.example.yourcare.mvp.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.shared.data.vos.PatientsVO
import com.example.shared.data.vos.SpecialitiesVO
import com.example.yourcare.activities.CaseSummaryTwoActivity
import com.example.yourcare.mvp.AbstractBasePresenter
import com.example.yourcare.mvp.CaseSummaryPresenter
import com.example.yourcare.mvp.view.CaseSummaryQuestionView
import com.example.yourcare.mvp.view.CaseSummaryView

class CaseSummaryPresenterImpl:CaseSummaryPresenter,AbstractBasePresenter<CaseSummaryView>() {
   private var mYourcareModel: YourcareModel = YourcareModelImpl

    private var TAG = "CASE SUMMARY"
    private var count:Int = 0
    var patientId:Int = 0
    private lateinit var patientsVO:PatientsVO
    private lateinit var specialityVO:SpecialitiesVO
    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {

    }

    override fun onTapContinue(
        lifecycleOwner: LifecycleOwner,
        speciality:String,
        id:Long,
        birthdate: String,
        height: String,
        bloodType: String,
        bloodPressure: String,
        weight:String,
        allergicMedicine: String,
        address: String
    ) {
        mYourcareModel.getSpecialityById(speciality).observe(lifecycleOwner, Observer {
            it.forEach {
                specialityVO = SpecialitiesVO(id = it.id,name = it.name)
            }
        })
        mYourcareModel.getPatientById(id).observe(lifecycleOwner, Observer { it ->
            it.forEach {
                 patientsVO = PatientsVO(
                    id = id,
                    deviceId = it.deviceId,
                    name = it.name,
                    email = it.email,
                    birthdate = birthdate,
                    height = height,
                    bloodType = bloodType,
                    bloodPressure = bloodPressure,
                    weight = weight,
                    allergyMedicine = allergicMedicine,
                    address = address,
                    specialitiesVO = specialityVO
                )
            }
            mYourcareModel.addPatient(patientsVO = patientsVO,onSuccess = {
                Log.d(TAG,"Added Patient"+it)
            },onFailure = {
                Log.d(TAG,"Failed Adding Patient"+it)

            })
            mView.navigateToCaseSummaryTwo(id,speciality)
        })




    }

//    override fun addSpecialityToPatient(lifecycleOwner: LifecycleOwner,speciality: String) {
//
//    }
}