package com.example.myspaceapp.mars

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myspaceapp.R
import com.example.myspaceapp.ui.earth.PODFragment
import kotlinx.android.synthetic.main.foto_of_mars_fragment.*

class FotoOfMarsFragment : Fragment() {

    companion object {
        fun newInstance() = FotoOfMarsFragment()
    }

    private lateinit var viewModel: FotoOfMarsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.foto_of_mars_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        get_more_fotos.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_history_photo_of_mars, HistoryPhotoOfMarsFragment())
            transaction?.commit()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FotoOfMarsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}