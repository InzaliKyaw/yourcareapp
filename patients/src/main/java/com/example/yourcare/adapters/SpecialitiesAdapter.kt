package com.example.yourcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.SpecialitiesVO
import com.example.yourcare.R
import com.example.yourcare.delegates.SpecialitiesDelegates
import com.example.yourcare.view.viewholders.SpecialitiesViewHolder

class SpecialitiesAdapter(private val sDelegates:SpecialitiesDelegates):RecyclerView.Adapter<SpecialitiesViewHolder>() {
    var mData:MutableList<SpecialitiesVO> = arrayListOf()
    var mDelegates:SpecialitiesDelegates = sDelegates
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_specialities,parent,false)
        return SpecialitiesViewHolder(view,sDelegates)
    }

    override fun onBindViewHolder(holder: SpecialitiesViewHolder, position: Int) {
        holder.bindSpecialities(mData[position])
    }
    fun setNewData(data:MutableList<SpecialitiesVO>){
        mData = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}