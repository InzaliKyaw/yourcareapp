package com.example.yourcare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.CaseSummaryVO
import com.example.shared.data.vos.RelatedQuestionVO
import com.example.yourcare.R
import com.example.yourcare.delegates.SpecialQuestionDelegates
import com.example.yourcare.view.viewholders.CaseSummaryViewHolder

class CaseSummaryAdapter(private val mDelegate:SpecialQuestionDelegates):BaseRecyclerAdapter<CaseSummaryViewHolder,RelatedQuestionVO>() {

    var mCaseSummaryList:List<CaseSummaryVO> = arrayListOf()

    fun setCaseSummaryList(caseSummaryList: List<CaseSummaryVO>){
        mCaseSummaryList = caseSummaryList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseSummaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_special_question,parent,false)
        return CaseSummaryViewHolder(view,mCaseSummaryList,mDelegate)
    }

}