package com.padcx.shared.network.auth

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

object FirebaseAuthManager:AuthManager {

    private val mFirebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun login(email: String, password: String,onSuccess: () -> Unit,onFailure: (String) -> Unit) {
        mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful && it.isComplete) {
                onSuccess()
            }else {
                onFailure(it.exception?.message?:"Please Check Internet Connection")
            }
        }
    }

    override fun register(email: String, password: String, userName: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful && it.isComplete) {
                mFirebaseAuth.currentUser?.updateProfile(
                        UserProfileChangeRequest.Builder().setDisplayName(userName).build()
                )
                onSuccess()
            }else {
                onFailure(it.exception?.message?:"Please Check Internet Connection")
            }
        }
    }

    override fun getUserName(): String {
        return mFirebaseAuth.currentUser?.displayName?:""
    }

    override fun getUserId(): String {
        return mFirebaseAuth.currentUser?.uid?:""
    }

    override fun setUserPhoneNumber(phoneNumber:String) {
        // update phone number needs verification
    }

    override fun setUserPassword(password: String) {
        mFirebaseAuth.currentUser?.updatePassword(password)
    }

    override fun getUserPhoneNumber(): String {
        return mFirebaseAuth.currentUser?.phoneNumber?:""
    }

    override fun getUserPhoto(): String {
        return mFirebaseAuth.currentUser?.photoUrl?.toString()?:""
    }

    override fun loginWithFb(token: String,onSuccess: () -> Unit,onFailure: (String) -> Unit) {
        val credential = FacebookAuthProvider.getCredential(token)
        mFirebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    // If sign in fails, display a message to the user.
                    onFailure(task.exception?.message?:"Please check internet connection")
                }
            }
    }
}