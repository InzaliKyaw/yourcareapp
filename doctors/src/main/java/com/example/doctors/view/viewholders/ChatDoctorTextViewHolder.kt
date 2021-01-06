package com.example.doctors.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.ChatVO
import kotlinx.android.synthetic.main.item_chat_doctor_text.view.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class ChatDoctorTextViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    fun bindChatText(chatVO:ChatVO){
        itemView.chatText.text = chatVO.messageText
        itemView.chatDeliveredTime.text = chatVO.sendAt
//        var currentTime:Date = Calendar.getInstance().time
//        var dateFormatter: SimpleDateFormat = SimpleDateFormat("hh:mm a")
//        itemView.chatDeliveredTime.text = dateFormatter.format(currentTime).toString()
    }

}