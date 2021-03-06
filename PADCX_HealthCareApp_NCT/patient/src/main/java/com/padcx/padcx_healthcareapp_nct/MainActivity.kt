package com.padcx.padcx_healthcareapp_nct

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.padcx.shared.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){

    val homeFragment : Fragment = HomeFragment()
    val messageFragment : Fragment = MessageFragment()
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
                    R.id.action_message -> {
                        fm.beginTransaction().hide(active).show(messageFragment).commit()
                        active = messageFragment
                        return true
                    }
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
        fm.beginTransaction().add(R.id.flBottomNavigationContainer,downloadFragment,"Download Fragment").hide(downloadFragment).commit()*/
        fm.beginTransaction().add(R.id.flBottomNavigationContainer,messageFragment,"Message Fragment").hide(messageFragment).commit()
        fm.beginTransaction().add(R.id.flBottomNavigationContainer,homeFragment).commit()
    }
    private fun setUpPresenter() {
    }

}