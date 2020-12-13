package com.padcx.doctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctor.mvp.views.ConsultView
import com.padcx.shared.data.vos.MedicineVO
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface ConsultPresenter :BasePresenter<ConsultView>{
    fun onUiReady(specialityName: String,lifecycleOwner: LifecycleOwner)
    fun onTapGetQuestions(specialityName: String)
    fun onTapGetMedicines(specialityName: String)
    fun onTapSendMessage(message:MessageVO)
}