package com.padcx.padcx_healthcareapp_nct.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padcx.padcx_healthcareapp_nct.delegates.ConsultationItemDelegate
import com.padcx.shared.data.vos.DoctorVO
import kotlinx.android.synthetic.main.consultation_item.view.*

class ConsultationListViewHolder(itemView:View,delegate: ConsultationItemDelegate):BaseConsultationListViewHolder(itemView) {
    override fun bindData(data: DoctorVO) {
        mData = data
        itemView.tvDoctorName.text = data.name
        itemView.tvDescription.text = data.description
        itemView.tvDoctorSpeciality.text = data.speciality
        itemView.tvInfo.text = "ဆွေးနွေးမှု ပြုလုပ်ရန် ${data.name} မှ လက်ခံထားပါသည် ။"
        Glide.with(itemView.context)
            .load(data.profilephoto)
            .into(itemView.ivDoctor)
    }
}