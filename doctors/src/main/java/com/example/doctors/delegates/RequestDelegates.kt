package com.example.doctors.delegates

import com.example.shared.data.vos.ConsultationRequestVO

interface RequestDelegates {
    fun onTapAcceptRequest(consultationId: Long)
}