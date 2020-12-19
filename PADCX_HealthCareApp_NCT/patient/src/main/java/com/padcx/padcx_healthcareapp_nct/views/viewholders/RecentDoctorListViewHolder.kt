package com.padcx.padcx_healthcareapp_nct.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padcx.padcx_healthcareapp_nct.delegates.DoctorItemDelegate
import com.padcx.shared.data.vos.DoctorVO
import kotlinx.android.synthetic.main.recent_doctor_items.view.*

class RecentDoctorListViewHolder(itemView:View,delegate: DoctorItemDelegate) : BaseRecentDoctorListViewHolder(itemView) {
    init {
        itemView.setOnClickListener{
            delegate.onTapDoctor(itemView.tvName.text.toString())
        }
    }
    override fun bindData(data: DoctorVO) {
        mData = data
        itemView.tvSpeciality.text = data.speciality
        itemView.tvName.text = data.name
        Glide.with(itemView.context)
            .load(data.profilephoto)
            .into(itemView.ivDoctor)
    }
}