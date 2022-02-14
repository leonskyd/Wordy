package com.example.myspaceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myspaceapp.mars.FotoOfMarsFragment
import com.example.myspaceapp.ui.earth.PODFragment
import com.example.myspaceapp.solar.SolarFlareFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavigation()
        bottom_navigation_view.selectedItemId = R.id.navigation_earth

        }
    fun setNavigation (){
        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_earth -> {

                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.bottom_activity_container,
                            PODFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.navigation_mars -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.bottom_activity_container,
                           FotoOfMarsFragment()
                        )
                        .commitAllowingStateLoss()
                    true

                }
                R.id.navigation_solar-> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.bottom_activity_container,
                            SolarFlareFragment()
                        )
                        .commitAllowingStateLoss()
                    true

                }

                else -> {
                    Toast.makeText(this@MainActivity,
                        "Item Earth tapped", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }
    }
