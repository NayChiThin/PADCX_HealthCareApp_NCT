package com.padcx.shared.data.models.impls

import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.network.auth.AuthManager
import com.padcx.shared.network.auth.FirebaseAuthManager

object AuthenticationModelImpl:AuthenticationModel {
    override var mAuthManager: AuthManager = FirebaseAuthManager

    override fun login(
            email: String,
            password: String,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        mAuthManager.login(email, password, onSuccess, onFailure)
    }

    override fun register(
            email: String,
            password: String,
            userName: String,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        mAuthManager.register(email, password, userName, onSuccess, onFailure)
    }

    override fun getUserId(): String {
        return mAuthManager.getUserId()
    }

    override fun getUserPhoto(): String {
        return mAuthManager.getUserPhoto()
    }

    override fun getUserPhoneNumber(): String {
        return mAuthManager.getUserPhoneNumber()
    }

    override fun setUserPhoneNumber(phone: String) {
        mAuthManager.setUserPhoneNumber(phone)
    }

    override fun getUserName(): String {
        return mAuthManager.getUserName()
    }

    override fun loginWithFb(token: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.loginWithFb(token,onSuccess,onFailure)
    }
}