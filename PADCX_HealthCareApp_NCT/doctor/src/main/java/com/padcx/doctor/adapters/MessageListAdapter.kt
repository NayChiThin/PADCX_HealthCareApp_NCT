package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctor.R
import com.padcx.doctor.views.viewholders.BaseSentMessageListViewHolder
import com.padcx.doctor.views.viewholders.SentMessageListViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vos.MessageVO

class MessageListAdapter(private val userName:String):BaseRecyclerAdapter<BaseSentMessageListViewHolder,MessageVO>() {
    companion object {
        private const val VIEW_TYPE_SENDER = 1
        private const val VIEW_TYPE_RECEIVER = 2
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseSentMessageListViewHolder {
        return when(viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.sent_message_item,parent,false)
                SentMessageListViewHolder(view)
            }else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.received_message_item,parent,false)
                SentMessageListViewHolder(view)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if(mData[position].sender == userName) {
            VIEW_TYPE_SENDER
        }else {
            VIEW_TYPE_RECEIVER
        }
    }

    override fun onBindViewHolder(holder: BaseSentMessageListViewHolder, position: Int) {
        holder.bindData(mData[position])
    }
}