package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import com.padcx.padcx_healthcareapp_nct.mvp.views.CheckoutView
import com.padcx.shared.data.vos.CheckoutVO
import com.padcx.shared.data.vos.PrescriptionVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface CheckoutPresenter:BasePresenter<CheckoutView> {
    fun onTapOrder()
    fun onTapCheckout(checkout:CheckoutVO,onSuccess:()->Unit,onFailure:(String)->Unit)
}