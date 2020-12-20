package com.padcx.padcx_healthcareapp_nct.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.ConsultPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.views.ConsultView
import com.padcx.shared.data.models.ConsultModel
import com.padcx.shared.data.models.impls.ConsultModelImpl
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class ConsultPresenterImpl:
    ConsultPresenter,AbstractBasePresenter<ConsultView>() {

    private val mConsultModel : ConsultModel =
            ConsultModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
//        loadDataFromApi()
        loadDataFromDb(lifecycleOwner)
    }
    private fun loadDataFromDb(lifecycleOwner: LifecycleOwner) {
        mConsultModel.getMessagesFromDb()
            .observe(lifecycleOwner, Observer {
                it?.let {
                    // show messages in ui
                }
            })
    }
  /*  private fun loadDataFromApi() {
        mConsultModel.getMessages(
            onSuccess = {
            },
            onFailure = {
                mView.showError(it)
            }
        )
    }*/

    override fun onTapCheckoutPrescription() {
        // navigate to checkout screen
    }

    /*override fun onTapSendMessage(message: MessageVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mConsultModel.saveMessage(message,
                onSuccess={
                    Log.d("Success","Sent message")
                },
                onFailure = {
                    mView.showError(it)
                })
    }*/
}