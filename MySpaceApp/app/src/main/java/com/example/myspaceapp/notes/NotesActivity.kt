package com.example.myspaceapp.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myspaceapp.R
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity() {

    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val notes = arrayListOf<Note>(
            Note(1,"Service", "auto", "march 15, 8-00"),
            Note(2,"Stepplatform", "shopping", "buy 2 steplatforms"),
            Note(3,"Cash", "money", "Withdraw 200"),
            Note(4,"Carwash", "auto","wash 12 march 11-00"),
            Note(5,"GymOut", "sport", "return key for gym"),
            Note(6,"Skates", "shopping", "buy skates when discounted")
        )

        val adapter = NoteAdapter(notes, object: OnNoteItemClickListener{
            override fun OnNoteItemClick(note: Note) {
                openNote()
            }

        })

        noteRecycler.adapter = adapter

        createNote.setOnClickListener{
            adapter.appendItem()
        }

    }

    private fun openNote() {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.noteContainer,
                NoteFragment()
            )
            .commit()
    }

}