package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.CheckoutVO
import com.padcx.shared.data.vos.PrescriptionVO
import com.padcx.shared.network.FirebaseApi

interface CheckoutModel {
    var mFirebaseApi : FirebaseApi
    fun saveCheckoutToFirestore(checkoutVO: CheckoutVO,onSuccess:()->Unit,onFailure:(String)->Unit)
}