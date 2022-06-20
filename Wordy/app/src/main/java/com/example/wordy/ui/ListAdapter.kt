package com.example.wordy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordy.database.WordEntity
import com.example.wordy.databinding.ListItemLayoutBinding

class ListAdapter (
    private var savedWords: List<WordEntity>
        ): RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    private lateinit var _listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        _listener = listener
    }

    inner class ListViewHolder(
        private val binding: ListItemLayoutBinding,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.wordNameTextView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
            binding.deleteWordImageView.setOnClickListener {
                // delete word from database
            }
        }
        fun bind(word:WordEntity) {
            binding.wordNameTextView.text=word.wordName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListViewHolder(
            ListItemLayoutBinding.inflate(inflater), _listener
        )
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        holder.bind(savedWords[position])
    }

    override fun getItemCount(): Int = savedWords.size
}

interface OnItemClickListener {
    fun onItemClick(position:Int)
}