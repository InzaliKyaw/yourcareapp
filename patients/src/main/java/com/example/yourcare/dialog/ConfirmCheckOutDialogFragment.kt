package com.example.yourcare.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.data.vos.MedicineVO
import com.example.yourcare.R
import com.example.yourcare.adapters.PrescriptionCheckoutAdapter
import com.example.yourcare.mvp.CheckoutPresenter
import com.example.yourcare.mvp.impls.CheckOutPresenterImpl
import com.example.yourcare.mvp.view.CheckOutView
import com.example.yourcare.view.view.DialogView
import kotlinx.android.synthetic.main.dialog_prescription.*

class ConfirmCheckOutDialogFragment:DialogFragment(),CheckOutView {

    private lateinit var mPresenter:CheckoutPresenter
    private var chatId: Long = 0
    //private lateinit var prescriptionCheckoutAdapter: PrescriptionCheckoutAdapter

    companion object{
        const val CHAT_ID = "CHAT_ID"
        const val TAG_CONFIRM_CHECKOUT_DIALOG = "TAG_CONFIRM_CHECKOUT_DIALOG"

        fun newFragment(chatId:Long)=
            ConfirmCheckOutDialogFragment().apply {
                arguments = Bundle().apply{
                    putLong(CHAT_ID,chatId)
                }
            }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_prescription,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        if (arguments!= null){
            chatId = arguments?.getLong(CHAT_ID)!!
        }
        mPresenter.loadPrescriptionList(chatId)
//        prescriptionCheckoutAdapter = PrescriptionCheckoutAdapter()
//        setUpPrescription(prescriptionCheckoutAdapter)
        btnClose.setOnClickListener {
            dismiss()
        }
    }
    fun setUpPresenter(){
            mPresenter = ViewModelProviders.of(this).get(CheckOutPresenterImpl::class.java)
    }
    fun setUpPrescription(prescriptionCheckoutAdapter: PrescriptionCheckoutAdapter){
        rvCheckout.adapter = prescriptionCheckoutAdapter
        val linearLayout = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL,false)
        rvCheckout.layoutManager = linearLayout
    }

    override fun showPrescriptionList(medicineList: List<MedicineVO>) {
        //prescriptionCheckoutAdapter.setNewData(medicineList.toMutableList())
    }

    override fun showTotalPrice(price: Long) {

    }

    override fun getAppContext(): Context {
        return this.requireContext()
    }

    override fun showError(error: String) {
    }
}