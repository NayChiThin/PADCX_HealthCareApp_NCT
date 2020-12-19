package com.padcx.padcx_healthcareapp_nct

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.RequestConsultPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.RequestConsultPresenterImpl
import com.padcx.padcx_healthcareapp_nct.mvp.views.RequestConsultView
import com.padcx.shared.activity.BaseActivity
import com.padcx.shared.data.vos.QuestionVO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){

    val homeFragment : Fragment = HomeFragment()
    val fm : FragmentManager = supportFragmentManager
    var active : Fragment = homeFragment

    companion object {
        fun newIntent(context:Context):Intent {
            return Intent(context,MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrangeFragments()
        setUpPresenter()
        bottomNavigation.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId) {
                    R.id.action_home -> {
                        fm.beginTransaction().hide(active).show(homeFragment).commit()
                        active = homeFragment
                        /*supportFragmentManager.beginTransaction()
                            .replace(R.id.flBottomNavigationContainer,HomeFragment())
                            .commit()*/
                        return true
                    }
                    /*
                    R.id.action_search -> {
                        fm.beginTransaction().hide(active).show(searchFragment).commit()
                        active = searchFragment
                        return true
                    }*/
                }
                return false
            }
        })
        if(savedInstanceState == null) {
            bottomNavigation.selectedItemId =
                R.id.action_home
        }

    }
    private fun arrangeFragments() {
        /*fm.beginTransaction().add(R.id.flBottomNavigationContainer,searchFragment,"Search Fragment").hide(searchFragment).commit()
        fm.beginTransaction().add(R.id.flBottomNavigationContainer,downloadFragment,"Download Fragment").hide(downloadFragment).commit()
        fm.beginTransaction().add(R.id.flBottomNavigationContainer,blankFragment,"Blank Fragment").hide(blankFragment).commit()*/
        fm.beginTransaction().add(R.id.flBottomNavigationContainer,homeFragment).commit()
    }
    private fun setUpPresenter() {
    }

}