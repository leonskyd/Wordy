package com.example.myspaceapp.ui.earth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.myspaceapp.R
import kotlinx.android.synthetic.main.p_o_d_fragment.*
import coil.load

class PODFragment : Fragment() {

    companion object {
        fun newInstance() = PODFragment()
    }

    private lateinit var viewModel: PODViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.p_o_d_fragment, container, false)
    }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PODViewModel::class.java)
        viewModel.getData().observe(this@PODFragment,Observer<PictureOfTheDayData> { renderData(it) })
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