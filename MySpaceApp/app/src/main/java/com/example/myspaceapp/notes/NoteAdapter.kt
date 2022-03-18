package com.example.myspaceapp.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myspaceapp.R
import com.example.myspaceapp.recycler.Data
import kotlinx.android.synthetic.main.note_recycler_item.view.*

class NoteAdapter(
    private var notes: MutableList<Note>,
    private var onNoteItemClickListener: OnNoteItemClickListener
): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(
            inflater.inflate(
                R.layout.note_recycler_item, parent, false) as View)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    fun appendItem() {
        notes.add(generateItem())
        notifyItemInserted(itemCount-1)
    }

    private fun generateItem(): Note {
        val id = itemCount+1
        return Note(id, "Note_$id", " ", " ")
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (note: Note) {
            if(layoutPosition != RecyclerView.NO_POSITION){
                itemView.noteImageView.setOnClickListener { onNoteItemClickListener.OnNoteItemClick(note) }
                itemView.noteTextView.text=note.title
                itemView.noteDesriptionTextView.text=note.description
                itemView.removeItemImageView.setOnClickListener { deleteNote() }
                itemView.moveItemDown.setOnClickListener { moveDown() }
                itemView.moveItemUp.setOnClickListener { moveUp() }
            }
        }

        private fun moveUp() {
            layoutPosition.takeIf { it > 1 }?.also {currentPosition ->
                notes.removeAt(currentPosition).apply{
                    notes.add(currentPosition-1,this)
                }
                notifyItemMoved(currentPosition,currentPosition-1)
            }
        }

        private fun moveDown() {
            layoutPosition.takeIf { it < notes.size-1 }?.also {currentPosition ->
                notes.removeAt(currentPosition).apply{
                    notes.add(currentPosition+1,this)
                }
                notifyItemMoved(currentPosition,currentPosition+1)
            }
        }

        private fun deleteNote() {
            notes.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }
}
