package com.padcx.padcx_healthcareapp_nct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.RegisterPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.impls.RegisterPresenterImpl
import com.padcx.padcx_healthcareapp_nct.mvp.views.RegisterView
import com.padcx.shared.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(),RegisterView {

    private lateinit var mRegisterPresenter : RegisterPresenter

    companion object {
        fun newIntent(context:Context) :Intent{
            return Intent(context,RegisterActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setUpPresenter()
        setUpListener()
        mRegisterPresenter.onUiReady()
    }
    private fun setUpListener() {
        btnNext.setOnClickListener {
            mRegisterPresenter.onTapNext(etPhoneNumber.text.toString())
        }
    }
    private fun setUpPresenter() {
        mRegisterPresenter = ViewModelProviders.of(this).get(RegisterPresenterImpl::class.java)
        mRegisterPresenter.initPresenter(this)
    }

    override fun displayUserName(name: String) {
        tvName.text = name
    }

    override fun displayUserPhoto(photo: String) {
        Glide.with(this)
            .load(photo)
            .into(ivProfile)
    }

    override fun displayPhoneNumber(phone: String) {
        etPhoneNumber.setText(phone)
    }

    override fun navigateToRegisterPassword() {
        startActivity(RegisterPasswordActivity.newIntent(this))
    }

    override fun showError(error: String) {
        Snackbar.make(window.decorView,error, Snackbar.LENGTH_LONG)
    }
}