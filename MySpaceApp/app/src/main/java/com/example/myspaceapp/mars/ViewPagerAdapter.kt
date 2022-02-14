package com.example.myspaceapp.mars

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter (
    private val fragmentManager: FragmentManager
        ) : FragmentStatePagerAdapter(fragmentManager){

            private val fragments = arrayOf(
                HistoryPhotoOfMarsFragment(),
                MarsPhotoDetailFragment()
            )

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[0]
            1 -> fragments[1]
            else -> fragments[0]
        }

    }
}