package com.padcx.padcx_healthcareapp_nct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.RequestConsultPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.RequestConsultPresenterImpl
import com.padcx.padcx_healthcareapp_nct.mvp.views.RequestConsultView
import com.padcx.shared.data.vos.QuestionVO

class MainActivity : AppCompatActivity(),RequestConsultView {

    private lateinit var mRequestConsultPresenter : RequestConsultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()

        /*FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TOKEN", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = "TOKEN is $token"
            Log.d("TOKEN", msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })*/
    }
    private fun setUpPresenter() {
    }

    override fun showError(error: String) {

    }
}