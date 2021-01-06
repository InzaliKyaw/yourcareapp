package com.example.yourcare.mvp.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.shared.data.model.YourcareModel
import com.example.shared.data.model.YourcareModelImpl
import com.example.yourcare.mvp.AbstractBasePresenter
import com.example.yourcare.mvp.CheckoutPresenter
import com.example.yourcare.mvp.view.CheckOutView

class CheckOutPresenterImpl:CheckoutPresenter, AbstractBasePresenter<CheckOutView>() {
    private var mYourcareModel: YourcareModel = YourcareModelImpl
    val TAG = "CHECK OUT"
    var day:Long= 0
    var price:Long = 0
    var amount:Long = 0
    var eachTotal:Long = 0
    var total:Long=0
    override fun loadPrescriptionList(chatId: Long) {
        mYourcareModel.getPrescriptionFromConsultation(chatId,onSuccess = {
            mView.showPrescriptionList(it)
            it.forEach {
                    day = it.days!!
                    price = it.price!!
                    amount = it.amount!!
                    eachTotal = (day*amount)*price
                    total+= eachTotal
            }
            mView.showTotalPrice(total)
        },onFailure = {
            Log.d(TAG,"Failure")
        })
    }

    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {
    }
}