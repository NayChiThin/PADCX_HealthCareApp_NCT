package com.padcx.padcx_healthcareapp_nct.views.viewholders

import android.view.View
import com.padcmyanmar.androidftc.shared.views.viewholders.BaseViewHolder
import com.padcx.padcx_healthcareapp_nct.mvp.views.FillCaseSummaryView
import com.padcx.shared.data.vos.QuestionVO

abstract class BaseSpecialityQuestionListViewHolder(itemView:View):BaseViewHolder<QuestionVO>(itemView) {
    abstract fun bindCount(count:Int)
}