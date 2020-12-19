package com.padcx.doctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctor.delegates.ConsultRequestItemDelegate
import com.padcx.doctor.delegates.ConsultationItemDelegate
import com.padcx.doctor.mvp.views.MainView
import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface HomePresenter:BasePresenter<MainView>,ConsultationItemDelegate,ConsultRequestItemDelegate {
    fun onUiReady(lifecycleOwner: LifecycleOwner)
}