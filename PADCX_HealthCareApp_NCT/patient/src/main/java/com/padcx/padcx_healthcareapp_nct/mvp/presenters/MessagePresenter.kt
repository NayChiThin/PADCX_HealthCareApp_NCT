package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.padcx_healthcareapp_nct.delegates.ConsultHistoryItemDelegate
import com.padcx.padcx_healthcareapp_nct.mvp.views.MessageView
import com.padcx.shared.mvp.presenters.BasePresenter

interface MessagePresenter:BasePresenter<MessageView>,ConsultHistoryItemDelegate {
    fun onUiReady(lifecycleOwner: LifecycleOwner)
}