package com.farzin.notescleanarchitecture.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.farzin.core.data.Note
import com.farzin.notescleanarchitecture.databinding.FragmentListBinding
import com.farzin.notescleanarchitecture.framework.viewmodel.ListViewModel
import com.farzin.notescleanarchitecture.framework.viewmodel.NoteViewModel
import com.farzin.notescleanarchitecture.presentation.adapter.ListActions
import com.farzin.notescleanarchitecture.presentation.adapter.NoteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(),ListActions {

    private lateinit var binding: FragmentListBinding
    private val listViewModel: ListViewModel by viewModels<ListViewModel>()
    private val noteViewModel: NoteViewModel by viewModels<NoteViewModel>()

    private lateinit var noteAdapter :NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notesRv.apply {
            layoutManager = LinearLayoutManager(context)
            noteAdapter = NoteAdapter(arrayListOf(),this@ListFragment,requireContext(),noteViewModel)
            adapter = noteAdapter
        }

        observeViewModel()

        binding.addNote.setOnClickListener {
            goToNoteFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        listViewModel.getAllNotes()
    }

    fun observeViewModel(){
        listViewModel.notesList.observe(viewLifecycleOwner){noteList->
            binding.progressBar.visibility = View.GONE
            binding.notesRv.visibility = View.VISIBLE
            noteAdapter.updateNote(noteList.sortedByDescending { it.updateTime })
        }
    }

    private fun goToNoteFragment(id:Long = 0L){
        val action =
            ListFragmentDirections.actionListFragmentToNoteFragment().setNoteId(id)
        Navigation.findNavController(binding.notesRv).navigate(action)
    }

    override fun onNoteClicked(id: Long) {
        goToNoteFragment(id)
    }

}