package com.example.myfoto.ui.main

import android.icu.text.ConstrainedFieldPosition
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

import com.example.myfoto.R
import com.example.myfoto.databinding.DailyPictureFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

private val ViewBinding?.image_view: Any
    get() = Unit

class DailyPictureFragment: Fragment() {

    companion object {
        fun newInstance() = DailyPictureFragment()
        private var isMain = true
    }
    private var _binding: DailyPictureFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DailyPictureViewModel
    private lateinit var bottomSheetBehavior
    : BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(
        savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DailyPictureViewModel::class.java)

        viewModel.getData()
                .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> {
                    renderData(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun renderData(data:PictureOfTheDayData) = with(_binding) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    toast("Link is Empty")
                } else {
                    image_view.load(url) {
                        lifecycle(this@DailyPictureFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)

                    }
                }

            }
            is PictureOfTheDayData.Loading -> {

            }
            is PictureOfTheDayData.Error -> {

            }
        }
    }

    private fun toast(string: String?) {
        Toast.makeText(context,string,Toast.LENGTH_LONG).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }
}