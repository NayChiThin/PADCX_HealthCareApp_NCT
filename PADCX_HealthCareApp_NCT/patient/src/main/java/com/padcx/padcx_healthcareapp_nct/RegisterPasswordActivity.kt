package com.padcx.padcx_healthcareapp_nct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.RegisterPasswordPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.impls.RegisterPasswordPresenterImpl
import com.padcx.padcx_healthcareapp_nct.mvp.views.RegisterPasswordView
import com.padcx.shared.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_register_password.*

class RegisterPasswordActivity : BaseActivity(),RegisterPasswordView {

    private lateinit var mRegisterPasswordPresenter: RegisterPasswordPresenter
    companion object {
        fun newIntent(context:Context):Intent {
            return Intent(context,RegisterPasswordActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_password)

        setUpPresenter()
        setUpListener()
        mRegisterPasswordPresenter.onUiReady(this)
    }
    private fun setUpListener() {
        btnConfirm.setOnClickListener {
            mRegisterPasswordPresenter.onTapConfirm(etPassword.text.toString(),etRetypePassword.text.toString())
        }
    }
    private fun setUpPresenter() {
        mRegisterPasswordPresenter = ViewModelProviders.of(this).get(RegisterPasswordPresenterImpl::class.java)
        mRegisterPasswordPresenter.initPresenter(this)
    }


    override fun displayUserName(name: String) {
        tvName.text = name
    }

    override fun displayUserPhoto(photo: String) {
        Glide.with(this)
            .load(photo)
            .into(ivProfile)
    }

    override fun navigateToHome() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error, Snackbar.LENGTH_LONG).show()
    }
}