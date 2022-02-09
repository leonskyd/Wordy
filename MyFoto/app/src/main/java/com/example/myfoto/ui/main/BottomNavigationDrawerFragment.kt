package com.example.myfoto.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myfoto.R
import com.example.myfoto.ui.main.DailyPictureFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_navigation_drawer.*


class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_navigation_drawer,
            container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // val navigation_view: View = view.findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.navigation_one -> Toast.makeText(context, "1",
                Toast.LENGTH_SHORT).show()
                R.id.navigation_two -> Toast.makeText(context, "2",
                    Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}