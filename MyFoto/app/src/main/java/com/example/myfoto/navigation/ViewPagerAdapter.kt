package com.example.myfoto.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val EARTH = 0
private const val MARS = 1
private const val WEATHER = 2


class ViewPagerAdapter(
    private val fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(
        EarthFragment(),
        MarsFragment(),
        WeatherFragment()
    )

    override fun getCount(): Int {
        return  fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> fragments[EARTH]
            1 -> fragments[MARS]
            2 -> fragments[WEATHER]
            else -> fragments[EARTH]
        }
    }
}