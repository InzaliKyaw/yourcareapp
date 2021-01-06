package com.example.yourcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yourcare.R
import com.example.yourcare.mvp.view.ProfileView

class ProfileFragment:BaseFragment(),ProfileView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    companion object{
        fun newInstance():ProfileFragment{
            return ProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile,container,false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}