


package com.example.wordy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wordy.R
import com.example.wordy.WordData
import com.example.wordy.database.WordEntity
import com.example.wordy.databinding.ActivityListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MainViewModel by viewModel()
        val saveWords = viewModel.getAllFromDatabase()
        val adapter = ListAdapter(saveWords)
        binding.listRecyclerView.adapter = adapter

    }


}