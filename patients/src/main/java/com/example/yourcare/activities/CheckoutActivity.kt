package com.example.yourcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.data.vos.MedicineVO
import com.example.yourcare.R
import com.example.yourcare.adapters.PrescriptionCheckoutAdapter
import com.example.yourcare.dialog.ConfirmCheckOutDialogFragment
import com.example.yourcare.mvp.CheckoutPresenter
import com.example.yourcare.mvp.impls.CheckOutPresenterImpl
import com.example.yourcare.mvp.view.CheckOutView
import com.example.yourcare.util.SessionManager
import kotlinx.android.synthetic.main.activity_casesummary.*
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_main.*

class CheckoutActivity:BaseActivity(),CheckOutView{

    private lateinit var prescriptionCheckoutAdapter: PrescriptionCheckoutAdapter
    private lateinit var mPresenter:CheckoutPresenter
    private var chatId:Long = 0
    private val TAG="CHECKOUT"

    companion object{
        const val CHAT_ID = "CHAT_ID"
        fun newIntent(context: Context,chatId:Long):Intent{
            val intent = Intent(context,CheckoutActivity::class.java)
            intent.putExtra(CHAT_ID,chatId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        setUpPresenter()
        setUpListeners()

        chatId = intent?.getLongExtra(CHAT_ID,0)!!
        mPresenter.loadPrescriptionList(chatId)
        prescriptionCheckoutAdapter = PrescriptionCheckoutAdapter()
        setUpPrescription(prescriptionCheckoutAdapter)
        addressText.text = SessionManager.patient_address.toString()
        Log.d(TAG,"ADDRESS"+ SessionManager.patient_address.toString())
        //setUpActionListeners()
    }
    fun setUpPresenter(){
        mPresenter = getPresenter<CheckOutPresenterImpl,CheckOutView>()
    }
    fun setUpListeners(){
        setSupportActionBar(checkOutToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
    fun setUpActionListeners(){
        btnCheckoutConfirm.setOnClickListener {
            fragmentManager.let {
                ConfirmCheckOutDialogFragment.newFragment(chatId)
                    .show(supportFragmentManager,ConfirmCheckOutDialogFragment.TAG_CONFIRM_CHECKOUT_DIALOG)
            }

        }
    }

    fun setUpPrescription(prescriptionCheckoutAdapter: PrescriptionCheckoutAdapter){
        rvPrescripeCheckout.adapter = prescriptionCheckoutAdapter
        val linearLayout = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvPrescripeCheckout.layoutManager = linearLayout
    }

    override fun showPrescriptionList(medicineList: List<MedicineVO>) {
        prescriptionCheckoutAdapter.setNewData(medicineList.toMutableList())
    }

    override fun showTotalPrice(price: Long) {
        priceTxT.text = price.toString()
    }

    override fun showError(error: String) {
    }
}