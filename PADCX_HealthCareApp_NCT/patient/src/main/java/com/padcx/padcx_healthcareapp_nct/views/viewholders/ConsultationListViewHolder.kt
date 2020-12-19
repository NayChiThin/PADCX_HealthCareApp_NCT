package com.padcx.padcx_healthcareapp_nct.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padcx.padcx_healthcareapp_nct.delegates.ConsultationItemDelegate
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.DoctorVO
import kotlinx.android.synthetic.main.consultation_item.view.*

class ConsultationListViewHolder(itemView:View,delegate: ConsultationItemDelegate):BaseConsultationListViewHolder(itemView) {
    init {
        itemView.tvStartConsult.setOnClickListener {
            delegate.onTapStartConsult()
        }
    }
    override fun bindData(data: ConsultVO) {
        mData = data
        itemView.tvDoctorName.text = data.doctor?.name
        itemView.tvDescription.text = data.doctor?.description
        itemView.tvDoctorSpeciality.text = data.doctor?.speciality
        itemView.tvInfo.text = "ဆွေးနွေးမှု ပြုလုပ်ရန် ${data.doctor?.name} မှ လက်ခံထားပါသည် ။"
        Glide.with(itemView.context)
            .load(data.doctor?.profilephoto)
            .into(itemView.ivDoctor)
    }
}