package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import android.util.Log
import com.padcx.padcx_healthcareapp_nct.mvp.views.CheckoutView
import com.padcx.shared.data.models.CheckoutModel
import com.padcx.shared.data.models.impls.CheckoutModelImpl
import com.padcx.shared.data.vos.CheckoutVO
import com.padcx.shared.data.vos.PrescriptionVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class CheckoutPresenterImpl:CheckoutPresenter,AbstractBasePresenter<CheckoutView>() {

    private val mCheckoutModel : CheckoutModel = CheckoutModelImpl

    override fun onTapOrder() {
        // show order details
    }

    override fun onTapCheckout(checkout: CheckoutVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mCheckoutModel.saveCheckoutToFirestore(checkout,
            onSuccess={
            },
            onFailure={

            })
    }
}