package com.example.yourcare.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.data.vos.ChatVO
import kotlinx.android.synthetic.main.item_chat_doctor_text.view.*

class ChatPatientViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
    fun bindChat(chatVO: ChatVO){
        itemView.chatText.text = chatVO.messageText
        itemView.chatDeliveredTime.text = chatVO.sendAt
    }
}