package com.padcx.doctor.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padcx.doctor.delegates.ConsultRequestItemDelegate
import com.padcx.shared.data.vos.ConsultRequestVO
import kotlinx.android.synthetic.main.consult_request_items.view.*

class ConsultRequestListViewHolder(itemView: View,private val mDelegate: ConsultRequestItemDelegate):BaseConsultRequestListViewHolder(itemView) {
    override fun bindData(data: ConsultRequestVO) {
        mData = data
        itemView.btnAccept.setOnClickListener {
            mDelegate.onTapAccept(data)
        }
        itemView.tvPatientName.text = data.patient?.name
        for(question in data.caseSummary?: arrayListOf()) {
            if(question.name == "dob") {
                itemView.tvPatientBod.text = "မွေးနေ့ : "+question.answer
            }
        }
        Glide.with(itemView.context)
            .load(data.patient?.profilephoto)
            .into(itemView.ivPatient)
    }
}