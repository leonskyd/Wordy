package com.example.myfoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfoto.navigation.ViewPagerAdapter
import com.example.myfoto.ui.main.DailyPictureFragment
import kotlinx.android.synthetic.main.activity_api.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)
       /* if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, DailyPictureFragment.newInstance())
                    .commitNow()
        }*/
        view_pager.adapter = ViewPagerAdapter(
            supportFragmentManager)
    }
}