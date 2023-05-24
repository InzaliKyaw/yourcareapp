package com.example.yourcare.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.yourcare.R
import com.example.yourcare.mvp.RegisterPresenter
import com.example.yourcare.mvp.impls.RegisterPresenterImpl
import com.example.yourcare.mvp.view.RegisterView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_casesummary_two.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*


class RegisterActivity : BaseActivity(), RegisterView {
    private lateinit var mPresenter: RegisterPresenter
    private val REQUEST_READ_PERMISSION = 123
    var deviceId: String? = ""



    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpPresenter()
        setUpActionListener()
    }

    fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
    }

    @SuppressLint("HardwareIds")
    fun setUpActionListener() {
        if (checkPermission(this, Manifest.permission.READ_PHONE_STATE)) {
            deviceId =
                Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        } else {
            requestPermission(
                this,
                Manifest.permission.READ_PHONE_STATE,
                REQUEST_READ_PERMISSION
            );
        }


        registerPatient.setOnClickListener {
            if (patientName.text.toString().isNotEmpty() && patientEmail.text.toString()
                    .isNotEmpty() && patientPswd.text.toString().isNotEmpty()
            ) {
                mPresenter.onTapRegister(
                    patientName.text.toString(),
                    patientEmail.text.toString(),
                    patientPswd.text.toString(),
                    deviceId!!
                )

            } else {
                checkInput(patientName.text.toString(), "name")
                checkInput(patientEmail.text.toString(), "email")
                checkInput(patientPswd.text.toString(), "password")
            }
        }
        navigateLoginTxt.setOnClickListener {
            navigateToLoginScreen(patientName.text.toString(), patientEmail.text.toString())
        }
    }

    override fun navigateToLoginScreen(name: String, email: String) {
        startActivity(LoginActivity.newIntent(this, name, email))
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG).show()
    }

    override fun successMessage() {
        Toast.makeText(this, "User resgisteration success ", Toast.LENGTH_LONG).show()
    }

    fun checkInput(chkStr: String, inputStr: String) {
        if (chkStr.isEmpty()) {
            Toast.makeText(this, "Please enter $inputStr", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermission(context: Context?, permissionFromUser: String?): Boolean {
        return ContextCompat.checkSelfPermission(
            context!!,
            permissionFromUser!!
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(thisActivity: Activity?, requestedPermission: String, Code: Int) {
        if (ContextCompat.checkSelfPermission(
                thisActivity!!,
                requestedPermission
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    thisActivity,
                    requestedPermission
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    thisActivity, arrayOf(requestedPermission),
                    Code
                )
            }
        }
    }


}