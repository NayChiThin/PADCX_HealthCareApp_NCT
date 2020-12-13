package com.padcx.shared.data.models.impls

import androidx.lifecycle.LiveData
import com.padcx.shared.data.models.BaseModel
import com.padcx.shared.data.models.CheckoutModel
import com.padcx.shared.data.vos.CheckoutVO
import com.padcx.shared.data.vos.PrescriptionVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object CheckoutModelImpl:CheckoutModel,BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl
    override fun saveCheckoutToFirestore(checkoutVO: CheckoutVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.checkout(checkoutVO, onSuccess, onFailure)
    }
}