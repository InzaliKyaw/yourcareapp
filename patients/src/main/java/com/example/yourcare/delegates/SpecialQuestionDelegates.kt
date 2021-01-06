package com.example.yourcare.delegates

import com.example.shared.data.vos.CaseSummaryVO

interface SpecialQuestionDelegates {
    fun onAnswerChange(position: Int, caseSummaryVO: CaseSummaryVO)
}