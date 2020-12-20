package com.padcx.padcx_healthcareapp_nct.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padcx.padcx_healthcareapp_nct.delegates.SpecialityItemDelegate
import com.padcx.padcx_healthcareapp_nct.views.viewholders.baseviewholders.BaseSpecialityListViewHolder
import com.padcx.shared.data.vos.SpecialityVO
import kotlinx.android.synthetic.main.speciality_items.view.*

class SpecialityListViewHolder(itemView:View,delegate:SpecialityItemDelegate):
    BaseSpecialityListViewHolder(itemView) {
    init {
        itemView.setOnClickListener{
            delegate.onTapSpeciality(itemView.tvSpecialityName.text.toString())
        }
    }
    override fun bindData(data: SpecialityVO) {
        mData = data
        itemView.tvSpecialityName.text = data.name
        Glide.with(itemView.context)
            .load(data.image)
            .into(itemView.ivSpeciality)
    }
}