package com.example.yourcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ChatVO
import com.example.shared.data.vos.MedicineVO
import com.example.shared.data.vos.PatientsVO
import com.example.yourcare.R
import com.example.yourcare.adapters.ChatPatientTextAdapter
import com.example.yourcare.adapters.ConfirmCaseSummaryAdapter
import com.example.yourcare.adapters.PrescriptionListAdapter
import com.example.yourcare.mvp.ChatPresenter
import com.example.yourcare.mvp.impls.ChatPresenterImpl
import com.example.yourcare.mvp.view.ChatView
import kotlinx.android.synthetic.main.activity_chat_patient.*
import kotlinx.android.synthetic.main.activity_confirm_consultation.*

class ChatActivity:BaseActivity(),ChatView {

    private var patientId:Long = 0
    private lateinit var mPresenter:ChatPresenter
    private lateinit var mAdapter: ConfirmCaseSummaryAdapter
    private lateinit var mChatPatientTextAdapter: ChatPatientTextAdapter
    private lateinit var prescriptionListAdapter: PrescriptionListAdapter



    companion object{
        const val PATIENT_ID = "PATIENT_ID"
        fun newIntent(context: Context,patientId:Long):Intent{
            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra(PATIENT_ID,patientId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_patient)
        setUpPresenter()
        patientId = intent.getLongExtra(PATIENT_ID,0) ?: 0
        mPresenter.loadPatientData(this,patientId)
        mPresenter.loadPatientCaseSummary(this,patientId)
        mAdapter = ConfirmCaseSummaryAdapter()
        setUpConfirmRecycler(mAdapter)

        mChatPatientTextAdapter = ChatPatientTextAdapter()
        setUpChatAdapter(mChatPatientTextAdapter)

        prescriptionListAdapter = PrescriptionListAdapter()
        setUpPrescriptionRecycler(prescriptionListAdapter)

        mPresenter.loadPrescriptionFromFirebase(1)

        sendBtn.setOnClickListener {
            if (chatTextWrite.text != null) {
                mPresenter.addChatText(chatTextWrite.text.toString(),1)
                chatTextWrite.text.clear()
            }
        }
    }

    override fun showPatientData(patientsVO: PatientsVO) {
        patientNameChat.text = patientsVO.name
        patientBirthdayChat.text = patientsVO.birthdate
        patientHeightChat.text = patientsVO.height
        patientBloodChat.text = patientsVO.bloodType
        patientAllergyChat.text = patientsVO.allergyMedicine
        patientWeightChat.text = patientsVO.weight
        patientBloodPressureChat.text = patientsVO.bloodPressure
    }

    override fun showCaseSummary(caseSummaryList: List<CaseSummaryVO>) {
        mAdapter.setNewData(caseSummaryList.toMutableList())
    }

    fun setUpPresenter(){
        mPresenter = getPresenter<ChatPresenterImpl,ChatView>()
    }

    fun setUpConfirmRecycler(confirmAdapter:ConfirmCaseSummaryAdapter){
        rvCaseSummaryQAChat.adapter = confirmAdapter
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rvCaseSummaryQAChat.layoutManager = linearLayout
    }

    fun setUpChatAdapter(mChatPatientTextAdapter: ChatPatientTextAdapter){
        rvChatPatientText.adapter = mChatPatientTextAdapter
        val linearLayout = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvChatPatientText.layoutManager = linearLayout
    }

    fun setUpPrescriptionRecycler(prescriptionListAdapter: PrescriptionListAdapter){
        rvPrescriptionList.adapter = prescriptionListAdapter
        val linearLayout = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvPrescriptionList.layoutManager = linearLayout
    }
    override fun displayChatText(chatText: List<ChatVO>) {
        mChatPatientTextAdapter.setNewData(chatText.toMutableList())
    }

    override fun showPrescription(medicineList: List<MedicineVO>) {
        prescriptionListAdapter.setNewData(medicineList.toMutableList())
        checkOutLnl.visibility = View.VISIBLE
        btnPrescription.setOnClickListener {
            startActivity(CheckoutActivity.newIntent(this,1))
        }
    }

    override fun showError(error: String) {
    }
}