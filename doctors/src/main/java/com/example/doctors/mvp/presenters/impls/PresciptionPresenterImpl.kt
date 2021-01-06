package com.example.doctors.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.doctors.mvp.presenters.AbstractBasePresenter
import com.example.doctors.mvp.presenters.PrescriptionPresenter
import com.example.doctors.mvp.view.PrescriptionView
import com.example.doctors.util.SessionManager
import com.example.doctors.util.savePill
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.shared.data.vos.Routine
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class PresciptionPresenterImpl:PrescriptionPresenter,AbstractBasePresenter<PrescriptionView>(){
    private var mYourcareModel:YourcareModel = YourcareModelImpl
    val tag = "Prescription presenter"
        override fun loadMostUsedMedicineBySpeciality(lifecycleOwner: LifecycleOwner,speciality: String) {

          mYourcareModel.getMostUsedMedicineWithSpecialities(speciality).observe(lifecycleOwner,
              Observer {
                  mView.displayMedicineList(it)
                  Log.d(tag,"most used medicine with specialities $it" )
              })
    }

    override fun onTapAddPrescription(
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
    ) {
        mYourcareModel.getMedicineDataWithId(id).observe(lifecycleOwner, Observer {
            it.forEach {
                mYourcareModel.addPrescriptionConsultation(chatId, id= it.id, pillName= it.pillName.toString(), speciality=it.speciality.toString(), price= it.price!!, routine, days, amount, remark)
                savePill(it)
            }

        })

    }

    override fun finishConsultation(consultationId: Long) {
        mYourcareModel.finishConsultation(consultationId)
    }

    override fun loadAllPrescriptionAndAddedToChat(consultationId: Long) {
        var medicineList:MutableList<String> = arrayListOf()
        var currentTime: Date = Calendar.getInstance().time
        var dateFormatter: SimpleDateFormat = SimpleDateFormat("hh:mm a")
        var sendAt: String = dateFormatter.format(currentTime).toString()
        var message = ""

        mYourcareModel.getPrescriptionFromConsultation(consultationId,onSuccess = {
            var sb:StringBuilder = StringBuilder()
            sb.append("Medicine Recommendation")
            sb.append(System.getProperty("line.separator"))
            sb.append("-----------------------")
            sb.append(System.getProperty("line.separator"))
            var tab = ""
            var daysForPill= ""
            it.forEach {
                medicineList.add(it.pillName.toString())
                sb.append(it.pillName.toString())
                tab = "  "+it.amount.toString()+ "tab"
                sb.append(tab)

                daysForPill = "  "+"("+it.days.toString()+ "days"+")"
                sb.append(daysForPill)
                sb.append(System.getProperty("line.separator"))

            }
            //message = sb.toString()
            //Log.d(tag,"Pills "+ message)
            //var sendBy = SessionManager.doctor_name.toString()
            //mYourcareModel.sendMessage(consultationId,"","",messageText = message ,sendAt ,sendBy )
        },onFailure = {
            Log.d(tag,"Failed")
        })

        Log.d(tag,message)

    }


    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }

    override fun onTapMedicine(medicineId: String,pillName: String) {
        mView.showDialogPrescription(medicineId,pillName)
    }
}