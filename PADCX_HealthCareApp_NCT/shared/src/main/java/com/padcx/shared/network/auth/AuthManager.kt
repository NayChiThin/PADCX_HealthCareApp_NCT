package com.padcx.shared.network.auth

interface AuthManager {
    fun login(email:String,password:String,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun register(email:String,password:String,userName:String,onSuccess:()->Unit,onFailure:(String)->Unit)
    fun getUserName():String
    fun getUserId():String
    fun setUserPhoneNumber(phoneNumber:String)
    fun setUserPassword(password: String)
    fun getUserPhoneNumber():String
    fun getUserPhoto():String
    fun loginWithFb(token:String,onSuccess: () -> Unit,onFailure: (String) -> Unit)
}