package com.example.myspaceapp.solar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myspaceapp.R

class SolarFlareFragment : Fragment() {

    companion object {
        fun newInstance() = SolarFlareFragment()
    }

    private lateinit var viewModel: SolarFlareViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.solar_flare_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SolarFlareViewModel::class.java)
        // TODO: Use the ViewModel
    }

}