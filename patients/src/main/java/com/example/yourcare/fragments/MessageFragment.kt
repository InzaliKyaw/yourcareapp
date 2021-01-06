package com.example.yourcare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yourcare.R
import com.example.yourcare.mvp.view.MessageView

class MessageFragment:BaseFragment(),MessageView {
    companion object{
        fun newInstance():MessageFragment{
            return MessageFragment()
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
        val view = inflater.inflate(R.layout.fragment_message_record,container,false)
        return view
    }
}