package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.padcx_healthcareapp_nct.mvp.views.ConsultView
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.mvp.presenters.BasePresenter
import com.padcx.shared.mvp.views.BaseView

interface ConsultPresenter:BasePresenter<ConsultView> {
    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapCheckoutPrescription()
    fun onTapSendMessage(message: MessageVO, onSuccess:()->Unit, onFailure: (String) -> Unit)
}