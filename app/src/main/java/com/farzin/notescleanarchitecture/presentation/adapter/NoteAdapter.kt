package com.farzin.notescleanarchitecture.presentation.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farzin.core.data.Note
import com.farzin.notescleanarchitecture.R
import com.farzin.notescleanarchitecture.databinding.NoteItemBinding
import com.farzin.notescleanarchitecture.framework.viewmodel.NoteViewModel

class NoteAdapter(
    var notes: ArrayList<Note>,
    val listActions: ListActions,
    val context: Context,
    val noteViewModel: NoteViewModel
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])

    }


    fun updateNote(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = NoteItemBinding.bind(view)
        @SuppressLint("SetTextI18n")
        fun bind(note: Note) {
            binding.title.text = note.title
            binding.content.text = note.content
            binding.date.text = note.updateTime.toString()
            binding.wordCount.text = "Words : ${note.wordCount}"

            binding.noteLayout.setOnClickListener {
                listActions.onNoteClicked(note.id)
            }

            binding.delete.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("delete note")
                    .setMessage("are you sure?")
                    .setPositiveButton("yes") { dialogInterface, i ->
                        noteViewModel.deleteNote(note)
                        notes.remove(note)
                        notifyItemRemoved(position)
                    }
                    .setNegativeButton("no") { dialogInterface, i ->

                    }
                    .create()
                    .show()
            }


        }
    }


}