package com.example.myspaceapp.solar

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myspaceapp.R
import com.example.myspaceapp.notes.NotesActivity
import com.example.myspaceapp.recycler.RecyclerActivity
import kotlinx.android.synthetic.main.solar_flare_fragment.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingButton.setOnClickListener {
            activity?.let{
            startActivity(Intent(it,NotesActivity::class.java))
        }
        }
    }
}