package com.example.yourcare.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shared.data.vos.SpecialitiesVO
import com.example.yourcare.delegates.SpecialitiesDelegates
import kotlinx.android.synthetic.main.item_specialities.view.*

class SpecialitiesViewHolder(itemView: View,private val delegate:SpecialitiesDelegates): BaseSpecialitiesViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            mData.let {
                delegate.onTapCard(it?.id.toString())
            }
        }
    }
    fun bindSpecialities(specialitiesVO: SpecialitiesVO){
        mData = specialitiesVO
        itemView.specialityName.text = specialitiesVO.name
        var url = specialitiesVO.image
        Glide.with(itemView)
            .load(url)
            .into(itemView.specialityImage)

    }

    override fun bindData(data: SpecialitiesVO) {

    }
}