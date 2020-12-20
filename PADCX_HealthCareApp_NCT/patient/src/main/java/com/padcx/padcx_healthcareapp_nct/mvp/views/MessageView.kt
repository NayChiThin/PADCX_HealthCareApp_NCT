package com.padcx.padcx_healthcareapp_nct.mvp.views

import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.mvp.views.BaseView

interface MessageView:BaseView{
    fun displayConsultationHistory(consults:List<ConsultVO>)
    fun navigateToChat(consult: ConsultVO)
    fun displayPrescription(consult: ConsultVO)
}