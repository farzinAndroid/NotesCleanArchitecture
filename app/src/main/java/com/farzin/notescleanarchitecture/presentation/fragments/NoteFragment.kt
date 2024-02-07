package com.farzin.notescleanarchitecture.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.farzin.core.data.Note
import com.farzin.notescleanarchitecture.R
import com.farzin.notescleanarchitecture.databinding.FragmentNoteBinding
import com.farzin.notescleanarchitecture.framework.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var noteId = 0L
    private lateinit var binding: FragmentNoteBinding

    private val noteViewModel: NoteViewModel by viewModels<NoteViewModel>()

    private var currentNote = Note(title = "", updateTime = 0L, creationTime = 0L, content = "")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L) {
            noteViewModel.getNote(noteId)
        }


        binding.done.setOnClickListener {
            if (binding.title.text.toString() != "" || binding.content.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.title = binding.title.text.toString()
                currentNote.content = binding.content.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }
                noteViewModel.addNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }

        }

        observeViewModel()
    }

    private fun observeViewModel() {
        noteViewModel.saved.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(binding.done).popBackStack()
            } else {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        noteViewModel.currentNote.observe(viewLifecycleOwner) { note ->
            note?.let {
                currentNote = it
                binding.title.setText(it.title, TextView.BufferType.EDITABLE)
                binding.content.setText(it.content, TextView.BufferType.EDITABLE)
            }

        }
    }


}