package com.example.doctors.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctors.R
import com.example.doctors.adapters.PrescriptionAdapter
import com.example.doctors.dialogs.PrescriptionDialogFragment
import com.example.doctors.mvp.presenters.PrescriptionPresenter
import com.example.doctors.mvp.presenters.impls.PresciptionPresenterImpl
import com.example.doctors.mvp.view.PrescriptionView
import com.example.shared.data.vos.MedicineVO
import kotlinx.android.synthetic.main.activity_presciption.*
import kotlinx.android.synthetic.main.activity_start_consultation.*

class PrescriptionActivity:BaseActivity(),PrescriptionView {

    private lateinit var mPresenter:PrescriptionPresenter
    private lateinit var prescriptionAdapter: PrescriptionAdapter

    companion object{
        const val PRESCIPTION = "PRESCIPTION"
        fun newIntent(context: Context,speciality:String): Intent {
            val intent = Intent(context,PrescriptionActivity::class.java)
            intent.putExtra(PRESCIPTION,speciality)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presciption)
        setUpPresenter()
        setUpListeners()

        val speciality = intent.extras?.get(PRESCIPTION).toString()
        mPresenter.loadMostUsedMedicineBySpeciality(this,"stomach")
        prescriptionAdapter = PrescriptionAdapter(mPresenter)
        setUpRecyclerMedicine(prescriptionAdapter)

        btnFinishConsultation.setOnClickListener {
            //mPresenter.finishConsultation(1)
            //mPresenter.loadAllPrescriptionAndAddedToChat(1)
            startActivity(ChatActivity.newIntent(this,1))
        }

    }
    override fun displayMedicineList(medicineList: List<MedicineVO>) {
        prescriptionAdapter.setNewData(medicineList.toMutableList())
    }

    override fun showDialogPrescription(medicineId: String,pillName:String) {
        PrescriptionDialogFragment.newFragment(medicineId,pillName)
            .show(supportFragmentManager,PrescriptionDialogFragment.TAG_ADD_MEDICINE_DIALOG)
    }

    fun setUpListeners(){
        setSupportActionBar(presciptionToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }


    fun setUpRecyclerMedicine(prescriptionAdapter: PrescriptionAdapter){
        rvPrescription.adapter = prescriptionAdapter
        val linearLayout = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        rvPrescription.layoutManager = linearLayout
    }
    fun setUpPresenter(){
        mPresenter = getPresenter<PresciptionPresenterImpl,PrescriptionView>()
    }

    override fun showError(error: String) {

    }
}