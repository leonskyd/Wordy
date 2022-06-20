package com.example.wordy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.wordy.databinding.ActivityMainBinding
import com.example.wordy.model.DataToShow
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), AppView {

    private lateinit var binding: ActivityMainBinding
    private val ERROR_MESSAGE = "There is no such a word in the dictionary !!!"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MainViewModel by viewModel()

        binding.searchFab.setOnClickListener {
            viewModel.getLiveDataObserver()
            val word = binding.wordEditText.text.toString()
            viewModel.let{
                it.makeCall(word)
                it.liveDataList.observe( this, Observer{ dataToShow ->
                    showSuccess(dataToShow) })
            }
        }

        binding.saveFab.setOnClickListener {
            val word = binding.wordEditText.text.toString()
            viewModel.saveWord(word)
        }

        binding.leaveFab.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val definitionsShown = binding.definitionsTextview.text.toString()
        val synonymsShown = binding.synonymsTextview.text.toString()
        val dataShown = DataToShow(definitionsShown,synonymsShown)
        outState.putParcelable("toShow",dataShown)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val dataToShow = savedInstanceState.getParcelable<DataToShow>("toShow")
        showSuccess(dataToShow)
    }

    override fun showSuccess(
        dataToShow: DataToShow?) {
        val definitions = dataToShow?.definitionsToShow
        val synonyms = dataToShow?.synonymsToShow
        binding.definitionsTextview.visibility = View.VISIBLE
        binding.synonymsTextview.visibility = View.VISIBLE
        binding.definitionsTextview.text = definitions
        binding.synonymsTextview.text = synonyms
    }

    override fun showError() {
        binding.definitionsTextview.visibility = View.VISIBLE
        binding.definitionsTextview.text = ERROR_MESSAGE
    }

    override fun showSearch() {
        binding.definitionsTextview.visibility = View.GONE
        binding.synonymsTextview.visibility = View.GONE
    }
}
