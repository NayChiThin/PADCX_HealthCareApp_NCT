package com.padcx.padcx_healthcareapp_nct

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.RequestConsultPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.RequestConsultPresenterImpl
import com.padcx.padcx_healthcareapp_nct.mvp.views.RequestConsultView
import com.padcx.shared.activity.BaseActivity
import com.padcx.shared.data.vos.QuestionVO

class MainActivity : BaseActivity(){

    companion object {
        fun newIntent(context:Context):Intent {
            return Intent(context,MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()


    }
    private fun setUpPresenter() {
    }

}