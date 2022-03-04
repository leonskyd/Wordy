package com.example.myspaceapp.ui.earth

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.myspaceapp.R
import kotlinx.android.synthetic.main.p_o_d_fragment.*
import coil.load
import kotlinx.android.synthetic.main.activity_main.*

class PODFragment : Fragment() {

    companion object {
        fun newInstance() = PODFragment()
    }

    private lateinit var viewModel: PODViewModel
    private var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.p_o_d_fragment, container, false)
    }

          @SuppressLint("FragmentLiveDataObserve")
        override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PODViewModel::class.java)
        viewModel.getData().observe(this@PODFragment,Observer<PictureOfTheDayData> { renderData(it) })

            image_view.setOnClickListener {
                isExpanded = !isExpanded

                TransitionManager.beginDelayedTransition(main, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform()))

                val params: ViewGroup.LayoutParams = image_view.layoutParams
                params.height = if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT
                else ViewGroup.LayoutParams.WRAP_CONTENT

                image_view.layoutParams = params
                image_view.scaleType = if (isExpanded) ImageView.ScaleType.CENTER_CROP
                else ImageView.ScaleType.FIT_END
            }
    }
    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //Отобразите ошибку
                    //showError("Сообщение, что ссылка пустая")
                } else {
                    //Отобразите фото
                    //showSuccess()
                    //Coil в работе: достаточно вызвать у нашего ImageView
                    //нужную extension-функцию и передать ссылку и заглушки для placeholder
                    image_view.load(url) {
                        lifecycle(this@PODFragment)
                        error(R.drawable.ic_launcher_foreground)
                        placeholder(R.drawable.ic_launcher_foreground)
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {
                //Отобразите загрузку
                //showLoading()
            }
            is PictureOfTheDayData.Error -> {
            }
        }
    }
}