package com.padcx.doctor.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padcx.doctor.delegates.ConsultationItemDelegate
import com.padcx.shared.data.vos.ConsultVO
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.previous_consult_items.view.*

class PreviousConsultListViewHolder(itemView:View,private val mDelegate:ConsultationItemDelegate):BasePreviousConsultListViewHolder(itemView) {
    override fun bindData(data: ConsultVO) {
        itemView.tvPatientName.text = data.patient?.name
        itemView.tvDate.text = ""
        Glide.with(itemView.context)
            .load(data.patient?.profilephoto)
            .into(itemView.ivPatient)
        itemView.tvConsultNote.setOnClickListener {
            mDelegate.onTapCaseSummary(data)
        }
        itemView.tvPrescription.setOnClickListener {
            mDelegate.onTapPrescription(data)
        }
        itemView.tvNote.setOnClickListener {
            mDelegate.onTapConsultNote(data)
        }
        itemView.btnSendMessage.setOnClickListener {
            mDelegate.onTapSendMessage(data)
        }
        mData = data
    }
}