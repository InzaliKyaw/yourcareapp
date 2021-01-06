package com.example.doctors.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctors.R
import com.example.doctors.adapters.ChatDoctorTextAdapter
import com.example.doctors.adapters.ConsultationRequestCaseSAdapter
import com.example.doctors.mvp.presenters.ChatPresenter
import com.example.doctors.mvp.presenters.impls.ChatPresenterImpl
import com.example.doctors.mvp.view.ChatView
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.ChatVO
import com.example.shared.data.vos.ConsultationRequestVO
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity:BaseActivity(),ChatView {
    private lateinit var mPresenter: ChatPresenter
    private lateinit var mChatDoctorTextAdapter: ChatDoctorTextAdapter
    private lateinit var mConsultationRequestCaseSAdapter: ConsultationRequestCaseSAdapter

    companion object {
        const val CONSULT = "CONSULT"
        fun newIntent(context: Context, consultationId: Long): Intent {
            val intent = Intent(context, ChatActivity::class.java)
            //intent.putExtra(CHAT, question)
            intent.putExtra(CONSULT, consultationId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setUpPresenter()
        setUpActionListeners()
        mChatDoctorTextAdapter = ChatDoctorTextAdapter()
        setUpChatRecyclerView(mChatDoctorTextAdapter)
        var consultationId = intent.getLongExtra(CONSULT, 0)

        mPresenter.loadCaseSummaryQAndA(consultationId)
        mPresenter.loadChatMessages(1)
        mConsultationRequestCaseSAdapter = ConsultationRequestCaseSAdapter()
        setUpRecycelQA(mConsultationRequestCaseSAdapter)

        sendBtn.setOnClickListener {
            if (chatTextWrite.text != null) {
                mPresenter.addChatText(chatTextWrite.text.toString(),consultationId)
                chatTextWrite.text.clear()
            }
        }
//        if (question != null) {
//            mPresenter.addChatText(question,consultationId)
//        }
        mPresenter.loadConsultationRequestWithId(this, consultationId)


    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG)
    }

    fun setUpPresenter() {
        mPresenter = getPresenter<ChatPresenterImpl, ChatView>()
    }

    fun setUpActionListeners() {
        questionFAB.setOnClickListener {
            mPresenter.onTapGeneralQuestionFAB()
        }

        medicalRecordFAB.setOnClickListener {
            mPresenter.onTapMedicalRecord()
        }
    }

    fun setUpChatRecyclerView(chatDoctorTextAdapter: ChatDoctorTextAdapter) {
        rvChatDocText.adapter = chatDoctorTextAdapter
        val linearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvChatDocText.layoutManager = linearLayoutManager
    }

    override fun navigateToGeneralQuestionTemplate() {
        startActivity(GeneralQuestionTemplateActivity.newIntent(this))
    }

    override fun navigateToPrescription(speciality: String) {
        startActivity(PrescriptionActivity.newIntent(this, "Bones and Joints"))
    }

    override fun navigateToMedicalRecordScreen() {
        startActivity(MedicalRecordActivity.newIntent(this))
    }

    override fun displayChatText(chatText: List<ChatVO>) {
        mChatDoctorTextAdapter.setNewData(chatText.toMutableList())
    }

    override fun showCaseSummaryQAndA(caseSummaryList: List<CaseSummaryVO>) {
        mConsultationRequestCaseSAdapter.setNewData(caseSummaryList.toMutableList())
    }

    override fun getConsultationSpeciality(speciality: String) {
        prescriptionFAB.setOnClickListener {
            mPresenter.onTapPrescription(speciality)
        }

    }

    fun setUpRecycelQA(mAdapter: ConsultationRequestCaseSAdapter) {
        rvCaseSummaryQAChat.adapter = mAdapter
        val linearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvCaseSummaryQAChat.layoutManager = linearLayoutManager

    }

    override fun showConsultationRequestData(consultationRequestVO: ConsultationRequestVO) {
        patientNameChat.text = consultationRequestVO.patientsVO?.name.toString()
        patientBirthdayChat.text = consultationRequestVO.patientsVO?.birthdate.toString()
        patientHeightChat.text = consultationRequestVO.patientsVO?.height.toString()
        patientBloodChat.text = consultationRequestVO.patientsVO?.bloodType.toString()
        patientAllergyChat.text = consultationRequestVO.patientsVO?.allergyMedicine.toString()
        patientWeightChat.text = consultationRequestVO.patientsVO?.weight.toString()
    }
}