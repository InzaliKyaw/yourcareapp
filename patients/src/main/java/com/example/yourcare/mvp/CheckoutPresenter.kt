package com.example.yourcare.mvp

import com.example.yourcare.mvp.view.CheckOutView

interface CheckoutPresenter:BasePresenter<CheckOutView> {
    fun loadPrescriptionList(chatId:Long)
}