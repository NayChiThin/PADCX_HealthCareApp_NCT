package com.padcx.doctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctor.mvp.views.ChatView
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface ChatPresenter:BasePresenter<ChatView> {
    fun onTapBack()
    fun onTapSend(consultId:String,text:String)
    fun onTapAttach()
    fun onTapSeeMore()
    fun onUiReady(lifecycleOwner: LifecycleOwner,consult:ConsultVO)
}