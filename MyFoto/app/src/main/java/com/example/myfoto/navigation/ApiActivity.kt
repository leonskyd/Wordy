package com.example.myfoto.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfoto.R
import kotlinx.android.synthetic.main.activity_api.*

class ApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        view_pager.adapter = ViewPagerAdapter(
            supportFragmentManager
        )
    }
}