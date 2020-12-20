package com.padcx.doctor.views.viewholders

import android.view.View
import com.padcx.shared.data.vos.MessageVO
import kotlinx.android.synthetic.main.sent_message_item.view.*

class SentMessageListViewHolder(itemView:View):
    BaseSentMessageListViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            if(itemView.tvMessageTime.visibility == View.GONE) {
                itemView.tvMessageTime.visibility = View.VISIBLE
            }else {
                itemView.tvMessageTime.visibility = View.GONE
            }
        }
    }
    override fun bindData(data: MessageVO) {
        mData = data
        itemView.tvMessageBody.text = data.text
        itemView.tvMessageTime.text = data.time
    }
}