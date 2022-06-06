package com.example.wordy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wordy.WordyApp
import com.example.wordy.app
import com.example.wordy.data.Repository
import com.example.wordy.databinding.ActivityMainBinding
import com.example.wordy.model.DataToShow
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AppView {

    private lateinit var binding: ActivityMainBinding
    private val ERROR_MESSAGE = "There is no such a word in the dictionary !!!"
    @Inject
    lateinit var retrofitRepository: Repository

    private val viewModel: MainViewModel by lazy {
        val factory = MainViewModelFactory(retrofitRepository)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WordyApp.instance.appDependenciesComponent.inject(this)
        viewModel.getLiveDataObserver()

        binding.searchFab.setOnClickListener {
            val word = binding.wordEditText.text.toString()
            viewModel.makeCall(word)
            viewModel.liveDataList.observe( this, Observer{ dataToShow ->
                showSuccess(dataToShow) })
        }

        binding.wordEditText.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus) { showSearch() }
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