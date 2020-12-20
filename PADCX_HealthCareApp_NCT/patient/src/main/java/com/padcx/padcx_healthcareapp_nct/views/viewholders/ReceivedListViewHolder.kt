package com.padcx.padcx_healthcareapp_nct.views.viewholders

import android.view.View
import com.padcx.padcx_healthcareapp_nct.views.viewholders.baseviewholders.BaseReceivedListViewHolder
import com.padcx.shared.data.vos.MessageVO
import kotlinx.android.synthetic.main.received_message_item.view.*

class ReceivedListViewHolder(itemView:View):
    BaseReceivedListViewHolder(itemView) {
    override fun bindData(data: MessageVO) {
        mData = data
        itemView.tvMessageBody.text = data.text
        itemView.tvMessageTime.text = data.time
    }

}