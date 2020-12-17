package com.padcx.doctor

import android.content.ClipData.newIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.snackbar.Snackbar
import com.padcx.doctor.mvp.presenters.LoginPresenter
import com.padcx.doctor.mvp.presenters.impls.LoginPresenterImpl
import com.padcx.doctor.mvp.views.LoginView
import com.padcx.shared.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(),LoginView {

    private lateinit var mLoginPresenter : LoginPresenter
    private lateinit var callbackManager : CallbackManager

    companion object {
        fun newIntent(context:Context):Intent {
            return Intent(context,LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FacebookSdk.sdkInitialize(applicationContext)

        setUpPresenter()
        setUpListeners()
    }

    private fun setUpPresenter() {
        mLoginPresenter = ViewModelProviders.of(this).get(LoginPresenterImpl::class.java)
        mLoginPresenter.initPresenter(this)
    }
    private fun setUpListeners() {
        btnFBLogin.setOnClickListener {
            mLoginPresenter.onTapSignInWithFb()
        }
    }

    override fun navigateToHome() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }

    override fun navigateToSignInWithFB() {
        callbackManager = CallbackManager.Factory.create()

        btnFBLogin.setPermissions("email","public_profile")
        btnFBLogin.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    loginResult?.let {
                        mLoginPresenter.onSuccessLoginWithFb(it.accessToken.token)
                    }
                }

                override fun onCancel() {
                    // App code
                    Log.d("Login","Canceled")
                }

                override fun onError(exception: FacebookException) {
                    // App code
                    Log.d("Login",exception.toString())
                }
            })
    }

    override fun navigateToRegister() {
        startActivity(RegisterActivity.newIntent(this))
        finish()
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error, Snackbar.LENGTH_LONG).show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}