package com.example.myspaceapp.mars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myspaceapp.R

class HistoryPhotoOfMarsFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryPhotoOfMarsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history_photo_of_mars, container, false)
    }
}