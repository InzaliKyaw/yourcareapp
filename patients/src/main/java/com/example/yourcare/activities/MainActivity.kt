package com.example.yourcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import com.example.yourcare.R
import com.example.yourcare.fragments.HomeFragment
import com.example.yourcare.fragments.MessageFragment
import com.example.yourcare.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity:BaseActivity() {
    private  var patientId:Long = 0
    private var consultationRequestId:Long = 0

    companion object{
        const val PATIENT_ID = "ID"
        const val CONSULTATION_ID = "CONSULTATION_ID"
        fun newIntent(context: Context,id:Long,consultationRequestId:Long):Intent{
            val intent = Intent(context,MainActivity::class.java)
            intent.putExtra(PATIENT_ID,id)
            intent.putExtra(CONSULTATION_ID,consultationRequestId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        patientId = intent.getLongExtra(PATIENT_ID,0)
        consultationRequestId = intent.getLongExtra(CONSULTATION_ID,0)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainerNavigation,HomeFragment.newInstance(patientId,consultationRequestId))
            .commit()

        bottomPatient.setOnNavigationItemSelectedListener (object :
           BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.home ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameContainerNavigation,HomeFragment.newInstance(patientId,consultationRequestId))
                            .commit()
                    }
                    R.id.messagePatient ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameContainerNavigation,MessageFragment.newInstance())
                            .commit()
                    }
                    R.id.profile->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameContainerNavigation,ProfileFragment.newInstance())
                            .commit()
                    }
                }
                return true
            }
        }

                )

        }
    }