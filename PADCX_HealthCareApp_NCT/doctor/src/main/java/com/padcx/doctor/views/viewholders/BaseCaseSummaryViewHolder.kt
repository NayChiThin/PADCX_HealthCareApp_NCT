package com.padcx.doctor.views.viewholders

import android.view.View
import com.padcmyanmar.androidftc.shared.views.viewholders.BaseViewHolder
import com.padcx.shared.data.vos.QuestionVO

abstract class BaseCaseSummaryViewHolder(itemView:View):BaseViewHolder<QuestionVO>(itemView) {
    abstract fun bindCount(count:Int)
}