package com.example.doctors.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.doctors.R
import com.example.doctors.mvp.presenters.RegisterFormPresenter
import com.example.doctors.mvp.presenters.impls.RegisterFormPresenterImpl
import com.example.doctors.mvp.view.RegisterFormView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register_form.*
import java.util.*

class RegisterFormActivity : BaseActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener, RegisterFormView {
    var day = 0
    var month = 0
    var year = 0

    private lateinit var deviceId: String
    private lateinit var gender: String
    private lateinit var mPresenter: RegisterFormPresenter
    private lateinit var speciality: String

    companion object {
        const val REGISTER = "REGISTER"

        fun newIntent(context: Context, userName: String): Intent {
            val intent: Intent = Intent(context, RegisterFormActivity::class.java)
            intent.putExtra(REGISTER, userName)
            return intent
        }
    }

    @SuppressLint("HardwareIds")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_form)
        setUpPresenter()

        var userName = intent.extras?.get("REGISTER")
        deviceId = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        )

        genderRadioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                gender = radio.text.toString()
            }
        )
        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.specialities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            speciality = spinner.selectedItem.toString()
            Log.d(REGISTER, "Speciality Dropdown" + speciality)

        }
        setUpDateTimePicker()
        setUpActionListener(userName.toString())
    }

    fun setUpPresenter() {
        mPresenter = getPresenter<RegisterFormPresenterImpl, RegisterFormView>()
    }

    fun setUpActionListener(userName: String) {

        btnCreateAccount.setOnClickListener {

            mPresenter.onTapCreateAccount(
                this,
                deviceId,
                userName,
                phoneDoc.text.toString(),
                gender,
                speciality,
                experiencesDoc.text.toString().toLong(),
                degreesDoc.text.toString(),
                bioDoc.text.toString(),
                addressDoc.text.toString()
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setUpDateTimePicker() {
        btnDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DATE)
            val datePickerDialog = DatePickerDialog(this,android.R.style.Widget_CalendarView)
            btnDate.text = day.toString()
            datePickerDialog.show()
        }
        btnMonths.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            month = calendar.get(Calendar.MONTH)
            btnMonths.text = month.toString()
            val datePickerDialog = DatePickerDialog(this)
            datePickerDialog.show()
        }
        btnYears.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            btnYears.text = year.toString()
            val datePickerDialog = DatePickerDialog(this)
            datePickerDialog.show()
        }
    }

    override fun navigateToHomeScreen(userName: String) {
        startActivity(MainActivity.newIntent(this,userName))
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG)

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        TODO("Not yet implemented")
    }
}