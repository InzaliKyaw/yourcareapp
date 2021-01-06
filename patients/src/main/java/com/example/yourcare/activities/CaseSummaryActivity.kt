package com.example.yourcare.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.Spinner
import com.example.yourcare.R
import com.example.yourcare.mvp.CaseSummaryPresenter
import com.example.yourcare.mvp.impls.CaseSummaryPresenterImpl
import com.example.yourcare.mvp.view.CaseSummaryView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_casesummary.*

class CaseSummaryActivity : BaseActivity(),CaseSummaryView {

    private lateinit var mPresenter: CaseSummaryPresenter
    private lateinit var bloodType: String
    private lateinit var deviceId:String
    private  var patientId:Long = 0
    private lateinit var patientSpeciality:String
    private var allergyMedicine:String = ""
    private  var days:String = "00"
    private  var month:String = "00"
    private  var years:String = "00"
    private lateinit var birthdate:String

    companion object {
        const val PATIENT_ID = "ID"
        const val SPECIALITY = "SPECIALITY"
        const val TAG = "TAG"

        fun newIntent(context: Context,id:Long,speciality:String): Intent {
            val intent = Intent(context, CaseSummaryActivity::class.java)
            intent.putExtra(PATIENT_ID,id)
            intent.putExtra(SPECIALITY,speciality)
            return intent
        }
    }

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casesummary)
        setUpPresenter()
        setUpListener()

        patientId = intent.getLongExtra(PATIENT_ID,0)
        patientSpeciality = intent.getStringExtra(SPECIALITY).toString()
        deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        //Day Spin   ner
        var dayspinner: Spinner = findViewById(R.id.spinnerDays)
        dayspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                days = parent?.getItemAtPosition(position).toString()
            }
        }

        //Months Spinner
        var monthSpinner: Spinner = findViewById(R.id.spinnerMonths)
        monthSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                month = parent?.getItemAtPosition(position).toString()
            }
        }

        //years Spinner
        var yearSpinner: Spinner = findViewById(R.id.spinnerYears)
        yearSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                years = parent?.getItemAtPosition(position).toString()
            }
        }


        birthdate = "$days: $month: $years"
        Log.d(TAG, "Birthdate " + birthdate)

        bloodTypeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            bloodType = radio.text.toString()
        }

        var stateLable = arrayOf("အထွေထွေမေးခွန်းများ", "ရောဂါဆိုင်ရာမေးခွန်းများ")
        stateProgressBar.setStateDescriptionData(stateLable)

        allergyMedicine = allergicTxt.text.toString()
        Log.d(TAG,"Patient Data"+ allergyMedicine )
        Log.d(TAG,"Patient Data"+ birthdate )

        btnContinueCaseSummary.setOnClickListener {
            mPresenter.onTapContinue(
                this,
                patientSpeciality,
                patientId,
                birthdate,
                heightPatient.text.toString(),
                bloodType,
                bloodPressurePatient.text.toString(),
                weightPatient.text.toString(),
                allergyMedicine,
                "",
            )
        }


    }
    fun setUpListener(){
        setSupportActionBar(caseSummaryToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun setUpPresenter() {
        mPresenter = getPresenter<CaseSummaryPresenterImpl, CaseSummaryView>()
    }


    override fun navigateToCaseSummaryTwo(patientId:Long,speciality: String) {
        startActivity(CaseSummaryTwoActivity.newIntent(this,patientId,speciality))
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error,Snackbar.LENGTH_LONG).show()
    }
}