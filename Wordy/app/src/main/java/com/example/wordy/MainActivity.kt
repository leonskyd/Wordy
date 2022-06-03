package com.example.wordy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.wordy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AppView {

    private lateinit var binding: ActivityMainBinding
    private var presenter: MainPresenter? = null
    private val ERROR_MESSAGE = "There is no such a word in the dictionary !!!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter()
        presenter?.onAttach(this)

        binding.searchFab.setOnClickListener {
            val word = binding.wordEditText.text.toString()
            presenter?.getContent(word)
        }

        binding.wordEditText.setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus) { showSearch() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDetach(this)
    }

    override fun showSuccess(
        definitions: StringBuilder,
        synonyms: String) {
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