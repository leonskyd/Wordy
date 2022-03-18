package com.example.myspaceapp.notes

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myspaceapp.R
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment: Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?): NoteFragment {
            val fragment = NoteFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        saveButton.setOnClickListener{ Activity().onBackPressed()}
    }
}