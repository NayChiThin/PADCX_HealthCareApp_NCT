package com.padcx.padcx_healthcareapp_nct.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padcx.padcx_healthcareapp_nct.delegates.ConsultHistoryItemDelegate
import com.padcx.padcx_healthcareapp_nct.views.viewholders.baseviewholders.BaseConsultHistoryViewHolder
import com.padcx.shared.data.vos.ConsultVO
import kotlinx.android.synthetic.main.consult_history_items.view.*

class ConsultHistoryViewHolder(itemView:View,delegate: ConsultHistoryItemDelegate):BaseConsultHistoryViewHolder(itemView) {
    init {
        itemView.llSendMessage.setOnClickListener {
            delegate.onTapSendMessage(mData?:ConsultVO())
        }
        itemView.llPrescription.setOnClickListener {
            delegate.onTapPrescription(mData?:ConsultVO())
        }
    }
    override fun bindData(data: ConsultVO) {
        mData = data
        Glide.with(itemView.context)
            .load(data.doctor?.profilephoto)
            .into(itemView.ivDoctor)
        itemView.tvDoctorName.text = data.doctor?.name
        itemView.tvSpeciality.text = data.doctor?.speciality
    }
}